package com.napier.sem.web;

import com.napier.sem.cities.CityReports;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CityController {

    private final CityReports cityReports = new CityReports();

    @GetMapping("/cities")
    public String cityReport() {
        return "cityReport";
    }

    @GetMapping("/cities/all")
    public String getAllCitiesInWorldReport(Model model) {
        String output = cityReports.getAllCitiesInWorldReport();
        model.addAttribute("getAllCitiesInWorldReport", output);
        return "cityReport";
    }
}
