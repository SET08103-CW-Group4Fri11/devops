package com.napier.sem.cities;

import com.napier.sem.tools.DbTools;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class CityReports {

    // Attributes
    private ArrayList<City> cities;

    /**
     * Method to format a report of cities
     * @param cities ArrayList of cities
     * @return String with the formatted report
     */
    public String formatCityReport(ArrayList<City> cities) {
        StringBuilder cityReport = new StringBuilder();
        if (cities == null || cities.isEmpty()) {
            cityReport.append("No cities found");
        } else {
            cityReport.append(String.format("%-35s %-40s %-30s %-10s%n", "Name", "Country", "District", "Population"));
            for (City city : cities) {
                if (city != null) {
                    cityReport.append(String.format("%-35s %-40s %-30s %-10d%n", city.getName(), city.getCountry(), city.getDistrict(), city.getPopulation()));
                }
            }
        }
        return cityReport.toString();
    }

    /**
     * A method to run a SQL query that stores the different queried values in an ArrayList of cities
     * @param query  the SQL query to be executed
     * @param params the parameters for the prepared statement
     * @return ArrayList of City
     * @throws SQLException         when it cannot connect to the DB
     * @throws InterruptedException when it cannot connect to the DB
     * @throws RuntimeException     when the query is incorrect
     */
    public ArrayList<City> runCityQuery(String query, Object... params) throws SQLException, InterruptedException, RuntimeException {

        if (DbTools.getCon() == null) {
            throw new SQLException("No DB connection. Call DbTools.connect() before executing queries.");
        }
        ArrayList<City> cities = new ArrayList<>();

        // No params provided
        if (params == null || params.length == 0) {
            try (Statement stmt = DbTools.getCon().createStatement()) {
                ResultSet rs = stmt.executeQuery(query);
                while (rs.next()) {
                    cities.add(new City(rs.getString("Name"), rs.getString("Country"), rs.getString("District"), rs.getInt("Population")));
                }
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
                ResultSet rs = pstmt.executeQuery();
                while (rs.next()) {
                    cities.add(new City(rs.getString("Name"), rs.getString("Country"), rs.getString("District"), rs.getInt("Population")));
                }
                return cities;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * Print out a report with all the cities in the world ordered by population
     * @return a Formatted report as a String or an error message
     */
    public String getAllCitiesInWorldReport() {
        String query = CitySqlQueries.ALL_CITIES_WORLD;
        try {
//            System.out.println("A report of all the cities in the world ordered by population:");
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
     * @return a Formatted report as a String or an error message
     */
    public String getAllCitiesInContinentReport(String continent) {
        if (continent==null || continent.isEmpty()) {
            return "No cities found";
        }
        String query = CitySqlQueries.ALL_CITIES_CONTINENT;
        try {
//            System.out.println("A report of all the cities in " + continent + " continent ordered by population:");
            cities = runCityQuery(query, continent);
            return formatCityReport(cities);
        } catch (SQLException | InterruptedException e) {
            System.out.println("Error generating city report for " + continent + " continent: " + e.getMessage());
            return "Error generating city report for " + continent + " continent.";
        }
    }

    /**
     * Print out a report with all the cities in a region ordered by population
     * @param region A String region name
     * @return a Formatted report as a String or an error message
     */
    public String getAllCitiesInRegionReport(String region) {
        if (region==null || region.isEmpty()) {
            return "No cities found";
        }
        String query = CitySqlQueries.ALL_CITIES_REGION;
        try {
//            System.out.println("A report of all the cities in " + region + " region ordered by population:");
            cities = runCityQuery(query, region);
            return formatCityReport(cities);
        } catch (SQLException | InterruptedException e) {
            System.out.println("Error generating city report for " + region + " region: " + e.getMessage());
            return "Error generating city report for " + region + " region.";
        }
    }

    /**
     * Print out a report with all the cities in a country ordered by population
     * @param country A String country name
     * @return a Formatted report as a String or an error message
     */
    public String getAllCitiesInCountryReport(String country) {
        if (country==null || country.isEmpty()) {
            return "No cities found";
        }
        String query = CitySqlQueries.ALL_CITIES_COUNTRY;
        try {
//            System.out.println("A report of all the cities in " + country + " country ordered by population:");
            cities = runCityQuery(query, country);
            return formatCityReport(cities);
        } catch (SQLException | InterruptedException e) {
            System.out.println("Error generating city report for " + country + " country: " + e.getMessage());
            return "Error generating city report for " + country + " country.";
        }
    }

    /**
     * Print out a report with all the cities in a district ordered by population
     * @param district A String district name
     * @return a Formatted report as a String or an error message
     */
    public String getAllCitiesInDistrictReport(String district) {
        if (district==null || district.isEmpty()) {
            return "No cities found";
        }
        String query = CitySqlQueries.ALL_CITIES_DISTRICT;
        try {
//            System.out.println("A report of all the cities in " + district + " district ordered by population:");
            cities = runCityQuery(query, district);
            return formatCityReport(cities);
        } catch (SQLException | InterruptedException e) {
            System.out.println("Error generating city report for " + district + " district: " + e.getMessage());
            return "Error generating city report for " + district + " district.";
        }
    }

    /**
     * Print out a report with the top N cities in the world ordered by population
     * @param n An integer number of cities
     * @return a Formatted report as a String or an error message
     */
    public String getTopNCitiesInWorldReport(int n) {
        if (n <= 0) {
            return "No cities found";
        }
        String query = CitySqlQueries.ALL_CITIES_WORLD.replaceAll(";", " LIMIT ?;"); // Add LIMIT clause
        try {
//            System.out.println("A report of the top " + n + " cities in the world ordered by population:");
            cities = runCityQuery(query, n);
            return formatCityReport(cities);
        } catch (SQLException | InterruptedException e) {
            System.out.println("Error generating top " + n + " cities in the world report: " + e.getMessage());
            return "Error generating top " + n + " cities in the world report.";
        }
    }

    /**
     * Print out a report with the top N cities in a continent ordered by population
     * @param n         An integer number of cities
     * @param continent A String continent name
     * @return a Formatted report as a String or an error message
     */
    public String getTopNCitiesInContinentReport(int n, String continent) {
        if (n <= 0) {
            return "No cities found";
        }
        String query = CitySqlQueries.ALL_CITIES_CONTINENT.replaceAll(";", " LIMIT ?;"); // Add LIMIT clause
        try {
//            System.out.println("A report of the top " + n + " cities in " + continent + " continent ordered by population:");
            cities = runCityQuery(query, continent, n);
            return formatCityReport(cities);
        } catch (SQLException | InterruptedException e) {
            System.out.println("Error generating top " + n + " cities in " + continent + " continent report: " + e.getMessage());
            return "Error generating top " + n + " cities in " + continent + " continent report.";
        }
    }

    /**
     * Print out a report with the top N cities in a region ordered by population
     * @param n      An integer number of cities
     * @param region A String region name
     * @return a Formatted report as a String or an error message
     */
    public String getTopNCitiesInRegionReport(int n, String region) {
        if (n <= 0) {
            return "No cities found";
        }
        String query = CitySqlQueries.ALL_CITIES_REGION.replaceAll(";", " LIMIT ?;"); // Add LIMIT clause
        try {
//            System.out.println("A report of the top " + n + " cities in " + region + " region ordered by population:");
            cities = runCityQuery(query, region, n);
            return formatCityReport(cities);
        } catch (SQLException | InterruptedException e) {
            System.out.println("Error generating top " + n + " cities in " + region + " region report: " + e.getMessage());
            return "Error generating top " + n + " cities in " + region + " region report.";
        }
    }

    /**
     * Print out a report with the top N cities in a country ordered by population
     * @param n       An integer number of cities
     * @param country A String country name
     * @return a Formatted report as a String or an error message
     */
    public String getTopNCitiesInCountryReport(int n, String country) {
        if (n <= 0) {
            return "No cities found";
        }
        String query = CitySqlQueries.ALL_CITIES_COUNTRY.replaceAll(";", " LIMIT ?;"); // Add LIMIT clause
        try {
//            System.out.println("A report of the top " + n + " cities in " + country + " country ordered by population:");
            cities = runCityQuery(query, country, n);
            return formatCityReport(cities);
        } catch (SQLException | InterruptedException e) {
            System.out.println("Error generating top " + n + " cities in " + country + " country report: " + e.getMessage());
            return "Error generating top " + n + " cities in " + country + " country report.";
        }
    }

    /**
     * Print out a report with the top N cities in a district ordered by population
     * @param n        An integer number of cities
     * @param district A String district name
     * @return a Formatted report as a String or an error message
     */
    public String getTopNCitiesInDistrictReport(int n, String district) {
        if (n <= 0) {
            return "No cities found";
        }
        String query = CitySqlQueries.ALL_CITIES_DISTRICT.replaceAll(";", " LIMIT ?;"); // Add LIMIT clause
        try {
//            System.out.println("A report of the top " + n + " cities in " + district + " district ordered by population:");
            cities = runCityQuery(query, district, n);
            return formatCityReport(cities);
        } catch (SQLException | InterruptedException e) {
            System.out.println("Error generating top " + n + " cities in " + district + " district report: " + e.getMessage());
            return "Error generating top " + n + " cities in " + district + " district report.";
        }
    }
}
