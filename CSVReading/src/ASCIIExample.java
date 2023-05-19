public class ASCIIExample {
    public static void main(String[] args) {
        String[][] data = new String[3][3];

        data[0][0] = "João";
        data[0][1] = "25";
        data[0][2] = "São Paulo";
        data[1][0] = "Teste4";
        data[1][1] = "Teste5";
        data[1][2] = "Teste6";
        data[2][0] = "Teste7";
        data[2][1] = "Teste8";
        data[2][2] = "Teste9";

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
