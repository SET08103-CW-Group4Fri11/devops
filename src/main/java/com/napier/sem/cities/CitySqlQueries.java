package com.napier.sem.cities;

public final class CitySqlQueries {

    private CitySqlQueries() {  } // Prevent instantiation

    // SQL Queries for City Reports

    public static final String ALL_CITIES_WORLD =
            "SELECT city.Name, country.Name AS Country, city.District, city.Population "
            + "FROM city JOIN country ON city.CountryCode = country.Code "
            + "ORDER BY city.Population DESC;";

    public static final String ALL_CITIES_CONTINENT =
            "SELECT city.Name, country.Name AS Country, city.District, city.Population "
            + "FROM city JOIN country ON city.CountryCode = country.Code "
            + "WHERE country.Continent = ? "
            + "ORDER BY city.Population DESC;";

    public static final String ALL_CITIES_REGION =
            "SELECT city.Name, country.Name AS Country, city.District, city.Population "
            + "FROM city JOIN country ON city.CountryCode = country.Code "
            + "WHERE country.Region = ? "
            + "ORDER BY city.Population DESC;";

    public static final String ALL_CITIES_COUNTRY =
            "SELECT city.Name, country.Name AS Country, city.District, city.Population "
            + "FROM city JOIN country ON city.CountryCode = country.Code "
            + "WHERE country.Name = ? "
            + "ORDER BY city.Population DESC;";

    public static final String ALL_CITIES_DISTRICT =
            "SELECT city.Name, country.Name AS Country, city.District, city.Population "
            + "FROM city JOIN country ON city.CountryCode = country.Code "
            + "WHERE city.District = ? "
            + "ORDER BY city.Population DESC;";


}
