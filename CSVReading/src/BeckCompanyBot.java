import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class BeckCompanyBot {
    public static void main(String[] args) {
        Scanner tec = new Scanner(System.in);

        clsTerminal();
        System.out.println("Welcome to Beck's Company Bot!");
        sleep(2000);

        System.out.print("Input the archives path>> ");
        String archivesPath = tec.nextLine();

        Bot bot = new Bot(archivesPath);

        while (true) {
            int chosenOption = bot.showMenu();
            if (chosenOption == 3) {
                sleep(1000);
                break;
            }

            bot.processOption(chosenOption);
        }

        System.out.println();
        System.out.println("Thank you for using our services!");
        System.out.println("See you next!");
    }

    public static void sleep(int time) {
        try {
            Thread.currentThread();
            Thread.sleep(time);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void clsTerminal() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}

class Bot {
    private String archivesPath;

    public Bot(String archivesPath) {
        this.archivesPath = archivesPath;
    }


    public int showMenu() {
        Scanner tec = new Scanner(System.in);
        int chosenOption;

        int[] firstOption = { 1, 2, 3 };

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
        } while (!contains(firstOption, chosenOption));

        return chosenOption;
    }

    public void processOption(int chosenOption) {
        Scanner tec = new Scanner(System.in);
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
                int chosenCategory;
                int[] secondOption = { 1, 2, 3, 4, 5, 6, 7 };

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
                } while (!contains(secondOption, chosenCategory));

                if (chosenCategory == 7) {
                    sleep(1000);
                    break;
                }

                System.out.println("OK, just a second! We are collecting the data.");
                sleep(2000);
                System.out.println();

                String categoryPath = archivesPath + getCategoryPath(chosenCategory) + ".csv";
                System.out.println(getCategoryName(chosenCategory) + " available: ");
                printTable(categoryPath);
                break;
        }
    }

    private String getCategoryPath(int category) {
        switch (category) {
            case 1:
                return "/computers";
            case 2:
                return "/notebooks";
            case 3:
                return "/monitors";
            case 4:
                return "/keyboards";
            case 5:
                return "/mouses";
            case 6:
                return "/headset";
            default:
                return "";
        }
    }

    private String getCategoryName(int category) {
        switch (category) {
            case 1:
                return "Computers";
            case 2:
                return "Notebooks";
            case 3:
                return "Monitors";
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

    private void printTable(String path) {
        ArrayList<String[]> productData = new ArrayList<>();

        try (BufferedReader bReader = new BufferedReader(new FileReader(path))) {
            String line = bReader.readLine();
            line = bReader.readLine();
            while (line != null) {
                String[] vecProducts = line.split(";");
                productData.add(vecProducts);
                line = bReader.readLine();
            }

            int[] columnWidths = getColumnWidths(productData);

            for (String[] row : productData) {
                for (int i = 0; i < row.length; i++) {
                    System.out.print("| " + formatCell(row[i], columnWidths[i]) + " ");
                    sleep(20);
                }
                System.out.println("|");
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private int[] getColumnWidths(ArrayList<String[]> data) {
        int numColumns = data.get(0).length;
        int[] columnWidths = new int[numColumns];

        for (String[] row : data) {
            for (int i = 0; i < numColumns; i++) {
                if (row[i].length() > columnWidths[i]) {
                    columnWidths[i] = row[i].length();
                }
            }
        }

        return columnWidths;
    }

    private String formatCell(String cell, int width) {
        return String.format("%-" + width + "s", cell);
    }

    private boolean contains(final int[] array, final int v) {
        for (int i : array) {
            if (i == v) {
                return true;
            }
        }
        return false;
    }

    private void sleep(int time) {
        try {
            Thread.currentThread();
            Thread.sleep(time);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }

    private void clsTerminal() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
