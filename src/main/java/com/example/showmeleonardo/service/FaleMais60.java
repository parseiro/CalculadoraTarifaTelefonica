package com.example.showmeleonardo.service;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class FaleMais60 extends FaleMais {
    public FaleMais60() {
        super(BigDecimal.valueOf(60));
    }
}
