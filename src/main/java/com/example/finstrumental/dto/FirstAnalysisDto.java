package com.example.finstrumental.dto;

import com.example.finstrumental.dto.yahooDto.YahooFundamental;
import lombok.Data;

import java.util.Map;

@Data
public class FirstAnalysisDto {

    private String ticker;

    private Map<String, YahooFundamental> fundamentals;

    private Double grotheRate;

    public FirstAnalysisDto(String ticker, Map<String, YahooFundamental> fundamentals, Double grotheRate) {
        this.ticker = ticker;
        this.fundamentals = fundamentals;
        this.grotheRate = grotheRate;
    }
}
