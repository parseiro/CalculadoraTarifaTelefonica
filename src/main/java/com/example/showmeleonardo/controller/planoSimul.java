package com.example.showmeleonardo.controller;

import com.example.showmeleonardo.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.TreeSet;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/planos")
public class planoSimul {
    @Autowired
    PlanoBasico planoBasico;

    @Autowired
    PacoteLigueSempre30 faleMais30;

    @Autowired
    PacoteLigueSempre60 faleMais60;

    @Autowired
    PacoteLigueSempre120 faleMais120;

    @GetMapping(value = "/greet", produces = MediaType.TEXT_HTML_VALUE)
    @ResponseBody
    public String greetUser() {
        return "<html><p>Hello and welcome!</p></html>";
    }

    @GetMapping("hello")
    public String hello(Model modelo, @RequestParam(value = "name", required = false, defaultValue = "World") String name) {
        modelo.addAttribute("name", name);
        return "hello";
    }

    @GetMapping("/simular")
    public String simular(Model model,
                          @RequestParam(value = "DDDsource", required = true) DDD DDDsource,
                          @RequestParam(value = "DDDdestination", required = true) DDD DDDdestination,
                          @RequestParam(value = "minutes", required = true) BigDecimal minutes) {
        final Locale brazilLocale = new Locale("pt", "BR");
        final NumberFormat currencyFomatter = NumberFormat.getCurrencyInstance(brazilLocale);
        final NumberFormat numberFormatter = DecimalFormat.getNumberInstance();

        model.addAttribute("minutes", numberFormatter.format(minutes));
        model.addAttribute("DDDsource", DDDsource);
        model.addAttribute("DDDdestination", DDDdestination);
        model.addAttribute("custoPlanoBasico", currencyFomatter.format(planoBasico.precoDaChamada(DDDsource, DDDdestination, minutes)));

        PlanoDeChamadas planoSugerido;
        BigDecimal precoNoPlanoSugerido;
        {
            var precoNoFaleMais30 = new PacoteLigueSempre30().precoDaChamada(DDDsource, DDDdestination, minutes);
            var precoNoFaleMais60 = new PacoteLigueSempre60().precoDaChamada(DDDsource, DDDdestination, minutes);
            var precoNoFaleMais120 = new PacoteLigueSempre120().precoDaChamada(DDDsource, DDDdestination, minutes);

            var precos = new TreeSet<BigDecimal>();
            precos.add(precoNoFaleMais30);
            precos.add(precoNoFaleMais60);
            precos.add(precoNoFaleMais120);

            precoNoPlanoSugerido = precos.first();

            if (precoNoPlanoSugerido == precoNoFaleMais30) {
                planoSugerido = new PacoteLigueSempre30();
            } else if (precoNoPlanoSugerido == precoNoFaleMais60) {
                planoSugerido = new PacoteLigueSempre60();
            } else {
                planoSugerido = new PacoteLigueSempre120();
            }
        }

        /*PlanoDeChamadas planoSugerido = new FaleMais30();
        if (BigDecimal.valueOf(45).compareTo(minutes) <= 0) planoSugerido = new FaleMais60();
        else if (BigDecimal.valueOf(90).compareTo(minutes) <= 0) planoSugerido = new FaleMais120();*/
        model.addAttribute("planoSugerido", planoSugerido.getClass().getSimpleName());
        model.addAttribute("custoPlanoSugerido", currencyFomatter.format(precoNoPlanoSugerido));
        return "simulador";
    }

    @GetMapping("/iniciarSimulador")
    public String iniciarSimulador(Model model) {
        var valoresPorMinuto = PrecosTarifaBasica.getValoresPorMinuto();
        final var sourceDDDSet = new TreeSet<DDD>(
                valoresPorMinuto.parallelStream()
                        .map(x -> x.getSource())
                        .collect(Collectors.toUnmodifiableSet())
        );

        final var destinationDDDSet = new TreeSet<DDD>(
                valoresPorMinuto.parallelStream()
                        .map(x -> x.getDestination())
                        .collect(Collectors.toUnmodifiableSet())
        );


        //        model.addAttribute("valoresPorMinuto", PrecosTarifaBasica.getValoresPorMinuto());
        model.addAttribute("destinationDDD", destinationDDDSet);
        model.addAttribute("sourceDDD", sourceDDDSet);
        return "iniciarSimulador";
    }
}
