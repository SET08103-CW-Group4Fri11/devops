package com.napier.unit;
import com.napier.sem.populationReports.SpecificPopulationReports;

import java.sql.SQLException;

public class SpecificPopulationTest {
    public static void init() {
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
        SpecificPopulationReports specificPopulationReportsTest = new SpecificPopulationReports();

        // this loops through the test data
        for (int i = 0; i < list.length; i++) {
            String type = list[i][0];
            String name = list[i][1];
            try {
                Long pop = specificPopulationReportsTest.getPopulation(type, name);
                System.out.println("Population of " + name + " (" + type + "): " + pop);
            } catch (SQLException e) {
                System.err.println("Error fetching population for " + name + ": " + e.getMessage());
            }
        }
    }
}
