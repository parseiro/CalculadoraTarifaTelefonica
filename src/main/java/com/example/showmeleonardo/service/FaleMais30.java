package com.example.showmeleonardo.service;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class FaleMais30 extends FaleMais {
    public FaleMais30() {
        super(BigDecimal.valueOf(30));
    }
}
