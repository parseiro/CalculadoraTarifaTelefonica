package com.example.showmeleonardo;

import com.example.showmeleonardo.service.DDD;
import com.example.showmeleonardo.service.PacoteLigueSempre60;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FaleMais60Test {
    @Test
    void test011to016_3minutos() {
        var plano = new PacoteLigueSempre60();

        assertEquals(BigDecimal.ZERO.setScale(2),
                plano.precoDaChamada(DDD.DDD_011, DDD.DDD_016, BigDecimal.valueOf(3)));
    }

    @Test
    void test011to016_fracaoDeMinutos() {
        var plano = new PacoteLigueSempre60();

        assertEquals(BigDecimal.valueOf(2.09),
                plano.precoDaChamada(DDD.DDD_011, DDD.DDD_016, BigDecimal.valueOf(60.4)));
    }

    @Test
    void test011to016_60minutos() {
        var plano = new PacoteLigueSempre60();

        assertEquals(BigDecimal.ZERO.setScale(2),
                plano.precoDaChamada(DDD.DDD_011, DDD.DDD_016, BigDecimal.valueOf(60)));
    }

    @Test
    void test011to016_61minutos() {
        var plano = new PacoteLigueSempre60();

        assertEquals(BigDecimal.valueOf(2.09),
                plano.precoDaChamada(DDD.DDD_011, DDD.DDD_016, BigDecimal.valueOf(61)));
    }
}

