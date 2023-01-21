package com.example.finstrumental.controller;

import com.example.finstrumental.dto.DcfAnalysis;
import com.example.finstrumental.service.DcfAnalysisService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.text.ParseException;

@RestController
@RequestMapping("dcfAnalysis")
@RequiredArgsConstructor
public class DcfAnalysisRestController {

    @NonNull
    private final DcfAnalysisService dcfAnalysisService;

    @GetMapping("getFundamental/{ticker}")
    public ResponseEntity<DcfAnalysis> getFundamental(@PathVariable("ticker") String ticker) throws IOException, ParseException {
        return new ResponseEntity<>(dcfAnalysisService.doAnslys(ticker), HttpStatus.OK);
    }

}
