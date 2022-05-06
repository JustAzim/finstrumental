package com.example.finstrumental.otherapi;

import org.springframework.boot.json.*;
import org.springframework.stereotype.*;

import java.io.*;
import java.net.*;
import java.nio.charset.*;
import java.time.*;
import java.util.*;

@Component
public class YahooFinance {

    public Map<String, Object> getDivedents(String ticker, LocalDate dbeg, LocalDate dend) throws IOException {
//        String ticker = "HPQ";
//        LocalDate dend = LocalDate.now();
//        LocalDate dbeg = dend.minusYears(10);
        String period2 = String.valueOf(dend.toEpochSecond(LocalTime.of(0,0), ZoneOffset.ofHours(0)));
        String period1 = String.valueOf(dbeg.toEpochSecond(LocalTime.of(0,0), ZoneOffset.ofHours(0)));

        URL url = new URL("https://query2.finance.yahoo.com/v8/finance/chart/" + ticker + "?formatted=true&crumb=kclwR%2FDZymg&lang=en-US&region=US&includeAdjustedClose=true&interval=1d&period1=" + period1 + "&period2=" + period2 + "&events=capitalGain%7Cdiv%7Csplit&useYfid=true&corsDomain=finance.yahoo.com");

        HttpURLConnection http = (HttpURLConnection) url.openConnection();
        http.setRequestMethod("GET");
        http.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");
        http.setRequestProperty("Accept", "application/json");

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
            Map<String, Object> res = s.parseMap(sb.toString());
            return res;
        }
    }
}
