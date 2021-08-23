package ca.shenru.StockTracking;

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
    private static void analyze() {
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

            rawData[0][i] = String.valueOf(temp[0]);
            rawData[1][i] = String.valueOf(temp[1]);
            System.out.println("[Info] Finished analyzing " + i + ". Results are: " + String.valueOf(temp[0]) + " and " + String.valueOf(temp[1]));
        }
    }
    public static void main(String[] args) {
        //Set up the program and terminal
        setup();
        analyze();
    }
}