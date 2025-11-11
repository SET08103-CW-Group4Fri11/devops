package com.napier.sem.cities;
import com.napier.sem.tools.DbTools;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class CityReports {

    // Atributes
    private static ArrayList<City> cities;

    /**
     * Method to format a report of cities
     * @param cities
     * @return String with the formatted report
     */
    public String formatCityReport(ArrayList<City> cities) {
        StringBuilder cityReport = new StringBuilder();
        if (cities == null || cities.isEmpty()) {
            cityReport.append("No cities found");
        } else {
            cityReport.append(String.format("%-35s %-30s %-30s %-15s%n", "Name", "Country", "District", "Population"));
            for (City city : cities) {
                if (city != null) {
                    cityReport.append(String.format("%-35s %-30s %-30s %-15d%n", city.getName(), city.getCountry(), city.getDistrict(), city.getPopulation()));
                }
            }
        }
        return cityReport.toString();
    }

    /**
     * A method to run a SQL query that stores the different queried values in an ArrayList of cities
     * @param query
     * @return ArrayList of City
     * @throws SQLException when it cannot connect to the DB
     * @throws InterruptedException when it cannot connect to the DB
     * @throws RuntimeException when the query is incorrect
     */
    public ArrayList<City> runCityQuery(String query, Object... params) throws SQLException, InterruptedException, RuntimeException {
        try {
            DbTools.connect();
            // No params provided
            if (params == null || params.length == 0) {
                try (Statement stmt = DbTools.getCon().createStatement()) {
                    ResultSet rs = stmt.executeQuery(query);
                    ArrayList<City> cities = new ArrayList<>();
                    while (rs.next()) {
                        cities.add(new City(rs.getString("Name"), rs.getString("Country"), rs.getString("District"), rs.getInt("Population")));
                    }
                    DbTools.disconnect();
                    return cities;
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            } else {
                // Params provided
                try (PreparedStatement pstmt = DbTools.getCon().prepareStatement(query)) {
                    for (int i = 0; i < params.length; i++) {
                        pstmt.setObject(i + 1, params[i]);
                    }
                    try (ResultSet rs = pstmt.executeQuery()) {
                        ArrayList<City> cities = new ArrayList<>();
                        while (rs.next()) {
                            cities.add(new City(rs.getString("Name"), rs.getString("Country"), rs.getString("District"), rs.getInt("Population")));
                        }
                        DbTools.disconnect();
                        return cities;
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        } catch (SQLException | InterruptedException e) {
            System.out.println("Cant connect to database: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    /**
     * Print out a report with all the cities in the world ordered by population
     * @return a Formatted report as a String
     */
    public String getAllCitiesInWorldReport() {
        String query = CitySqlQueries.ALL_CITIES_WORLD;
        try {
            cities = runCityQuery(query);
            return formatCityReport(cities);
        } catch (SQLException | InterruptedException e) {
            System.out.println("Error generating city report: " + e.getMessage());
            return "Error generating city report.";
        }
    }

    /**
     * Print out a report with all the cities in a continent ordered by population
     * @param continent A String continent name
     * @return a Formatted report as a String
     */
    public String getAllCitiesInContinentReport(String continent) {
        String query = CitySqlQueries.ALL_CITIES_CONTINENT;
        try {
            cities = runCityQuery(query, continent);
            return formatCityReport(cities);
        } catch (SQLException | InterruptedException e) {
            System.out.println("Error generating city report for continent " + continent + ": " + e.getMessage());
            return "Error generating city report for " + continent + " continent.";
        }
    }
}
