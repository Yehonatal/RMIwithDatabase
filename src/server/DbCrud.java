package server;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface DbCrud extends Remote {
    List<User> retrieveUsers() throws RemoteException;
    void createUser(User user) throws RemoteException;
    void deleteUser(int UserID) throws RemoteException;
    void updateUser(User user) throws RemoteException;
}