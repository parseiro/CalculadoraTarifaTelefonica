package com.example.showmeleonardo.service;

import lombok.Getter;
import lombok.NonNull;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class PrecosTarifaBasica {
    @Getter
    private DDD source;

    @Getter
    private DDD destination;

    @Getter
    private BigDecimal priceByMinute;

    public PrecosTarifaBasica(@NonNull DDD source, @NonNull DDD destination, @NonNull BigDecimal priceByMinute) {
        this.source = source;
        this.destination = destination;
        this.priceByMinute = priceByMinute;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PrecosTarifaBasica dupla = (PrecosTarifaBasica) o;
        return source == dupla.source && destination == dupla.destination;
    }

    @Override
    public int hashCode() {
        return Objects.hash(source, destination);
    }

    @Getter
    static private Set<PrecosTarifaBasica> valoresPorMinuto = Set.of(
            new PrecosTarifaBasica(DDD.DDD_011, DDD.DDD_016, BigDecimal.valueOf(1.90)),
            new PrecosTarifaBasica(DDD.DDD_016, DDD.DDD_011, BigDecimal.valueOf(2.90)),
            new PrecosTarifaBasica(DDD.DDD_011, DDD.DDD_017, BigDecimal.valueOf(1.70)),
            new PrecosTarifaBasica(DDD.DDD_017, DDD.DDD_011, BigDecimal.valueOf(2.70)),
            new PrecosTarifaBasica(DDD.DDD_011, DDD.DDD_018, BigDecimal.valueOf(0.90)),
            new PrecosTarifaBasica(DDD.DDD_018, DDD.DDD_011, BigDecimal.valueOf(1.90))
    );

    public static Optional<BigDecimal> valorPorMinuto(@NonNull DDD source, @NonNull DDD destination) {
        var precoPorMinuto = PrecosTarifaBasica.getValoresPorMinuto().parallelStream()
                .filter(d -> d.getSource() == source && d.getDestination() == destination)
                .findAny();

        return precoPorMinuto.map(p -> p.getPriceByMinute());
    }
}
