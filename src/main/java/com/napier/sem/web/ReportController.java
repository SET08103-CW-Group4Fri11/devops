package com.napier.sem.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

// Controller for handling home page requests
@Controller
public class ReportController {

    // Endpoint to display the home page
    @GetMapping("/")
    public String index() {
        return "index";
    }
}
