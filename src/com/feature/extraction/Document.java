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
public class Document {

    private DataSet dt;
    Set<String> vocabSet;
    FeatureExtractor fe = new FeatureExtractor();
    private String documentText;

    public Document(String textDoc) {
        dt = new DataSet();//this helps to instantiate the DataSet class
        dt.loadTrainingData();
        dt.buildVocabulary(dt.getTrainingCorpusMap());
        this.documentText = textDoc;
        dt.updateVocabulary(textDoc);
        vocabSet = dt.getVocabulary();
        System.out.println(vocabSet);
    }

    public String getDocumentText() {
        return this.documentText;
    }

    public List<Double> getDocumentVector() {

        List<Double> vector = new ArrayList();

        //now tokenize the textDoc
        String textDoc = getDocumentText();
        String tokens[] = textDoc.split(" ");
        // System.out.println("vocabSize before update = " + vocabSet.size());
        dt.updateVocabulary(textDoc);
        Iterator it = vocabSet.iterator();

        System.out.println("Text doc = " + textDoc);
        System.out.println("VocabSize = " + vocabSet.size());

        while (it.hasNext()) {

            String vocabWord = it.next().toString();
            System.out.println("vocab word = " + vocabWord);
            //for (int tk = 0; tk < tokens.length; tk++) {
            //  String wordTokenInTextDoc = tokens[tk];//this is the token of each word in the text document
            //now calculate the tf-idf of each word in this textDocument using the textDoc,allDocList and current term/token
            double tfIdf = FeatureExtractor.tfIdf(textDoc, dt.getAllDocsList(), vocabWord);
            // Double tf = new Double(FeatureExtractor.tf(vocabWord, textDoc));
            vector.add(tfIdf);
            // System.out.println("counter value = " + tk);
            //}

        }

        System.out.println("doc vector size = " + vector.size());
        return vector;

    }

    public double vectorLength(List<Double> documentVector) {
        return new Double("");
    }

    public static void main(String args[]) {

        Document dv = new Document("gold silver truck");
        List<Double> vector = dv.getDocumentVector();
        System.out.println(vector);

    }

}
