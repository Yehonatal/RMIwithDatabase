package server;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface DbCrud extends Remote {
    List<String> retrieveUsers() throws RemoteException;
    void createUser(User user) throws RemoteException;
    // void deleteUser(User user) throws RemoteException;
    void updateUser(User user) throws RemoteException;
    
}