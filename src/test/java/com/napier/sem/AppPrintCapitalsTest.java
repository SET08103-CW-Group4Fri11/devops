package com.napier.sem;


import org.junit.jupiter.api.Test;        // JUnit 5 (@Test) annotation.
import java.util.ArrayList;               // Mutable list implementation used in a couple of tests.
import java.util.List;                    // List interface for method parameters/returns.

public class AppPrintCapitalsTest {       // A JUnit test class. Public so any runner can access it.

    // Create one App instance to call the method-under-test.
    // This is a *unit* test for a pure printer method (no DB access).
    private final App app = new App();

    /**
     * Test 1: Passing a null list
     * Why: to survive null inputs without throwing (e.g., NullPointerException).
     */
    @Test
    public void nullList_ok() {
        app.printCapitals(null);          // Should not throw; prints a friendly message.
    }

    /**
     * Test 2: Passing an empty list.
     * Why: Boundary case — empty collections often break naive loops/formatters.
     */
    @Test
    public void emptyList_ok() {
        app.printCapitals(new ArrayList<>());  // Diamond operator <> infers type (Java 9+ here).
    }

    /**
     * Test 3: List contains a null element.
     * Why: Real lists can contain nulls; loops should guard each row.
     */
    @Test
    public void containsNull_ok() {
        List<CapitalReportRow> rows = new ArrayList<>();
        rows.add(null);                    // Intentionally add a null entry.
        app.printCapitals(rows);           // Should not throw; null row is skipped.
    }

    /**
     * Test 4: Normal happy-path list.
     * Why: Ensure typical usage works: prints header + each row formatted.
     */
    @Test
    public void normalList_ok() {
        List<CapitalReportRow> rows = List.of(
                new CapitalReportRow("Edinburgh", "United Kingdom", 488050),
                new CapitalReportRow("Madrid", "Spain", 3223334)
        );
        app.printCapitals(rows);           // Should not throw; prints two aligned rows.
    }
}
