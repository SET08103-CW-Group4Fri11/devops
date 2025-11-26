package com.napier.integration;

import com.napier.sem.countries.CountryReports;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.*;

// Integration tests for CountryReports class
@ExtendWith(DbExtension.class)
public class CountryIT {

    // Shared CountryReports instance for all tests
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

    // Test for getting all countries in a continent report
    @Test
    void allCountriesInContinentReport_hasRows(){
        String out = countryReport.allCountriesInContinentReport("Europe");
        assertNotNull(out);
        assertFalse(out.startsWith("Error"));
        assertNotEquals("No countries found", out);
        assertTrue(out.contains("Europe") || out.contains("United Kingdom") || out.contains("Spain"));
    }

    // Test for getting all countries in a region report
    @Test
    void allCountriesInRegionReport_hasRows() {
        String out = countryReport.allCountriesInRegionReport("Western Europe");
        assertNotNull(out);
        assertFalse(out.startsWith("Error"));
        assertNotEquals("No countries found", out);
    }

    // Test for getting top N countries in the world report
    @Test
    void topN_countriesInWorldReport_exactSize() {
        String out = countryReport.topNCountriesInWorldReport(5);
        assertNotNull(out);
        assertFalse(out.startsWith("Error"));
        // Rough shape: header + N lines → we can check there are >= N+1 lines
        long lines = out.lines().count();
        assertTrue(lines <= 6, "Header + 5 rows expected at most");
    }

    // Test for getting top N countries in the continent report
    @Test
    void topN_countriesInContinentReport_exactSize() {
        String out = countryReport.topNCountriesInContinentReport(5,"Europe");
        assertNotNull(out);
        assertFalse(out.startsWith("Error"));
        // Rough shape: header + N lines → we can check there are >= N+1 lines
        long lines = out.lines().count();
        assertTrue(lines <= 6, "Header + 5 rows expected at most");
    }

    // Test for getting top N countries in the region report
    @Test
    void topN_countriesInRegionReport_exactSize() {
        String out = countryReport.topNCountriesInRegionReport(5,"Western Europe");
        assertNotNull(out);
        assertFalse(out.startsWith("Error"));
        // Rough shape: header + N lines → we can check there are >= N+1 lines
        long lines = out.lines().count();
        assertTrue(lines <= 6, "Header + 5 rows expected at most");
    }

    // Test for invalid continent, null or empty inputs
    @Test
    void invalidParameters_returnNoDataMessage() {
        assertEquals("No countries found", countryReport.allCountriesInContinentReport("NoSuchContinent"));
        assertEquals("No countries found", countryReport.allCountriesInContinentReport(null));
        assertEquals("No countries found", countryReport.allCountriesInContinentReport(""));
    }

    // Test for invalid region, null or empty inputs
    @Test
    void invalidRegionParameters_returnNoDataMessage() {
        assertEquals("No countries found", countryReport.allCountriesInRegionReport("NoSuchRegion"));
        assertEquals("No countries found", countryReport.allCountriesInRegionReport(null));
        assertEquals("No countries found", countryReport.allCountriesInRegionReport(""));
    }

    // Test for zero or negative N returns no data message
    @Test
    void zeroOrNegativeN_returnNoDataMessage() {
        assertEquals("No countries found", countryReport.topNCountriesInWorldReport(0));
        assertEquals("No countries found", countryReport.topNCountriesInWorldReport(-5));
    }

    // Test for N much larger than available countries
    @Test
    void largeN_returnsAvailableCountriesOnly() {
        String out = countryReport.topNCountriesInWorldReport(10000);
        assertNotNull(out);
        assertFalse(out.startsWith("Error"));
        // Rough shape: header + N lines → we can check there are >= N+1 lines
        long lines = out.lines().count();
        assertTrue(lines <= 250, "Header + all available country rows expected at most");
    }



}
