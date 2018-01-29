/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.remotetokenizer.services;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author Georgi Spasov
 */
public interface IAuthentication extends Remote {

    public boolean authenticate(String username, String password) throws RemoteException;
}
