package ca.shenru.StockTracking;

import com.google.cloud.language.v1.Document;
import com.google.cloud.language.v1.Document.Type;
import com.google.cloud.language.v1.LanguageServiceClient;
import com.google.cloud.language.v1.Sentiment;


import java.io.IOException;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Sentiments {
    public static float[] analyze(String content){
        LanguageServiceClient language = null;
        Sentiment sentiment = null;
        try {
            language = LanguageServiceClient.create();
        }
        catch (IOException e) {
            System.err.println("[FATAL] IOException with Google Language Service Client.  See code 6 in error.txt");
            System.exit(6);
        }
        Document doc = Document.newBuilder().setContent(content).setType(Type.PLAIN_TEXT).build();
        try {
            sentiment = language.analyzeSentiment(doc).getDocumentSentiment();
        }
        catch(Exception e) {
            System.err.println("Google language service client cannot connect to the server.  See code 8 in error.txt");
            System.exit(8);
        }
        float[] Data = new float[2];
        int totalCannotCompute = 0;
        Data[0] = sentiment.getScore();
        Data[1] = sentiment.getMagnitude();
        language.shutdown();
        try {
            language.awaitTermination(30, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            totalCannotCompute++;
            System.err.println("[WARNING] One of the elements cannot be computed. In total, "+totalCannotCompute+" elements cannot be computed.");
        }
        language.close();

        return Data;
    }
    public static float[] testAnalyze(String content){
        float[] Data = new float[2];
        Data[0] = (float) Math.random();
        Data[1] = (float) Math.random();
        return Data;
    }

}
