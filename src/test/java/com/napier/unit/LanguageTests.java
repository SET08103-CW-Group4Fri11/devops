package com.napier.unit;

import com.napier.sem.languages.Language;
import com.napier.sem.languages.LanguageReports;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LanguageTests {
    // Test data
    static LanguageReports languageReports;
    static Language spanishLanguage;
    static Language englishLanguage;
    static ArrayList<Language> languages;
    static ArrayList<Language> emptyLanguages;
    static ArrayList<Language> nullLanguageList;
    static ArrayList<Language> edgeLanguageList;
    static ArrayList<Language> bigLanguageList;

    @BeforeAll
    static void init() {
        // Initialize test data
        languageReports = new LanguageReports();
        spanishLanguage = new Language("Spanish", 460000000, 6.0);
        englishLanguage = new Language("English", 380000000, 5.0);

        languages = new ArrayList<>();
        languages.add(spanishLanguage);
        languages.add(englishLanguage);

        emptyLanguages = new ArrayList<>();

        nullLanguageList = new ArrayList<>();
        nullLanguageList.add(spanishLanguage);
        nullLanguageList.add(null);

        Language edgeLanguage = new Language("", 0, 0.0);
        edgeLanguageList = new ArrayList<>();
        edgeLanguageList.add(edgeLanguage);

        Language bigLanguage = new Language("MegaLanguage", Integer.MAX_VALUE, Double.MAX_VALUE);
        bigLanguageList = new ArrayList<>();
        bigLanguageList.add(bigLanguage);
    }

    // Test to check formatting of language report with a language list
    @Test
    void formatLanguageReport_languageList_printsRows() {
        assertTrue(languageReports.formatLanguageReport(languages).contains("Spanish"));
        assertTrue(languageReports.formatLanguageReport(languages).contains("English"));
    }

    // Test to check formatting of language report with an empty list
    @Test
    void formatLanguageReport_emptyList_returnsNoLanguages() {
        assertTrue(languageReports.formatLanguageReport(emptyLanguages).contains("No languages found"));
    }

    // Test to check formatting of language report with null list
    @Test
    void formatLanguageReport_nullList_returnsNoLanguages() {
        assertTrue(languageReports.formatLanguageReport(null).contains("No languages found"));
    }

    // Test to check formatting of language report with a list containing null
    @Test
    void formatLanguageReport_listWithNullLanguage_handlesNull() {
        String report = languageReports.formatLanguageReport(nullLanguageList);
        assertTrue(report.contains("Spanish"));
        assertFalse(report.contains("null"));
    }

    // Test to check formatting of language report with edge case language
    @Test
    void formatLanguageReport_edgeLanguage_printsCorrectly() {
        String report = languageReports.formatLanguageReport(edgeLanguageList);
        assertTrue(report.contains("0"));
    }

    // Test to check formatting of language report with big language
    @Test
    void formatLanguageReport_bigLanguage_printsCorrectly() {
        String report = languageReports.formatLanguageReport(bigLanguageList);
        assertTrue(report.contains(String.valueOf(Integer.MAX_VALUE)));
    }
}
