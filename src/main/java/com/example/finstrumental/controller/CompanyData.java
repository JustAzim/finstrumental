package com.example.finstrumental.controller;

import com.example.finstrumental.model.*;
import com.example.finstrumental.otherapi.*;
import lombok.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.util.*;

@RestController
@RequestMapping("companyData")
@RequiredArgsConstructor
public class CompanyData {

    @NonNull
    private final Mfi mfi;

    @GetMapping("{ticker}")
    public Map<String, String> getCompanyData(@PathVariable("ticker") String ticker) {
        return Finvizz.getCompanyData(ticker);
    }

    @GetMapping("mfi/{cap}")
    public List<MFIdataModel> getMfiData(@PathVariable("cap") int cap) throws IOException {
        List<MFIdataModel> mfiDataList = mfi.getData(cap);
        for (MFIdataModel data : mfiDataList) {
            Map<String, String> companyData = Finvizz.getCompanyData(data.getTicker());
            data.addPrices(companyData.get(Finvizz.PriceToSale), companyData.get(Finvizz.PriceToEarn));
        }

        return mfiDataList;
    }
}
