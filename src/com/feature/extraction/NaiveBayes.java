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
public class NaiveBayes {

    Dataset datasetObject = new Dataset();

    private HashMap<String, Double> priorProbMap = new HashMap<>();
    private HashMap<String, Double> nbProbMap = new HashMap<>();

    public NaiveBayes() {

    }

    private HashMap<String, Double> naiveBayesProbabilities(ArrayList<String> langClassesList, String testData) {
        ListIterator<String> languageClassesListIt = langClassesList.listIterator();

        HashMap<String, Double> nbp = new HashMap<>();

        while (languageClassesListIt.hasNext()) {
            String languageClass = languageClassesListIt.next();

            double priorProb = datasetObject.getNumberOfDocsInClass(languageClass) / datasetObject.getNumberOfTrainingDocs();

            //using laplace smoothing technique
            double count1 = countInClass(testData, datasetObject.allDocsInClass(languageClass)) + 1;
            //System.out.println("count1 = " + count1);

            double count2 = countInClass(datasetObject.vocabularyToString(), datasetObject.allDocsInClass(languageClass)) + datasetObject.getVocabulary().size();
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

        datasetObject.loadTrainingData();
        datasetObject.buildVocabulary(datasetObject.getTrainingCorpus());

    }

    private double highestValue(Collection<Double> val) {
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        double valu = 0.0, max = 0.0;
        Iterator it = val.iterator();

        while (it.hasNext()) {

            valu = Double.parseDouble(it.next().toString());
            if (max <= valu) {
                max = valu;
            }
        }

        return max;
    }

    public String predictUsingNaiveBayes(Instance inst) {

        String textValue = inst.loadData();
        HashMap<String, Double> x = naiveBayesProbabilities(datasetObject.getLanguageClasses(), textValue);
        double highestProbability = highestValue(x.values());
        String language = getPredictedLanguage(x, highestProbability);

        return language;
    }

    private String getPredictedLanguage(HashMap<String, Double> probMap, double value) {

        Double d = new Double("" + value + "");
        String languageKey = null;

        for (Map.Entry entry : probMap.entrySet()) {
            if (d.equals(entry.getValue())) {
                languageKey = entry.getKey().toString();
                break; //break out because it is assumed to be a one to one map,no other value is assigned to such ket
            }
        }

        return languageKey;
    }

    public static void main(String args[]) {

        NaiveBayes ld = new NaiveBayes();
        ld.trainUsingNaiveBayes();

        // Instance inst = new Instance("Don't tell me it works like that");
        Instance inst = new Instance("I told him i was not involved in the incident yesterday.");

        String lang = ld.predictUsingNaiveBayes(inst);
        System.out.println("the predicted Language is = " + lang);

        //System.out.println(ld.getVocabulary().size());
    }

}
