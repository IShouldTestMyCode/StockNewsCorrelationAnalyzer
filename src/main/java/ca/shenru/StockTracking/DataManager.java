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
            System.exit(3);
        }
        int index = 0;
        int rows = 0;
        try {
            rows = Integer.parseInt(Configurations.getConfig("DatasetEntries"));
        }
        catch(NumberFormatException e){
            System.err.println("[FATAL] DatasetEntries in Config.json is not an integer.  See code 5 in error.txt");
            System.exit(5);
        }
        String[][] putParsedValuesHere = new String[2][rows+1];
        for (CSVRecord csvRecord : csvParser) {
            // Accessing Values by Column Index
            index++;
            String headlines = csvRecord.get(0);
            String time = csvRecord.get(1);
            String description = csvRecord.get(2);
            putParsedValuesHere[0][index] = time;
            putParsedValuesHere[1][index] = description;
        }
        System.out.println("[DEBUG] [1] [2] of getData is "+putParsedValuesHere[1][2]);
        return putParsedValuesHere;
    }

    public static LocalDate parseDate(String time){
        LocalDate date = null;
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy");
            date = LocalDate.parse(time, formatter);
        }
        catch (DateTimeParseException e) {
            System.err.println("[FATAL] Incorrect date format.  See code 2 in error.txt.");
            System.exit(2);
        }
        return date;
    }
}
