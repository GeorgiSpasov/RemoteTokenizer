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
public interface ITokenizer extends Remote {

    public static final String LOOKUPNAME = "Tokenizer";

    public String createToken(String bankId, UUID cookie) throws RemoteException;
}
