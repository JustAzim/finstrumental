package com.example.finstrumental.dto;

import lombok.*;

@Data
public class MFIdataDto {

    String companyName;

    String ticker;

    String marketCap;

    String priceFrom;

    String mostRecentQuarterData;

    public MFIdataDto(String companyName, String ticker, String marketCap, String priceFrom, String mostRecentQuarterData) {
        this.companyName = companyName;
        this.ticker = ticker;
        this.marketCap = marketCap;
        this.priceFrom = priceFrom;
        this.mostRecentQuarterData = mostRecentQuarterData;
    }

}
