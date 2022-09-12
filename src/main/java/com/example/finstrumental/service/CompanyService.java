package com.example.finstrumental.service;

import com.example.finstrumental.dto.*;
import com.example.finstrumental.otherapi.*;
import lombok.*;
import org.springframework.stereotype.*;

import java.io.*;
import java.util.*;

@Service
@RequiredArgsConstructor
public class CompanyService {
    @NonNull
    private final Mfi mfi;

    @NonNull
    private final Finvizz finvizz;

    public List<ScreenOneDto> getFiftyCompanyByMarketCap(int cap) throws IOException {
        List<FinvizDataDto> allQuotes = new LinkedList<>();
        Set<String> tickers = new HashSet<>();
        List<MFIdataDto> mfiDataList = mfi.getData(cap);
        for (MFIdataDto data : mfiDataList) {
            tickers.add(data.getTicker());
            if (tickers.size() >= 20) {
                List<FinvizDataDto> quotes = finvizz.getQuotes(tickers);
                allQuotes.addAll(quotes);
                tickers.clear();
            }
        }
        if (tickers.size() > 0) {
            List<FinvizDataDto> quotes = finvizz.getQuotes(tickers);
            allQuotes.addAll(quotes);
        }
        LinkedList<ScreenOneDto> viewModelList = new LinkedList<>();
        for (FinvizDataDto quotes : allQuotes) {
            for (MFIdataDto mfi : mfiDataList) {
                if (mfi.getTicker().equals(quotes.getTicker())) {
                    ScreenOneDto model = new ScreenOneDto(mfi, quotes);
                    viewModelList.add(model);
                    break;
                }
            }
        }


        return viewModelList;
    }

    public FinvizDataDto getCompanyData(String ticker) throws IOException {
        return finvizz.getQuotes(ticker).get(0);
    }

    public String getCompanyDescription(String ticker) throws IOException {
        return finvizz.getCompanyDescription(ticker);
    }
}
