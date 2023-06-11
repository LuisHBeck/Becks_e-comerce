from selenium import webdriver
from selenium.webdriver.common.by import By
from time import sleep
import pandas as pd
import os
import sys

class Web():
    def __init__(self, archives_path) -> None:
        
        self.site = 'https://luishbeck.github.io/Becks_e-comerce/site/'

        self.archives_path = archives_path

        self.map = {
            'product': {
                'xpath': '/html/body/div[*Y*]/div[*X*]/div/h5'
            },
            'description': {
                'xpath': '/html/body/div[*Y*]/div[*X*]/div/p[1]'
            },
            'price': {
                'xpath': '/html/body/div[*Y*]/div[*X*]/div/p[2]/strong'
            }
        }

        # options = webdriver.ChromeOptions()
        # options.add_argument("--headless")
        # self.driver = webdriver.Chrome(options=options)  

        options = webdriver.EdgeOptions()
        options.add_argument("--headless")
        self.driver = webdriver.Edge(options=options)  

        self.scrap(2, 'bestSeller', 21)
        self.scrap(3, 'computers')
        self.scrap(4, 'notebooks')
        self.scrap(5, 'monitors')
        self.scrap(6, 'keyboards')
        self.scrap(7, 'mouses')
        self.scrap(8, 'headset')


    def scrap(self, y, category, k=11):
        sleep(2)
        self.driver.get(self.site)
        sleep(2)

        product_list = []
        description_list = []
        price_list = []


        for x in range(1, k):
            os.system("cls")
            product = self.driver.find_element(
                By.XPATH, self.map['product']['xpath']
                .replace('*X*', f'{x}').replace('*Y*', f'{y}')).text
            product_list.append(product)
            
            description = self.driver.find_element(
                By.XPATH, self.map['description']['xpath']
                .replace('*X*', f'{x}').replace('*Y*', f'{y}')).text
            description_list.append(description)
            
            price = self.driver.find_element(
                By.XPATH, self.map['price']['xpath']
                .replace('*X*', f'{x}').replace('*Y*', f'{y}')).text
            price_list.append(price)

            products = {
                'product': product_list,
                'description': description_list,
                'price': price_list
            }

            print(f'{category} success')

        self.archive_create(products, category)
            

    def archive_create(self, products, category):
        dataframe = pd.DataFrame(products)
        dataframe.to_csv(f'{self.archives_path}/{category}.csv', index=False, sep=";")
        


if __name__ == '__main__':
    archives_path = str(input('Input where do you want to save>> '))
    site = Web(archives_path)
    print('Success')
    sys.exit()
