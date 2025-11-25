// java
package com.napier.sem.web;

import com.napier.sem.populationReports.PopulationReports;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ReportController {

    private final PopulationReports populationReports = new PopulationReports();

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/population/continent")
    public String continentReport(Model model) {
        String output = populationReports.getContinentPopulationReport();
        model.addAttribute("reportText", output);
        return "populationReport";
    }
}
