import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CustomTableModel implements TableModel {
    private List<String> columnNames = Arrays.asList("Id", "Name", "Dict");
    private List<Test> data = new ArrayList<>();

    private List<TableModelListener> listeners = new ArrayList<>();

    public void refresh() throws SQLException {
        data.clear();
        Statement statement = JdbcConnection.getInstance().createStatement();
        ResultSet resultSet = statement.executeQuery("select * from test");
        while (resultSet.next()) {
            data.add(new Test(resultSet.getInt(1), resultSet.getString(2), resultSet.getInt(3)));
        }
    }

    public CustomTableModel() {
        try {
            refresh();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getRowCount() {
        return data.size();
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public String getColumnName(int columnIndex) {
        return columnNames.get(columnIndex);
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return String.class;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return columnIndex == 2;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if(columnIndex != 2) {
            if(columnIndex == 0) return data.get(rowIndex).getId();
            if(columnIndex == 1) return data.get(rowIndex).getName();
            else return "";
        } else {
            try {
                Statement statement = JdbcConnection.getInstance().createStatement();
                ResultSet resultSet = statement.executeQuery("select name from dictionary where id = " + data.get(rowIndex).getDictId());
                resultSet.next();
                return resultSet.getString(1);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return "";
        }
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
    }

    @Override
    public void addTableModelListener(TableModelListener l) {
        listeners.add(l);
    }

    @Override
    public void removeTableModelListener(TableModelListener l) {
        listeners.remove(l);
    }

    public Test getRow(int row) {
        return data.get(row);
    }
}
