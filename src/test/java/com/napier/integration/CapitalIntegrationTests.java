package com.napier.integration;

import com.napier.sem.App;                      // We’ll use App.startDb()/stopDb() to match team style
import com.napier.sem.capitals.CapitalReports;  // Your CapitalReports service
import org.junit.jupiter.api.*;                 // JUnit 5

import static org.junit.jupiter.api.Assertions.*;

/**
 * Integration tests for CapitalReports.
 *
 * This class connects to the real MySQL (via App.startDb()) once before all tests,
 * runs a few read-only report methods, and performs lightweight assertions to verify
 * that the system returns something sensible. Finally, it disconnects once after all tests.
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CapitalIntegrationTests {

    // references to be reuse across tests
    private App app;
    private CapitalReports capitalReports;

    /**
     * Runs once before all tests in this class.
     * We start the DB (so integration tests can reach MySQL),
     * and create our report object.
     */
    @BeforeAll
    void init() throws Exception {
        // Prefer App helpers so tests don’t touch DbTools directly.
        App.startDb();

        // App instance
        app = new App();
        capitalReports = new CapitalReports();
    }

    /**
     * Runs once after all tests complete.
     * Always stop the DB connection to release resources.
     */
    @AfterAll
    void cleanup() {
        App.stopDb();
    }

    /**
     * All capitals in the world ordered by population.
     * Assert: non-null, not an error message, contains the header token “Population”.
     */
    @Test
    void worldCapitals_report_hasRows() {
        String out = capitalReports.getAllCapitalsWorldReport();

        assertNotNull(out, "Report should not be null");
        assertFalse(out.startsWith("Error"), "Report should not start with an error");
        assertFalse(out.equals("No capital data found"), "Report should not be the empty-data message");
        assertTrue(out.contains("Population"), "Report should include the header line");
    }

    /**
     * Capitals in a specific continent.
     * Europe should exist in the sample world DB; expect non-empty formatted table.
     */
    @Test
    void continentCapitals_Europe_report_hasRows() {
        String out = capitalReports.getAllCapitalsInContinentReport("Europe");

        assertNotNull(out, "Report should not be null");
        assertFalse(out.startsWith("Error"), "Report should not start with an error");
        assertFalse(out.equals("No capital data found"),
                "Europe should yield data in the sample world database");
    }

    /**
     * Capitals in a specific region.
     * Western Europe should exist; we just sanity-check that we got formatted output.
     */
    @Test
    void regionCapitals_WesternEurope_report_hasRows() {
        String out = capitalReports.getAllCapitalsInRegionReport("Western Europe");

        assertNotNull(out, "Report should not be null");
        assertFalse(out.startsWith("Error"), "Report should not start with an error");
    }

    /**
     * Top-N capitals in the world.
     * We request N=5 and verify that we get at least header + N lines.
     * (Don’t assert exact values; only assert shape.)
     */
    @Test
    void topN_worldCapitals_hasAtLeastNLines() {
        int n = 5;
        String out = capitalReports.getTopNCapitalsWorldReport(n);

        assertNotNull(out, "Report should not be null");
        assertFalse(out.startsWith("Error"), "Report should not start with an error");

        long lineCount = out.lines().count(); // header + rows
        assertTrue(lineCount >= n + 1, "Expected at least header + " + n + " rows");
    }

    /**
     * Invalid continent should trigger “No capital data found” friendly message.
     */
    @Test
    void invalidContinent_returnsNoDataMessage() {
        String out = capitalReports.getAllCapitalsInContinentReport("NoSuchContinent");
        assertEquals("No capital data found", out);
    }

    /**
     * Invalid N (<= 0) should return the same “No capital data found” message,
     * as implemented in CapitalReports.
     */
    @Test
    void negativeN_returnsNoDataMessage() {
        String out = capitalReports.getTopNCapitalsWorldReport(-10);
        assertEquals("No capital data found", out);
    }
}
