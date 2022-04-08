package com.example.finstrumental.model;

import lombok.*;

@Data
public class MFIdataModel {

    String companyName;

    String ticker;

    String marketCap;

    String priceFrom;

    String mostRecentQuarterData;

    public MFIdataModel(String companyName, String ticker, String marketCap, String priceFrom, String mostRecentQuarterData) {
        this.companyName = companyName;
        this.ticker = ticker;
        this.marketCap = marketCap;
        this.priceFrom = priceFrom;
        this.mostRecentQuarterData = mostRecentQuarterData;
    }

}
