import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JdbcConnection {
    private static Connection connection = null;

    public static void connect(String jdbcUrl, String username, String password) {
        try {
            connection = DriverManager.getConnection(jdbcUrl, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Connection getInstance() {
        return connection;
    }
}
