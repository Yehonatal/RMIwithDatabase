package server;

import java.io.Serializable;

public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    public int userID;
    public String firstName;
    public String lastName;
    public String email;
    public String password;

    // For Development
    public User() {
    }

    // Used for retrieving the users' information
    public User(String firstName, String lastName, String email, int userID) {
        this.userID = userID;
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
    public User(String firstName, String lastName, String email, String password, int userID) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.userID = userID;
    }

    public int getUserID() {
        return userID;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }
}
