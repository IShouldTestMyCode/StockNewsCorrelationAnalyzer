package ca.shenru.StockTracking;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;
import java.lang.*;
import java.net.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.apache.commons.csv.*;
import java.io.*;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

public class DataManager {
    private static final Marker fatal = MarkerFactory.getMarker("FATAL");
    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static String[][] getData(String path) {
        logger.info("Starting to get data from dataset");
        CSVParser csvParser = null;
        try {
            Reader reader = Files.newBufferedReader(Paths.get(path));
            csvParser = new CSVParser(reader, CSVFormat.DEFAULT);
        }
        catch(IOException e){
            logger.error(fatal, "Data is inaccessible, corrupted, or the format is incorrect.  See code 3 in error.txt.", e);
            System.exit(3);
        }
        int index = 0;
        int rows = 32771;
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
        return putParsedValuesHere;
    }
    public static LocalDate parseDate(String time){
        LocalDate date = null;
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy");
            date = LocalDate.parse(time, formatter);
        }
        catch (DateTimeParseException e) {
            logger.error(fatal, "Incorrect date format.  See code 2 in error.txt.", e);
            System.exit(2);
        }
        return date;
    }
}
