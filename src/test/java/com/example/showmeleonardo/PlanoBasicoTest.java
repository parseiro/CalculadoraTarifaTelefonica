package com.example.showmeleonardo;

import com.example.showmeleonardo.service.DDD;
import com.example.showmeleonardo.service.PlanoBasico;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PlanoBasicoTest {
    @Test
    void test011to016_3minutos() {
        var planoBasico = new PlanoBasico();

        assertEquals(BigDecimal.valueOf(5.70).setScale(2),
                planoBasico.precoDaChamada(DDD.DDD_011, DDD.DDD_016, BigDecimal.valueOf(3)));
    }

    @Test
    void test011to016_fracaoDeMinutos() {
        var planoBasico = new PlanoBasico();

        assertEquals(BigDecimal.valueOf(7.60).setScale(2),
                planoBasico.precoDaChamada(DDD.DDD_011, DDD.DDD_016, BigDecimal.valueOf(3.4)));
    }

    @Test
    void test011to017_65minutos() {
        var planoBasico = new PlanoBasico();

        assertEquals(BigDecimal.valueOf(110.50).setScale(2),
                planoBasico.precoDaChamada(DDD.DDD_011, DDD.DDD_017, BigDecimal.valueOf(65)));
    }
}
