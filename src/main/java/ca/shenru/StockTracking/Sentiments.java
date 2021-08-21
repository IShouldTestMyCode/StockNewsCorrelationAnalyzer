package ca.shenru.StockTracking;

import com.google.cloud.language.v1.Document;
import com.google.cloud.language.v1.Document.Type;
import com.google.cloud.language.v1.LanguageServiceClient;
import com.google.cloud.language.v1.Sentiment;


import java.io.IOException;

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

        Data[0] = sentiment.getScore();
        Data[1] = sentiment.getMagnitude();
        return Data;
    }
}
