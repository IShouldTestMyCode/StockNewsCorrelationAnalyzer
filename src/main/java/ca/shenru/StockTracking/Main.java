package ca.shenru.StockTracking;

import java.time.LocalDate;
import java.util.*;
import java.lang.*;
import java.net.*;
import org.apache.commons.csv.*;


import java.io.*;
import java.nio.*;

import static java.lang.Integer.parseInt;

public class Main {
    private static void setup(){
        Configurations.verifyConfig();
        //Setup.setup();
    }
    private static String[][] analyze() {
        //Get the dataset path
        String DatasetPath = Configurations.getConfig("DatasetLocation");
        //Get the data
        String[][] rawData = DataManager.getData(DatasetPath);

        String Description;
        float[] temp;
        float[][] SentimentScore = new float[2][parseInt(Configurations.getConfig("DatasetEntries"))];
        System.out.println("[DEBUG] Length of table is: " + rawData[1].length);
        for (int i = 2; i < rawData[1].length; i++) {
            Description = rawData[1][i];
            System.out.println("[DEBUG] Verifying dataset path: " + DatasetPath);
            System.out.println("[INFO] Starting to analyze " + Description);
            temp = Sentiments.analyze(Description);

            rawData[1][i] = String.valueOf(temp[0]);
            rawData[2][i] = String.valueOf(temp[1]);

            System.out.println("[Info] Finished analyzing " + i + ". Results are: " + String.valueOf(temp[0]) + " and " + String.valueOf(temp[1]));
        }
        return rawData;
    }
    private static String[][] testAnalyze() {
        //Get the dataset path
        String DatasetPath = Configurations.getConfig("DatasetLocation");
        //Get the data
        String[][] rawData = DataManager.getData(DatasetPath);

        String Description;
        float[] temp;
        float[][] SentimentScore = new float[2][parseInt(Configurations.getConfig("DatasetEntries"))];
        System.out.println("[DEBUG] Length of table is: " + rawData[1].length);
        for (int i = 2; i < rawData[1].length; i++) {
            Description = rawData[1][i];
            System.out.println("[DEBUG] Verifying dataset path: " + DatasetPath);
            System.out.println("[INFO] Starting to analyze " + Description);
            temp = Sentiments.testAnalyze(Description);

            rawData[1][i] = String.valueOf(temp[0]);
            rawData[2][i] = String.valueOf(temp[1]);
            System.out.println("[Info] Finished analyzing " + i + ". Results are: " + String.valueOf(temp[0]) + " and " + String.valueOf(temp[1]));
        }
        return rawData;
    }
    private static void getResults(String[][] in){
        System.out.println(DataManager.toCSV(in));
    }

    public static void main(String[] args) {
        //Set up the program and terminal
        setup();
        getResults(analyze());

    }
}