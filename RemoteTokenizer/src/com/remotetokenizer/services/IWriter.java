/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.remotetokenizer.services;

/**
 *
 * @author Georgi Spasov
 */
public interface IWriter {

    public <T> void writeXML(T objectTowrite, String filePath, WriteMode writeMode);
}
