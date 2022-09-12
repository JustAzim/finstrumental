package com.example.finstrumental.otherapi;


import com.example.finstrumental.dto.*;
import org.jsoup.*;
import org.jsoup.nodes.*;
import org.jsoup.select.*;
import org.springframework.boot.json.*;
import org.springframework.stereotype.*;

import java.io.*;
import java.net.*;
import java.nio.charset.*;
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
    public List<FinvizDataDto> getQuotes(String tickers) throws IOException {
        Connection.Response document = Jsoup.connect(screenerURL)
                .method(Connection.Method.GET)
                .data("v", "152")
                .data("t", tickers)
//                .data("c", "1,7,10,38,65,4,14,39")
                .data("c", "1,2,3,5,6,7,10,14,26,38,39,65")
                .timeout(100000)
                .execute();
        Document doc = document.parse();
        LinkedList<FinvizDataDto> list = new LinkedList<>();

        Elements t = doc.select("td.screener-body-table-nw");
        int i = 0;
        while (i < t.size()) {
            String ticker = t.get(i++).text();
            String companyName = t.get(i++).text();
            String sector = t.get(i++).text();
            String country = t.get(i++).text();
            String marketCap = t.get(i++).text();
            String pe = t.get(i++).text();
            String ps = t.get(i++).text();
            String dividentYield = t.get(i++).text();
            String insiderOwn = t.get(i++).text();
            String de = t.get(i++).text();
            String grossMargin = t.get(i++).text();
            String price = t.get(i++).text();
            FinvizDataDto model = new FinvizDataDto(ticker, companyName, sector, country, marketCap, pe, ps, dividentYield, insiderOwn, de, grossMargin, price);
            list.add(model);
        }

        return list;
    }

    /**
     * @param tickers -set тикеров (МАКСИМУМ 20!!!)
     * @return лист с нужными данными
     */
    public List<FinvizDataDto> getQuotes(Set<String> tickers) throws IOException {

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

    public Map<String, Object> getIncomeStatement(String ticker) throws IOException {
        return getJson("IA", ticker);
    }

    public Map<String, Object> getBalanceSheet(String ticker) throws IOException {
        return getJson("BA", ticker);
    }

    public Map<String, Object> getCashFlow(String ticker) throws IOException {
        return getJson("CA", ticker);
    }

    public Map<String, Object> getJson(String method, String ticker) throws IOException {
        StringBuilder urlString = new StringBuilder("https://finviz.com/api/statement.ashx?t=");
        urlString.append(ticker);
        urlString.append("&s=");
        urlString.append(method);
        URL url = new URL(urlString.toString());

        HttpURLConnection http = (HttpURLConnection) url.openConnection();
        http.setRequestMethod("GET");
        http.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");
        http.setRequestProperty("Accept", "application/json");
        http.setRequestProperty("X-API-KEY", "vSxpavutu3a5L597NjkLW21B979DgPnF9yrWdrRk");

        http.connect();
        int responsecode = http.getResponseCode();

        if (responsecode != 200) {
            throw new RuntimeException("HttpResponseCode: " + responsecode);
        } else {
            BufferedReader r = new BufferedReader(new InputStreamReader(http.getInputStream(), Charset.forName("UTF-8")));

            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = r.readLine()) != null) {
                sb.append(line);
            }
            JacksonJsonParser s = new JacksonJsonParser();
            return s.parseMap(sb.toString());
        }
    }

    public String getCompanyDescription(String ticker) throws IOException {
        String url = "https://finviz.com/quote.ashx?";
        Connection.Response document = Jsoup.connect(url)
                .method(Connection.Method.GET)
                .data("t", ticker)
                .timeout(100000)
                .execute();
        Document doc = document.parse();

        Elements t = doc.select("td.fullview-profile");
        String desc = t.get(0).text();

        return desc;
    }
}
