package com.napier.sem.web;

import com.napier.sem.populationReports.PopulationReports;
import com.napier.sem.populationReports.SpecificPopulationReports;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

// Controller for handling population-related web requests
@Controller
public class PopulationController {

    // Instantiate the report classes
    private final PopulationReports populationReports = new PopulationReports();
    private final SpecificPopulationReports specificPopulationReports = new SpecificPopulationReports();

    // Endpoint to display the population report page
    @GetMapping("/population")
    public String populationReport() {
        return "populationReport";
    }

    // Endpoint to get population report of cities in continents
    @GetMapping("/population/continent-cities")
    public String getContinentPopulationReport(Model model) {
        String output = populationReports.getContinentPopulationReport();
        model.addAttribute("title", "Continent Population Report");
        model.addAttribute("output", output);
        return "populationReport";
    }

    // Endpoint to get population report of cities in regions
    @GetMapping("/population/region-cities")
    public String getRegionPopulationReport(Model model) {
        String output = populationReports.getRegionPopulationReport();
        model.addAttribute("title", "Region Population Report");
        model.addAttribute("output", output);
        return "populationReport";
    }

    // Endpoint to get population report of cities in countries
    @GetMapping("/population/country-cities")
    public String getCountryPopulationReport(Model model) {
        String output = populationReports.getCountryPopulationReport();
        model.addAttribute("title", "Country Population Report");
        model.addAttribute("output", output);
        return "populationReport";
    }

    // Endpoint to get world population report
    @GetMapping("/population/world")
    public String getWorldPopulationReport(Model model) {
        String output = specificPopulationReports.getPopulationReport("world", "");
        model.addAttribute("title", "World Population Report");
        model.addAttribute("output", output);
        return "populationReport";
    }

    // Endpoint to get continent specific population report
    @GetMapping("/population/continent")
    public String getContinentSpecificPopulationReport(@RequestParam("continent") String continent, Model model) {
        String output = specificPopulationReports.getPopulationReport("continent", continent);
        model.addAttribute("title", continent + " Continent Population Report");
        model.addAttribute("output", output);
        return "populationReport";
    }

    // Endpoint to get region specific population report
    @GetMapping("/population/region")
    public String getRegionSpecificPopulationReport(@RequestParam("region") String region, Model model) {
        String output = specificPopulationReports.getPopulationReport("region", region);
        model.addAttribute("title", region + " Region Population Report");
        model.addAttribute("output", output);
        return "populationReport";
    }
}
