package com.example.finstrumental.service;

import com.example.finstrumental.otherapi.*;
import lombok.*;
import org.springframework.stereotype.*;

import java.io.*;
import java.time.*;
import java.util.*;

@Component
@RequiredArgsConstructor
public class ChartService {

    @NonNull
    private final YahooFinance yahooFinance;

    public Map<String, Object> getDividents(String ticker) throws IOException {
        LocalDate dend = LocalDate.now();
        LocalDate dbeg = dend.minusYears(10);
        return yahooFinance.getDividents(ticker, dbeg, dend);
    }
}
