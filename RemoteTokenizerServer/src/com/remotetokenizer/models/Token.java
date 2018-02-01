/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.remotetokenizer.models;

import java.io.Serializable;

/**
 *
 * @author Georgi Spasov
 */
public class Token implements Serializable {

    private String tokenString;
    private String cardId;

    public Token() {
    }

    public Token(String tokenString, String cardId) {
        this.tokenString = tokenString;
        this.cardId = cardId;
    }

    public String getTokenString() {
        return tokenString;
    }

    public void setTokenString(String tokenString) {
        if (tokenString.length() != 16) {
            throw new IllegalArgumentException("Passed invalid token!");
        } else {
            this.tokenString = tokenString;
        }
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        if (tokenString.length() != 16) {
            throw new IllegalArgumentException("Passed invalid card id!");
        } else {
            this.cardId = cardId;
        }
    }

    @Override
    public String toString() {
        return String.format("%s <-> %s", this.getTokenString(),this.getCardId());
    }

}
