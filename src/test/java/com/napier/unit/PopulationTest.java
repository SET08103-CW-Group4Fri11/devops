package com.napier.unit;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import com.napier.sem.populationReports.Population;
import com.napier.sem.populationReports.PopulationReports;

//import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

// Unit tests for PopulationReports class
public class PopulationTest {

    // Test data
    static PopulationReports reportsTest;
    static Population europePop;
    static Population asiaPop;
    static Population edgePop;
    static Population bigPop;

    static ArrayList<Population> popList;
    static ArrayList<Population> emptyList;
    static ArrayList<Population> listWithNull;
    static ArrayList<Population> edgeList;
    static ArrayList<Population> bigList;

    // Initialize test data before all tests
    @BeforeAll
    static void init() {
        reportsTest = new PopulationReports();
        // creating test objects
        europePop = new Population("Europe", 700000000L, 300000000L, 400000000L);
        asiaPop = new Population("Asia", 4600000000L, 2200000000L, 2400000000L);
        edgePop = new Population("", 0, 0, 0);
        bigPop = new Population("FictionalRegion", Long.MAX_VALUE, Long.MAX_VALUE / 2, Long.MAX_VALUE / 2);

        popList = new ArrayList<>();
        popList.add(europePop);
        popList.add(asiaPop);

        emptyList = new ArrayList<>();

        listWithNull = new ArrayList<>();
        listWithNull.add(europePop);
        listWithNull.add(null);

        edgeList = new ArrayList<>();
        edgeList.add(edgePop);

        bigList = new ArrayList<>();
        bigList.add(bigPop);
    }

    // this tests the formatting of list containing valid populations
    @Test
    void formatPopulationReport_popList_containsNames() {
        String report = reportsTest.formatPopulationReport(popList);
        assertTrue(report.contains("Europe"));
        assertTrue(report.contains("Asia"));
    }

    // tests empty list handling
    @Test
    void formatPopulationReport_emptyList_returnsNoData() {
        assertEquals("No population data found.\n",
                reportsTest.formatPopulationReport(emptyList));
    }

    // tests null list handling
    @Test
    void formatPopulationReport_nullList_returnsNoData() {
        assertEquals("No population data found.\n",
                reportsTest.formatPopulationReport(null));
    }

    // Tests a list containing a null population object
    @Test
    void formatPopulationReport_listWithNull_handlesGracefully() {
        String report = reportsTest.formatPopulationReport(listWithNull);
        assertTrue(report.contains("Europe"));
        assertFalse(report.contains("null"));  // Ensure "null" string is not printed
    }

    // test edge case population (zeros, empty name)
    @Test
    void formatPopulationReport_edgeCase_printsZeroValues() {
        String report = reportsTest.formatPopulationReport(edgeList);
        assertTrue(report.contains("0"));
    }

    // tests extremely large population values
    @Test
    void formatPopulationReport_bigValues_printsMaxLong() {
        String report = reportsTest.formatPopulationReport(bigList);
        assertTrue(report.contains(String.valueOf(Long.MAX_VALUE)));
    }
}
