package com.example.showmeleonardo.service;

import lombok.NonNull;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

// O plano Básico usa a tarifa básica
@Service
public class PlanoBasico extends PlanoDeChamadas {
    @Override
    public BigDecimal precoDaChamada(@NonNull DDD source, @NonNull DDD destination, @NonNull BigDecimal minutos) {
        if (source == destination) return BigDecimal.ZERO;

        var precoPorMinuto = PrecosTarifaBasica.valorPorMinuto(source, destination);

        if (precoPorMinuto.isEmpty()) {
            throw new IllegalArgumentException("Origem x destino não encontrados");
        }

        BigDecimal minutosUp = minutos.setScale(0, RoundingMode.UP).setScale(2);

//        System.out.println("Multiplicando " + minutosUp + " (minutos) por " + precoPorMinuto.get());


        return minutosUp.multiply(precoPorMinuto.get()/*, new MathContext(4, RoundingMode.HALF_EVEN)*/).setScale(2);
    }
}
