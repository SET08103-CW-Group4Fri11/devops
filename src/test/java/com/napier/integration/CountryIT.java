package com.napier.integration;

import com.napier.sem.countries.CountryReports;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(DbExtension.class)
public class CountryIT {

    static CountryReports countryReport = new CountryReports();

    // Naming convention: methodName_condition_expectedResult


    @Test
    void runCountryQueryTest(){
        countryReport.allCountriesInWorldReport();
    }

}
