from selenium import webdriver
from selenium.webdriver.common.by import By
from time import sleep
import pandas as pd
from reportlab.lib.pagesizes import letter
from reportlab.lib import colors
from reportlab.platypus import SimpleDocTemplate, Table, TableStyle

class Web():
    def __init__(self) -> None:
        self.site = 'http://127.0.0.1:5500/site/teste.html'

        self.map = {
            'product': {
                'xpath': '/html/body/div[*Y*]/div[*X*]/div/h5'
            },
            'description': {
                'xpath': '/html/body/div[*Y*]/div[*X*]/div/p[1]'
            },
            'price': {
                'xpath': '/html/body/div[*Y*]/div[*X*]/div/p[2]/b'
            }
        }

        options = webdriver.ChromeOptions()
        options.add_argument("--headless")
        self.driver = webdriver.Chrome(options=options)  

        self.scrap()  

    def scrap(self):
        sleep(2)
        self.driver.get(self.site)
        sleep(2)

        product_list = []
        description_list = []
        price_list = []

        for y in range(2, 6):
            for x in range(1, 6):
                product = self.driver.find_element(
                    By.XPATH, self.map['product']['xpath']
                    .replace('*Y*', f'{y}').replace('*X*', f'{x}')).text
                product_list.append(product)
                
                description = self.driver.find_element(
                    By.XPATH, self.map['description']['xpath']
                    .replace('*Y*', f'{y}').replace('*X*', f'{x}')).text
                description_list.append(description)
                
                price = self.driver.find_element(
                    By.XPATH, self.map['price']['xpath']
                    .replace('*Y*', f'{y}').replace('*X*', f'{x}')).text
                price_list.append(price)

                products = {
                    'product': product_list,
                    'description': description_list,
                    'price': price_list
                }

        self.archive_create(products, "CSV")
        self.archive_create(products, "XLSX")
        self.pdf_generator(products)
            

    @staticmethod
    def archive_create(products, archive_type):
        dataframe = pd.DataFrame(products)
        if archive_type == "CSV":
            dataframe.to_csv('Products.csv', index=False, sep=";")
        elif archive_type == "XLSX":
            dataframe.to_excel('Products.xlsx', index=False)

    
    @staticmethod
    def pdf_generator(data):
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

        pdf = SimpleDocTemplate("tabela.pdf", pagesize=letter)
        pdf.build([table])