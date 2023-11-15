package client;
import java.rmi.Naming;

import io.github.cdimascio.dotenv.Dotenv;
import server.DbCrud;

public class RMIClient {
    public static void main(String[] args) {
        Dotenv dotenv = Dotenv.load();

        String port = dotenv.get("PORT");
        int PORT = Integer.parseInt(port);

        try {
            DbCrud crudService = (DbCrud)Naming.lookup("rmi://localhost:" + PORT + "/CRUD");
            String test = crudService.test();            

            System.out.println(test);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}