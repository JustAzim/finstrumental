package com.example.finstrumental;

import org.jsoup.*;
import org.jsoup.nodes.*;
import org.jsoup.select.*;
import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.*;

import java.io.*;
import java.util.*;

@SpringBootTest
class FinstrumentalApplicationTests {

    @Test
    void contextLoads() {

        String url = "https://finviz.com/quote.ashx?t=TSLA";
        try {
            Document doc = Jsoup.connect(url).get();
            Elements t = doc.select("table.snapshot-table2 td");
            HashMap<String, String> map = new HashMap<>();
            int i = 0;
            while (i < t.size()) {
                String label = t.get(i).text();
                String value = t.get(i + 1).text();
                map.put(label, value);
                i += 2;
            }
            System.out.println(map);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
