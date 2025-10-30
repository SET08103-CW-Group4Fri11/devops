package com.napier.sem;

import java.util.List;

public class App {
    public static void main(String[] args) {
        // initiate dbTools to connect with db
        dbTools dbTools = new dbTools();
        dbTools.connect();
        dbTools.disconnect();
    }

    /** printCapitals
     * Prints a simple, DB-free capitals report: safely handles
     * null/empty input, prints a header, and outputs each row in aligned columns using
     * printf field widths (%-30s, %-12d).
     */
    public void printCapitals(List<CapitalReportRow> rows) {
        if (rows == null) {
            System.out.println("No capitals");
            return;
        }
        System.out.printf("%-30s %-30s %-12s%n", "Capital", "Country", "Population");
        for (CapitalReportRow r : rows) {
            if (r == null) continue;
            System.out.printf("%-30s %-30s %-12d%n",
                    r.capital(), r.country(), r.population());
        }
    }
}

