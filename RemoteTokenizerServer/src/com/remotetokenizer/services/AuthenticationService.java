/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.remotetokenizer.services;

import com.remotetokenizer.models.User;
import com.remotetokenizer.providers.XMLStream;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Georgi Spasov
 */
public class AuthenticationService extends UnicastRemoteObject implements IAuthentication {

    private final XMLStream stream;

    public AuthenticationService() throws RemoteException {
        this.stream = new XMLStream();
    }

    @Override
    public boolean authenticate(String username, String password) throws RemoteException {
        boolean result;

        List<User> usersFromXML = stream.<ArrayList<User>>readXML("users.xml");
        result = usersFromXML
                .stream()
                .anyMatch(u -> (u.getName() == null ? username == null : u.getName().equals(username))
                && (u.getPassword() == null ? password == null : u.getPassword().equals(password)));

        return result;
    }
}
