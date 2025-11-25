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

    @GetMapping("/population/continent-cities")
    public String getContinentPopulationReport(Model model) {
        String output = populationReports.getContinentPopulationReport();
        model.addAttribute("title", "Continent Population Report");
        model.addAttribute("output", output);
        return "populationReport";
    }

    @GetMapping("/population/region-cities")
    public String getRegionPopulationReport(Model model) {
        String output = populationReports.getRegionPopulationReport();
        model.addAttribute("title", "Region Population Report");
        model.addAttribute("output", output);
        return "populationReport";
    }

    @GetMapping("/population/country-cities")
    public String getCountryPopulationReport(Model model) {
        String output = populationReports.getCountryPopulationReport();
        model.addAttribute("title", "Country Population Report");
        model.addAttribute("output", output);
        return "populationReport";
    }
}
