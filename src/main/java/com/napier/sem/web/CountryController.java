package com.napier.sem.web;

import com.napier.sem.countries.CountryReports;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CountryController {

    private final CountryReports countryReports = new CountryReports();

    @GetMapping("/countries")
    public String countryReport() {
        return "countryReport";
    }

    @GetMapping("/countries/all")
    public String getAllCountriesInWorldReport(Model model) {
        String output = countryReports.allCountriesInWorldReport();
        model.addAttribute("getAllCountriesInWorldReport", output);
        return "countryReport";
    }
}
