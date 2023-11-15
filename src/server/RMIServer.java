package server;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import io.github.cdimascio.dotenv.Dotenv;


public class RMIServer {
    public static void main(String[] args) {
        Dotenv dotenv = Dotenv.load();

        String port = dotenv.get("PORT");
        int PORT = Integer.parseInt(port);

         try {
            // Create Registry object instance 
            Registry registry = LocateRegistry.createRegistry(PORT);

            // Create the instance for the remote interface implementation 
            DbCrudImpl calImp = new DbCrudImpl();
            registry.rebind("CRUD", calImp);
            System.out.println("Server Listening: [PORT: " + PORT + "]");
            
        } catch (RemoteException e) {
            System.out.println("Initiating...... Failed :(");
            e.printStackTrace();
        }

    }
}