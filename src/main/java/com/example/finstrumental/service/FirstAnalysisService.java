package com.example.finstrumental.service;


import com.example.finstrumental.dto.FirstAnalysisDto;
import com.example.finstrumental.dto.yahooDto.YahooFundamental;
import com.example.finstrumental.otherapi.YahooFinance;
import com.example.finstrumental.otherapi.Ycharts;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class FirstAnalysisService {

    @NonNull
    private final YahooFinance yahooFinance;

    @NonNull
    private final Ycharts ycharts;

    public FirstAnalysisDto doAnslys(String ticker) throws IOException {
        Map<String, YahooFundamental> fundamentalIndexes = yahooFinance.getFundamentalIndexes(ticker);
        Double grotheRate = ycharts.getGrotheRate();
        FirstAnalysisDto res = new FirstAnalysisDto(ticker, fundamentalIndexes, grotheRate);
        return res;
    }

}
