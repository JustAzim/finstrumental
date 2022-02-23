package com.example.finstrumental.magicformulainvesting;

import com.example.finstrumental.model.*;
import com.example.finstrumental.otherapi.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.*;

import java.io.*;
import java.util.*;

@SpringBootTest(classes = {Mfi.class})
public class Mfi_Test {
    @Autowired
    private Mfi mfi;

    @Test
    public void test() throws IOException {
        List<MFIdataModel> list = mfi.getData();
        System.out.println(list);
    }
}
