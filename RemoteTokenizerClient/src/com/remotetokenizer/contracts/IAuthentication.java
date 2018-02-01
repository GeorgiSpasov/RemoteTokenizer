/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.remotetokenizer.contracts;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.UUID;

/**
 *
 * @author Georgi Spasov
 */
public interface IAuthentication extends Remote {

    public static final String LOOKUPNAME = "Authentication";

    public UUID authenticate(String username, String password, INotification alertUser)
            throws RemoteException;

    public void logOut(UUID cookie)
            throws RemoteException;
}
