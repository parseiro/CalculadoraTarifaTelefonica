package com.example.showmeleonardo;

import com.example.showmeleonardo.service.DDD;
import com.example.showmeleonardo.service.FaleMais30;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FaleMais30Test {
    @Test
    void test011to016_3minutos() {
        var plano = new FaleMais30();

        assertEquals(BigDecimal.ZERO.setScale(2),
                plano.precoDaChamada(DDD.DDD_011, DDD.DDD_016, BigDecimal.valueOf(3)));
    }

    @Test
    void test011to016_fracaoDeMinutos() {
        var plano = new FaleMais30();

        assertEquals(BigDecimal.valueOf(2.09),
                plano.precoDaChamada(DDD.DDD_011, DDD.DDD_016, BigDecimal.valueOf(30.4)));
    }

    @Test
    void test011to016_30minutos() {
        var plano = new FaleMais30();

        assertEquals(BigDecimal.ZERO.setScale(2),
                plano.precoDaChamada(DDD.DDD_011, DDD.DDD_016, BigDecimal.valueOf(30)));
    }

    @Test
    void test011to016_31minutos() {
        var plano = new FaleMais30();

        assertEquals(BigDecimal.valueOf(2.09),
                plano.precoDaChamada(DDD.DDD_011, DDD.DDD_016, BigDecimal.valueOf(31)));
    }
}
