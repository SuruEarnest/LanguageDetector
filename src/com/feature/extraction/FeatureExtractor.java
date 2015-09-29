package com.feature.extraction;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Steps: 1.Removing stop words. 2
 * .Stemming 3.keywords frequency calculation
 *
 */
public class FeatureExtractor {

    public String preProcessing(/*String fileName*/) {
        return new String(""); 
    }

    public String callStemmer(String word) {
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
            Logger.getLogger(FeatureExtractor.class.getName()).log(Level.SEVERE, null, ex);
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

    public static void main(String args[]) {

        //1.Remove Stop words...2.Stemming  3.Word Frequency Calculation
        System.out.println("Stop word base:");
       //System.out.println(new FeatureExtractor().getWordFrequencies("That Girl went to the baba's house.I can't imagine myself going to such a place as horrible as that"));
       HashMap<String,Integer> x = new FeatureExtractor().getWordFrequencies("That Girl went to the baba's house.I can't imagine myself going to such a place as horrible as that");
       int count = x.get("that");System.out.println(count);
    }
}
