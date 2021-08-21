package ca.shenru.StockTracking;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.*;
import java.nio.charset.*;
import org.apache.commons.io.*;

public class Configurations {
///Users/the_creeper2007/Desktop/StockMarket/StockTracking/config.json
    private static String getConfig(){
        String basePath = new File("").getAbsolutePath();
        System.out.println("[INFO] Retrieved absolute path: "+basePath);
        String fullPath = basePath+"/config.json";
        System.out.println("[INFO] Got full path: "+fullPath);
        String content = null;
        try {
            content = FileUtils.readFileToString(new File(fullPath), "UTF-8");
        }
        catch(IOException e){
            System.err.println("[FATAL] Cannot find the configuration file, or the file is inaccessible. See code 4 in error.txt.");
            System.exit(4);
        }
        return content;
    }
    private static boolean checkConfig(String string){

        try {
            new JSONObject(string);
        } catch (JSONException ex) {
            try {
                new JSONArray(string);
            } catch (JSONException ex1) {
                return false;
            }
        }
        return true;
    }
    public static void verifyConfig(){
        if (!checkConfig(getConfig())){
            System.err.println("[FATAL] Malformed configuration file, see code 1 in error.txt");
            System.exit(1);
        }
    }
}
