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
 * @author Suru Earnest under the supervision of V.T Odumuyiwa(Ph.D),Computer
 * Sciences,University of Lagos,Akoka.
 */
public class NaiveBayes extends DataSet {

 
    private String language;
    private HashMap<String,Double> naiveBayesProbabilities;
   
    public NaiveBayes() {
     
        naiveBayesProbabilities = new HashMap<>();
     
    }

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
     * building vocabulary
     *
     * use
     * {@link #buildVocabulary(HashMap<String,ArrayList<String>> trainingDocs)}
     * to build vocabulary from the given training corpus
     *
     * @param trainingDocs represents the training corpus
     * @return void
     */
    /**
     * getVocabulary() make sure
     * {@link #buildVocabulary(HashMap<String, ArrayList<String>> trainingDocs)}
     * is called before ever using {@link #getVocabulary())} to obtain the
     * vocabulary extracted from the training corpus
     *
     *
     * @return a set of all words extracted from the training corpus
     */
    public void trainUsingNaiveBayes() {

        loadTrainingData();
        buildVocabulary(getTrainingCorpusMap());

    }

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
