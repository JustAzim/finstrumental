package com.example.finstrumental.otherapi;


import org.jsoup.*;
import org.jsoup.nodes.*;
import org.jsoup.select.*;

import java.util.*;

public class Finvizz {
    public static final String PriceToSale = "P/S";
    public static final String PriceToEarn = "P/E";

    public static Map<String, String> getCompanyData(String ticker) {

        String url = "https://finviz.com/quote.ashx?t=" + ticker;
        HashMap<String, String> map = new HashMap<>();
        try {
            Document doc = Jsoup.connect(url).get();
            Elements t = doc.select("table.snapshot-table2 td");
            int i = 0;
            while (i < t.size()) {
                String label = t.get(i).text();
                String value = t.get(i + 1).text();
                map.put(label, value);
                i += 2;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

}
