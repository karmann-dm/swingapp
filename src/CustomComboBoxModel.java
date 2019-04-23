import javax.swing.*;
import javax.swing.event.ListDataListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CustomComboBoxModel implements ComboBoxModel<Dictionary> {
    private List<Dictionary> data = new ArrayList<>();
    Dictionary selected = null;

    public CustomComboBoxModel() {
        try {
            Statement statement = JdbcConnection.getInstance().createStatement();
            ResultSet resultSet = statement.executeQuery("select id, name from dictionary");
            while (resultSet.next()) {
                data.add(new Dictionary(resultSet.getInt(1), resultSet.getString(2)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setSelectedItem(Object anItem) {
        selected = (Dictionary)anItem;
    }

    @Override
    public Object getSelectedItem() {
        return selected;
    }

    @Override
    public int getSize() {
        return data.size();
    }

    @Override
    public Dictionary getElementAt(int index) {
        return data.get(index);
    }

    @Override
    public void addListDataListener(ListDataListener l) {

    }

    @Override
    public void removeListDataListener(ListDataListener l) {

    }
}
