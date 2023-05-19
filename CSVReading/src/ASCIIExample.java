public class ASCIIExample {
    public static void main(String[] args) {
        String[][] data = {
            {"Nome", "Idade", "Cidade"},
            {"João", "25", "São Paulo"},
            {"Maria", "30", "Rio de Janeiro"},
            {"Carlos", "40", "Curitiba"}
        };

        // Encontra a largura máxima de cada coluna
        int[] columnWidths = new int[data[0].length];
        for (String[] row : data) {
            for (int i = 0; i < row.length; i++) {
                if (row[i].length() > columnWidths[i]) {
                    columnWidths[i] = row[i].length();
                }
            }
        }

        // Imprime a tabela com colunas de tamanho igual
        for (String[] row : data) {
            for (int i = 0; i < row.length; i++) {
                System.out.print("| " + formatCell(row[i], columnWidths[i]) + " ");
            }
            System.out.println("|");
        }
    }

    // Formata uma célula com um tamanho fixo
    private static String formatCell(String cell, int width) {
        return String.format("%-" + width + "s", cell);
    }
}
