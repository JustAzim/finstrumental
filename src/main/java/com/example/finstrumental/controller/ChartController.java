package com.example.finstrumental.controller;

import com.example.finstrumental.service.*;
import lombok.*;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.util.*;

@RestController
@RequestMapping("chart")
@RequiredArgsConstructor
public class ChartController {

    @NonNull
    private final ChartService chartService;

    @GetMapping("getStatements/{ticker}")
    public Map<String, Object> getStatements(@PathVariable("ticker") String ticker) throws IOException {
        return chartService.getStatements(ticker);
    }

    @GetMapping("getBalanceSheet/{ticker}")
    public Map<String, Object> getBalanceSheet(@PathVariable("ticker") String ticker) throws IOException {
        return chartService.getBalanceSheet(ticker);
    }

    @GetMapping("getCashFlow/{ticker}")
    public Map<String, Object> GetCashFlow(@PathVariable("ticker") String ticker) throws IOException {
        return chartService.GetCashFlow(ticker);
    }

    @GetMapping("getDividents/{ticker}")
    public Map<String, Object> GetDividents(@PathVariable("ticker") String ticker) throws IOException {
        return chartService.getDividents(ticker);
    }

}
