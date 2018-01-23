/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.remotetokenizer.services;

import static com.remotetokenizer.services.Tokenizer.checkCardId;
import static com.remotetokenizer.services.Tokenizer.tokenize;
import static com.remotetokenizer.services.XMLStream.readXML;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

/**
 *
 * @author Georgi Spasov
 */
public class Test {
    public static void main(String[] args) {
        readXML("path");
        
        
        
        String cardId = "4563960122019991";
        System.out.println(checkCardId(cardId));
        
        // Token, CardId
        Map<String, String> tokens = new HashMap<>();
        String token = tokenize(cardId);

        int tokenNumSum = Stream
                .of(token.split(""))
                .mapToInt(n -> Integer.parseInt(n))
                .sum();

         //JUnit @RepeatedTest(10)
        if (tokenNumSum % 10 == 0) {
            System.out.println("problem");
        }
        
        // Check for duplicates
        while (tokens.containsKey(token)) {
            token = tokenize(cardId);
        }       

        tokens.put(token, cardId);
        String retrievedId = tokens.get(token);
        System.out.println("Token:   " + token + "\n" + "Card Id: " + retrievedId);
    }
}
