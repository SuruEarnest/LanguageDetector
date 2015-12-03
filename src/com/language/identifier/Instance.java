/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.language.identifier;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author      Suru Earnest Erihbra <serihbrah@gmail.com>
 * @version     1.0                
 * @since       2015-12-02         
 */
public class Instance {

    private String testDoc;
    private FeatureExtractor fe = new FeatureExtractor();

    public Instance() {
        //for parameterless constructor
    }

    /**
     * This Constructor helps to specify that the new test data instance is to be read from a text File
     * @param textFile   the File from which we want to read our test data from
     */
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

    /**
     * This Constructor helps to specify that the new test data instance is a String value
     * @param text   the test data specified as string
     */
    public Instance(String text) {
        //for a constructor with string value parameter
        this.testDoc = fe.preProcess(text);
    }

    /**
     * Helps to obtain the value of the test data in string format(even if it was a file that was specified)
     * @return simply returns the text format of the test data
     */
    public String loadData() {
        return this.testDoc;
    }

}
