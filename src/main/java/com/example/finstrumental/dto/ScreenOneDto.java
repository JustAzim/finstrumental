package com.example.finstrumental.dto;

import lombok.*;

@Data
public class ScreenOneDto {

    String ticker;

    String companyName;

    String marketCap;

    String mostRecentQuarterData;

    String priceToEarn;

    String priceToSale;

    String debtEq;

    String price;

    String dividentYield;

    String grossMargin;

    String sector;

    String country;

    String insiderOwn;

    public ScreenOneDto(MFIdataDto mfi, FinvizDataDto finvizz) {
        this.ticker = mfi.ticker;
        this.mostRecentQuarterData = mfi.mostRecentQuarterData;
        this.companyName = finvizz.companyName;
        this.marketCap = finvizz.marketCap;
        this.priceToSale = finvizz.priceToSale;
        this.priceToEarn = finvizz.priceToEarn;
        this.debtEq = finvizz.debtEq;
        this.price = finvizz.price;
        this.dividentYield = finvizz.dividentYield;
        this.grossMargin = finvizz.grossMargin;
        this.sector = finvizz.sector;
        this.country = finvizz.country;
        this.insiderOwn = finvizz.insiderOwn;
    }
}
