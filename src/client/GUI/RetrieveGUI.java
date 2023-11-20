package client.GUI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import server.User;

import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

import java.util.List;

public class RetrieveGUI extends JFrame {
    private static JTable table;
    private static JScrollPane scrollPane;
    private static JButton reloadBtn;
    public RetrieveGUI() {
        setTitle("Retrieve the Users");
        setSize(850, 350);

        setLayout(new GridBagLayout());
        GridBagConstraints anchor = new GridBagConstraints();
        anchor.gridx = 0;
        anchor.gridy = 0;

        // Getting a list of users from the User Table
        List<User> users = ClientMainGUI.getUsersFromServer();

        // Define the column names
        String[] columnNames = {"User ID", "First Name", "Last Name", "Email"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);

        // Populate the tableModel with user data
        for (User user : users) {
            Object[] rowData = {user.getUserID(), user.getFirstName(), user.getLastName(), user.getEmail()};
            tableModel.addRow(rowData);
        }

        table = new JTable(tableModel);

        scrollPane = new JScrollPane(table);
        // Set the preferred size for the scroll pane
        scrollPane.setPreferredSize(new Dimension(550, 150));

        add(scrollPane, anchor);

        reloadBtn = new JButton("Reload Table");
        anchor.gridy++;
        add(reloadBtn, anchor);
        reloadBtn.addActionListener(e -> {
            reloadTable();
        });

        setLocationRelativeTo(null);
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
