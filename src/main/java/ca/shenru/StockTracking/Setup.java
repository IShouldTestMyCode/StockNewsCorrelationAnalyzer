package ca.shenru.StockTracking;

import java.io.IOException;

public class Setup {
    public static void setup(){
        String command = "export GOOGLE_APPLICATION_CREDENTIALS=";
        command = command + "\"";
        command = command + Configurations.getConfig("GoogleCredentialsPath");
        command = command + "\"";
        System.out.println("[INFO] Got setup command: "+command);
        try {
            Runtime.getRuntime().exec(command);
        }
        catch(IOException e) {
            System.err.println("[FATAL] Cannot execute command. See code 7 in error.txt");
            System.exit(7);
        }
    }
}
