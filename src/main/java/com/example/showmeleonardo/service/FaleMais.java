package com.example.showmeleonardo.service;

import lombok.NonNull;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class FaleMais extends PlanoDeChamadas {
    private BigDecimal minutosDoPlano;

    public FaleMais(BigDecimal minutosDoPlano) {
        this.minutosDoPlano = minutosDoPlano;
    }

    @Override
    public BigDecimal precoDaChamada(@NonNull DDD source, @NonNull DDD destination, @NonNull BigDecimal minutos) {
        if (minutos == BigDecimal.ZERO) {
            return BigDecimal.ZERO.setScale(2);
        }

        BigDecimal minutosUp = minutos.setScale(0, RoundingMode.UP);

        BigDecimal minutosExcedentes;
        if (minutosUp.compareTo(minutosDoPlano) > 0) {
            minutosExcedentes = minutosUp.subtract(minutosDoPlano);
        } else {
            minutosExcedentes = BigDecimal.ZERO;
        }

        if (minutosExcedentes == BigDecimal.ZERO) {
            return BigDecimal.ZERO.setScale(2);
        }

        BigDecimal precoPorMinutoBasico = PrecosTarifaBasica.valorPorMinuto(source, destination).get();

        // adiciona 10% no valor do minuto da tarifa básica
        BigDecimal precoPorMinutoExcedente = BigDecimal.valueOf(1.10).multiply(precoPorMinutoBasico);

        var valor = minutosExcedentes.multiply(precoPorMinutoExcedente, new MathContext(0, RoundingMode.HALF_EVEN)).setScale(2);

//        System.out.println("Multiplicando " + minutosExcedentes + " (minutos) por " + precoPorMinutoExcedente + ": " + valor);

        return valor;
    }
}
