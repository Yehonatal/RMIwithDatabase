package client;
import java.rmi.Naming;
import java.util.ArrayList;
import java.util.List;

import io.github.cdimascio.dotenv.Dotenv;
import server.DbCrud;

public class RMIClient {
    public static void main(String[] args) {
        List<String> result = new ArrayList<>();

        Dotenv dotenv = Dotenv.load();

        String port = dotenv.get("PORT");
        int PORT = Integer.parseInt(port);

        try {
            DbCrud crudService = (DbCrud)Naming.lookup("rmi://localhost:" + PORT + "/CRUD");
            result = crudService.retrieveUsers();
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}