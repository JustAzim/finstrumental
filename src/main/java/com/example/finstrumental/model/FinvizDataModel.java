package com.example.finstrumental.model;

import lombok.*;

@Data
public class FinvizDataModel {

    String ticker;

    String companyName;

    String marketCap;

    String priceToEarn;

    String priceToSale;

    String debtEq;

    String price;

    public FinvizDataModel(String ticker, String companyName, String marketCap, String priceToEarn, String priceToSale, String debtEq, String price) {
        this.ticker = ticker;
        this.companyName = companyName;
        this.marketCap = marketCap;
        this.priceToEarn = priceToEarn;
        this.priceToSale = priceToSale;
        this.debtEq = debtEq;
        this.price = price;
    }

}
