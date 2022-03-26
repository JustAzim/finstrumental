package com.example.finstrumental.otherapi;

import com.example.finstrumental.model.*;
import org.jsoup.*;
import org.jsoup.nodes.*;
import org.jsoup.select.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import java.io.*;
import java.util.*;

@Component
public class Mfi {

    final String loginFormUrl = "https://www.magicformulainvesting.com/Account/LogOn";

    final String getScreenUrl = "https://www.magicformulainvesting.com/Screening/StockScreening";

    @Value("${mfiusername}")
    private String username;

    @Value("${mfipassword}")
    private String password;

    Connection.Response authDoc;

    public List<MFIdataModel> getData(int cap) throws IOException {
        List<MFIdataModel> list = new ArrayList<>();
        Document doc = getMFIDoc(cap);
        Elements t = doc.select("table.screeningdata td");
        int i = 0;
        while (i < t.size()) {
            String name = t.get(i++).text();
            String ticker = t.get(i++).text();
            String mc = t.get(i++).text();
            String pf = t.get(i++).text();
            String mrqd = t.get(i++).text();
            MFIdataModel model = new MFIdataModel(name, ticker, mc, pf, mrqd);
            list.add(model);
        }
        return list;
    }

    public Document getMFIDoc(int cap) throws IOException {
        if(authDoc == null) {
            getAuthResponse();
        }
        //GET a document with post request
        try {
            Connection.Response document = Jsoup.connect(getScreenUrl)
                    .method(Connection.Method.POST)
                    .data("MinimumMarketCap", String.valueOf(cap))
                    .data("Select30", "false")
                    .data("stocks", "Get Stocks")
                    .cookies(authDoc.cookies())
                    .timeout(100000)
                    .execute();
            return document.parse();

        }
        catch (Exception e) {
            getAuthResponse();
            Connection.Response document = Jsoup.connect(getScreenUrl)
                    .method(Connection.Method.POST)
                    .data("MinimumMarketCap", String.valueOf(cap))
                    .data("Select30", "true")
                    .data("stocks", "Get Stocks")
                    .cookies(authDoc.cookies())
                    .timeout(100000)
                    .execute();
            return document.parse();
        }
    }

    private void getAuthResponse() throws IOException {
        // get login form
        Connection.Response loginForm = Jsoup.connect(loginFormUrl)
                .method(Connection.Method.GET)
                .execute();

        // POST login data
        authDoc = Jsoup.connect(loginFormUrl).
                method(Connection.Method.POST)
                .data("Email", username)
                .data("Password", password)
                .data("login", "Login")
                .cookies(loginForm.cookies())
                .timeout(100000)
                .execute();
    }
}
