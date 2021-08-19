package com.example.showmeleonardo.service;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class PacoteLigueSempre60 extends PacoteLigueSempre {
    public PacoteLigueSempre60() {
        super(BigDecimal.valueOf(60));
    }
}
