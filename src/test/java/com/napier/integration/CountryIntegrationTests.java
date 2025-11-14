package com.napier.integration;
import com.napier.sem.App;
import com.napier.sem.countries.CountryReports;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
public class CountryIntegrationTests {
    static App reportSystem;
    static CountryReports countryReport;
    @BeforeAll
    static void init()
    {
        reportSystem = new App();
        countryReport = new CountryReports();
    }
    @Test
    void runCountryQueryTest(){

    }
}
