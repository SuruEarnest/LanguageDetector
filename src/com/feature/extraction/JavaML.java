/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.feature.extraction;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.javaml.classification.Classifier;
import net.sf.javaml.classification.KNearestNeighbors;
import net.sf.javaml.core.Dataset;
import net.sf.javaml.core.Instance;
import net.sf.javaml.tools.data.FileHandler;

/**
 * @author suruearnest
 */
public class JavaML {

    public static void doClassification() throws Exception {
        /* Load a data set */

        Dataset data = FileHandler.loadDataset(new File("C:\\Users\\suruearnest\\Documents\\NetBeansProjects\\Extraction\\src\\FilePackage\\iris.data"), 4, ",");
        /*
         * Contruct a KNN classifier that uses 5 neighbors to make a decision.
         */

        System.out.println("Dataset value = " + data.get(0));
        Classifier knn = new KNearestNeighbors(2);
        knn.buildClassifier(data);
        System.out.println("building the knn");

        /*
         * Load a data set for evaluation, this can be a different one, but we
         * will use the same one.
         */
        Dataset dataForClassification = FileHandler.loadDataset(new File("C:\\Users\\suruearnest\\Documents\\NetBeansProjects\\Extraction\\src\\FilePackage\\iris2.data"), 4, ",");
        /* Counters for correct and wrong predictions. */
        int correct = 0, wrong = 0;
        /* Classify all instances and check with the correct class values */

        for (Instance inst : dataForClassification) {
            Object predictedClassValue = knn.classify(inst);
            Object realClassValue = inst.classValue();

            if (predictedClassValue.equals(realClassValue)) {
                correct++;
                // System.out.println("");
            } else {

                wrong++;
                // System.out.println("Instance value = " + inst.classValue());
                System.out.println("Wrong prediction value = " + predictedClassValue);

            }
        }
        System.out.println("Correct predictions  " + correct);
        System.out.println("Wrong predictions " + wrong);

    }

    public static void main(String args[]) {

        System.out.println("Using the knn classification");
        try {
            doClassification();
        } catch (Exception ex) {
            Logger.getLogger(JavaML.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
