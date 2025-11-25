package com.napier.sem.web;

import com.napier.sem.populationReports.PopulationReports;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PopulationController {

    private final PopulationReports populationReports = new PopulationReports();

    @GetMapping("/population")
    public String populationReport() {
        return "populationReport";
    }

    @GetMapping("/population/all")
    public String getContinentPopulationReport(Model model) {
        String output = populationReports.getContinentPopulationReport();
        model.addAttribute("getContinentPopulationReport", output);
        return "populationReport";
    }
}
