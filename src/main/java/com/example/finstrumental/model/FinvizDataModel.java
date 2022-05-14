package com.example.finstrumental.model;

import lombok.*;

@Data
public class FinvizDataModel {

    String ticker;

    String priceToEarn;

    String priceToSale;

    String debtEq;

    String price;

    String dividentYield;

    String grossMargin;

    String sector;

    String country;

    String insiderOwn;

    public FinvizDataModel(String ticker, String priceToEarn, String priceToSale, String debtEq, String price, String dividentYield, String grossMargin, String sector, String country, String insiderOwn) {
        this.ticker = ticker;
        this.priceToEarn = priceToEarn;
        this.priceToSale = priceToSale;
        this.debtEq = debtEq;
        this.price = price;
        this.dividentYield = dividentYield;
        this.grossMargin = grossMargin;
        this.sector = sector;
        this.country = country;
        this.insiderOwn = insiderOwn;
    }

}
