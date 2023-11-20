package client.GUI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import server.User;

public class DeleteGUI extends JFrame {
    private static JLabel userID;
    private static JTextField userIdField;
    private static JButton deleteUserBtn;
    private static JTable table;
    private static JScrollPane scrollPane;
    private static JPanel box;

    public DeleteGUI() {
        setTitle("Delete a User");
        setSize(850, 350);
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

        // GridBagConstraints for scrollPane
        GridBagConstraints tableConstraints = new GridBagConstraints();
        tableConstraints.gridx = 0;
        tableConstraints.gridy = 0;
        tableConstraints.gridwidth = 2;
        add(scrollPane, tableConstraints);

        // Box panel for delete user
        box = new JPanel();
        box.setLayout(new GridBagLayout());
        GridBagConstraints anchor = new GridBagConstraints();
        anchor.insets = new Insets(5, 5, 5, 5);
        anchor.gridx = 0;
        anchor.gridy = 1;

        userID = new JLabel("UserID: ");
        userIdField = new JTextField(5);
        deleteUserBtn = new JButton("Delete User");

        box.add(userID, anchor);
        anchor.gridx++;
        box.add(userIdField, anchor);
        anchor.gridx = 0;
        anchor.gridy++;
        anchor.gridwidth = 2;
        box.add(deleteUserBtn, anchor);

        // GridBagConstraints for box
        GridBagConstraints boxConstraints = new GridBagConstraints();
        boxConstraints.gridx = 0;
        boxConstraints.gridy = 1;
        boxConstraints.insets = new Insets(10, 0, 0, 0);
        add(box, boxConstraints);

        deleteUserBtn.addActionListener(e -> {
            int userId = Integer.parseInt(userIdField.getText());
            ClientMainGUI.deleteUser(userId);
            reloadTable();

            int option = JOptionPane.showConfirmDialog(this, "Do you want to delete another User", "Delete Another User", JOptionPane.YES_NO_OPTION);

            if (option == JOptionPane.YES_OPTION) {
                clearInputFields();
            } else {
                dispose();
            }
        });
    }

    private void clearInputFields() {
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
