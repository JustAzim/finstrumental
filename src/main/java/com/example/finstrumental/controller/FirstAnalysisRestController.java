package com.example.finstrumental.controller;

import com.example.finstrumental.dto.FirstAnalysisDto;
import com.example.finstrumental.service.FirstAnalysisService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("firstAnalysis")
@RequiredArgsConstructor
public class FirstAnalysisRestController {

    @NonNull
    private final FirstAnalysisService firstAnalysisService;

    @GetMapping("getFundamental/{ticker}")
    public ResponseEntity<FirstAnalysisDto> getFundamental(@PathVariable("ticker") String ticker) throws IOException {
        return new ResponseEntity<>(firstAnalysisService.doAnslys(ticker), HttpStatus.OK);
    }

}
