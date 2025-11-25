package com.napier.sem.web;

import com.napier.sem.countries.CountryReports;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CountryController {

    private final CountryReports countryReports = new CountryReports();

    @GetMapping("/countries")
    public String countryReport() {
        return "countryReport";
    }

    @GetMapping("/countries/all-world")
    public String getAllCountriesInWorldReport(Model model) {
        String output = countryReports.allCountriesInWorldReport();
        model.addAttribute("title", "All Countries in the World Report");
        model.addAttribute("output", output);
        return "countryReport";
    }

    @GetMapping("/countries/all-continent")
    public String getAllCountriesInContinentReport(@RequestParam("continent") String continent, Model model) {
        String output = countryReports.allCountriesInContinentReport(continent);
        model.addAttribute("title", "All Countries in " + continent + " Continent Report");
        model.addAttribute("output", output);
        return "countryReport";
    }

    @GetMapping("/countries/all-region")
    public String getAllCountriesInRegionReport(@RequestParam("region") String region, Model model) {
        String output = countryReports.allCountriesInRegionReport(region);
        model.addAttribute("title", "All Countries in " + region + " Region Report");
        model.addAttribute("output", output);
        return "countryReport";
    }

    @GetMapping("/countries/n-world")
    public String getNCountriesInWorldReport(@RequestParam("n-w") int n, Model model) {
        String output = countryReports.topNCountriesInWorldReport(n);
        model.addAttribute("title", "Top " + n + " Countries in the World Report");
        model.addAttribute("output", output);
        return "countryReport";
    }

    @GetMapping("/countries/n-continent")
    public String getNCountriesInContinentReport(@RequestParam("n-c") int n, @RequestParam("continent-n") String continent, Model model) {
        String output = countryReports.topNCountriesInContinentReport(n, continent);
        model.addAttribute("title", "Top " + n + " Countries in " + continent + " Continent Report");
        model.addAttribute("output", output);
        return "countryReport";
    }

    @GetMapping("/countries/n-region")
    public String getNCountriesInRegionReport(@RequestParam("n-r") int n, @RequestParam("region-n") String region, Model model) {
        String output = countryReports.topNCountriesInRegionReport(n, region);
        model.addAttribute("title", "Top " + n + " Countries in " + region + " Region Report");
        model.addAttribute("output", output);
        return "countryReport";
    }
}
