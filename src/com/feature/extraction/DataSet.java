/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feature.extraction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Set;

/**
 *
 * @author suruearnest
 */
public class DataSet {

    private ArrayList<String> langClass = new ArrayList<>();

    private final Set<String> vocabulary = new HashSet<>();
    private HashMap<String, ArrayList<String>> trainingCorpus = new HashMap<>();

    private List<List<String>> allDocsList = new ArrayList<>();

    private int numberOfTrainingDocs;

    public DataSet() {

    }

    public void loadTrainingData() {

        FeatureExtractor fe = new FeatureExtractor();

        String docEng1 = "For God so loved the world,that he gave his only son,that whoever believes in him should not perish but have eternal life";
        String docEng2 = "Jesus,also referred to as Jesus of Nazareth, is the central figure of Christianity, whom the teachings of most Christian "
                + "denominations hold to be the Son of God. Christianity regards Jesus as the awaited Messiah (or Christ) of the Old Testament and refers to him as Jesus Christ,"
                + "a name that is also used in non-Christian contexts.";
        ArrayList<String> englishDocs = new ArrayList<>();

        String docFrench = "Car Dieu a tant aime le monde qu'il a donne son Fils unique,afin que quiconque croit en lui ne perisse point.maise qu'il ait la vie eternelle";
        String docFrench2 = "Jésus-Christ, le Christ ou simplement Christ est le nom donné par l'ensemble des chrétiens à Jésus de Nazareth qu'ils considèrent comme le Messie,"
                + "l’oint du Seigneur » annoncé par l'Ancien Testament du judaïsme, mort et ressuscité pour le salut des hommes. En effet, les chrétiens reconnaissent Jésus-Christ "
                + "comme le Messie et le Fils unique de Dieu. De plus, une majorité de ceux-ci le reconnaissent comme l'une des trois personnes du Dieu trinitaire.";
        ArrayList<String> frenchDocs = new ArrayList<>();

        String docItalia = "Poiche Iddio ha tanto amayo il mondo,che ha dato il suo unigenito Figiliuolo,affinche chuinque crede in lui non perisca,ma abbia vita eterna";
        String docItalia2 = "Gesù di Nazaret è il fondatore e la figura centrale del Cristianesimo, religione che lo "
                + "riconosce come il Cristo (Messia) atteso dalla tradizione ebraica e Dio fatto uomo. Secondo le fonti disponibili, Gesù ha svolto la sua attività di "
                + "predicatore,guaritore ed esorcista negli ultimi anni della sua vita nella provincia romana della Giudea nella regione storica della Palestina.";
        ArrayList<String> italianDocs = new ArrayList<>();

        englishDocs.add(fe.preProcess(docEng1));
        englishDocs.add(fe.preProcess(docEng2));

        frenchDocs.add(fe.preProcess(docFrench));
        frenchDocs.add(fe.preProcess(docFrench2));

        italianDocs.add(fe.preProcess(docItalia));
        italianDocs.add(fe.preProcess(docItalia2));

        trainingCorpus.put("English", englishDocs);
        trainingCorpus.put("French", frenchDocs);
        trainingCorpus.put("Italian", italianDocs);
    }

    public ArrayList<String> getLanguageClasses() {
        return langClass;
    }

    /**
     * use {@link #getNumberOfClasses() }
     * to obtain the number of class of languages available from the training
     * corpus
     *
     *
     * @return integer value
     */
    private int getNumberOfClasses() {
        return trainingCorpus.size();
    }

    public HashMap<String, ArrayList<String>> getTrainingCorpusMap() {
        return this.trainingCorpus;
    }

    public double getNumberOfTrainingDocs() {
        return this.numberOfTrainingDocs;
    }

    private void setNumberOfTrainigDocs(int numOfDocs) {
        this.numberOfTrainingDocs = numOfDocs;
    }

    public double getNumberOfDocsInClass(String specLangClass) {
        ArrayList<String> langClassList = trainingCorpus.get(specLangClass);

        System.out.println("The number of docs in " + specLangClass + " = " + langClassList.size());
        return langClassList.size();
    }

    public String allDocsInClass(String specLangClass) {
        String docString = "";
        FeatureExtractor fe = new FeatureExtractor();

        Set keyObj = trainingCorpus.keySet();
        if (keyObj.contains(specLangClass)) {

            ArrayList<String> docList = trainingCorpus.get(specLangClass);
            ListIterator docListIterator = docList.listIterator();
            while (docListIterator.hasNext()) {
                docString += fe.callStemmer(docListIterator.next().toString().toLowerCase());
            }

        }

        return docString;
    }

    public void buildVocabulary(HashMap<String, ArrayList<String>> trainingDocs) {
        Iterator it = trainingDocs.keySet().iterator();
        FeatureExtractor fe = new FeatureExtractor();
        int numOfDocs = 0;

        while (it.hasNext()) {
            //looping through the training docs hashMap using the keys
            Object languageClass = it.next();//this takes the keys of the training docs HashMap..NB:Keys = LanguageClasses
            langClass.add(String.valueOf(languageClass));//adding to or registering languages...
            ArrayList<String> docsList = trainingDocs.get(languageClass);

            ListIterator listIt = docsList.listIterator();
            numOfDocs = numOfDocs + docsList.size();//gets and adds the size() of each doc in the ArrayList() 

            System.out.println("docsList = " + docsList);
            allDocsList.add(docsList);//populating the list of the documents lists in the training corpus HashMap
            while (listIt.hasNext()) {
                String docsString = listIt.next().toString();

                // String tokens[] = docsString.toLowerCase().replaceAll("[-(),'?.\"]", " ").split(" ");//splitting upon whitespace
                String tokens[] = fe.preProcess(docsString).split(" ");//splitting upon whitespace

                for (String token : tokens) {
                    String vocToken = fe.callStemmer(token);

                    vocabulary.add(vocToken);//adding to the vocabulary set...
                }
            }

        }

        setNumberOfTrainigDocs(numOfDocs);

    }

//    public void updateVocabulary(String textDocument) {
//        FeatureExtractor fe = new FeatureExtractor();
//        String tokens[] = fe.preProcess(textDocument).split(" ");//splitting upon whitespace
//        for (String token : tokens) {
//            String vocToken = fe.callStemmer(token);
//
//            vocabulary.add(vocToken);//adding to the vocabulary set...
//        }
//    }
    public List<List<String>> getAllDocsList() {
        return this.allDocsList;
    }

    public Set<String> getVocabulary() {
        return this.vocabulary;
    }

    public String vocabularyToString() {

        String vocabString = "";
        Set<String> vocab = this.getVocabulary();
        Iterator vocabIterator = vocab.iterator();
        while (vocabIterator.hasNext()) {
            vocabString += " " + vocabIterator.next();
        }
        return vocabString;

    }

    public static void main(String args[]) {

        DataSet dt = new DataSet();
        dt.loadTrainingData();
        dt.buildVocabulary(dt.getTrainingCorpusMap());
        System.out.println(dt.getAllDocsList());
        // System.out.println("docs in english= " + dt.allDocsInClass("English"));
    }
}
