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
public class CityIntegrationTests {

    CityReports reports = new CityReports();

    @BeforeAll
    void init() throws Exception {
        DbTools.connect();      // connect to DB
    }

    @AfterAll
    void cleanup() {
        DbTools.disconnect();
    }

    // Naming convention: methodName_condition_expectedResult

    // Verify that the database connection is established
    @Test
    void connection_is_established() {
        assertTrue(DbTools.isConnected(), "Database connection not established");
    }

    // Test for getting all cities in the world report
    @Test
    void getAllCitiesInWorldReport_has_rows() {
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
    void getAllCitiesInContinentReport_has_rows() {
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
    void getAllCitiesInRegionReport_has_rows() {
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
    void getAllCitiesInCountryReport_has_rows() {
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
    void getAllCitiesInDistrictReport_has_rows() {
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
    // Test for invalid continent returns no data message
    @Test
    void invalidContinent_returnsNoDataMessage() {
        String out = reports.getAllCitiesInContinentReport("NoSuchContinent");
        assertEquals("No cities found", out);
    }

    // Test for invalid region returns no data message
    @Test
    void invalidRegion_returnsNoDataMessage() {
        String out = reports.getAllCitiesInRegionReport("NoSuchRegion");
        assertEquals("No cities found", out);
    }

    // Test for invalid country returns no data message
    @Test
    void invalidCountry_returnsNoDataMessage() {
        String out = reports.getAllCitiesInCountryReport("NoSuchCountry");
        assertEquals("No cities found", out);
    }

    // Test for invalid district returns no data message
    @Test
    void invalidDistrict_returnsNoDataMessage() {
        String out = reports.getAllCitiesInDistrictReport("NoSuchDistrict");
        assertEquals("No cities found", out);
    }
}
