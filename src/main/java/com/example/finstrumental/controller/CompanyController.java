package com.example.finstrumental.controller;

import com.example.finstrumental.dto.*;
import com.example.finstrumental.service.*;
import lombok.*;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.util.*;

@RestController
@RequestMapping("companyData")
@RequiredArgsConstructor
public class CompanyController {

    @NonNull
    private final CompanyService companyService;

    @GetMapping("{ticker}")
    public FinvizDataDto getCompanyData(@PathVariable("ticker") String ticker) throws IOException {
        return companyService.getCompanyData(ticker);
    }

    @GetMapping("getFiftyCompanies/{cap}")
    public List<ScreenOneDto> getFiftyCompanyByMarketCap(@PathVariable("cap") int cap) throws IOException {
        return companyService.getFiftyCompanyByMarketCap(cap);
    }

    @GetMapping("getCompanyDescription/{ticker}")
    public String getCompanyDescription(@PathVariable("ticker") String ticker) throws IOException {
        return companyService.getCompanyDescription(ticker);
    }

}
