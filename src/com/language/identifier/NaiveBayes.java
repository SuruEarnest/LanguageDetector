/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.language.identifier;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.ListIterator;

/**
 * @author      Suru Earnest Erihbra <serihbrah@gmail.com>
 * @version     1.0                
 * @since       2015-12-02         
 */
public class NaiveBayes extends DataSet {

 
    private String language;
    private HashMap<String,Double> naiveBayesProbabilities;
   
    /**
     * Some Initializations taking place...here
     */
    public NaiveBayes() {
     
        naiveBayesProbabilities = new HashMap<>();
     
    }
    
/**
 * <p>
 * This method helps to store the naive bayes probabilities for each language class in a HashMap
 * where the KEYs are the Languages and the Values are the probability values that a text belongs to the particular language
 * <p>
 * @param langClassesList  
 * <p>
 * this is the array list of language categories/classes
 * <p>
 * @param testData  
 * <p>
 * this is the text sample which is to be classified into a language class/category
 * <p>
 * @return 
 */
    private HashMap<String, Double> naiveBayesProbabilities(ArrayList<String> langClassesList, String testData) {
        //this computes and maps each langauge category/class to its respective probability given the testData
        ListIterator<String> languageClassesListIt = langClassesList.listIterator();

        while (languageClassesListIt.hasNext()) {
            String languageClass = languageClassesListIt.next();
            double priorProb = getNumberOfDocsInClass(languageClass) / getNumberOfTrainingDocs();
            //using laplace smoothing technique
            double count1 = countInClass(testData, allDocsInClass(languageClass)) + 1;
            //
            double count2 = countInClass(vocabularyToString(), allDocsInClass(languageClass)) + getVocabulary().size();
            double aposterioriProb = count1 / count2;
            double naiveBayesProb = (priorProb * aposterioriProb);

            naiveBayesProbabilities.put(languageClass, naiveBayesProb);
        }

        return naiveBayesProbabilities;
    }

    
    private int countInClass(String words, String allDocsInClass) {

        int sumOfCount = 0;
        FeatureExtractor fe = new FeatureExtractor();
        HashMap<String, Integer> wordFreqMap = fe.getWordFrequencies(allDocsInClass);

        String tokenArray[] = words.toLowerCase().split(" ");

        for (int i = 0; i < tokenArray.length; i++) {

            if (wordFreqMap.containsKey(tokenArray[i])) {
                int count = wordFreqMap.get(tokenArray[i]);
                sumOfCount = sumOfCount + count;
            } else {
                // System.out.println("token value not in allDocs = " + tokenArray[i]);
            }
        }

        return sumOfCount;
    }

   
    /**
     * this method helps to make a call to {@link #loadTrainingData()} and {@link #buildVocabulary(HashMap<String, ArrayList<String>> trainingDocs }
     * <p>That is the training phase of the Naive Bayes Classifier<p>
     */
    public void trainUsingNaiveBayes() {

        loadTrainingData();
        buildVocabulary(getTrainingCorpusMap());

    }

    /**
     *<p> This Method when called simply helps to compute by statistical learning and prediction 
     * the language of the specified test data instance<p>
     * @param inst    
     * <p>
     * a test data instance object to be predicted 
     * <p>
     * @return the predicted language in string format
     */
    public String predictUsingNaiveBayes(Instance inst) {

        String textValue = inst.loadData();
       // System.out.println("preprocessed text value = " + textValue);
        HashMap<String, Double> x = naiveBayesProbabilities(getLanguageClasses(), textValue);
        double highestProbability = highestValue(x.values());
        language = getPredictedLanguage(x, highestProbability);

        return language;
    }

//    public static void main(String args[]) {
//
//        NaiveBayes ld = new NaiveBayes();
//        ld.trainUsingNaiveBayes();
//
//        // Instance inst = new Instance("Don't tell me it works like that");
//        Instance inst = new Instance("My name is suru earnest,I am a student of the university of lagos.I love to be a good student");
//
//        String lang = ld.predictUsingNaiveBayes(inst);
//        System.out.println("the predicted Language is = " + lang);
//        //System.out.println(ld.getVocabulary().size());
//
//    }

}
