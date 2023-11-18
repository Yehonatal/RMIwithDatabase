package client.GUI;

import java.rmi.RemoteException;
import java.util.List;

import client.RMIClient;
import server.DbCrud;
import server.User;
public class ClientMainGUI {
    // GUI COMPONENTS
    public static DbCrud crudServiceGUI;
    static List<String> users;


    public ClientMainGUI() throws RemoteException{
        initializeGUI();
        
        // * COMPLETE: Getting a list of users from the User Table
        getUsersFromServer(); 
        
        // * COMPLETE: Create User
        // User test = new User("test","test","test@gmail.com","test");
        // createUser(test);

        // * COMPLETE: Updating user details 
        // User testUpdate = new User("testUpdate","testUpdate","testUpdate@gmail.com","testUpdate", "5");
        // updateUser(testUpdate);


        // * COMPLETE: Deleting a user from the table
        // deleteUser(5);
    }

    private static void initializeGUI() throws RemoteException {
        crudServiceGUI = RMIClient.crudService;
    }

    public static void getUsersFromServer() {
        try {
            users = crudServiceGUI.retrieveUsers();
            System.out.println(users);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public static void createUser(User user) {
        try {
            crudServiceGUI.createUser(user);
            getUsersFromServer(); 
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public static void updateUser(User user) {
        try {
            crudServiceGUI.updateUser(user);
            getUsersFromServer();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public static void deleteUser(int userId) {
        try {
            crudServiceGUI.deleteUser(userId);
            getUsersFromServer(); 
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    
}