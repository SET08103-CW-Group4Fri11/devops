package com.napier.integration;

import com.napier.sem.cities.CityReports;
import com.napier.sem.tools.DbTools;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CityIT extends DbIntegrationTestBase {

    CityReports reports = new CityReports();

    // Naming convention: methodName_condition_expectedResult

    // Test for getting all cities in the world report
    @Test
    void getAllCitiesInWorldReport_hasRows() {
        String out = reports.getAllCitiesInWorldReport();
        assertTrue(
                out.contains("Name") &&
                        out.contains("Country") &&
                        out.contains("District") &&
                        out.contains("Population") &&
                        !out.startsWith("Error") &&
                        !out.equals("No cities found"));
    }

    // Test for getting all cities in a continent report
    @Test
    void getAllCitiesInContinentReport_europe_hasRows() {
        String out = reports.getAllCitiesInContinentReport("Europe");
        assertTrue(
                out.contains("Name") &&
                        out.contains("Country") &&
                        out.contains("District") &&
                        out.contains("Population") &&
                        !out.startsWith("Error") &&
                        !out.equals("No cities found"));
    }

    // Test for getting all cities in a region report
    @Test
    void getAllCitiesInRegionReport_westernEurope_hasRows() {
        String out = reports.getAllCitiesInRegionReport("Western Europe");
        assertTrue(
                out.contains("Name") &&
                        out.contains("Country") &&
                        out.contains("District") &&
                        out.contains("Population") &&
                        !out.startsWith("Error") &&
                        !out.equals("No cities found"));
    }

    // Test for getting all cities in a country report
    @Test
    void getAllCitiesInCountryReport_unitedKingdom_hasRows() {
        String out = reports.getAllCitiesInCountryReport("United Kingdom");
        assertTrue(
                out.contains("Name") &&
                        out.contains("Country") &&
                        out.contains("District") &&
                        out.contains("Population") &&
                        !out.startsWith("Error") &&
                        !out.equals("No cities found"));
    }

    // Test for getting all cities in a district report
    @Test
    void getAllCitiesInDistrictReport_england_hasRows() {
        String out = reports.getAllCitiesInDistrictReport("England");
        assertTrue(
                out.contains("Name") &&
                        out.contains("Country") &&
                        out.contains("District") &&
                        out.contains("Population") &&
                        !out.startsWith("Error") &&
                        !out.equals("No cities found"));
    }

    // Test for getting top N cities in the world report
    @Test
    void getTopNCitiesInWorldReport_has_sizeAtMost() {
        int N = 5;
        String out = reports.getTopNCitiesInWorldReport(N);
        long lines = out.lines().count();
        assertTrue(lines <= N + 1); // Header + N rows
    }

    // Test for getting top N cities in a continent report
    @Test
    void getTopNCitiesInContinentReport_has_sizeAtMost() {
        int N = 5;
        String out = reports.getTopNCitiesInContinentReport(N, "Europe");
        long lines = out.lines().count();
        assertTrue(lines <= N + 1); // Header + N rows
    }

    // Test for getting top N cities in a region report
    @Test
    void getTopNCitiesInRegionReport_has_sizeAtMost() {
        int N = 5;
        String out = reports.getTopNCitiesInRegionReport(N, "Western Europe");
        long lines = out.lines().count();
        assertTrue(lines <= N + 1); // Header + N rows
    }

    // Test for getting top N cities in a country report
    @Test
    void getTopNCitiesInCountryReport_has_sizeAtMost() {
        int N = 5;
        String out = reports.getTopNCitiesInCountryReport(N, "United Kingdom");
        long lines = out.lines().count();
        assertTrue(lines <= N + 1); // Header + N rows
    }

    // Test for getting top N cities in a district report
    @Test
    void getTopNCitiesInDistrictReport_has_sizeAtMost() {
        int N = 5;
        String out = reports.getTopNCitiesInDistrictReport(N, "England");
        long lines = out.lines().count();
        assertTrue(lines <= N + 1); // Header + N rows
    }

    // Invalid parameter tests
    // Test for invalid continent, null or empty returns no data message
    @Test
    void getAllCitiesInContinentReport_invalidContinentOrNullOrEmpty_returnsNoDataMessage() {
        assertEquals("No cities found", reports.getAllCitiesInContinentReport("NoSuchContinent"));
        assertEquals("No cities found", reports.getAllCitiesInContinentReport(null));
        assertEquals("No cities found", reports.getAllCitiesInContinentReport(""));
    }

    // Test for invalid region, null or empty returns no data message
    @Test
    void getAllCitiesInRegionReport_invalidRegionOrNullOrEmpty_returnsNoDataMessage() {
        assertEquals("No cities found", reports.getAllCitiesInRegionReport("NoSuchRegion"));
        assertEquals("No cities found", reports.getAllCitiesInRegionReport(null));
        assertEquals("No cities found", reports.getAllCitiesInRegionReport(""));
    }

    // Test for invalid country, null or empty returns no data message
    @Test
    void getAllCitiesInRegionReport_invalidCountryOrNullOrEmpty_returnsNoDataMessage() {
        assertEquals("No cities found", reports.getAllCitiesInCountryReport("NoSuchCountry"));
        assertEquals("No cities found", reports.getAllCitiesInCountryReport(null));
        assertEquals("No cities found", reports.getAllCitiesInCountryReport(""));
    }

    // Test for invalid district, null or empty returns no data message
    @Test
    void getAllCitiesInRegionReport_invalidDistrictOrNullOrEmpty_returnsNoDataMessage() {
        assertEquals("No cities found", reports.getAllCitiesInDistrictReport("NoSuchDistrict"));
        assertEquals("No cities found", reports.getAllCitiesInDistrictReport(null));
        assertEquals("No cities found", reports.getAllCitiesInDistrictReport(""));
    }

    // Test for negative N returns no data message
    @Test
    void getTopNCitiesInWorldReport_negativeN_returnsNoDataMessage() {
        String out = reports.getTopNCitiesInWorldReport(-10);
        assertTrue(out.equals("No cities found") || out.lines().count() == 1);
    }

    // Test for N = 1 (should return header + 1 row)
    @Test
    void getTopNCitiesInWorldReport_oneN_returnsOneRow() {
        String out = reports.getTopNCitiesInWorldReport(1);
        assertEquals(2, out.lines().count()); // Header + 1 row
    }

    // Test for N much larger than available cities
    @Test
    void getTopNCitiesInWorldReport_largeN_returnsAllRows() {
        String out = reports.getTopNCitiesInWorldReport(10000);
        long lines = out.lines().count();
        assertTrue(lines >= 1); // At least header
    }
}
