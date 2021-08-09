package com.example.showmeleonardo.service;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class FaleMais120 extends FaleMais {
    public FaleMais120() {
        super(BigDecimal.valueOf(120));
    }
}
