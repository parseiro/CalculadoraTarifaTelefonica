package com.example.showmeleonardo.service;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;

// A classe abstrata que permite o uso de todos os service
@Service
public abstract class PlanoDeChamadas {
    public abstract BigDecimal precoDaChamada(DDD source, DDD destination, BigDecimal minutos);
}



