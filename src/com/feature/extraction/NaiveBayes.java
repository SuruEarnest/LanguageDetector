/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feature.extraction;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;

/**
 * @author Suru Earnest under the supervision of Victor Odumuyiwa(Ph.D),Computer
 * Science,University of Lagos,Akoka.
 */
public class NaiveBayes extends DataSet {

    DataSet datasetObject = new DataSet();

    private HashMap<String, Double> priorProbMap = new HashMap<>();
    private HashMap<String, Double> nbProbMap = new HashMap<>();

    public NaiveBayes() {

    }

    private HashMap<String, Double> naiveBayesProbabilities(ArrayList<String> langClassesList, String testData) {

        ListIterator<String> languageClassesListIt = langClassesList.listIterator();

        HashMap<String, Double> nbp = new HashMap<>();

        while (languageClassesListIt.hasNext()) {
            String languageClass = languageClassesListIt.next();

            double priorProb = getNumberOfDocsInClass(languageClass) / getNumberOfTrainingDocs();

            //using laplace smoothing technique
            double count1 = countInClass(testData, allDocsInClass(languageClass)) + 1;
            //System.out.println("count1 = " + count1);

            double count2 = countInClass(vocabularyToString(), allDocsInClass(languageClass)) + getVocabulary().size();
            //System.out.println("count2 = " + count2);

            double aposterioriProb = count1 / count2;
            double naiveBayesProb = (priorProb * aposterioriProb);

            nbp.put(languageClass, naiveBayesProb);
        }

        return nbp;
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
        System.out.println("preprocessed text value = " + textValue);
        HashMap<String, Double> x = naiveBayesProbabilities(getLanguageClasses(), textValue);
        double highestProbability = highestValue(x.values());
        String language = getPredictedLanguage(x, highestProbability);

        return language;
    }

    

    public static void main(String args[]) {

        NaiveBayes ld = new NaiveBayes();
        ld.trainUsingNaiveBayes();

        // Instance inst = new Instance("Don't tell me it works like that");
        Instance inst = new Instance("Please,come along with the other two men");

        String lang = ld.predictUsingNaiveBayes(inst);
        System.out.println("the predicted Language is = " + lang);
        //System.out.println(ld.getVocabulary().size());

    }

}
