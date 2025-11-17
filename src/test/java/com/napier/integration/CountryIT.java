package com.napier.integration;

import com.napier.sem.countries.CountryReports;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(DbExtension.class)
public class CountryIT {

    static CountryReports countryReport = new CountryReports();

    // Naming convention: methodName_condition_expectedResult

    // Test for getting all countries in the world report
    @Test
    void allCountriesInWorldReport_hasRows(){
        String out = countryReport.allCountriesInWorldReport();
        assertNotNull(out);
        assertFalse(out.startsWith("Error"));
        assertNotEquals("No countries found", out);
        assertTrue(out.contains("Code"));  // header present
        assertTrue(out.contains("Name"));  // header present
        assertTrue(out.contains("Continent"));  // header present
        assertTrue(out.contains("Region"));  // header present
        assertTrue(out.contains("Population"));  // header present
        assertTrue(out.contains("Capital"));  // header present
    }



}
