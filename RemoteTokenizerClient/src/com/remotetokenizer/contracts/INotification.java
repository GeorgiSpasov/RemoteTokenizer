/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.remotetokenizer.contracts;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author Georgi Spasov
 */
public interface INotification extends Remote {

    public static final String LOOKUPNAME = "Notification";

    public void notify(String message) throws RemoteException;
}
