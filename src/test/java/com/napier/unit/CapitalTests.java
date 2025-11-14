package com.napier.unit;

import com.napier.sem.capital.Capital;
import com.napier.sem.capital.CapitalReports;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CapitalTests {
    static CapitalReports reportsTest;
    static Capital cap1;
    static Capital cap2;
    static Capital nullCapital;                  // explicitly null
    static ArrayList<Capital> listWithCapitals;  // normal list
    static ArrayList<Capital> listWithNullCap;   // list containing a null element
    static ArrayList<Capital> emptyCapList;      // empty list

    @BeforeAll
    static void init() {
        reportsTest = new CapitalReports();

        // Example capitals (name, country, population)
        cap1 = new Capital("Edinburgh", "United Kingdom", "Midlothian", 488_050);
        cap2 = new Capital("Madrid", "Spain", "Madrid", 3_223_334);

        nullCapital = null;

        // normal list
        listWithCapitals = new ArrayList<>();
        listWithCapitals.add(cap1);
        listWithCapitals.add(cap2);

        // empty list
        emptyCapList = new ArrayList<>();

        // list with a null in the middle
        listWithNullCap = new ArrayList<>();
        listWithNullCap.add(cap1);
        listWithNullCap.add(nullCapital);
        listWithNullCap.add(cap2);
    }

    @Test
    void formatCapitalReport_normalList_printsRows() {
        System.out.println("Capitals (valid list):");
        System.out.println(reportsTest.formatCapitalReport(listWithCapitals));
    }

    @Test
    void formatCapitalReport_nullList_returnsNoCapitals() {
        // Expect the same “no data” message your formatter returns
        assertEquals("No capital data found", reportsTest.formatCapitalReport(null));
        System.out.println("Null list test: OK\n");
    }

    @Test
    void formatCapitalReport_emptyList_returnsNoCapitals() {
        assertEquals("No capital data found", reportsTest.formatCapitalReport(emptyCapList));
        System.out.println("Empty list test: OK\n");
    }

    @Test
    void formatCapitalReport_listWithNull_skipsNulls() {
        System.out.println("Capitals (list with a null in between):");
        System.out.println(reportsTest.formatCapitalReport(listWithNullCap));
    }
}