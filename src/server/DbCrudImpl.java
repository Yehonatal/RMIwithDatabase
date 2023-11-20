package server;

import java.sql.Statement;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
// import server.DbConnector;
import java.util.List;

public class DbCrudImpl extends UnicastRemoteObject implements DbCrud{
    public Connection conn = null;
    public Statement statement = null;
    public PreparedStatement prepStatement = null;
    public String query = "";

    // Constant queries 
    private static final String SELECT_USERS_QUERY = "SELECT * FROM Users";
    private static final String INSERT_USER_QUERY = "INSERT INTO Users (FirstName, LastName, Email, Password) VALUES (?, ?, ?, ?)";
    private static final String DELETE_USER_QUERY = "DELETE FROM Users WHERE UserId=?";
    
    public DbCrudImpl() throws RemoteException {
        super();
    }

    @Override
    public List<User> retrieveUsers() throws RemoteException {
        List<User> users = new ArrayList<>();

        try {
            conn = DbConnector.getConnection();
            statement = conn.createStatement();

            ResultSet resultSet = statement.executeQuery(SELECT_USERS_QUERY);

            while (resultSet.next()) {
                int userId = resultSet.getInt("UserID");  // Make sure "UserID" matches the column name in your database
                String firstName = resultSet.getString("FirstName");
                String lastName = resultSet.getString("LastName");
                String email = resultSet.getString("Email");

                User user = new User(firstName, lastName, email, userId);
                users.add(user);
            }

            closeDatabaseResources(); 
        } catch (Exception e) {
            e.printStackTrace();
        }

        return users;
    }

    @Override
    public void createUser(User user) throws RemoteException {
        try {
            conn = DbConnector.getConnection();
            prepStatement = conn.prepareStatement(INSERT_USER_QUERY);

            prepStatement.setString(1, user.firstName);
            prepStatement.setString(2, user.lastName);
            prepStatement.setString(3, user.email);
            prepStatement.setString(4, user.password);

            int rowsAffected = prepStatement.executeUpdate();
            
            closeDatabaseResources();

            if (rowsAffected > 0) {
                System.out.println("User created successfully.");
            } else {
                System.out.println("Failed to create user.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateUser(User user) throws RemoteException {
        try {
            query = "UPDATE Users SET FirstName=?, LastName=?, Email=? , Password=? WHERE UserID=?";

            conn = DbConnector.getConnection();
            prepStatement = conn.prepareStatement(query);

            prepStatement.setString(1, user.firstName);
            prepStatement.setString(2, user.lastName);
            prepStatement.setString(3, user.email);
            prepStatement.setString(4, user.password);
            prepStatement.setInt(5,user.userID);

            int rowsAffected = prepStatement.executeUpdate();

            closeDatabaseResources();


            if (rowsAffected > 0) {
                System.out.println("User updated successfully.");
            } else {
                System.out.println("Failed to update user. User may not exist.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteUser(int UserID) throws RemoteException {
        try {
            conn = DbConnector.getConnection();
            prepStatement = conn.prepareStatement(DELETE_USER_QUERY);

            prepStatement.setInt(1, UserID);

            int rowsAffected = prepStatement.executeUpdate();

            closeDatabaseResources();

            if (rowsAffected > 0) {
                System.out.println("User deleted successfully.");
            } else {
                System.out.println("Failed to delete user. User may not exist.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void closeDatabaseResources() {
        try {
            if (prepStatement != null) {
                prepStatement.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}