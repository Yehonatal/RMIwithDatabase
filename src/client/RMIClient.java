package client;
import java.rmi.Naming;

import client.GUI.ClientMainGUI;
import io.github.cdimascio.dotenv.Dotenv;
import server.DbCrud;

public class RMIClient {
    
    public static Dotenv dotenv = Dotenv.load();
    public static String port = dotenv.get("PORT");
    static int PORT = Integer.parseInt(port);
    public static DbCrud crudService;

    public static void main(String[] args) {
        try {
            crudService = (DbCrud)Naming.lookup("rmi://localhost:" + PORT + "/CRUD");    
            startGUI();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void startGUI() {
        try {
            new ClientMainGUI();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}