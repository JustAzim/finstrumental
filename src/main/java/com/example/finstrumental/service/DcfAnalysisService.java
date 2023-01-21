package com.example.finstrumental.service;


import com.example.finstrumental.dto.DcfAnalysis;
import com.example.finstrumental.dto.yahooDto.YahooFundamental;
import com.example.finstrumental.otherapi.YahooFinance;
import com.example.finstrumental.otherapi.Ycharts;
import com.example.finstrumental.utils.DateFormatterUtils;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.ParseException;
import java.util.*;

@Service
@RequiredArgsConstructor
public class DcfAnalysisService {

    @NonNull
    private final YahooFinance yahooFinance;

    @NonNull
    private final Ycharts ycharts;

    public DcfAnalysis doAnslys(String ticker) throws IOException, ParseException {
        Map<String, YahooFundamental> fundamentalIndexes = yahooFinance.getFundamentalIndexes(ticker);
        Double grotheRate = ycharts.getGrotheRate();
        DcfAnalysis res = new DcfAnalysis(ticker, fundamentalIndexes, grotheRate);
        analysData(res);
        return res;
    }

    private void analysData(DcfAnalysis dcfAnalysis) throws ParseException {
        calcGrotheRate(dcfAnalysis.getFundamentals());
        calcAmortizationAndCapex(dcfAnalysis);

        calcForecastData(dcfAnalysis);

        calcEbitda(dcfAnalysis.getFundamentals());
    }

    private void calcEbitda(Map<String, YahooFundamental> fundamental) {
        fundamental.keySet().forEach((String key) -> {
            YahooFundamental f = fundamental.get(key);
            if(f.getEbit() != null &&  f.getReconciledDepreciation() != null) {
                f.setEbitda(f.getEbit() + f.getReconciledDepreciation());
            }
        });
    }

    private void calcGrotheRate(Map<String, YahooFundamental> fundamental) {
        List<String> datesList = new ArrayList(fundamental.keySet());
        Collections.sort(datesList);
        for(int i = fundamental.size() - 1; i > 0; i--) {

            String curDtKey = datesList.get(i);
            String prevDtKey = datesList.get(i - 1);

            Double curTR = fundamental.get(curDtKey).getTotalRevenue();
            Double prevTR = fundamental.get(prevDtKey).getTotalRevenue();
            if(curTR != null && prevTR != null) {
                Double gr = (curTR - prevTR)/ prevTR;
                if(gr != null) {
                    fundamental.get(curDtKey).setGrotheRate(gr);
                }
            }
        }
    }

    private void calcAmortizationAndCapex(DcfAnalysis dcfAnalysis) {
        Map<String, YahooFundamental> fundamental = dcfAnalysis.getFundamentals();
        List<String> datesList = new ArrayList(fundamental.keySet());
        Collections.sort(datesList);

        List<Double> amortizationList = new ArrayList();
        List<Double> totalRevenueList = new ArrayList();
        List<Double> capexList = new ArrayList<>();
        datesList.forEach((dt) -> {
            YahooFundamental yahooFundamental = fundamental.get(dt);
            if(Objects.nonNull(yahooFundamental.getReconciledDepreciation()))
                amortizationList.add(yahooFundamental.getReconciledDepreciation());
            if(Objects.nonNull(yahooFundamental.getTotalRevenue()))
                totalRevenueList.add(yahooFundamental.getTotalRevenue());
            if(Objects.nonNull(yahooFundamental.getCapitalExpenditure()))
                capexList.add(yahooFundamental.getCapitalExpenditure());

        });

        Double avgAmortization = getAvg(amortizationList);
        Double avgTotalRevenue = getAvg(totalRevenueList);

        if(Objects.nonNull(avgAmortization) && Objects.nonNull(avgTotalRevenue)) {
            dcfAnalysis.setReconciledDepreciation(avgAmortization/avgTotalRevenue);
        }

        Double avgCapex = getAvg(capexList);
        if(Objects.nonNull(avgCapex)) {
            dcfAnalysis.setCapitalExpenditure(avgCapex);
        }

    }

    private void calcForecastData(DcfAnalysis dcfAnalysis) throws ParseException {
        Map<String, YahooFundamental> fundamental = dcfAnalysis.getFundamentals();
        //adding forecast dates
        List<String> datesList = new ArrayList(fundamental.keySet());
        Collections.sort(datesList);

        List<String> forecastedDatesList = new ArrayList<>();
        String lastDtString = datesList.get(datesList.size() - 1);
        Date lastDt = DateFormatterUtils.getDateFromString(lastDtString);
        for (int i = 1; i <= 5; i++) {
            lastDt.setYear(lastDt.getYear() + 1);
            String newDt = DateFormatterUtils.getStringDateFromDate(lastDt);
            forecastedDatesList.add(newDt);
            fundamental.put(newDt, new YahooFundamental());
        }

        //calc all indexes

        //collect list of grotheRate, amortization
        List<Double> grList = new ArrayList<>();
        List<Double> capexList = new ArrayList<>();
        datesList.forEach((dt) -> {
            Double gr = fundamental.get(dt).getGrotheRate();
            if(gr != null) {
                grList.add(gr);
            }
        });

        Double avgGrotheRate = getAvg(grList);

        for(int i = 0; i < forecastedDatesList.size(); i++) {
            YahooFundamental yahooFundamental = fundamental.get(forecastedDatesList.get(i));

            //grotherate
            if(i < 2) {
                yahooFundamental.setGrotheRate(avgGrotheRate);
            } else {
                yahooFundamental.setGrotheRate(dcfAnalysis.getGrotheRate());
            }
            //totalRevenue
            Double prevTr = i == 0 ?
                    fundamental.get(lastDtString).getTotalRevenue() :
                    fundamental.get(forecastedDatesList.get(i - 1)).getTotalRevenue();
            if(Objects.nonNull(prevTr)) {
                Double totalRevenue = prevTr * (1 + yahooFundamental.getGrotheRate());
                yahooFundamental.setTotalRevenue(totalRevenue);
            }
            //Cost Of Revenue
            Double prevCr = i == 0 ?
                    fundamental.get(lastDtString).getCostOfRevenue() :
                    fundamental.get(forecastedDatesList.get(i - 1)).getCostOfRevenue();
            if(Objects.nonNull(prevTr) && Objects.nonNull(prevCr) && Objects.nonNull(yahooFundamental.getTotalRevenue())) {
                Double costOfRevenue = (prevCr / prevTr) * yahooFundamental.getTotalRevenue();
                yahooFundamental.setCostOfRevenue(costOfRevenue);
            }

            //Amortization
            if(Objects.nonNull(yahooFundamental.getTotalRevenue()) && Objects.nonNull(dcfAnalysis.getReconciledDepreciation())) {
                yahooFundamental.setReconciledDepreciation(yahooFundamental.getTotalRevenue() * dcfAnalysis.getReconciledDepreciation());
            }

            //Ebit
            if(Objects.nonNull(yahooFundamental.getTotalRevenue()) && Objects.nonNull(yahooFundamental.getCostOfRevenue())) {
                Double ebit = yahooFundamental.getTotalRevenue() - yahooFundamental.getCostOfRevenue();
                yahooFundamental.setEbit(ebit);
            }

            //CAPEX
            yahooFundamental.setCapitalExpenditure(dcfAnalysis.getCapitalExpenditure());
        }
    }

    private Double getAvg(List<Double> list) {
        OptionalDouble average = list
                .stream()
                .mapToDouble(a -> a)
                .average();
        return average.isPresent() ? average.getAsDouble() : 0;
    }

}
