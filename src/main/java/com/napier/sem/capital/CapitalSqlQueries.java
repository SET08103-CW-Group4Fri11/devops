
package com.napier.sem.capital;

public final class CapitalSqlQueries {

    private CapitalSqlQueries() {}

    // All capitals in the world, largest to smallest (city is the capital of its country)
    public static final String ALL_CAPITALS_WORLD =
            "SELECT ci.Name AS Capital, co.Name AS Country, ci.Population " +
                    "FROM country co JOIN city ci ON co.Capital = ci.ID " +
                    "ORDER BY ci.Population DESC;";

    // All capitals in a continent
    public static final String ALL_CAPITALS_CONTINENT =
            "SELECT ci.Name AS Capital, co.Name AS Country, ci.Population " +
                    "FROM country co JOIN city ci ON co.Capital = ci.ID " +
                    "WHERE co.Continent = ? " +
                    "ORDER BY ci.Population DESC;";

    // All capitals in a region
    public static final String ALL_CAPITALS_REGION =
            "SELECT ci.Name AS Capital, co.Name AS Country, ci.Population " +
                    "FROM country co JOIN city ci ON co.Capital = ci.ID " +
                    "WHERE co.Region = ? " +
                    "ORDER BY ci.Population DESC;";
}
