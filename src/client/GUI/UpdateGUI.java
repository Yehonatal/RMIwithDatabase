package client.GUI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

import server.User;

public class UpdateGUI extends JFrame {
    private static JTextField firstNameField;
    private static JTextField lastNameField;
    private static JTextField emailField;
    private static JPasswordField pswField;
    private static JPanel box;
    private static JLabel userID;
    private static JTextField userIdField;
    private static JButton updateUserBtn;
    private static JTable table;
    private static JScrollPane scrollPane;
    private static JButton reloadBtn;

    public UpdateGUI() {
        setTitle("Update a User");
        setSize(850, 400);
        setLayout(new GridBagLayout());

        // Table of users
        List<User> users = ClientMainGUI.getUsersFromServer();
        String[] columnNames = {"User ID", "First Name", "Last Name", "Email"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        for (User user : users) {
            Object[] rowData = {user.getUserID(), user.getFirstName(), user.getLastName(), user.getEmail()};
            tableModel.addRow(rowData);
        }
        table = new JTable(tableModel);
        scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(550, 150));

        GridBagConstraints tableConstraints = new GridBagConstraints();
        tableConstraints.gridx = 0;
        tableConstraints.gridy = 0;
        tableConstraints.gridwidth = 2;
        add(scrollPane, tableConstraints);

        // Initialize components
        firstNameField = new JTextField(20);
        lastNameField = new JTextField(20);
        emailField = new JTextField(20);
        pswField = new JPasswordField(20);
        userID = new JLabel("UserID: ");
        userIdField = new JTextField(5);
        updateUserBtn = new JButton("Update User");
        reloadBtn = new JButton("Reload Table");

        // The name row
        box = new JPanel();
        box.setLayout(new GridBagLayout());
        GridBagConstraints anchor = new GridBagConstraints();
        anchor.insets = new Insets(5, 5, 5, 5);
        anchor.gridx = 0;
        anchor.gridy = 0;

        // Add labels and text fields to the panel
        anchor.gridx++;
        box.add(reloadBtn, anchor);
        
        anchor.gridx = 0;
        anchor.gridy++;

        box.add(new JLabel("First Name:"), anchor);
        anchor.gridx++;
        box.add(firstNameField, anchor);

        anchor.gridx = 0;
        anchor.gridy++;
        box.add(new JLabel("Last Name:"), anchor);
        anchor.gridx++;
        box.add(lastNameField, anchor);

        anchor.gridx = 0;
        anchor.gridy++;
        box.add(new JLabel("Email:"), anchor);
        anchor.gridx++;
        box.add(emailField, anchor);

        anchor.gridx = 0;
        anchor.gridy++;
        box.add(new JLabel("Password:"), anchor);
        anchor.gridx++;
        box.add(pswField, anchor);

        anchor.gridx = 0;
        anchor.gridy++;
        box.add(userID, anchor);
        anchor.gridx++;
        box.add(userIdField, anchor);
        
        anchor.gridx = 0;
        anchor.gridy++;
        box.add(updateUserBtn, anchor);
        

        GridBagConstraints boxConstraints = new GridBagConstraints();
        boxConstraints.gridx = 0;
        boxConstraints.gridy = 1;
        add(box, boxConstraints);


        updateUserBtn.addActionListener(e -> {
            String firstName = firstNameField.getText();
            String lastName = lastNameField.getText();
            String email = emailField.getText();
            String password = new String(pswField.getPassword());
            String userId = userIdField.getText();

            // Create a new User object
            User updatedUser = new User(firstName, lastName, email, password, Integer.parseInt(userId));
            ClientMainGUI.updateUser(updatedUser);

            reloadTable();

            int option = JOptionPane.showConfirmDialog(this, "Do you want to update other users Information?", "Update Another User", JOptionPane.YES_NO_OPTION);

            if (option == JOptionPane.YES_OPTION) {
                clearInputFields();
            } else {
                dispose();
            }
        });

      
        reloadBtn.addActionListener(e -> {
            reloadTable();
        });
    }

    private void clearInputFields() {
        firstNameField.setText("");
        lastNameField.setText("");
        emailField.setText("");
        pswField.setText("");
        userIdField.setText("");
    }
    private void reloadTable() {
        List<User> users = ClientMainGUI.getUsersFromServer();
        DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
        tableModel.setRowCount(0);

        // Populate the tableModel with the updated user data
        for (User user : users) {
            Object[] rowData = {user.getUserID(), user.getFirstName(), user.getLastName(), user.getEmail()};
            tableModel.addRow(rowData);
        }
    }
}
