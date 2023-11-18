package server;
// Implement serialization so the class can be sent over RMI
import java.io.Serializable;

public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    
    public String firstName;
    public String lastName;
    public String email;
    public String password;
    public String UserID;

    public User() {
    }

    // Used for retrieving the users information
    public User(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }
  
    // Used for creating a user
    public User(String firstName, String lastName, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }
    
    // Used for Updating a user
    public User(String firstName, String lastName, String email,  String password, String UserID) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.UserID = UserID;
    }
  

    public String getUser(){
        return firstName + " " + lastName + " " + email;
    }

 
}
