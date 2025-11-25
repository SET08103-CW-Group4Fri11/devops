package com.napier.sem.web;

import com.napier.sem.cities.CityReports;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CityController {

    private final CityReports cityReports = new CityReports();

    @GetMapping("/cities")
    public String cityReport() {
        return "cityReport";
    }

    @GetMapping("/cities/all-world")
    public String getAllCitiesInWorldReport(Model model) {
        String output = cityReports.getAllCitiesInWorldReport();
        model.addAttribute("title", "All Cities in the World Report");
        model.addAttribute("output", output);
        return "cityReport";
    }

    @GetMapping("/cities/all-continent")
    public String getAllCitiesInContinentReport(@RequestParam("continent") String continent, Model model) {
        String output = cityReports.getAllCitiesInContinentReport(continent);
        model.addAttribute("title", "All Cities in " + continent + " Continent Report");
        model.addAttribute("output", output);
        return "cityReport";
    }

    @GetMapping("/cities/all-region")
    public String getAllCitiesInRegionReport(@RequestParam("region") String region, Model model) {
        String output = cityReports.getAllCitiesInRegionReport(region);
        model.addAttribute("title", "All Cities in " + region + " Region Report");
        model.addAttribute("output", output);
        return "cityReport";
    }

    @GetMapping("/cities/all-country")
    public String getAllCitiesInCountryReport(@RequestParam("country") String country, Model model) {
        String output = cityReports.getAllCitiesInCountryReport(country);
        model.addAttribute("title", "All Cities in " + country + " Country Report");
        model.addAttribute("output", output);
        return "cityReport";
    }

    @GetMapping("/cities/all-district")
    public String getAllCitiesInDistrictReport(@RequestParam("district") String district, Model model) {
        String output = cityReports.getAllCitiesInDistrictReport(district);
        model.addAttribute("title", "All Cities in " + district + " District Report");
        model.addAttribute("output", output);
        return "cityReport";
    }

    @GetMapping("/cities/n-world")
    public String getNCitiesInWorldReport(@RequestParam("n-w") int n, Model model) {
        String output = cityReports.getTopNCitiesInWorldReport(n);
        model.addAttribute("title", "Top " + n + " Cities in the World Report");
        model.addAttribute("output", output);
        return "cityReport";
    }

    @GetMapping("/cities/n-continent")
    public String getNCitiesInContinentReport(@RequestParam("n-c") int n, @RequestParam("continent-n") String continent, Model model) {
        String output = cityReports.getTopNCitiesInContinentReport(n, continent);
        model.addAttribute("title", "Top " + n + " Cities in " + continent + " Continent Report");
        model.addAttribute("output", output);
        return "cityReport";
    }

    @GetMapping("/cities/n-region")
    public String getNCitiesInRegionReport(@RequestParam("n-r") int n, @RequestParam("region-n") String region, Model model) {
        String output = cityReports.getTopNCitiesInRegionReport(n, region);
        model.addAttribute("title", "Top " + n + " Cities in " + region + " Region Report");
        model.addAttribute("output", output);
        return "cityReport";
    }

    @GetMapping("/cities/n-country")
    public String getNCitiesInCountryReport(@RequestParam("n-ct") int n, @RequestParam("country-n") String country, Model model) {
        String output = cityReports.getTopNCitiesInCountryReport(n, country);
        model.addAttribute("title", "Top " + n + " Cities in " + country + " Country Report");
        model.addAttribute("output", output);
        return "cityReport";
    }

    @GetMapping("/cities/n-district")
    public String getNCitiesInDistrictReport(@RequestParam("n-d") int n, @RequestParam("district-n") String district, Model model) {
        String output = cityReports.getTopNCitiesInDistrictReport(n, district);
        model.addAttribute("title", "Top " + n + " Cities in " + district + " District Report");
        model.addAttribute("output", output);
        return "cityReport";
    }
}
