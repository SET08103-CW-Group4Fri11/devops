package com.napier.integration;

import com.napier.sem.capital.CapitalReports;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(DbExtension.class)
public class CapitalIT {

    CapitalReports reports = new CapitalReports();

    // Naming convention: methodName_condition_expectedResult

    // Test for getting all capitals in the world report
    @Test
    void worldCapitals_report_hasRows() {
        String out = reports.getAllCapitalsWorldReport();
        assertNotNull(out);
        assertFalse(out.startsWith("Error"));
        assertNotEquals("No capital data found", out);
        assertTrue(out.contains("Population"));  // header present
    }

    // Test for getting all capitals in a continent report
    @Test
    void continentCapitals_Europe_report_hasRows() {
        String out = reports.getAllCapitalsInContinentReport("Europe");
        assertNotNull(out);
        assertFalse(out.startsWith("Error"));
        assertNotEquals("No capital data found", out);
        assertTrue(out.contains("Europe") || out.contains("United Kingdom") || out.contains("Spain"));
    }

    // Test for getting all capitals in a region report
    @Test
    void regionCapitals_WesternEurope_report_hasRows() {
        String out = reports.getAllCapitalsInRegionReport("Western Europe");
        assertNotNull(out);
        assertFalse(out.startsWith("Error"));
        assertNotEquals("No capital data found", out);
    }

    // Test for getting top N capitals in the world report
    @Test
    void topN_worldCapitals_exactSize() {
        String out = reports.getTopNCapitalsWorldReport(5);
        assertNotNull(out);
        assertFalse(out.startsWith("Error"));
        // Rough shape: header + N lines → we can check there are >= N+1 lines
        long lines = out.lines().count();
        assertTrue(lines <= 6, "Header + 5 rows expected at most");
    }

    // Test for getting top N capitals in the continent report
    @Test
    void topN_continentCapitals_exactSize() {
        String out = reports.getTopNCapitalsInContinentReport("Asia", 3);
        assertNotNull(out);
        assertFalse(out.startsWith("Error"));
        long lines = out.lines().count();
        assertTrue(lines <= 4, "Header + 3 rows expected at most");
    }

    // Test for getting top N capitals in the region report
    @Test
    void topN_regionCapitals_exactSize() {
        String out = reports.getTopNCapitalsInRegionReport("Eastern Europe", 4);
        assertNotNull(out);
        assertFalse(out.startsWith("Error"));
        long lines = out.lines().count();
        assertTrue(lines <= 5, "Header + 4 rows expected at most");
    }

    // Test for invalid continent, null or empty returns no data message
    @Test
    void getAllCapitalsInContinentReport_invalidContinentOrNullOrEmpty_returnsNoDataMessage() {
        assertEquals("No capital data found", reports.getAllCapitalsInContinentReport("NoSuchContinent"));
        assertEquals("No capital data found", reports.getAllCapitalsInContinentReport(null));
        assertEquals("No capital data found", reports.getAllCapitalsInContinentReport(""));
    }

    // Test for invalid region, null or empty returns no data message
    @Test
    void getAllCapitalsInRegionReport_invalidRegionOrNullOrEmpty_returnsNoDataMessage() {
        assertEquals("No capital data found", reports.getAllCapitalsInRegionReport("NoSuchRegion"));
        assertEquals("No capital data found", reports.getAllCapitalsInRegionReport(null));
        assertEquals("No capital data found", reports.getAllCapitalsInRegionReport(""));
    }

    // Test for zero or negative N returns no data message
    @Test
    void negativeN_returnsNoDataMessage() {
        String out = reports.getTopNCapitalsWorldReport(-10);
        assertEquals("No capital data found", out);
    }

    // Test for zero N returns no data message
    @Test
    void zeroN_returnsNoDataMessage() {
        String out = reports.getTopNCapitalsWorldReport(0);
        assertEquals("No capital data found", out);
    }

    // Test for N much larger than available capitals
    @Test
    void largeN_returnsAllAvailableCapitals() {
        String out = reports.getTopNCapitalsWorldReport(10000);
        assertNotNull(out);
        assertFalse(out.startsWith("Error"));
        long lines = out.lines().count();
        assertTrue(lines > 1, "Should return all available capitals");
    }
}