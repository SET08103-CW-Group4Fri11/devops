package com.napier.integration;
import com.napier.sem.App;
import com.napier.sem.countries.CountryReports;
import com.napier.sem.tools.DbTools;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.sql.SQLException;

public class CountryIntegrationTests {
    static App reportSystem;
    static CountryReports countryReport;
    @BeforeAll
    static void init() throws SQLException, InterruptedException {
        DbTools.connect();
        reportSystem = new App();
        countryReport = new CountryReports();
    }
    @Test
    void runCountryQueryTest(){
        countryReport.allCountriesInWorldReport();
    }

}
