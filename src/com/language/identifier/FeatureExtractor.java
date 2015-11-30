package com.language.identifier;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FeatureExtractor {

    public String preProcess(String fileContentInString) {
        //this method simply preprocess the text contents of a file by removing different characters as seen  below
        String newString = fileContentInString.toLowerCase().replaceAll("[\"-(),@!{}><'?.\'/`~#$%^&*]", " ");
        
        return newString;
    }

    public static int tf(String term, String documentText) {

        String tokens[] = documentText.split(" ");
        int count = 0;

        for (int i = 0; i < tokens.length; i++) {

            if (term.equalsIgnoreCase(FeatureExtractor.callStemmer(tokens[i]))) {

                count = count + 1;
            
            }
        }
        return count;
    }

    public static double idf(String term, List< List<String>> docs) {

        double totalNumOfDocs = 0;//this holds the total number of documents
        double numOfDocsWhereTermAppears = 0;//this is document Frequency
        ListIterator allDocsListIterator = docs.listIterator();

        for (int i = 0; i < docs.size(); i++) {
            // Object doc = allDocsListIterator.next();
            List<String> currentDocList = docs.get(i);
            // System.out.println("print current list = " + currentDocList);
            totalNumOfDocs = totalNumOfDocs + currentDocList.size();
            //now iterate thru this current list and compare with the term...if term is foumd increment docFrequency
            for (int j = 0; j < currentDocList.size(); j++) {

                String docString = currentDocList.get(j);
                // System.out.println("doc string "+j+"th  string = " + docString);

                String tokens[] = docString.split(" ");//splitting into tokens array

                for (int tk = 0; tk < tokens.length; tk++) {
                    if (term.equalsIgnoreCase(FeatureExtractor.callStemmer(tokens[tk]))) {
                        numOfDocsWhereTermAppears++;
                        break;
                    } else {
                        // System.out.println("token " + tokens[tk] + " is not there");

                    }
                }
            }

        }
        double value = (totalNumOfDocs / (1 + numOfDocsWhereTermAppears));
        //System.out.println("fraction value = " + value);
        return Math.log10(value);
    }

    public static double tfIdf(String doc, List<List<String>> allDocsList, String term) {
        return tf(term, doc) * idf(term, allDocsList);
    }

    public static String callStemmer(String word) {
        Stemmer stemmer = new Stemmer();

        char wordCharArray[] = word.toCharArray();
        int counter = 0;

        while (counter < wordCharArray.length) {
            stemmer.add(wordCharArray[counter]);
            counter++;
        }

        stemmer.stem();//reduces the word to the base format.
        return stemmer.toString();//gets the base formats in String.
    }

    public String FileContentInStringFormat(File file) {

        String fileContentInString = null;
        FileInputStream fis;

        try {

            byte[] contentArray = new byte[0];
            fis = new FileInputStream(file);
            int availableBytes = fis.available();

            contentArray = new byte[availableBytes];
            fis.read(contentArray);

            fileContentInString = new String(contentArray);

        } catch (IOException ex) {
            Logger.getLogger(FeatureExtractor.class
                    .getName()).log(Level.SEVERE, null, ex);
        }

        return fileContentInString;

    }

    public HashMap<String, Integer> getWordFrequencies(String text) {

        HashMap<String, Integer> wordFreqMap = new HashMap<>();
        String[] wordToken = text.toLowerCase().replaceAll("[-(),'?.\"]", " ").split(" ");//split the strings by whitespace character into chunks of words

        for (String word : wordToken) {
            // System.out.println("Contains == >" + fileToken[i]);
            Integer freq = wordFreqMap.get(word);
            wordFreqMap.put(word, (freq == null) ? 1 : freq + 1); //For Each word the count will be incremented in the Hashmap
        }

        //System.out.println("The Words and their frequencies-->");
        return wordFreqMap;
    }

//    public static void main(String args[]) {
//
//        // System.out.println("TF-IDF (ipsum) = " + tfidf);S
//        // System.out.println(documents);
//        List<String> d1 = new ArrayList<>();
//        d1.add("I will be going to oshodi tommorow");
//        d1.add("I won't go back no matter what");
//
//        List<String> d2 = new ArrayList<>();
//        d2.add("I would still love to come home sha");
//        d2.add("I sure know that I love Naomi");
//
//        List<String> d3 = new ArrayList<>();
//        d3.add("this boy is just too awesome to me");
//        d3.add("He loves Naomi terribly");
//
//        List<List<String>> allDocsList = new ArrayList<>();
//        allDocsList.add(d1);
//        allDocsList.add(d2);
//        allDocsList.add(d3);
//
//        String docText = "I wont go to his place today because I hate his character";
//        int tf = tf("to", docText);
//        System.out.println("Term frequency= " + tf);
//
//        double idf = idf("to", allDocsList);
//        System.out.println("idf = " + idf);
//
//        double tfidf = tfIdf(docText, allDocsList, "to");
//        System.out.println("tfidf = " + tfidf);
//
//        System.out.println("tfidf from the separate functions = " + tf * idf);
//    }
}
