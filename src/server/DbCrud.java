package server;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface DbCrud extends Remote {
    String test() throws RemoteException;
    
}