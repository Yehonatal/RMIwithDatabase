package server;
// Implement serialization so the class can be sent over RMI
import java.io.Serializable;

public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    
    public String firstName;
    public String lastName;
    public String email;

    public User() {
    }

    public User(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public String getUser(){
        return firstName + " " + lastName + " " + email;
    }

 
}
