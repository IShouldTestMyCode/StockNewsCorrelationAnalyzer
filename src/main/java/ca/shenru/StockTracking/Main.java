package ca.shenru.StockTracking;

import java.util.*;
import java.lang.*;
import java.net.*;
import org.apache.commons.csv.*;


import java.io.*;
import java.nio.*;

import static java.lang.Integer.parseInt;

public class Main {
    public static void setup(){
        Configurations.verifyConfig();
        //Setup.setup();
    }
    public static void main(String[] args) {
        //Set up the program and terminal
        setup();
        //Get the dataset path
        String DatasetPath = Configurations.getConfig("DatasetLocation");
        //Get the data
        String[][] rawData = DataManager.getData(DatasetPath);
        String Description;
        float[] temp;
        float[][] SentimentScore = new float[2][parseInt(Configurations.getConfig("DatasetEntries"))];
        for (int i = 0; i < rawData.length; i++){
            Description = rawData[1][i];
            temp = Sentiments.analyze(Description);

            rawData[0][i] = String.valueOf(temp[0]);
            rawData[1][i] = String.valueOf(temp[1]);
            System.out.println("[Info] Finished analyzing "+1+". Results are: " +String.valueOf(temp[0])+ " and "+String.valueOf(temp[1]));
        }


    }
}