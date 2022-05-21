package com.example.finstrumental.model;

import lombok.*;

@Data
public class FinvizDataModel {

    String ticker;

    String companyName;

    String sector;

    String country;

    String marketCap;

    String priceToEarn;

    String priceToSale;

    String dividentYield;

    String insiderOwn;

    String debtEq;

    String grossMargin;

    String price;

    public FinvizDataModel(String ticker, String companyName, String sector, String country, String marketCap, String priceToEarn, String priceToSale, String dividentYield, String insiderOwn, String debtEq, String grossMargin, String price) {
        this.ticker = ticker;
        this.companyName = companyName;
        this.sector = sector;
        this.country = country;
        this.marketCap = marketCap;
        this.priceToEarn = priceToEarn;
        this.priceToSale = priceToSale;
        this.dividentYield = dividentYield;
        this.insiderOwn = insiderOwn;
        this.debtEq = debtEq;
        this.grossMargin = grossMargin;
        this.price = price;
    }

}
