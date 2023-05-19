import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class CSVTable {
    public static void main(String[] args) {
        String path = "C:/Users/47238341840/Desktop/e-comerce-main/produtos.csv";
        ArrayList<String> generalProd = new ArrayList<>();
        String[][] productData = new String[21][3];
        int counter = 0;
        productData[0][0] = "Product";
        productData[0][1] = "Description";
        productData[0][2] = "Price";

        try (BufferedReader bReader = new BufferedReader(new FileReader(path))){
            String line = bReader.readLine();
            line = bReader.readLine();
            while (line != null){
                String[] vecProducts = line.split(";");
                String product = vecProducts[0];
                String description = vecProducts[1];
                String price = vecProducts[2];

                generalProd.add(product);
                generalProd.add(description);
                generalProd.add(price);

                line = bReader.readLine();
            }

            for (int x = 1; x < 22; x++) {
                for (int y = 0; y < 3; y++) {
                    if (counter < generalProd.size()) {
                        productData[x][y] = generalProd.get(counter);
                        counter++;
                    } else {
                        System.out.println("Deu merda");
                    }
                }
            }
            
            JTable table = new JTable(productData, productData[0]);
            JScrollPane scrollPane = new JScrollPane(table);

            JFrame frame = new JFrame();
            frame.add(scrollPane);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.pack();
            frame.setVisible(true);

        }catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}


