/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feature.extraction;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 *
 * @author suruearnest
 */
class DocumentVector {

    private DataSet dt;
    Set<String> vocabList;
    FeatureExtractor fe = new FeatureExtractor();

    public DocumentVector() {
        dt = new DataSet();//this helps to instantiate the DataSet class
        dt.loadTrainingData();
        dt.buildVocabulary(dt.getTrainingCorpusMap());
        vocabList = dt.getVocabulary();
        System.out.println(vocabList);
    }

    public List<Double> getDocumentVector(String textDoc) {
        List<Double> vector = new ArrayList();
        //now tokenize the textDoc
        String tokens[] = textDoc.split(" ");
        Iterator it = vocabList.iterator();

        while (it.hasNext()) {

            String vocabWord = it.next().toString();
            System.out.println("vocab word = " + vocabWord);

            for (int tk = 0; tk < tokens.length; tk++) {
                String wordTokenInTextDoc = tokens[tk];//this is the token of each word in the text document
                //now calculate the tf-idf of each word in this textDocument using the textDoc,allDocList and current term/token
                double tfIdf = FeatureExtractor.tfIdf(textDoc, dt.getAllDocsList(), wordTokenInTextDoc);
                
                vector.add(tfIdf);
            }
        }

        return vector;
    }

    
    public static void main(String args[]) {

        DocumentVector dv = new DocumentVector();
        List<Double> vector = dv.getDocumentVector("I am going home today");
        System.out.println(vector);

    }

}
