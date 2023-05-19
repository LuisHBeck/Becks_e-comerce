import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSVReader {
    public static void main(String[] args) {
        String path = "C:/Users/Luis Beck/Desktop/e-comerce-main/produtos.csv";
        List<Products> productsList = new ArrayList<>();

        try (BufferedReader bReader = new BufferedReader(new FileReader(path))){
            String line = bReader.readLine();
            line = bReader.readLine();
            while (line != null){
                String[] vecProducts = line.split(";");
                String product = vecProducts[0];
                String description = vecProducts[1];
                String price = vecProducts[2];

                Products product1 = new Products(product, description, price);
                productsList.add(product1);

                line = bReader.readLine();
            }
            System.out.println("PRODUCTS: ");
            for (Products prod: productsList){
                System.out.println(prod);
            }
        }catch (IOException e) {
            System.out.println(e.getMessage());
        }

        
    }
}
