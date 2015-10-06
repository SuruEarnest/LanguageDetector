/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feature.extraction;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Set;

/**
 *
 * @author suruearnest
 */
class Document {

    private DataSet dt;
    Set<String> vocabSet;
    FeatureExtractor fe = new FeatureExtractor();
    private String textDocument = "";

//    public Document() {
//        dt = new DataSet();//this helps to instantiate the DataSet class
//        dt.loadTrainingData();
//        dt.buildVocabulary(dt.getTrainingCorpusMap());
//        vocabSet = dt.getVocabulary();
//        System.out.println(vocabSet);
//
//    }
    public Document(String textDoc) {
        dt = new DataSet();//this helps to instantiate the DataSet class
        dt.loadTrainingData();
        dt.buildVocabulary(dt.getTrainingCorpusMap());
        dt.updateVocabulary(textDoc);
        vocabSet = dt.getVocabulary();
        System.out.println(vocabSet);
        this.textDocument = textDoc;
    }

    public String getTextDocument() {

        return this.textDocument;
    }

    public List<Double> getDocumentVector() {

        List<Double> vector = new ArrayList();

        //now tokenize the textDoc
        String textDoc = getTextDocument();
        System.out.println("textDoc = " + textDoc);
        // String tokens[] = textDoc.split(" ");
        System.out.println("vocabSize before update = " + vocabSet.size());
        //dt.updateVocabulary(textDoc);
        Iterator it = vocabSet.iterator();

        // System.out.println("Text doc = " + textDoc);
        System.out.println("VocabSize = " + vocabSet.size());
        while (it.hasNext()) {

            String vocabWord = it.next().toString();
            System.out.println("vocab word = " + vocabWord);
            //for (int tk = 0; tk < tokens.length; tk++) {
            //  String wordTokenInTextDoc = tokens[tk];//this is the token of each word in the text document
            //now calculate the tf-idf of each word in this textDocument using the textDoc,allDocList and current term/token
            // double tfIdf = FeatureExtractor.tfIdf(textDoc, dt.getAllDocsList(), vocabWord);
            Double tf = new Double(FeatureExtractor.tf(vocabWord, textDoc));
            vector.add(tf);
            // System.out.println("counter value = " + tk);
            //}

        }

        System.out.println("doc vector size = " + vector.size());
        return vector;

    }

    public double vectorMagnitude(List<Double> documentVector) {

        double magnitude;
        double sumOfSquares = 0.0;
        ListIterator listIt = documentVector.listIterator();

        while (listIt.hasNext()) {
            double x = Double.parseDouble(listIt.next().toString());
            sumOfSquares += Math.pow(x, 2);

        }
        magnitude = Math.sqrt(sumOfSquares);
        return magnitude;
    }

    public double vectorDotProduct(List<Double> docVector1, List<Double> docVector2) {
        double dotProduct = 0;

        for (int i = 0; i < docVector1.size(); i++) {

            for (int j = 0; j < docVector2.size(); j++) {

                dotProduct = dotProduct + docVector1.get(i) * docVector2.get(j);
            }
        }
        return dotProduct;
    }

    public double vectorSimilarity(List<Double> docVector1, List<Double> docVector2) {
        double similarity = vectorDotProduct(docVector1, docVector2) / vectorMagnitude(docVector1) * vectorMagnitude(docVector2);
        return similarity;

    }

    public static void main(String args[]) {

        Document doc = new Document("Shipment of gold damaged in a fire");
        List<Double> vector = doc.getDocumentVector();
        double vectorLength = doc.vectorMagnitude(vector);

        System.out.println("Vector = " + vector);
        System.out.println("Magnitude = " + vectorLength);

        Document doc2 = new Document("Delivery of silver arrived in a silver truck");
        List<Double> vector1 = doc2.getDocumentVector();
        double vectorLength1 = doc2.vectorMagnitude(vector1);

        System.out.println("Vector = " + vector1);
        System.out.println("Magnitude = " + vectorLength1);

        Document doc3 = new Document("Shipment of gold arrived in a truck");
        List<Double> vector2 = doc3.getDocumentVector();
        double vectorLength2 = doc3.vectorMagnitude(vector2);

        System.out.println("Vector = " + vector2);
        System.out.println("Magnitude = " + vectorLength2);

        Document qDoc = new Document("gold silver truck");
        List<Double> qvector = qDoc.getDocumentVector();
        double qvectorLength = qDoc.vectorMagnitude(vector);

        System.out.println("Vector = " + qvector);
        System.out.println("Magnitude = " + qvectorLength);

        //System.out.println("dot product = " + new Document().vectorDotProduct(vector, vector1));
        double d_doc = qDoc.vectorSimilarity(qvector, vector);
        double d_doc1 = qDoc.vectorSimilarity(qvector, vector1);
        double d_doc2 = qDoc.vectorSimilarity(qvector, vector2);

        System.out.println("values = " + d_doc + " " + d_doc1 + " " + d_doc2);
    }

}
