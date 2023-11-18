package client;
import java.rmi.Naming;
import java.util.ArrayList;
import java.util.List;

import io.github.cdimascio.dotenv.Dotenv;
import server.DbCrud;
// import server.User;

public class RMIClient {
    public static void main(String[] args) {
        List<String> result = new ArrayList<>();
        
        Dotenv dotenv = Dotenv.load();
        
        String port = dotenv.get("PORT");
        int PORT = Integer.parseInt(port);
        
        try {
            DbCrud crudService = (DbCrud)Naming.lookup("rmi://localhost:" + PORT + "/CRUD");
            
            // * COMPLETE: Getting a list of users from the User Table
            result = crudService.retrieveUsers();
          
            // * COMPLETE: Create User
            // User test = new User("test","test","test@gmail.com","test");
            // crudService.createUser(test);

            // * COMPLETE: Updating user details 
            // User testUpdate = new User("testUpdate","testUpdate","testUpdate@gmail.com","testUpdate", "5");

            // crudService.updateUser(testUpdate);

            // * COMPLETE: Deleting a user from the table
            // crudService.deleteUser(4);

            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}