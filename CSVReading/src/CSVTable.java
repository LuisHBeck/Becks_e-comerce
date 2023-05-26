import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class CSVTable {
    public static void main(String[] args) {
        String path = "C:/Users/47238341840/Desktop/e-comerce/archives/Products.csv";
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
                        System.out.println();
                    }
                }
            }

            // Encontra a largura máxima de cada coluna
            int[] columnWidths = new int[productData[0].length];
            for (String[] row : productData) {
                for (int i = 0; i < row.length; i++) {
                    if (row[i].length() > columnWidths[i]) {
                        columnWidths[i] = row[i].length();
                    }
                }
            }

            // Imprime a tabela com colunas de tamanho igual
            for (String[] row : productData) {
                for (int i = 0; i < row.length; i++) {
                    System.out.print("| " + formatCell(row[i], columnWidths[i]) + " ");
                }
                System.out.println("|");
            }

        }catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    // Formata uma célula com um tamanho fixo
    private static String formatCell(String cell, int width) {
        return String.format("%-" + width + "s", cell);
    }
}


