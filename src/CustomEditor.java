import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.sql.Statement;

public class CustomEditor extends DefaultCellEditor {
    private JComboBox<Dictionary> comboBox;
    private int row;
    private int column;
    private String value;
    private Test test;
    private JTable jTable;

    public CustomEditor(JComboBox<Dictionary> comboBox) {
        super(comboBox);
        this.comboBox = comboBox;
        this.comboBox.setModel(new CustomComboBoxModel());
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        this.row = row;
        this.column = column;
        this.value = value.toString();
        this.jTable = table;
        this.test = ((CustomTableModel)this.jTable.getModel()).getRow(row);
        return comboBox;
    }

    @Override
    public Object getCellEditorValue() {
        try {
            Statement statement = JdbcConnection.getInstance().createStatement();
            Dictionary d = (Dictionary)comboBox.getModel().getSelectedItem();
            statement.execute("update test set dict_id = " + d.getId() + " where id = " + test.getId());
            ((CustomTableModel)jTable.getModel()).refresh();
            jTable.updateUI();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return jTable.getModel().getValueAt(row, column);
    }
}
