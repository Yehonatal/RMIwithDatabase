package server;

// import java.sql.Statement;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
// import server.DbConnector;
import java.util.List;

public class DbCrudImpl extends UnicastRemoteObject implements DbCrud{
    public DbCrudImpl() throws RemoteException {
        super();
    }

    @Override
    public List<String> retrieveUsers() throws RemoteException {
        List<String> users = new ArrayList<>();

        try {
            String query = "SELECT * FROM Users";
            ResultSet resultSet = DbConnector.selectQuery(query);

            while (resultSet.next()) {
                String firstName = resultSet.getString("FirstName");
                String lastName = resultSet.getString("LastName");
                String email = resultSet.getString("Email");

                User user = new User(firstName, lastName, email);
                users.add(user.getUser());
            }
            // Close the connection after processing the ResultSet
            Connection conn = resultSet.getStatement().getConnection();
            DbConnector.closeConnection(conn);

            resultSet.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return users;
    }

     @Override
    public void createUser(User user) throws RemoteException {
        try {
            String query = "INSERT INTO Users (FirstName, LastName, Email, Password) VALUES (?, ?, ?, ?)";

            Connection conn = DbConnector.getConnection();
            PreparedStatement statement = conn.prepareStatement(query);

            statement.setString(1, user.firstName);
            statement.setString(2, user.lastName);
            statement.setString(3, user.email);
            statement.setString(4, user.password);

            int rowsAffected = statement.executeUpdate();

            // Close resources
            statement.close();
            conn.close();

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
            String query = "UPDATE Users SET FirstName=?, LastName=?, Email=? WHERE FirstName=?";

            Connection conn = DbConnector.getConnection();
            PreparedStatement statement = conn.prepareStatement(query);

            // Set values for parameters
            statement.setString(1, user.firstName);
            statement.setString(2, user.lastName);
            statement.setString(3, user.email);
            // TODO : Find a way to use the userID for the conditioned selection 
            statement.setString(4, user.firstName);

            // Execute the update
            int rowsAffected = statement.executeUpdate();

            // Close resources
            statement.close();
            conn.close();

            if (rowsAffected > 0) {
                System.out.println("User updated successfully.");
            } else {
                System.out.println("Failed to update user. User may not exist.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

  /*   @Override
    public void deleteUser(int userId) throws RemoteException {
        try {
            String query = "DELETE FROM Users WHERE UserId=?";

            Connection conn = DbConnector.getConnection();
            PreparedStatement statement = conn.prepareStatement(query);

            // Set values for parameters
            statement.setInt(1, userId);

            // Execute the delete
            int rowsAffected = statement.executeUpdate();

            // Close resources
            statement.close();
            conn.close();

            if (rowsAffected > 0) {
                System.out.println("User deleted successfully.");
            } else {
                System.out.println("Failed to delete user. User may not exist.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
 */

    
}