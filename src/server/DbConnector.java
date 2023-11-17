package server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DbConnector {
    private static String url = "jdbc:mysql://localhost:3306/RMIForm";
    private static String username = "root";
    private static String password = "root";

    public static Connection getConnection() throws Exception {
        return DriverManager.getConnection(url, username, password);
    }

    public static ResultSet selectQuery(String query) throws Exception {
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            conn = getConnection();
            statement = conn.prepareStatement(query);
            resultSet = statement.executeQuery();
        } catch (Exception e) {
            e.printStackTrace();
        } 

        return resultSet;
    }

    public static int executeUpdate(String query) throws Exception {
        Connection conn = null;
        PreparedStatement statement = null;
        int rowsAffected = 0;

        try {
            conn = getConnection();
            statement = conn.prepareStatement(query);
            rowsAffected = statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return rowsAffected;
    }
    // Close the connection separately after processing the ResultSet
    public static void closeConnection(Connection conn) throws SQLException {
        if (conn != null) conn.close();
    }

}
