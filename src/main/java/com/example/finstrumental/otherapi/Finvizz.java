package com.example.finstrumental.otherapi;


import com.example.finstrumental.model.*;
import org.jsoup.*;
import org.jsoup.nodes.*;
import org.jsoup.select.*;
import org.springframework.stereotype.*;

import java.io.*;
import java.util.*;

@Component
public class Finvizz {
    public final String PriceToSale = "P/S";
    public final String PriceToEarn = "P/E";

    public final String screenerURL = "https://finviz.com/screener.ashx";


    /**
     * @param tickers - Список тикеров через запятую (МАКСИМУМ 20!!!)
     * @return лист с нужными данными
     */
    public List<FinvizDataModel> getQuotes(String tickers) throws IOException {
        Connection.Response document = Jsoup.connect(screenerURL)
                .method(Connection.Method.GET)
                .data("v", "152")
                .data("t", tickers)
                .data("c", "1,7,10,38,65")
                .timeout(100000)
                .execute();
        Document doc = document.parse();
        LinkedList<FinvizDataModel> list = new LinkedList<>();

        Elements t = doc.select("td.screener-body-table-nw");
        int i = 0;
        while (i < t.size()) {
            String ticker = t.get(i++).text();
            String pe = t.get(i++).text();
            String ps = t.get(i++).text();
            String de = t.get(i++).text();
            String price = t.get(i++).text();
            FinvizDataModel model = new FinvizDataModel(ticker, pe, ps, de, price);
            list.add(model);
        }

        return list;
    }

    public List<FinvizDataModel> getQuotes(Set<String> tickers) throws IOException {

        String t = String.join(",", tickers);
        return getQuotes(t);
    }

    public Map<String, String> getCompanyData(String ticker) {

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
