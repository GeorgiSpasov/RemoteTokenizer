/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.remotetokenizer.core;

import com.remotetokenizer.models.Token;
import com.remotetokenizer.models.User;
import com.remotetokenizer.providers.Tokenizer;
import com.remotetokenizer.providers.WriteMode;
import com.remotetokenizer.providers.XMLStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Stream;

/**
 *
 * @author Georgi Spasov
 */
public class Test {

    public static void main(String[] args) {
        
        XMLStream stream = new XMLStream();

        Map<String, String> tokensFromXML = stream.<HashMap<String, String>>readXML("tokens.xml");
        List<Token> tokenObjects = new ArrayList<Token>();
        for (Map.Entry<String, String> entry : tokensFromXML.entrySet()) {
            tokenObjects.add(new Token(entry.getKey(), entry.getValue()));
        }
        tokenObjects.stream().sorted();
        for (Token tokenObject : tokenObjects) {
            System.out.println(tokenObject);
        }
        
        
        
//        // Tokenizer tests ==============================================================================
//        Tokenizer tokenizer = new Tokenizer();
//        
//        String cardId = "4563960122019991";
//        System.out.println(tokenizer.checkCardId(cardId));
//
//        // Token, CardId
//        Map<String, String> xmlTokens = new HashMap<>();
//        String token = tokenizer.tokenize(cardId);
//
//        int tokenNumSum = Stream
//                .of(token.split(""))
//                .mapToInt(n -> Integer.parseInt(n))
//                .sum();
//
//        //JUnit @RepeatedTest(10)
//        if (tokenNumSum % 10 == 0) {
//            System.out.println("problem");
//        }
//
//        // Check for duplicates!!!
//        while (xmlTokens.containsKey(token)) {
//            token = tokenizer.tokenize(cardId);
//        }
//        xmlTokens.put(token, cardId);
//        String retrievedId = xmlTokens.get(token);
//        System.out.println("Token:   " + token + "\n" + "Card Id: " + retrievedId);
//
//        //=================================================================================================
//        // TODO: concurent read/write to file!!! insted ConcurrentHashMap
//        Map<String, String> tokens = new HashMap<>();
//        tokens.put("token1", "firstCard");
//        tokens.put("token2", "secondCard");
//        tokens.put("token3", "thirdCard");
//
//        User user1 = new User("John Doe", "pass1", true, true);
//        User user2 = new User("Johny Cash", "pass2", false, true);
//        User user3 = new User("Danna Montana", "pass3", false, false);
//        List<User> users = new ArrayList<>();
//        users.addAll(Arrays.asList(user1, user2, user3));
//        //=================================================================================================
//
//        XMLStream stream = new XMLStream();
//        
//        Map<String, String> tokensFromXML = stream.<HashMap<String, String>>readXML("tokens.xml");
//        System.out.println(tokensFromXML.get("token1"));
//        List<User> usersFromXML = stream.<ArrayList<User>>readXML("users.xml");
//        System.out.println(usersFromXML.get(0));
//
//        stream.<Map<String, String>>writeXML(tokens, "tokens2.xml", WriteMode.UPDATE); //TODO: Fix append mode file header
//        stream.<List<User>>writeXML(users, "users2.xml", WriteMode.UPDATE);
    }
}
