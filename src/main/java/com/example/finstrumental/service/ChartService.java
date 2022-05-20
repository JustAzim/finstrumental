package com.example.finstrumental.service;

import com.example.finstrumental.otherapi.*;
import lombok.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.time.*;
import java.util.*;

@Service
@RequiredArgsConstructor
public class ChartService {

    @NonNull
    private final YahooFinance yahooFinance;

    @NonNull
    private final Finvizz finvizz;

    public Map<String, Object> getDividents(String ticker) throws IOException {
        LocalDate dend = LocalDate.now();
        LocalDate dbeg = dend.minusYears(10);
        return yahooFinance.getDividents(ticker, dbeg, dend);
    }

    public Map<String, Object> getStatements(String ticker) throws IOException {
        return finvizz.getIncomeStatement(ticker);
    }

    public Map<String, Object> getBalanceSheet(String ticker) throws IOException {
        return finvizz.getBalanceSheet(ticker);
    }

    public Map<String, Object> GetCashFlow(String ticker) throws IOException {
        return finvizz.getCashFlow(ticker);
    }
}
