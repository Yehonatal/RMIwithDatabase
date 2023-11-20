package client.GUI;

import java.rmi.RemoteException;
import java.util.List;

import client.RMIClient;
import server.DbCrud;
import server.User;

import javax.swing.*;
import java.awt.*;

public class ClientMainGUI {
    private static DbCrud crudServiceGUI;
    private static List<User> users;

    private static JFrame homeWindow;
    private static JPanel optionButtons;

    public ClientMainGUI() throws RemoteException {
        crudServiceGUI = RMIClient.crudService;
        initializeGUI();
    }

    private static void initializeGUI() {
        homeWindow = new JFrame("CRUD - RMI - DATABASE");
        homeWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        homeWindow.setLayout(new GridLayout());
        homeWindow.setSize(850, 350);


        optionButtons = new JPanel();
        optionButtons.setLayout(new GridBagLayout());
        GridBagConstraints anchor = new GridBagConstraints();
        anchor.insets = new Insets(5, 5, 5, 5);
        anchor.gridx = 0;

   
        createOptionButton(anchor,1,"Create new User", new CreateGUI());
        createOptionButton(anchor,2,"Retrieve the Users", new RetrieveGUI());
        createOptionButton(anchor,3,"Update a User", new UpdateGUI());
        createOptionButton(anchor,4,"Delete a User", new DeleteGUI());

        homeWindow.add(optionButtons);
        homeWindow.setVisible(true);
    }

    private static void createOptionButton(GridBagConstraints anchor,int x,String label, JFrame frame) {
        
        anchor.gridx = x;
        JButton button = new JButton(label);
        optionButtons.add(button, anchor);

        button.addActionListener(e ->{
                frame.setVisible(true);
        });
    }

    public static List<User> getUsersFromServer() {
        try {
            users = crudServiceGUI.retrieveUsers();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return users;
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
            List<User> users = getUsersFromServer();
            System.out.println(users);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public static void deleteUser(int userId) {
        try {
            crudServiceGUI.deleteUser(userId);
            List<User> users = getUsersFromServer();
            System.out.println(users);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
