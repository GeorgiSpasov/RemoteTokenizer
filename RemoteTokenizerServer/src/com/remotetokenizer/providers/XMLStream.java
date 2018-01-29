/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.remotetokenizer.providers;

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
import java.util.Collection;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Georgi Spasov
 */
public class XMLStream {

    private final XStream xStream;

    public XMLStream() {
        this.xStream = new XStream(new StaxDriver());
        // Setup security
        XStream.setupDefaultSecurity(xStream);
        this.xStream.addPermission(NoTypePermission.NONE);
        // Add permissions only for the used classes
        Class<?>[] additionalClasses = new Class[]{Map.class, String.class, User.class};
        this.xStream.allowTypes(additionalClasses);
        this.xStream.addPermission(NullPermission.NULL);
        this.xStream.addPermission(PrimitiveTypePermission.PRIMITIVES);
        this.xStream.allowTypeHierarchy(Collection.class);
        // Allow any type from the same package
        this.xStream.allowTypesByWildcard(new String[]{"xmlservices.**"});
        // Process anotations in model classes
        this.xStream.processAnnotations(User.class);
    }

    
    public <T> void writeXML(T objectTowrite, String filePath, WriteMode writeMode) {
        boolean isAppend = false;
        if (writeMode == WriteMode.APPEND) {
            isAppend = true;
        }
        try (FileWriter writer = new FileWriter(filePath, isAppend)) {
            xStream.toXML(objectTowrite, writer);
        } catch (IOException ex) {
            Logger.getLogger(XMLStream.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    public <T> T readXML(String filePath) {
        T result = null;
        try (InputStream stream = new FileInputStream(filePath);) {
            result = (T) xStream.fromXML(stream);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(XMLStream.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(XMLStream.class.getName()).log(Level.SEVERE, null, ex);
        }

        return result;
    }
}
