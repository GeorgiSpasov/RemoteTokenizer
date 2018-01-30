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
public interface IAlert extends Remote {

    public static final String LOOKUPNAME = "Alert";

    public void alert(String message) throws RemoteException;
}
