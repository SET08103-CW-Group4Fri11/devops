package com.napier.integration;

import com.napier.sem.countries.CountryReports;
import com.napier.sem.tools.DbTools;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertTrue;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CountryIT extends DbIntegrationTestBase {

    static CountryReports countryReport = new CountryReports();

    // Naming convention: methodName_condition_expectedResult


    @Test
    void runCountryQueryTest(){
        countryReport.allCountriesInWorldReport();
    }

}
