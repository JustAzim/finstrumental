package com.example.finstrumental.otherapi;

import org.springframework.boot.json.*;
import org.springframework.stereotype.*;

import java.io.*;
import java.net.*;
import java.nio.charset.*;
import java.util.*;

@Component
public class YahooFinance_temp {
    private static String API_KEY = "vSxpavutu3a5L597NjkLW21B979DgPnF9yrWdrRk";


    /**
     * @param ticker - тикер компании
     * @return возвращает мапу со значениями
     */
    public Map<String, Object> getQuote(String ticker) {
        return null;
    }

    /**
     * @param tickers - типкеры компаний через запятую
     * @return возвращает список мап со значениями
     */
    public List<Map<String, Object>> getQuotes(String tickers) throws IOException {
        URL url = new URL("https://yfapi.net/v6/finance/quote?region=US&lang=en&symbols=" + tickers);

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
//            System.out.println(sb.toString());
            JacksonJsonParser s = new JacksonJsonParser();
            Map<String, Object> quoteMap = s.parseMap(sb.toString());
            Map<String, Object> maps = (Map<String, Object>) quoteMap.get("quoteResponse");
            return (List<Map<String, Object>>) maps.get("result");
        }
    }

    /**
     * @param tickers - сет из тикеров компаний
     * @return возвращает список мап со значениями
     */
    public List<Map<String, Object>> getQuotes(Set<String> tickers) throws IOException {

        String t = String.join(",", tickers);
        return getQuotes(t);
    }
}
