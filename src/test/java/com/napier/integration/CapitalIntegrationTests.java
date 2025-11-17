package com.napier.integration;

import com.napier.sem.capital.CapitalReports;
import com.napier.sem.tools.DbTools;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CapitalIntegrationTests {

    CapitalReports reports = new CapitalReports();

    @BeforeAll
    void init() throws Exception {
        DbTools.connect();      // connect to DB
        assertTrue(DbTools.isConnected(), "Database connection not established");
    }

    @AfterAll
    void cleanup() {
        DbTools.disconnect();
    }

    @Test
    void worldCapitals_report_hasRows() {
        String out = reports.getAllCapitalsWorldReport();
        assertNotNull(out);
        assertFalse(out.startsWith("Error"));
        assertNotEquals("No capital data found", out);
        assertTrue(out.contains("Population"));  // header present
    }

    @Test
    void continentCapitals_Europe_report_hasRows() {
        String out = reports.getAllCapitalsInContinentReport("Europe");
        assertNotNull(out);
        assertFalse(out.startsWith("Error"));
        assertNotEquals("No capital data found", out);
        assertTrue(out.contains("Europe") || out.contains("United Kingdom") || out.contains("Spain"));
    }

    @Test
    void regionCapitals_WesternEurope_report_hasRows() {
        String out = reports.getAllCapitalsInRegionReport("Western Europe");
        assertNotNull(out);
        assertFalse(out.startsWith("Error"));
        assertNotEquals("No capital data found", out);
    }

    @Test
    void topN_worldCapitals_exactSize() {
        String out = reports.getTopNCapitalsWorldReport(5);
        assertNotNull(out);
        assertFalse(out.startsWith("Error"));
        // Rough shape: header + N lines → we can check there are >= N+1 lines
        long lines = out.lines().count();
        assertTrue(lines >= 6, "Header + 5 rows expected at minimum");
    }

    @Test
    void invalidContinent_returnsNoDataMessage() {
        String out = reports.getAllCapitalsInContinentReport("NoSuchContinent");
        assertEquals("No capital data found", out);
    }

    @Test
    void negativeN_returnsNoDataMessage() {
        String out = reports.getTopNCapitalsWorldReport(-10);
        assertEquals("No capital data found", out);
    }
}