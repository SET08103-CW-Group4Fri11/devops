package com.napier.sem.web;

import com.napier.sem.capital.CapitalReports;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

// Controller for handling capital-related web requests
@Controller
public class CapitalController {

    private final CapitalReports capitalReports = new CapitalReports();

    // Endpoint to display the capital report page
    @GetMapping("/capitals")
    public String capitalReport() {
        return "capitalReport";
    }

    // Endpoint to get all capitals in the world report
    @GetMapping("/capitals/all-world")
    public String getAllCountriesInWorldReport(Model model) {
        String output = capitalReports.getAllCapitalsWorldReport();
        model.addAttribute("title", "All Capitals in the World Report");
        model.addAttribute("output", output);
        return "capitalReport";
    }

    // Endpoint to get all capitals in a specific continent report
    @GetMapping("/capitals/all-continent")
    public String getAllCapitalsInContinentReport(@RequestParam("continent") String continent, Model model) {
        String output = capitalReports.getAllCapitalsInContinentReport(continent);
        model.addAttribute("title", "All Capitals in " + continent + " Continent Report");
        model.addAttribute("output", output);
        return "capitalReport";
    }

    // Endpoint to get all capitals in a specific region report
    @GetMapping("/capitals/all-region")
    public String getAllCapitalsInRegionReport(@RequestParam("region") String region, Model model) {
        String output = capitalReports.getAllCapitalsInRegionReport(region);
        model.addAttribute("title", "All Capitals in " + region + " Region Report");
        model.addAttribute("output", output);
        return "capitalReport";
    }

    // Endpoint to get top N capitals in the world report
    @GetMapping("/capitals/n-world")
    public String getNCapitalsInWorldReport(@RequestParam("n-w") int n, Model model) {
        String output = capitalReports.getTopNCapitalsWorldReport(n);
        model.addAttribute("title", "Top " + n + " Capitals in the World Report");
        model.addAttribute("output", output);
        return "capitalReport";
    }

    // Endpoint to get top N capitals in a specific continent report
    @GetMapping("/capitals/n-continent")
    public String getNCapitalsInContinentReport(@RequestParam("n-c") int n, @RequestParam("continent-n") String continent, Model model) {
        String output = capitalReports.getTopNCapitalsInContinentReport(continent, n);
        model.addAttribute("title", "Top " + n + " Capitals in " + continent + " Continent Report");
        model.addAttribute("output", output);
        return "capitalReport";
    }

    // Endpoint to get top N capitals in a specific region report
    @GetMapping("/capitals/n-region")
    public String getNCapitalsInRegionReport(@RequestParam("n-r") int n, @RequestParam("region-n") String region, Model model) {
        String output = capitalReports.getTopNCapitalsInRegionReport(region, n);
        model.addAttribute("title", "Top " + n + " Capitals in " + region + " Region Report");
        model.addAttribute("output", output);
        return "capitalReport";
    }
}
