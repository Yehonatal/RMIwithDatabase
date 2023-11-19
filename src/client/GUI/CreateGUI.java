package client.GUI;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.*;

import server.User;

// import server.User;

public class CreateGUI extends JFrame {
    private static JTextField firstNameField;
    private static JTextField lastNameField;
    private static JTextField emailField;
    private static JPasswordField pswField;
    private static JButton createButton;

    private static JPanel box;

    public CreateGUI() {
        setTitle("Create new User");
        setSize(850, 350);

        // Initialize components
        firstNameField = new JTextField(20);
        lastNameField = new JTextField(20);
        emailField = new JTextField(20);
        pswField = new JPasswordField(20);

        // Initialize button
        createButton = new JButton("Create User");

        // The name row
        box = new JPanel();
        box.setLayout(new GridBagLayout());
        GridBagConstraints anchor = new GridBagConstraints();
        anchor.insets = new Insets(5, 5, 5, 5);
        anchor.gridx = 0;
        anchor.gridy = 0;

        // Add labels and text fields to the panel
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

        anchor.gridy++;
        box.add(createButton, anchor);

        add(box);


        // Action Listener for the create button 
        createButton.addActionListener(e -> {
            String firstName = firstNameField.getText();
            String lastName = lastNameField.getText();
            String email = emailField.getText();
            String password = new String(pswField.getPassword());

            // Create a new User object
            User newUser = new User(firstName, lastName, email, password);
            ClientMainGUI.createUser(newUser);   
            
            int option = JOptionPane.showConfirmDialog(this, "Do you want to add another user?", "Add Another User", JOptionPane.YES_NO_OPTION);

            if (option == JOptionPane.YES_OPTION) {
                clearInputFields();
            } else {
                dispose();
            }
        });
    }

    private void clearInputFields() {
        firstNameField.setText("");
        lastNameField.setText("");
        emailField.setText("");
        pswField.setText("");
    }
    
}
