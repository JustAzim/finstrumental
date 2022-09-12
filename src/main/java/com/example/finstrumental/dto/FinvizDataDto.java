package com.example.finstrumental.dto;

import lombok.*;

@Data
public class FinvizDataDto {

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

    public FinvizDataDto(String ticker, String companyName, String sector, String country, String marketCap, String priceToEarn, String priceToSale, String dividentYield, String insiderOwn, String debtEq, String grossMargin, String price) {
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
