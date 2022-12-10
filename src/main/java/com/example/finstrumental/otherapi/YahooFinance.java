package com.example.finstrumental.otherapi;

import com.example.finstrumental.dto.yahooDto.YahooFundamental;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.json.*;
import org.springframework.stereotype.*;

import java.io.*;
import java.net.*;
import java.nio.charset.*;
import java.time.*;
import java.util.*;

@Component
public class YahooFinance {

    private final String URL_FUNDAMENTAL_API1 = "https://query2.finance.yahoo.com/ws/fundamentals-timeseries/v1/finance/timeseries/%s?lang=en-US&region=US&symbol=%s";
    private final String URL_FUNDAMENTAL_API2 = "&padTimeSeries=true&type=CannualReconciledCostOfRevenue%2CannualCostOfRevenue%2CannualReconciledDepreciation%2CannualTotalRevenue%2CannualCapitalExpenditure%2CannualEBIT%2CannualFreeCashFlow&merge=false&period1=493590046&period2=1667638858&corsDomain=finance.yahoo.com";


    public Map<String, YahooFundamental> getFundamentalIndexes(String ticker) throws IOException {
        URL url = new URL(String.format(URL_FUNDAMENTAL_API1, ticker, ticker) + URL_FUNDAMENTAL_API2);

        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonNode = mapper.readTree(url).get("timeseries").get("result");
        Map<String, YahooFundamental> fundamentals = new HashMap<>();
        int i = 0;

        while (jsonNode.get(i) != null) {
            JsonNode node = jsonNode.get(i);
            String type = node.get("meta").get("type").get(0).textValue();
            JsonNode indexes = node.get(type);
            int k = 0;
            while (indexes.get(k) != null) {
                JsonNode index = indexes.get(k);
                if(index.size() == 0) {
                    k++;
                    continue;
                }
                String dt = index.get("asOfDate").textValue();
                Double value = index.get("reportedValue").get("raw").doubleValue();

                if (fundamentals.containsKey(dt)) {
                    YahooFundamental fundamental = fundamentals.get(dt);
                    fillFundamentalByType(fundamental, type, value);
                } else {
                    YahooFundamental fundamental = new YahooFundamental();
                    fillFundamentalByType(fundamental, type, value);
                    fundamentals.put(dt, fundamental);
                }
                k++;
            }
            i++;
        }
        calcEbitda(fundamentals);
        return fundamentals;
    }

    public Map<String, Object> getDividents(String ticker, LocalDate dbeg, LocalDate dend) throws IOException {
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

    private void fillFundamentalByType(YahooFundamental fundamental, String type, Double value) {
        switch (type) {
            case "annualCostOfRevenue": fundamental.setCostOfRevenue(value); break;
            case "annualReconciledDepreciation": fundamental.setReconciledDepreciation(value); break;
            case "annualTotalRevenue": fundamental.setTotalRevenue(value); break;
            case "annualCapitalExpenditure": fundamental.setCapitalExpenditure(value); break;
            case "annualEBIT": fundamental.setEbit(value); break;
            case "annualFreeCashFlow": fundamental.setFreeCashFlow(value); break;
        }
    }

    private void calcEbitda(Map<String, YahooFundamental> fundamental) {
        fundamental.keySet().forEach((String key) -> {
            YahooFundamental f = fundamental.get(key);
            if(f.getEbit() != null &&  f.getReconciledDepreciation() != null) {
                f.setEbitda(f.getEbit() + f.getReconciledDepreciation());
            }
        });
    }
}
