package com.napier.sem.web;

import com.napier.sem.languages.LanguageReports;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LanguageController {

    private final LanguageReports languageReports = new LanguageReports();

    @GetMapping("/languages")
    public String languageReport() { return "languages"; }

    @GetMapping("/languages/top5")
    public String getTop5Languages(Model model) {
        String output = languageReports.getLanguageReport();
        model.addAttribute("title", "A number of people who speak Chinese, English, Hindi, Spanish, and Arabic as a percentage of the world population.");
        model.addAttribute("output", output);
        return "languageReport";
    }
}
