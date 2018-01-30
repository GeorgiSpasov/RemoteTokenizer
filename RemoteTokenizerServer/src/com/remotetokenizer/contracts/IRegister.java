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
public interface IRegister extends Remote {

    public static final String LOOKUPNAME = "RegisterUser";

    public void register(String name, String Password, boolean canTokenize, boolean canRetrieve, UUID cookie, IAlert alertUser) throws RemoteException;
}
