package ca.shenru.StockTracking;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;
import java.lang.*;
import java.net.*;


import org.apache.commons.csv.*;
import java.io.*;

import static java.lang.Integer.parseInt;


public class DataManager {


    public static String[][] getData(String path) {
        System.out.println("[INFO] Starting to get data from dataset");
        CSVParser csvParser = null;
        try {
            Reader reader = Files.newBufferedReader(Paths.get(path));
            csvParser = new CSVParser(reader, CSVFormat.DEFAULT);
        }
        catch(IOException e){
            System.err.println("[FATAL] Data is inaccessible, corrupted, or the format is incorrect.  See code 3 in error.txt.");
            e.printStackTrace();
            System.exit(3);
        }
        int index = 0;
        int rows = 0;
        try {
            rows = parseInt(Configurations.getConfig("DatasetEntries"));
        }
        catch(NumberFormatException e){
            System.err.println("[FATAL] DatasetEntries in Config.json is not an integer.  See code 5 in error.txt");
            e.printStackTrace();
            System.exit(5);
        }
        String[][] putParsedValuesHere = new String[3][rows+1];
        for (CSVRecord csvRecord : csvParser) {
            // Accessing Values by Column Index
            index++;
            String headlines = csvRecord.get(0);
            String time = csvRecord.get(1);
            String description = csvRecord.get(2);
            putParsedValuesHere[0][index] = time;
            putParsedValuesHere[1][index] = description;
            putParsedValuesHere[2][index] = null;
        }
        System.out.println("[DEBUG] [1] [2] of getData is "+putParsedValuesHere[1][2]);
        return putParsedValuesHere;
    }
    public static boolean toCSV(String[][] input){
        try {
            BufferedWriter br = new BufferedWriter(new FileWriter(Configurations.getConfig("OutputLocation")));
            StringBuilder sb = new StringBuilder();
            String x;
            String y;
            String z;
            for (int col = 0; col < input[0].length; col++) {
                x=input[0][col];
                y=input[1][col];
                z=input[2][col];
                sb.append(x);
                sb.append(",");
                sb.append(y);
                sb.append(",");
                sb.append(z);
                sb.append(System.getProperty("line.separator"));
            }
            br.write(sb.toString());
            br.close();
            System.out.println("[INFO] Success! Parsed all the data and put it into "+ Configurations.getConfig("OutputLocation"));
            System.out.println("[INFO] Took "+System.nanoTime()+" nanoseconds for this software to run! Nice!");
            System.exit(0);
        }

        catch (IOException e) {
            System.err.println("[FATAL] An IO exception happened on the last leg of the program!! Not much more to see here D:");
            e.printStackTrace();
            System.exit(999);
        }
        return true;
    }

    public static LocalDate parseStocksDate(String time){
        LocalDate date = null;
        try {
            //2018-03-26
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-d");
            date = LocalDate.parse(time, formatter);
        }
        catch (DateTimeParseException e) {
            System.err.println("[FATAL] Incorrect date format.  See code 2 in error.txt.");
            e.printStackTrace();
            System.exit(2);
        }
        return date;
    }

    public static LocalDate parseDatasetDate(String time){
        LocalDate date = null;
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM d yyyy");
            date = LocalDate.parse(time, formatter);
        }
        catch (DateTimeParseException e) {
            System.err.println("[FATAL] Incorrect date format.  See code 2 in error.txt.");
            System.exit(2);
        }
        return date;
    }
}
