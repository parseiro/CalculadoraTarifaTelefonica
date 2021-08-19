package com.example.showmeleonardo.service;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class PacoteLigueSempre120 extends PacoteLigueSempre {
    public PacoteLigueSempre120() {
        super(BigDecimal.valueOf(120));
    }
}
