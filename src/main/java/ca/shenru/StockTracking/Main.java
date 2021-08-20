package ca.shenru.StockTracking;

import java.util.*;
import java.lang.*;
import java.net.*;
import org.apache.commons.csv.*;
import java.io.*;
import java.nio.*;

public class Main {
    public static void main(String[] args) {
        String[][] data = DataManager.getData("/Users/the_creeper2007/Desktop/StockMarket/StockTracking/ds/reuters_headlines.csv");
        System.out.println(Arrays.deepToString(data));
    }
}