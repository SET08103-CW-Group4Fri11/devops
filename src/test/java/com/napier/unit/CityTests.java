package com.napier.unit;

import java.util.ArrayList;

import com.napier.sem.cities.City;
import com.napier.sem.cities.CityReports;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

// Unit tests for CityReports class
public class CityTests {

    // Test data
    static CityReports reportsTest;
    static City cityOfEdinburgh;
    static City cityOfLondon;
    static City edgeCity;
    static City bigCity;
    static ArrayList<City> cityList;
    static ArrayList<City> emptyList;
    static ArrayList<City> listWithNullCity;
    static ArrayList<City> edgeList;
    static ArrayList<City> bigList;

    // Initialize test data before all tests
    @BeforeAll
    static void init(){
        // Initialize test data
        reportsTest = new CityReports();
        cityOfEdinburgh = new City("Edinburgh", "United Kingdom", "Scotland", 488050);
        cityOfLondon = new City("London", "United Kingdom", "England", 8982000);
        bigCity = new City("MegaCity", "Fictionland", "FictionDistrict", Integer.MAX_VALUE);

        cityList = new ArrayList<>();
        cityList.add(cityOfEdinburgh);
        cityList.add(cityOfLondon);

        emptyList = new ArrayList<>();

        listWithNullCity = new ArrayList<>();
        listWithNullCity.add(cityOfEdinburgh);
        listWithNullCity.add(null);

        edgeCity = new City("", "", "", 0);
        edgeList = new ArrayList<>();
        edgeList.add(edgeCity);

        bigList = new ArrayList<>();
        bigList.add(bigCity);
    }

    // Test to check formatting of city report with a city list
    @Test
    void formatCityReport_cityList_printsRows() {
        assertTrue(reportsTest.formatCityReport(cityList).contains("Edinburgh"));
        assertTrue(reportsTest.formatCityReport(cityList).contains("London"));
    }

    // Test to check formatting of city report with an empty list
    @Test
    void formatCityReport_emptyList_returnsNoCities() {
        assertEquals("No cities found", reportsTest.formatCityReport(emptyList));
    }

    // Test to check formatting of city report with a null list
    @Test
    void formatCityReport_nullList_returnsNoCities() {
        assertEquals("No cities found", reportsTest.formatCityReport(null));
    }

    // Test to check formatting of city report with a list containing a null city
    @Test
    void formatCityReport_listWithNullCity_handlesNull() {
        String report = reportsTest.formatCityReport(listWithNullCity);
        assertTrue(report.contains("Edinburgh"));
        assertFalse(report.contains("null")); // Ensure "null" string is not printed
    }

    // Test to check formatting of city report with edge case city
    @Test
    void formatCityReport_edgeCaseCity_printsCorrectly() {
        String report = reportsTest.formatCityReport(edgeList);
        assertTrue(report.contains("0"));
    }

    // Test to check formatting of city report with big population city
    @Test
    void formatCityReport_bigPopulationCity_printsCorrectly() {
        String report = reportsTest.formatCityReport(bigList);
        assertTrue(report.contains(String.valueOf(Integer.MAX_VALUE)));
    }
}
