from selenium import webdriver
from selenium.webdriver.common.by import By
from time import sleep
import pandas as pd
from reportlab.lib.pagesizes import letter
from reportlab.lib import colors
from reportlab.platypus import SimpleDocTemplate, Table, TableStyle

class Web():
    def __init__(self) -> None:
        self.site = 'http://10.109.72.23/site/'

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

        options = webdriver.ChromeOptions()
        options.add_argument("--headless")
        self.driver = webdriver.Chrome(options=options)  

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

        self.archive_create(products, category)
        # self.pdf_generator(products, category)
            

    @staticmethod
    def archive_create(products, category):
        dataframe = pd.DataFrame(products)
        dataframe.to_csv(f'C:/Users/47238341840/Desktop/e-comerce/scraping/archives_/{category}.csv', index=False, sep=";")
        

    @staticmethod
    def pdf_generator(data, category):
        df = pd.DataFrame(data)

        # Converter o DataFrame em uma lista de listas
        data = [list(df.columns)] + df.values.tolist()

        style = TableStyle([('BACKGROUND', (0, 0), (-1, 0), colors.gray),
                            ('TEXTCOLOR', (0, 0), (-1, 0), colors.whitesmoke),
                            ('ALIGN', (0, 0), (-1, -1), 'CENTER'),
                            ('FONTNAME', (0, 0), (-1, 0), 'Helvetica-Bold'),
                            ('FONTSIZE', (0, 0), (-1, 0), 12),
                            ('BOTTOMPADDING', (0, 0), (-1, 0), 12),
                            ('BACKGROUND', (0, 1), (-1, -1), colors.beige),
                            ('GRID', (0, 0), (-1, -1), 1, colors.black)])

        col_widths = [250, 250, 150]

        table = Table(data, colWidths=col_widths)
        table.setStyle(style)

        pdf = SimpleDocTemplate(f"scraping/archives_/{category}.pdf", pagesize=letter)
        pdf.build([table])


if __name__ == '__main__':
    site = Web()