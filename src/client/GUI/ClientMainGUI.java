package client.GUI;

import java.rmi.RemoteException;
import java.util.List;

import client.RMIClient;
import server.DbCrud;
import server.User;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClientMainGUI {
    // GUI COMPONENTS
    private static JFrame homeWindow;

    private static JPanel optionButtons;
    private static JButton createOptionButton;
    private static JButton retrieveOptionButton;
    private static JButton updateOptionButton;
    private static JButton deleteOptionButton;



    public static DbCrud crudServiceGUI;
    static List<String> users;


    public ClientMainGUI() throws RemoteException{
        initializeGUI();
    }

    private static void initializeGUI() throws RemoteException {
        crudServiceGUI = RMIClient.crudService;

        homeWindow = new JFrame("CRUD - RMI - DATABASE");
        homeWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        homeWindow.setLayout(new GridLayout());
        homeWindow.setSize(850,350);

        // Panel for Options 
        optionButtons = new JPanel();
        optionButtons.setLayout(new GridBagLayout());
        GridBagConstraints anchor = new GridBagConstraints();
        anchor.insets = new Insets(5, 5, 5, 5);

        anchor.gridx = 0;
        anchor.gridy = 0;

        createOptionButton = new JButton("Create new User");
        optionButtons.add(createOptionButton, anchor);
        retrieveOptionButton = new JButton("Retrieve the Users");
        anchor.gridx++;
        optionButtons.add(retrieveOptionButton, anchor);
        updateOptionButton = new JButton("Update a User");
        anchor.gridx++;
        optionButtons.add(updateOptionButton, anchor);
        deleteOptionButton = new JButton("Delete a User");
        anchor.gridx++;
        optionButtons.add(deleteOptionButton, anchor);

        // Action Listener for the Buttons 
        createOptionButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                CreateGUI createGUI = new CreateGUI();
                createGUI.setVisible(true);
            }
        });
        retrieveOptionButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                RetrieveGUI retrieveGUI = new RetrieveGUI();
                retrieveGUI.setVisible(true);
            }
        });
        updateOptionButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                UpdateGUI updateGUI = new UpdateGUI();
                updateGUI.setVisible(true);
            }
        });
        deleteOptionButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                DeleteGUI deleteGUI = new DeleteGUI();
                deleteGUI.setVisible(true);
            }
        });



        
        homeWindow.add(optionButtons);
        homeWindow.setVisible(true);
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