/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.remotetokenizer.services;

import com.remotetokenizer.models.User;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;
import com.thoughtworks.xstream.security.NoTypePermission;
import com.thoughtworks.xstream.security.NullPermission;
import com.thoughtworks.xstream.security.PrimitiveTypePermission;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Georgi Spasov
 */
public class XMLStream {

    public static void readXML(String filePath) {
        XStream xStream = new XStream(new StaxDriver());
        XStream.setupDefaultSecurity(xStream);
        // clear out existing permissions and set own ones
        xStream.addPermission(NoTypePermission.NONE);
        Class<?>[] additionalClasses = new Class[]{Map.class, String.class, User.class};
        xStream.allowTypes(additionalClasses);
        xStream.addPermission(NullPermission.NULL);
        xStream.addPermission(PrimitiveTypePermission.PRIMITIVES);
        xStream.allowTypeHierarchy(Collection.class);
        // allow any type from the same package
        xStream.allowTypesByWildcard(new String[]{
            "xmlservices.**"
        });

        // TODO concurent read/write to file!!! insted ConcurrentHashMap
        Map<String, String> tokens = new HashMap<>();
        tokens.put("token1", "firstCard");
        tokens.put("token2", "secondCard");
        tokens.put("token3", "thirdCard");

        User user1 = new User("John Doe", "pass1", true, true);
        User user2 = new User("Johny Cash", "pass2", false, true);
        User user3 = new User("Danna Montana", "pass3", false, false);
        List<User> users = new ArrayList<>();
        users.addAll(Arrays.asList(user1, user2, user3));

        // xStream.alias("user", User.class);
        xStream.processAnnotations(User.class);
        try (FileWriter writer = new FileWriter("users.xml")) {
            xStream.toXML(users, writer);
        } catch (IOException ex) {
            Logger.getLogger(XMLStream.class.getName()).log(Level.SEVERE, null, ex);
        }

        try (InputStream inUsers = new FileInputStream("users.xml");) {
            List<User> xmlUsers = (List<User>) xStream.fromXML(inUsers);
            System.out.println(xmlUsers.get(0));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(XMLStream.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(XMLStream.class.getName()).log(Level.SEVERE, null, ex);
        }

        try (FileWriter writer2 = new FileWriter("tokens.xml")) {
            xStream.toXML(tokens, writer2);
        } catch (IOException ex) {
            Logger.getLogger(XMLStream.class.getName()).log(Level.SEVERE, null, ex);
        }

        try (InputStream inTokens = new FileInputStream("tokens.xml");) {
            Map<String, String> deserializedTokens
                    = (Map<String, String>) xStream.fromXML(inTokens);
            deserializedTokens.put("token4", "fourthCard");
            System.out.println(deserializedTokens.getOrDefault("token1", null)); //null or "not set"
        } catch (FileNotFoundException ex) {
            Logger.getLogger(XMLStream.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(XMLStream.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
