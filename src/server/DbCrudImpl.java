package server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
// import server.DbConnector;

public class DbCrudImpl extends UnicastRemoteObject implements DbCrud{
    public DbCrudImpl() throws RemoteException {
        super();
    }

    @Override
    public String test() throws RemoteException {
       return "TEST METHOD CALLED";
    }

    
}