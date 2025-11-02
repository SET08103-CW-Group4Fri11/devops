package com.napier;
import com.napier.sem.App;
import com.napier.sem.countries.Country;
import com.napier.sem.countries.CountryReports;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class CountryTests {
    static CountryReports reportsTest;
    static Country country1;
    static Country country2;
    static Country countryAllNull;
    static Country nullCountry;
    static ArrayList<Country> listWithCountries;
    static ArrayList<Country> listWithNullCountry;
    static ArrayList<Country> emptyCountriesList;

    @BeforeAll
    static void init(){
        reportsTest = new CountryReports();
        country1 = new Country("ARN","Arnemia","El Continente","A region", 1520563210,"La Capital");
        country2 = new Country("ARM","Armenia","Le Continente","Another region", 15205630,"La Capitalada");
        nullCountry = null;
        listWithCountries = new ArrayList<>();
        listWithCountries.add(country1);
        listWithCountries.add(country2);
        emptyCountriesList = new ArrayList<>();
        listWithNullCountry = new ArrayList<Country>();
        listWithNullCountry.add(country1);
        listWithNullCountry.add(nullCountry);
        listWithNullCountry.add(country2);
    }
    @Test
    void formatCountryReports()
    {
        System.out.println("Test with two countries with correct info: ");
        System.out.println(reportsTest.formatCountryReport(listWithCountries));
    }

    @Test
    void formatCountryReportsNullList()
    {
        assertEquals("No countries found",reportsTest.formatCountryReport(null));
        System.out.println("Test with null list ok\n");
    }

    @Test
    void formatCountryReportsEmptyList()
    {
        assertEquals("No countries found",reportsTest.formatCountryReport(emptyCountriesList));
        System.out.println("Test with empty list ok\n");
    }

    @Test
    void formatCountryReportsListWithNullCountry()
    {
        System.out.println("Test with two countries with correct info with a null country intercalated: ");
        System.out.println((reportsTest.formatCountryReport(listWithNullCountry)));
    }
}
