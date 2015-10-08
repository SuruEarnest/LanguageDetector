/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feature.extraction;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import net.sf.javaml.classification.KNearestNeighbors;

/**
 *
 * @author suruearnest
 */
public class kNearestNeigbour extends DataSet {

    private int k;
    //private DataSet dataObj = new DataSet();
    private List<List<Double>> trainingDocVectorList = new ArrayList<>();
    private HashMap<String, List<Double>> docToDocVectorMap = new HashMap<>();

    public kNearestNeigbour(int k) {

        this.k = k;

    }

    //this method takes the list of all the training docs as parameter and generates a HashMap that maps every document in the list to it's respective document vector
    private HashMap<String, List<Double>> getDocToDocVectorMap() {

        return this.docToDocVectorMap;
    }

    private void generateTrainingDocVectorList(List<List<String>> allDocsList) {
        //get a list of the feature vectors of all the training documents

        // List<List<String>> allDocsList = dataObj.getAllDocsList();
        for (int i = 0; i < allDocsList.size(); i++) {
            //loop larg list
            List<String> docsList = allDocsList.get(i);//getting the lists in the large list NB:each list contains strings of values representing the docs
            System.out.println("current training doc = " + docsList);
            for (int j = 0; j < docsList.size(); j++) {//loop thru the current list in the large list
                String docText = docsList.get(j);//gets the current doc in the current list
                //now calculate the document vector of this particular doc...
                Document doc = new Document(docText);
                trainingDocVectorList.add(doc.getDocumentVector());
                docToDocVectorMap.put(docText, doc.getDocumentVector());
            }

        }
        //return trainingDocVectorList;
    }

    public List<List<Double>> getTrainingDocVectorList() {
        return this.trainingDocVectorList;
    }

    public void trainKNN() {
        //must be called before ever attempting to predict
        loadTrainingData();
        buildVocabulary(this.getTrainingCorpusMap());
        generateTrainingDocVectorList(this.getAllDocsList());//generating the document vector for each training sample

    }

    private static String transformDocumentVectorToDocumentText(HashMap<String, List<Double>> docToDocVectorMap, List<Double> documentVector) {
        //get dccumentText given the documentVector using the docToDocVectorMap

        String docText = " ";

        for (Map.Entry entry : docToDocVectorMap.entrySet()) {
            if (documentVector.equals(entry.getValue())) {
                docText = entry.getKey().toString();
                break; //break out because it is assumed to be a one to one map,no other value is assigned to such ket
            }
        }

        return docText;
    }

    //must have been preprocessed too
    private String getCategoryInTrainingCorpusFromDocText(String documentText) {

        String languageCategory = "";
        HashMap<String, ArrayList<String>> trainingCorpus = getTrainingCorpusMap();
        //Note that the training corpus is a Map of Language category to ArrayLists of documents.
        Collection<ArrayList<String>> valuesCollection = trainingCorpus.values();
        Iterator it = valuesCollection.iterator();

        while (it.hasNext()) {
            //looping through the arrayList of documents
            Object x = it.next();
            ArrayList<String> docTextList = (ArrayList<String>) x;//current arrayList
            //System.out.println("Expected = " + docText);
            for (int i = 0; i < docTextList.size(); i++) {
                String docText = docTextList.get(i);
                if (docText.equalsIgnoreCase(documentText)) {
                    //then use the current ArrayList to get the languageCatgory in the training corpus
                    for (Map.Entry entry : trainingCorpus.entrySet()) {
                        if (docTextList.equals(entry.getValue())) {
                            languageCategory = entry.getKey().toString();
                            break; //break out because it is assumed to be a one to one map,no other value is assigned to such ket
                        }
                    }

                    break;
                }
            }

        }

        return languageCategory;
    }

    private int attributeFunctionY(List<Double> documentVector, String languageCategory) {
        //this attribute function is used in the calculation of the probability that a textDoc belongs to a particular language category  
        String docText = transformDocumentVectorToDocumentText(getDocToDocVectorMap(), documentVector);//gets the string format of the document vector
        String languageCategoryInrainingDocs = getCategoryInTrainingCorpusFromDocText(docText);

        if (languageCategoryInrainingDocs.equalsIgnoreCase(languageCategory)) {

            return 1;
        } else {
            return 0;

        }

    }

    public String predict(Instance inst) {

        String testDataInString = inst.loadData();
        Document testDoc = new Document(testDataInString);

        //doc vector-->similarity value
        HashMap<List<Double>, Double> similarityMap = new HashMap<>();
        //List<Double>similarityList = new ArrayList<>();

        //this is the the test document vector
        List<Double> testDocVector = testDoc.getDocumentVector();

        List<List<Double>> trainDocVectorList = getTrainingDocVectorList();
        // above line gets all the training docs and calculates their respective document vector and stores them in a list

        double similarityArray[] = new double[trainDocVectorList.size()];

        //Now generating a HashMap that contains each docVectors and their similarities with the testDocVector
        for (int i = 0; i < trainDocVectorList.size(); i++) {

            Document doc = new Document();//no need to pass any parameter since we are not creating a document vector

            List<Double> currentDocVector = getTrainingDocVectorList().get(i);

            double similarity = doc.vectorSimilarity(testDocVector, currentDocVector);

            similarityArray[i] = similarity;//storing similarity in an array...
            
            similarityMap.put(currentDocVector, similarity);//storing docVectors and their similarity values with the testDocVector
            
            
        }

        //System.out.println("Similarity map = " + similarityMap);
        //select the largest k samples from the  n similarities computed above....
        return "";
    }  

    public static void main(String args[]) {

        kNearestNeigbour knn = new kNearestNeigbour(7);
        knn.trainKNN();
        Instance inst = new Instance("silver gold truck");
        knn.predict(inst);
        //knn.getCategoryInTrainingCorpusFromDocText("Delivery of gold arrived in a gold truck");
    }

}
