package com.example.finstrumental.otherapi;

import com.example.finstrumental.dto.FinvizDataDto;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.LinkedList;

@Component
public class Ycharts {

    public Double getGrotheRate() throws IOException {
        String screenerURL = "https://ycharts.com/indicators/10_year_treasury_rate";
        Connection.Response document = Jsoup.connect(screenerURL)
                .method(Connection.Method.GET)
                .timeout(100000)
                .execute();
        Document doc = document.parse();
        LinkedList<FinvizDataDto> list = new LinkedList<>();

        Elements t = doc.select("div.key-stat-title");
        String res = t.get(0).text();
        String grotheRate = res.split("%")[0];

        return Double.parseDouble(grotheRate)/100;
    }

}
