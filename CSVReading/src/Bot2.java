import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

abstract class Product {
    private String name;
    private String description;
    private double price;

    public Product(String name, String description, double price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public abstract String getCategory();
}

class BestSeller extends Product {
    public BestSeller(String name, String description, double price) {
        super(name, description, price);
    }

    @Override
    public String getCategory() {
        return "Best Sellers";
    }
}

class Computer extends Product {
    public Computer(String name, String description, double price) {
        super(name, description, price);
    }

    @Override
    public String getCategory() {
        return "Computers";
    }
}

class Notebook extends Product {
    public Notebook(String name, String description, double price) {
        super(name, description, price);
    }

    @Override
    public String getCategory() {
        return "Notebooks";
    }
}

class Monitor extends Product {
    public Monitor(String name, String description, double price) {
        super(name, description, price);
    }

    @Override
    public String getCategory() {
        return "Monitors";
    }
}

class Keyboard extends Product {
    public Keyboard(String name, String description, double price) {
        super(name, description, price);
    }

    @Override
    public String getCategory() {
        return "Keyboards";
    }
}

class Mouse extends Product {
    public Mouse(String name, String description, double price) {
        super(name, description, price);
    }

    @Override
    public String getCategory() {
        return "Mouses";
    }
}

class Headset extends Product {
    public Headset(String name, String description, double price) {
        super(name, description, price);
    }

    @Override
    public String getCategory() {
        return "Headsets";
    }
}

public class Bot2 {
    private static Scanner tec = new Scanner(System.in);
    private static String archivesPath;

    public static void main(String[] args) {
        clsTerminal();
        System.out.println("Welcome to Beck's Company Bot!");
        sleep(2000);

        System.out.print("Input the archives path>> ");
        archivesPath = tec.nextLine();

        while (true) {
            int chosenOption = promptOptions();
            clsTerminal();

            switch (chosenOption) {
                case 1:
                    System.out.println("OK, just a second! We are collecting the data.");
                    sleep(2000);
                    System.out.println();
                    System.out.println("Best Sellers products: ");
                    String pathBest = archivesPath + "/bestSeller.csv";
                    printTable(loadProducts(pathBest));
                    break;

                case 2:
                    int chosenCategory = promptCategoryOptions();
                    clsTerminal();

                    if (chosenCategory == 7) {
                        sleep(1000);
                        System.out.println();
                        System.out.println("Thank you for using our services!");
                        System.out.println("See you next!");
                        return;
                    } else {
                        System.out.println("OK, just a second! We are collecting the data.");
                        sleep(2000);
                        System.out.println();
                        System.out.println(getCategoryName(chosenCategory) + " available: ");
                        String path = getCategoryPath(chosenCategory);
                        printTable(loadProducts(path));
                    }
                    break;

                case 3:
                    sleep(1000);
                    System.out.println();
                    System.out.println("Thank you for using our services!");
                    System.out.println("See you next!");
                    return;
            }
        }
    }

    private static int promptOptions() {
        int chosenOption;
        int[] validOptions = {1, 2, 3};

        do {
            System.out.println();
            System.out.println("What do you want to view?");
            System.out.println("[1] - Best Sellers");
            System.out.println("[2] - Specific Category");
            System.out.println("[3] - Exit");
            chosenOption = tec.nextInt();
            tec.nextLine();
            clsTerminal();
        } while (!contains(validOptions, chosenOption));

        return chosenOption;
    }

    private static int promptCategoryOptions() {
        int chosenCategory;
        int[] validOptions = {1, 2, 3, 4, 5, 6, 7};

        do {
            clsTerminal();
            System.out.println("Choose the category:");
            System.out.println("[1] - PCs");
            System.out.println("[2] - Notebooks");
            System.out.println("[3] - Video Monitor");
            System.out.println("[4] - Keyboards");
            System.out.println("[5] - Mouses");
            System.out.println("[6] - Headsets");
            System.out.println("[7] - Exit");
            chosenCategory = tec.nextInt();
            tec.nextLine();
            clsTerminal();
        } while (!contains(validOptions, chosenCategory));

        return chosenCategory;
    }

    private static ArrayList<Product> loadProducts(String path) {
    ArrayList<Product> products = new ArrayList<>();
    BufferedReader bReader = null;

    try {
        bReader = new BufferedReader(new FileReader(path));
        String line = bReader.readLine(); // Skip header
        line = bReader.readLine();

        while (line != null) {
            String[] vecProducts = line.split(";");
            String name = vecProducts[0];
            String description = vecProducts[1];
            double price = parsePrice(vecProducts[2]);

            int category = getCategoryFromPath(path);
            Product product = createProduct(category, name, description, price);
            products.add(product);

            line = bReader.readLine();
        }
    } catch (IOException e) {
        System.out.println(e.getMessage());
    } finally {
        if (bReader != null) {
            try {
                bReader.close();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    return products;
}


    private static double parsePrice(String priceString) {
    String numericalPart = priceString.replaceAll("[^\\d.,]", "");
    numericalPart = numericalPart.replace(",", "").replaceFirst("\\.(?!.*\\.)", ".");
    return Double.parseDouble(numericalPart);
}


    private static Product createProduct(int category, String name, String description, double price) {
        switch (category) {
            case 1:
                return new Computer(name, description, price);
            case 2:
                return new Notebook(name, description, price);
            case 3:
                return new Monitor(name, description, price);
            case 4:
                return new Keyboard(name, description, price);
            case 5:
                return new Mouse(name, description, price);
            case 6:
                return new Headset(name, description, price);
            default:
                return new BestSeller(name, description, price);
        }
    }

    private static void printTable(ArrayList<Product> products) {
        if (products.isEmpty()) {
            System.out.println("No products available.");
            return;
        }

        int numColumns = 3;
        String[][] productData = new String[products.size() + 1][numColumns];
        productData[0][0] = "Product";
        productData[0][1] = "Description";
        productData[0][2] = "Price";

        for (int i = 0; i < products.size(); i++) {
            Product product = products.get(i);
            productData[i + 1][0] = product.getName();
            productData[i + 1][1] = product.getDescription();
            productData[i + 1][2] = String.valueOf(product.getPrice());
        }

        // Find the maximum width of each column
        int[] columnWidths = new int[numColumns];
        for (String[] row : productData) {
            for (int i = 0; i < row.length; i++) {
                if (row[i].length() > columnWidths[i]) {
                    columnWidths[i] = row[i].length();
                }
            }
        }

        // Print the table with equal column widths
        for (String[] row : productData) {
            for (int i = 0; i < row.length; i++) {
                System.out.print("| " + formatCell(row[i], columnWidths[i]) + " ");
                sleep(20);
            }
            System.out.println("|");
        }
    }

    private static String formatCell(String cell, int width) {
        return String.format("%-" + width + "s", cell);
    }

    private static boolean contains(final int[] array, final int value) {
        for (int i : array) {
            if (i == value) {
                return true;
            }
        }
        return false;
    }

    private static void sleep(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void clsTerminal() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    private static String getCategoryPath(int category) {
        return archivesPath + "/" + getCategoryName(category).toLowerCase() + ".csv";
    }

    private static int getCategoryFromPath(String path) {
        String[] parts = path.split("/");
        String category = parts[parts.length - 1].split("\\.")[0];
        switch (category.toLowerCase()) {
            case "computers":
                return 1;
            case "notebooks":
                return 2;
            case "monitors":
                return 3;
            case "keyboards":
                return 4;
            case "mouses":
                return 5;
            case "headset":
                return 6;
            default:
                return 0;
        }
    }

    private static String getCategoryName(int category) {
        switch (category) {
            case 1:
                return "PCs";
            case 2:
                return "Notebooks";
            case 3:
                return "Video Monitor";
            case 4:
                return "Keyboards";
            case 5:
                return "Mouses";
            case 6:
                return "Headsets";
            default:
                return "";
        }
    }
}



