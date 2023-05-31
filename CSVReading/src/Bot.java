import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Bot {
    public static void main(String[] args) {
        Scanner tec = new Scanner(System.in);
        int chosenOption, chosenCategory;   
        String path;

        int[] firtsOption = {1,2,3}; 
        int[] secondOption = {1,2,3,4,5,6,7}; 

        clsTerminal();
        System.out.println("Welcome to Beck's Company Bot!");
        sleep(2000);

        System.out.print("Input the archives path>> ");
        String archivesPath = tec.nextLine();

        while (true) {
            do {
                System.out.println();
                System.out.println("What do you want to view?");
                System.out.println("""
                        [1] - Best Sellers
                        [2] - Specific Category       
                        [3] - Exit
                        """);
                chosenOption = tec.nextInt();
                tec.nextLine();
                clsTerminal();
            }while (contains(firtsOption, chosenOption) == false);

            switch (chosenOption) {
                case 1:
                    System.out.println("OK, just a second! We are collecting the data.");
                    sleep(2000);
                    System.out.println();
                    System.out.println("Best Sellers products: ");
                    String pathBest = archivesPath + "/bestSeller.csv";
                    printTable(pathBest);
                    break;
                
                case 2:
                    do {
                        clsTerminal();
                        System.out.println("Choose the category:");
                        System.out.println("""
                        [1] - PCs
                        [2] - Notebooks       
                        [3] - Video Monitor
                        [4] - Keyboards
                        [5] - Mouses
                        [6] - Headsets
                        [7] - Exit
                        """);
                        chosenCategory = tec.nextInt();
                        tec.nextLine();
                        clsTerminal();
                    }while (contains(secondOption, chosenCategory) == false);

                    switch (chosenCategory) {
                        case 1:
                            System.out.println("OK, just a second! We are collecting the data.");
                            sleep(2000);
                            System.out.println();
                            System.out.println("Computers available: ");
                            String pathComputers = archivesPath + "/computers.csv";
                            printTable(pathComputers);
                            break;

                        case 2:
                            System.out.println("OK, just a second! We are collecting the data.");
                            sleep(2000);
                            System.out.println();
                            System.out.println("Notebooks available: ");
                            String pathNote = archivesPath + "/notebooks.csv";
                            printTable(pathNote);
                            break;

                        case 3:
                            System.out.println("OK, just a second! We are collecting the data.");
                            sleep(2000);
                            System.out.println();
                            System.out.println("Monitors available: ");
                            String pathMonitors = archivesPath + "/monitors.csv";
                            printTable(pathMonitors);
                            break;

                        case 4:
                            System.out.println("OK, just a second! We are collecting the data.");
                            sleep(2000);
                            System.out.println();
                            System.out.println("Keyboards available: ");
                            String pathKeyboards = archivesPath + "/keyboards.csv";
                            printTable(pathKeyboards);
                            break;

                        case 5:      
                            System.out.println("OK, just a second! We are collecting the data.");
                            sleep(2000);
                            System.out.println();
                            System.out.println("Mouses available: ");
                            String pathMouses = archivesPath + "/mouses.csv";
                            printTable(pathMouses);
                            break;

                        case 6:
                            System.out.println("OK, just a second! We are collecting the data.");
                            sleep(2000);
                            System.out.println();
                            System.out.println("Headsets available: ");
                            String pathHeadset = archivesPath + "/headset.csv";
                            printTable(pathHeadset);
                            break;

                        case 7:
                            sleep(1000);
                            System.out.println();
                            System.out.println("Thank you for using our services!");
                            System.out.println("See you next!");
                            break;
                    }
                    if (chosenCategory == 7){
                        sleep(1000);
                        return;
                    }
                    break;

                case 3:
                    sleep(1000);
                    System.out.println();
                    System.out.println("Thank you for using our services!");
                    System.out.println("See you next!");
                    break;
            }
            if (chosenOption == 3){
                sleep(1000);
                break;
            }
        } 
    }

    // SLEEP FUNCTION
    public static void sleep(int time) {
        // time *= 1000;
        try {
            Thread.currentThread();
            Thread.sleep(time);
        }catch(InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }

    // Formata uma célula com um tamanho fixo
    private static String formatCell(String cell, int width) {
        return String.format("%-" + width + "s", cell);
    }

    private static void printTable(String path) {
        ArrayList<String> generalProd = new ArrayList<>();
        String[][] productData = new String[21][3];

        if (!path.contains("bestSeller.csv")){
            productData = new String[11][3];
        }
        
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
                        System.out.print("");
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
                    sleep(20);
                }
                System.out.println("|");
            }

        }catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static boolean contains(final int[] array, final int v) {
        boolean result = false;
        for(int i : array){
            if(i == v){
                result = true;
                break;
            }
        }
        return result;
    }

    public static void clsTerminal() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}

