package com.example.finstrumental.controller;

import com.example.finstrumental.otherapi.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("companyData")
public class CompanyData {

    @GetMapping("{ticker}")
    public Map<String, String> getCompanyData(@PathVariable("ticker") String ticker) {
        return Finvizz.getCompanyData(ticker);
    }
}
