package client.GUI;
import javax.swing.*;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class DeleteGUI extends JFrame {
    private static JLabel userID;
    private static JTextField userIdField;
    private static JButton deleteUserBtn;
    private static JPanel box;

    public DeleteGUI() {
        setTitle("Delete a User");
        setSize(850,350);
        
        box = new JPanel();
        box.setLayout(new GridBagLayout());
        GridBagConstraints anchor = new GridBagConstraints();
        anchor.insets = new Insets(5, 5, 5, 5);
        anchor.gridx = 0;
        anchor.gridy = 0;

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

        add(box);


        deleteUserBtn.addActionListener(e->{
            int userId = Integer.parseInt(userIdField.getText());
            ClientMainGUI.deleteUser(userId);

            int option = JOptionPane.showConfirmDialog(this, "Do you want to delete another User", "Delete Another User", JOptionPane.YES_NO_OPTION);

            if(option == JOptionPane.YES_OPTION){
                clearInputFields();
            } else {
                dispose();
            }
        });
    }

    private void clearInputFields() {
        userIdField.setText("");
    }
    
}
