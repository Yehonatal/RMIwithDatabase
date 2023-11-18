package server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import io.github.cdimascio.dotenv.Dotenv;

public class DbConnector {
    private static String url;
    private static String username;
    private static String password;

    static {
        try {
            Dotenv dotenv = Dotenv.load();
            url = dotenv.get("URL");
            username = dotenv.get("U");
            password = dotenv.get("P");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }

}
