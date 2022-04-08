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
    private final Finvizz finvizz;

    @GetMapping("{ticker}")
    public Map<String, String> getCompanyData(@PathVariable("ticker") String ticker) {
        return finvizz.getCompanyData(ticker);
    }

    @GetMapping("mfi/{cap}")
    public List<ScreenOneModel> getMfiData(@PathVariable("cap") int cap) throws IOException {
        List<FinvizDataModel> allQuotes = new LinkedList<>();
        Set<String> tickers = new HashSet<>();
        List<MFIdataModel> mfiDataList = mfi.getData(cap);
        for (MFIdataModel data : mfiDataList) {
            tickers.add(data.getTicker());
            if (tickers.size() >= 20) {
                List<FinvizDataModel> quotes = finvizz.getQuotes(tickers);
                allQuotes.addAll(quotes);
                tickers.clear();
            }
        }
        if (tickers.size() > 0) {
            List<FinvizDataModel> quotes = finvizz.getQuotes(tickers);
            allQuotes.addAll(quotes);
        }
        LinkedList<ScreenOneModel> viewModelList = new LinkedList<>();
        for (FinvizDataModel quotes : allQuotes) {
            for (MFIdataModel mfi : mfiDataList) {
                if (mfi.getTicker().equals(quotes.getTicker())) {
                    ScreenOneModel model = new ScreenOneModel(mfi, quotes);
                    viewModelList.add(model);
                    break;
                }
            }
        }


        return viewModelList;
    }
}
