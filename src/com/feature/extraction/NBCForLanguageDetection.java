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
import java.util.ListIterator;
import java.util.Set;

/**
 * @author suruearnest
 */
public class NBCForLanguageDetection {

    private ArrayList<String> langClass = new ArrayList<>();
    private final Set<String> vocabulary = new HashSet<>();
    private HashMap<String, ArrayList<String>> trainingCorpus = new HashMap<>();

    private int numberOfTrainingDocs;
    // private ArrayList<Double> priorProbList = new ArrayList<>();
    private HashMap<String, Double> priorProbMap = new HashMap<>();
    private HashMap<String, Double> nbProbMap = new HashMap<>();

    public NBCForLanguageDetection() {
        loadTrainingData();
        buildVocabulary(trainingCorpus);
    }

    private void loadTrainingData() {

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

        englishDocs.add(docEng1);
        englishDocs.add(docEng2);
        frenchDocs.add(docFrench);
        frenchDocs.add(docFrench2);

        italianDocs.add(docItalia);
        italianDocs.add(docItalia2);

        trainingCorpus.put("English", englishDocs);
        trainingCorpus.put("French", frenchDocs);
        trainingCorpus.put("Italian", italianDocs);
    }

    public HashMap<String, ArrayList<String>> getTrainigData() {
        return this.trainingCorpus;
    }

    private ArrayList<String> getLanguageClasses() {
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

    private double getNumberOfTrainingDocs() {
        return this.numberOfTrainingDocs;
    }

    private void setNumberOfTrainigDocs(int numOfDocs) {
        this.numberOfTrainingDocs = numOfDocs;
    }

    private double getNumberOfDocsInClass(String specLangClass) {
        ArrayList<String> langClassList = trainingCorpus.get(specLangClass);

        System.out.println("The number of docs in " + specLangClass + " = " + langClassList.size());
        return langClassList.size();
    }

    private String allDocsInClass(String specLangClass) {
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

    private HashMap<String, Double> naiveBayesProbabilities(ArrayList<String> langClassesList, String testData) {
        ListIterator<String> languageClassesListIt = langClassesList.listIterator();

        HashMap<String, Double> nbp = new HashMap<>();
        while (languageClassesListIt.hasNext()) {
            String languageClass = languageClassesListIt.next();

            double priorProb = getNumberOfDocsInClass(languageClass) / getNumberOfTrainingDocs();

            //using laplace smoothing technique
            double count1 = countInClass(testData, allDocsInClass(languageClass)) + 1;
            //System.out.println("count1 = " + count1);

            double count2 = countInClass(vocabToString(), allDocsInClass(languageClass)) + getVocabulary().size();
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
        Set keyObj = wordFreqMap.keySet();
        String tokenArray[] = words.toLowerCase().split(" ");

        for (int i = 0; i < tokenArray.length; i++) {

            if (keyObj.contains(tokenArray[i])) {
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
    private void buildVocabulary(HashMap<String, ArrayList<String>> trainingDocs) {
        Iterator it = trainingDocs.keySet().iterator();
        int numOfDocs = 0;

        while (it.hasNext()) {

            Object languageClass = it.next();//this takes the keys of the training docs HashMap..NB:Keys = LanguageClasses
            langClass.add(String.valueOf(languageClass));//adding to or registering languages...
            ArrayList<String> docsList = trainingDocs.get(languageClass);

            ListIterator listIt = docsList.listIterator();
            numOfDocs = numOfDocs + docsList.size();//gets and adds the size() of each doc in the ArrayList() 
            while (listIt.hasNext()) {
                String docsString = listIt.next().toString();
                String tokens[] = docsString.toLowerCase().replaceAll("[-(),'?.\"]", " ").split(" ");//splitting upon whitespace
                FeatureExtractor fe = new FeatureExtractor();

                for (String token : tokens) {
                    String vocToken = fe.callStemmer(token);

                    vocabulary.add(vocToken);
                }
            }

        }

        setNumberOfTrainigDocs(numOfDocs);
    }

    /**
     * getVocabulary() make sure
     * {@link #buildVocabulary(HashMap<String, ArrayList<String>> trainingDocs)}
     * is called before ever using {@link #getVocabulary())} to obtain the
     * vocabulary extracted from the training corpus
     *
     *
     * @return a set of words extracted from the training corpus
     */
    public Set<String> getVocabulary() {
        return this.vocabulary;
    }

    public String vocabToString() {

        String vocabString = "";
        Set<String> vocab = this.getVocabulary();
        Iterator vocabIterator = vocab.iterator();
        while (vocabIterator.hasNext()) {
            vocabString += " " + vocabIterator.next();
        }

        return vocabString;
    }

    public void trainUsingNaiveBayes() {

        loadTrainingData();
        buildVocabulary(trainingCorpus);

    }

    public String predictUsingNaiveBayes(Instance inst) {

        String textValue = inst.loadData();

        return "";
    }

    public static void main(String args[]) {

        NBCForLanguageDetection ld = new NBCForLanguageDetection();
        HashMap<String, Double> x = ld.naiveBayesProbabilities(ld.getLanguageClasses(), "Three of them, Matthew, Mark, and Luke, are known as the Synoptic Gospels, from the Greek σύν (syn \"together\") and ὄψις (opsis \"view\").[67][68][69] They are similar in content, narrative arrangement, language and paragraph structure.[67][68] Scholars generally agree that it is impossible to find any direct literary relationship between the Synoptic Gospels and the Gospel of John.[70] While the flow of some events (such as Jesus' baptism, transfiguration, crucifixion and interactions with the apostles) are shared among the Synoptic Gospels, incidents such as the transfiguration do not appear in John, which also differs on other matters, such as the Cleansing of the Temple.[71]");
        System.out.println(x);

    }
}
