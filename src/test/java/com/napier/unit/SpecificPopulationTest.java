package com.napier.unit;
import com.napier.sem.populationReports.SpecificPopulationReports;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SpecificPopulationTest {

    static SpecificPopulationReports specificPopulationReports;
    /** This method performs the setup for the following tests for the specificPopulationReports class.
     * */
    @BeforeAll
    public static void init() {
        System.out.println("----- Unit Test For SpecificPopulationReports Class -----");
        specificPopulationReports = new SpecificPopulationReports();
    }
    /**
     * This test method tests the specificPopulationReports with valid input data
     */
    @Test
    void testFormatPopulationReport() {
        String result = specificPopulationReports.formatPopulationReport("city","Anchorage",288000L);
        assertEquals("Population of Anchorage (city): 288000",result);
    }
    /**
     * This test method tests the specificPopulationReports with a null population
     * */
    @Test
    void testFormatPopulationReportWithNullPopulation() {
        String result = specificPopulationReports.formatPopulationReport("country","Canada",null);

        assertEquals("No population data found", result);
    }
    /**
     * This test method tests the specificPopulationReports with a missing feild.
     * */
    @Test
    void testFormatPopulationReportMissingFields() {
        String result = specificPopulationReports.formatPopulationReport("","Canada",100L);
        assertEquals("No population data found", result);
    }
}