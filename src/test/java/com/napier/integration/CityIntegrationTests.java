package com.napier.integration;

import com.napier.sem.cities.CityReports;
import com.napier.sem.tools.DbTools;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

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
                        !out.equals("No city data found"));
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
                        !out.equals("No city data found"));
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
                        !out.equals("No city data found"));
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
                        !out.equals("No city data found"));
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
                        !out.equals("No city data found"));
    }


}
