package com.napier.sem.web;

import com.napier.sem.capital.CapitalReports;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CapitalController {

    private final CapitalReports capitalReports = new CapitalReports();

    @GetMapping("/capitals")
    public String capitalReport() {
        return "capitalReport";
    }

    @GetMapping("/capitals/all")
    public String getAllCountriesInWorldReport(Model model) {
        String output = capitalReports.getAllCapitalsWorldReport();
        model.addAttribute("getAllCapitalsWorldReport", output);
        return "capitalReport";
    }
}
