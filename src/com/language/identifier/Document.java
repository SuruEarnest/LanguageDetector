/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.language.identifier;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 *
 * @author suruearnest
 */
public class Document {

    private DecimalFormat formatter = new DecimalFormat("#.####");
    private DataSet dt;
    private Set<String> vocabSet;
    FeatureExtractor fe = new FeatureExtractor();
    private String documentText;

    public Document() {

    }

    public Document(String textDoc) {
        dt = new DataSet();//this helps to instantiate the DataSet class
        dt.loadTrainingData();
        dt.buildVocabulary(dt.getTrainingCorpusMap());
        this.documentText = textDoc;
        dt.updateVocabulary(textDoc);
        vocabSet = dt.getVocabulary();

    }

    public String getDocumentText() {
        return this.documentText;
    }

    public List<Double> getDocumentVector() {

        List<Double> vector = new ArrayList();

        //now tokenize the textDoc
        String textDoc = getDocumentText();
        // String tokens[] = textDoc.split(" ");
        // System.out.println("vocabSize before update = " + vocabSet.size());
        dt.updateVocabulary(textDoc);
        Iterator it = vocabSet.iterator();

        // System.out.println("Text doc = " + textDoc);
        // System.out.println("VocabSize = " + vocabSet.size());
        while (it.hasNext()) {

            String vocabWord = it.next().toString();
            //now calculate the tf-idf of each word in this textDocument using the textDoc,allDocList and current term/token
            Double tfIdf = FeatureExtractor.tfIdf(textDoc, dt.getAllDocsList(), vocabWord);

            vector.add(tfIdf);

        }
        return vector;

    }

    public double vectorMagnitude(List<Double> documentVector) {

        double sumOfSquares = 0.0;
        double magnitude = 0.0;

        for (int i = 0; i < documentVector.size(); i++) {
            sumOfSquares = sumOfSquares + Math.pow(documentVector.get(i), 2);
        }
        magnitude = Math.sqrt(sumOfSquares);
        String formatted = formatter.format(magnitude);
        return magnitude;//Double.parseDouble(formatted);
    }

    public double vectorDotProduct(List<Double> vector1, List<Double> vector2) {

        double dotProduct = 0;
        double sumOfProduct = 0;

        for (int i = 0; i < vector2.size(); i++) {

            dotProduct = vector1.get(i) * vector2.get(i);
            //System.out.println("the current value in vector1 = " + vector1.get(i));
            // System.out.println("the current value in vector2 = " + vector2.get(j));
            sumOfProduct = sumOfProduct + dotProduct;

        }

        String formatted = formatter.format(sumOfProduct);
        return sumOfProduct;//Double.parseDouble(formatted);
    }

    //the vector1 is always the vocabulary document vector...while the vector2 is the test document vector
    public double vectorSimilarity(List<Double> vector1, List<Double> vector2) {

        double similarity = vectorDotProduct(vector1, vector2) / vectorMagnitude(vector1) * vectorMagnitude(vector2);
        //String formatted = formatter.format(similarity);
        return similarity;//Double.parseDouble(formatted);
    }

//    public static void main(String args[]) {
//
//        Document doc1 = new Document("a Shipment");
//        List<Double> vector1 = doc1.getDocumentVector();
//        System.out.println(vector1);
//
//        Document doc2 = new Document("Delivery of silver arrived in a silver truck");
//        List<Double> vector2 = doc2.getDocumentVector();//must use the object of the new document to call its vector representation
//        System.out.println(vector2);
//
//        Document doc3 = new Document("Shipment of gold arrived in a truck");
//        List<Double> vector3 = doc3.getDocumentVector();
//        System.out.println(vector3);
//
//        Document queryDoc = new Document("gold silver truck ");
//        List<Double> queryVector = queryDoc.getDocumentVector();
//        System.out.println(queryVector);
//
//        double simi_vectro1 = queryDoc.vectorSimilarity(queryVector, vector1);
//        double simi_vectro2 = queryDoc.vectorSimilarity(queryVector, vector2);
//        double simi_vectro3 = queryDoc.vectorSimilarity(queryVector, vector3);
//
//        System.out.println("sim btw query vector and vector 1 = " + simi_vectro1);
//        System.out.println("sim btw query vector and vector 2 = " + simi_vectro2);
//        System.out.println("sim btw query vector and vector 3 = " + simi_vectro3);
//    }
}
