package com.example.finstrumental.controller;

import com.example.finstrumental.model.*;
import com.example.finstrumental.otherapi.*;
import lombok.*;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.util.*;

@RestController
@RequestMapping("companyData")
@RequiredArgsConstructor
public class CompanyData {

    @NonNull
    private final Mfi mfi;

    @NonNull
    private final YahooFinance yahooFinance;

    @GetMapping("{ticker}")
    public Map<String, String> getCompanyData(@PathVariable("ticker") String ticker) {
        return Finvizz.getCompanyData(ticker);
    }

    @GetMapping("mfi/{cap}")
    public List<MFIdataModel> getMfiData(@PathVariable("cap") int cap) throws IOException {
        List<Map<String, Object>> allQuotes = new LinkedList<>();
        Set<String> tickers = new HashSet<>();
        List<MFIdataModel> mfiDataList = mfi.getData(cap);
        for (MFIdataModel data : mfiDataList) {
            tickers.add(data.getTicker());
            if (tickers.size() >= 10) {
                List<Map<String, Object>> quotes = yahooFinance.getQuotes(tickers);
                allQuotes.addAll(quotes);
                tickers.clear();
            }
        }
        if (tickers.size() > 0) {
            List<Map<String, Object>> quotes = yahooFinance.getQuotes(tickers);
            allQuotes.addAll(quotes);
        }

        Iterator<MFIdataModel> mfiList = mfiDataList.iterator();
        MFIdataModel mfi = mfiList.next();
        for (Map<String, Object> data : allQuotes) {
            while (!mfi.getTicker().equals(data.get("symbol"))) {
                mfi = mfiList.next();
            }
            mfi.addPrices(data.get("epsForward").toString(), data.get("forwardPE").toString());
        }


        return mfiDataList;
    }
}
