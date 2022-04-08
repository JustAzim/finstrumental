package com.example.finstrumental.model;

import lombok.*;

@Data
public class FinvizDataModel {

    String ticker;

    String priceToEarn;

    String priceToSale;

    String debtEq;

    String price;

    public FinvizDataModel(String ticker, String priceToEarn, String priceToSale, String debtEq, String price) {
        this.ticker = ticker;
        this.priceToEarn = priceToEarn;
        this.priceToSale = priceToSale;
        this.debtEq = debtEq;
        this.price = price;
    }

}
