package com.example.finstrumental.model;

import lombok.*;

@Data
public class ScreenOneModel {

    String ticker;

    String companyName;

    String marketCap;

    String priceFrom;

    String mostRecentQuarterData;

    String priceToEarn;

    String priceToSale;

    String debtEq;

    String price;

    public ScreenOneModel(MFIdataModel mfi, FinvizDataModel finvizz) {
        this.ticker = mfi.ticker;
        this.companyName = mfi.companyName;
        this.marketCap = mfi.marketCap;
        this.priceFrom = mfi.priceFrom;
        this.mostRecentQuarterData = mfi.mostRecentQuarterData;
        this.priceToSale = finvizz.priceToSale;
        this.priceToEarn = finvizz.priceToEarn;
        this.debtEq = finvizz.debtEq;
        this.price = finvizz.price;
    }
}
