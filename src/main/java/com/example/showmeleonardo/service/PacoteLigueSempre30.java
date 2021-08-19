package com.example.showmeleonardo.service;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class PacoteLigueSempre30 extends PacoteLigueSempre {
    public PacoteLigueSempre30() {
        super(BigDecimal.valueOf(30));
    }
}
