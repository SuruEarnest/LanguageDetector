/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feature.extraction;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 *
 * @author suruearnest
 */
public class kNearestNeigbour {

    private int k;
    private DataSet dataObj = new DataSet();
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
                String docText = docsList.get(i);//gets the current doc in the current list
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

        dataObj.loadTrainingData();
        dataObj.buildVocabulary(dataObj.getTrainingCorpusMap());
        generateTrainingDocVectorList(dataObj.getAllDocsList());//generating the document vector for each training sample

    }

    
    private String transformDocumentVectorToDocumentText(List<Double>documentVector)
    {
        //get dccumentText given the documentVector using the docToDocVectorMap
        HashMap<String,List<Double>> docToDocVectorMap1 = getDocToDocVectorMap();
        Collection<List<Double>> valueSet = docToDocVectorMap1.values();
        for(int i=0;i<valueSet.size();i++)
        {
          //List<Double> docVectorList = docToDocVectorMap1.
        
        }
        
        return "";
    }
    public int attributeFunctionY(List<Double>documentVector,String languageCategory) {
       
        
        
                
        
        return 0;
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

        double similarityArray[] = new double[trainDocVectorList.size()];

        //Now generating a HashMap that contains docVectors and their similarities with the testDocVector
        for (int i = 0; i < trainDocVectorList.size(); i++) {

            Document doc = new Document();//no need to pass any parameter since we are not creating a document vector

            List<Double> currentDocVector = getTrainingDocVectorList().get(i);

            double similarity = doc.vectorSimilarity(testDocVector, currentDocVector);

            similarityArray[i] = similarity;//storing similarity in an array...
            similarityMap.put(currentDocVector, similarity);//storing docVectors and their similarity values with the testDocVector
            
        }

        //select the largest k samples from the  n similarities computed above....
        
        return "";
    }

    public static void main(String args[]) {

        System.out.println("trying out stuffs related to knn...");

    }

}
