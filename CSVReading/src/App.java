import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class App {
    public static void main(String[] args) throws Exception {
        String[][] data = {
            {"Nome", "Idade", "Cidade"},
            {"João", "25", "São Paulo"},
            {"Maria", "30", "Rio de Janeiro"},
            {"Carlos", "40", "Curitiba"}
        };

        JTable table = new JTable(data, data[0]);
        JScrollPane scrollPane = new JScrollPane(table);

        JFrame frame = new JFrame();
        frame.add(scrollPane);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
