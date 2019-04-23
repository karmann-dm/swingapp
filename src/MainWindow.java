import javax.swing.*;

public class MainWindow extends JFrame {
    private JPanel rootPanel;
    private JTable table1;

    public MainWindow() {
        setContentPane(rootPanel);
        table1.setModel(new CustomTableModel());

        JComboBox comboBox = new JComboBox();
        table1.getColumnModel().getColumn(2).setCellEditor(new CustomEditor(comboBox));
    }
}
