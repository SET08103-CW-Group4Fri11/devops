package com.napier.sem.web;

import com.napier.sem.languages.LanguageReports;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LanguageController {

    private final LanguageReports languageReports = new LanguageReports();

    @GetMapping("/languages")
    public String languageReport() { return "languageReport"; }

    @GetMapping("/languages/world")
    public String getTop5Languages(Model model) {
        String output = languageReports.getChEnHiSpArLanguageSpeakersPercentageOfWorldReport();
        model.addAttribute("title", "A number of people who speak Chinese, \nEnglish, Hindi, Spanish, and Arabic \nas a percentage of the world population.");
        model.addAttribute("output", output);
        return "languageReport";
    }
}
