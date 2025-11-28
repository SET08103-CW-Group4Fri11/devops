package com.napier.sem.populationReports;

import com.napier.sem.tools.DbTools;

import java.sql.*;
import java.util.ArrayList;

public class PopulationReports {

    /**
     * Runs an population query and returns an list of population objects.
     */
    public ArrayList<Population> runPopulationQuery(String query, Object... params)
            throws SQLException, InterruptedException {

        if (DbTools.getCon() == null) {
            throw new SQLException("No DB connection. Call DbTools.connect() before executing queries.");
        }

        ArrayList<Population> populations = new ArrayList<>();

        // No parameters
        if (params == null || params.length == 0) {
            try (Statement stmt = DbTools.getCon().createStatement()) {
                ResultSet rs = stmt.executeQuery(query);

                while (rs.next()) {
                    populations.add(new Population(
                            rs.getString("Name"),
                            rs.getLong("TotalPopulation"),
                            rs.getLong("CityPopulation"),
                            rs.getLong("NonCityPopulation")
                    ));
                }
            }
        }
        // With parameters
        else {
            try (PreparedStatement pstmt = DbTools.getCon().prepareStatement(query)) {

                for (int i = 0; i < params.length; i++) {
                    pstmt.setObject(i + 1, params[i]);
                }

                ResultSet rs = pstmt.executeQuery();

                while (rs.next()) {
                    populations.add(new Population(
                            rs.getString("Name"),
                            rs.getLong("TotalPopulation"),
                            rs.getLong("CityPopulation"),
                            rs.getLong("NonCityPopulation")
                    ));
                }
            }
        }

        return populations;
    }

    /**
     * Formats a list of population objects
     */
    public String formatPopulationReport(ArrayList<Population> list) {
        StringBuilder sb = new StringBuilder();

        if (list == null || list.isEmpty()) {
            return "No population data found.\n";
        }

        sb.append(String.format("%-25s %-15s %-15s %-15s%n", "Name", "Total Pop", "City Pop", "Non-City Pop"));
        sb.append("\n");

        for (Population p : list) {
            if (p == null) {
                continue;   // thos is a fix to skip null entries to prevent a null pointer error.
            }
            sb.append(String.format("%-25s %-15d %-15d %-15d%n",
                    p.getName(),
                    p.getTotalPopulation(),
                    p.getCityPopulation(),
                    p.getNonCityPopulation()
            ));
        }

        return sb.toString();
    }


    /**
     * Population of each continent.
     */
    public String getContinentPopulationReport() {
        String query = """
                SELECT co.Continent AS Name,
                       co.TotalPopulation,
                       COALESCE(ci.CityPopulation, 0) AS CityPopulation,
                       (co.TotalPopulation - COALESCE(ci.CityPopulation, 0)) AS NonCityPopulation
                FROM (
                  SELECT Continent, SUM(Population) AS TotalPopulation
                  FROM country
                  GROUP BY Continent
                ) AS co
                LEFT JOIN (
                  SELECT country.Continent, SUM(city.Population) AS CityPopulation
                  FROM city
                  JOIN country ON city.CountryCode = country.Code
                  GROUP BY country.Continent
                ) AS ci ON co.Continent = ci.Continent;
                """;

        try {
            ArrayList<Population> data = runPopulationQuery(query);
            return formatPopulationReport(data);
        } catch (Exception e) {
            return "Error generating continent population report: " + e.getMessage();
        }
    }


    /**
     * Population of each region.
     */
    public String getRegionPopulationReport() {
        String query =
                "SELECT region AS Name, " +
                        "SUM(country.Population) AS TotalPopulation, " +
                        "SUM(city.Population) AS CityPopulation, " +
                        "(SUM(country.Population) - SUM(city.Population)) AS NonCityPopulation " +
                        "FROM country " +
                        "LEFT JOIN city ON country.Code = city.CountryCode " +
                        "GROUP BY region;";

        try {
            ArrayList<Population> data = runPopulationQuery(query);
            return formatPopulationReport(data);
        } catch (Exception e) {
            return "Error generating region population report: " + e.getMessage();
        }
    }


    /**
     * Population of each country
     */
    public String getCountryPopulationReport() {
        String query =
                "SELECT country.Name AS Name, " +
                        "country.Population AS TotalPopulation, " +
                        "SUM(city.Population) AS CityPopulation, " +
                        "(country.Population - SUM(city.Population)) AS NonCityPopulation " +
                        "FROM country " +
                        "LEFT JOIN city ON country.Code = city.CountryCode " +
                        "GROUP BY country.Code;";

        try {
            ArrayList<Population> data = runPopulationQuery(query);
            return formatPopulationReport(data);
        } catch (Exception e) {
            return "Error generating country population report: " + e.getMessage();
        }
    }

}
