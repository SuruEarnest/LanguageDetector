/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feature.extraction;

/**
 *
 * @author suruearnest
 */
public class kNearestNeigbour {

    public int k;
    DataSet dataObj = new DataSet();

    public kNearestNeigbour(int k) {
        this.k = k;
    }

    public void trainKNN() {
        dataObj.loadTrainingData();
        dataObj.buildVocabulary(dataObj.getTrainingCorpusMap());
    }

    public String predict(Instance inst) {

        String x = inst.loadData();
        return "";
    }

    public static void main(String args[]) {

        System.out.println("trying out stuffs related to knn...");

    }

    private class Similarity {
        
        private Similarity(DocumentVector x,DocumentVector y)
        {
        
        
        }

    }
}
