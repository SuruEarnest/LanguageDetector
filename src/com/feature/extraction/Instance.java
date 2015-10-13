/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feature.extraction;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author suruearnest
 */
public class Instance {

    private String testDoc;
    private FeatureExtractor fe = new FeatureExtractor();

    public Instance() {
     //for parameterless constructor
    }

    public Instance(File textFile) {
        //for a constructor with textFile

        String fileContentInString = null;
        FileInputStream fis;
        try {

            if (textFile.isFile() && textFile.canRead()) {

                byte[] contentArray = new byte[0];
                fis = new FileInputStream(textFile);
                int availableBytes = fis.available();

                contentArray = new byte[availableBytes];
                fis.read(contentArray);

                fileContentInString = new String(contentArray);
                //preprocess the file content by removing all sorts of symbols and then do stemming
                this.testDoc = fe.preProcess(fileContentInString);

            }
        } catch (IOException ex) {
            Logger.getLogger(FeatureExtractor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Instance(String text) {
        this.testDoc = fe.preProcess(text);
    }

    public String loadData() {
        return this.testDoc;
    }

}
