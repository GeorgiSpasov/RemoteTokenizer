/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.remotetokenizer.models;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 *
 * @author Georgi Spasov
 */
@XStreamAlias("user")
public class User {

    private String name;
    private String password;
    private boolean canTokenize;
    private boolean canRetrieve;

    public User() {
    }

    public User(String name, String password) {
        this(name, password, false, false);
    }

    public User(String name, String password, boolean canTokenize, boolean canRetrieve) {
        this();
        this.setName(name);
        this.setPassword(password);
        this.setCanTokenize(canTokenize);
        this.setCanRetrieve(canRetrieve);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name.length() < 5) {
            throw new IllegalArgumentException("Passed invalid name!");
        } else {
            this.name = name;
        }
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        if (name.length() < 5) {
            throw new IllegalArgumentException("Passed invalid password!");
        } else {
            this.password = password;
        }
    }

    public boolean getCanTokenize() {
        return canTokenize;
    }

    public void setCanTokenize(boolean canTokenize) {
        this.canTokenize = canTokenize;
    }

    public boolean getCanRetrieve() {
        return canRetrieve;
    }

    public void setCanRetrieve(boolean canRetrieve) {
        this.canRetrieve = canRetrieve;
    }

    @Override
    public String toString() {
        return String.format("Name: %s\nCan tokenize: %b\nCan retrieve: %b", this.getName(), this.getCanTokenize(), this.getCanRetrieve());
    }

}
