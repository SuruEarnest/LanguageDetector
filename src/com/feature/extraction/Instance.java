/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feature.extraction;

import java.io.File;

/**
 *
 * @author suruearnest
 */
public class Instance {

    private String testDoc;

    public Instance() {
        //for parameterless constructor
    }

    public Instance(File textFile) {
        //for a constructor with textFile
    }

    public Instance(String text) {
        this.testDoc = text;
    } 

    public String loadData() {
        return this.testDoc;
    }

    
}
