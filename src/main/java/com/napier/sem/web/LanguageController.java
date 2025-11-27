package com.napier.sem.web;

import com.napier.sem.languages.LanguageReports;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

// Controller for handling language-related web requests
@Controller
public class LanguageController {

    // Instantiate the LanguageReports class
    private final LanguageReports languageReports = new LanguageReports();

    // Endpoint to display the language report page
    @GetMapping("/languages")
    public String languageReport() { return "languageReport"; }

    // Endpoint to get top 5 languages spoken as a percentage of the world population
    @GetMapping("/languages/world")
    public String getTop5Languages(Model model) {
        String output = languageReports.getChEnHiSpArLanguageSpeakersPercentageOfWorldReport();
        model.addAttribute("title", "A number of people who speak Chinese, \nEnglish, Hindi, Spanish, and Arabic \nas a percentage of the world population.");
        model.addAttribute("output", output);
        return "languageReport";
    }
}
