package com.napier.unit;
import com.napier.sem.populationReports.SpecificPopulationReports;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

public class SpecificPopulationTest {

    static SpecificPopulationReports specificPopulationReportsTest;

    @BeforeAll
    public static void init() {
        System.out.println("----- Test For SpecificPopulationReports Class -----");
        specificPopulationReportsTest = new SpecificPopulationReports();
    }

    @Test
    void testPopulationData() {
        // test data in a list
        String[][] list = {
                {"city", "Anchorage"},
                {"country", "Canada"},
                {"district", "Alaska"},
                {"city", "Newcastle upon Tyne"},
                {"country", "United Kingdom"},
                {"district", "England"},
                {"city", "Paris"},
                {"country", "France"},
                {"district", "Île-de-France"}
        };
        //loops through all the test data
        for (String[] entry : list) {
            String type = entry[0];
            String name = entry[1];
            try {
                Long pop = specificPopulationReportsTest.getPopulation(type, name);
                System.out.println("Population of " + name + " (" + type + "): " + pop);
            } catch (SQLException e) {
                System.err.println("Error fetching population for " + name + ": " + e.getMessage());
            }
        }
    }
}