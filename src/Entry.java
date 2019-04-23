import java.awt.*;

public class Entry {
    public static void main(String[] args) throws ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        JdbcConnection.connect("jdbc:mysql://localhost:3306/tst", "karmanno", "qwe");

        MainWindow mainWindow = new MainWindow();
        mainWindow.setSize(new Dimension(400, 300));
        mainWindow.setVisible(true);
    }
}
