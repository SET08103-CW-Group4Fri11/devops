package com.napier.sem.capitals;

import com.napier.sem.tools.DbTools;

import java.sql.*;
import java.util.ArrayList;

public class CapitalReports {

    /* Attributes */
    private static ArrayList<Capital> capitalList;

    /**
     * Formats a capital report. Returns "No capital data found" for null/empty input.
     */
    public String formatCapitalReport(ArrayList<Capital> capitals) {
        StringBuilder out = new StringBuilder();
        if (capitals == null || capitals.isEmpty()) {
            out.append("No capital data found");
        } else {
            out.append(String.format("%-35s %-35s %-25s %-15s%n",
                    "Capital", "Country", "District", "Population"));
            for (Capital cap : capitals) {
                if (cap != null) {
                    out.append(String.format("%-35s %-35s %-25s %-15d%n",
                            cap.getName(), cap.getCountry(), cap.getDistrict(), cap.getPopulation()));
                }
            }
        }
        return out.toString();
    }

    /**
     * Runs a SQL query and maps rows to Capital objects (name, country, district, population).
     * Uses DbTools.getCon(); supports optional PreparedStatement params.
     */
    public ArrayList<Capital> runCapitalQuery(String query, Object... params)
            throws SQLException, InterruptedException, RuntimeException {

        if (DbTools.getCon() == null) {
            throw new SQLException("No DB connection. Call DbTools.connect() before executing queries.");
        }

        ArrayList<Capital> capitals = new ArrayList<>();

        // No params → simple Statement
        if (params == null || params.length == 0) {
            try (Statement stmt = DbTools.getCon().createStatement();
                 ResultSet rs = stmt.executeQuery(query)) {
                while (rs.next()) {
                    capitals.add(new Capital(
                            rs.getString("Capital"),
                            rs.getString("Country"),
                            rs.getString("District"),
                            rs.getInt("Population")
                    ));
                }
                return capitals;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        // With params → PreparedStatement
        try (PreparedStatement pstmt = DbTools.getCon().prepareStatement(query)) {
            for (int i = 0; i < params.length; i++) {
                pstmt.setObject(i + 1, params[i]);
            }
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    capitals.add(new Capital(
                            rs.getString("Capital"),
                            rs.getString("Country"),
                            rs.getString("District"),
                            rs.getInt("Population")
                    ));
                }
            }
            return capitals;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * All capital cities in the world, ordered by population desc.
     */
    public String getAllCapitalsWorldReport() {
        String query =
                "SELECT ci.Name AS Capital, co.Name AS Country, ci.District AS District, ci.Population " +
                        "FROM city ci " +
                        "JOIN country co ON ci.ID = co.Capital " +
                        "ORDER BY ci.Population DESC;";
        try {
            System.out.println("Report: All capitals in the world by population (desc).");
            capitalList = runCapitalQuery(query);
        } catch (SQLException | InterruptedException | RuntimeException e) {
            System.out.println("Error generating capitals (world) report: " + e.getMessage());
        }
        return formatCapitalReport(capitalList);
    }

    /**
     * All capital cities in a continent, ordered by population desc.
     */
    public String getAllCapitalsInContinentReport(String continent) {
        String query =
                "SELECT ci.Name AS Capital, co.Name AS Country, ci.District AS District, ci.Population " +
                        "FROM city ci " +
                        "JOIN country co ON ci.ID = co.Capital " +
                        "WHERE co.Continent = ? " +
                        "ORDER BY ci.Population DESC;";
        try {
            System.out.println("Report: Capitals in continent " + continent + " by population (desc).");
            capitalList = runCapitalQuery(query, continent);
        } catch (SQLException | InterruptedException | RuntimeException e) {
            System.out.println("Error generating capitals (continent) report: " + e.getMessage());
        }
        return formatCapitalReport(capitalList);
    }

    /**
     * All capital cities in a region, ordered by population desc.
     */
    public String getAllCapitalsInRegionReport(String region) {
        String query =
                "SELECT ci.Name AS Capital, co.Name AS Country, ci.District AS District, ci.Population " +
                        "FROM city ci " +
                        "JOIN country co ON ci.ID = co.Capital " +
                        "WHERE co.Region = ? " +
                        "ORDER BY ci.Population DESC;";
        try {
            System.out.println("Report: Capitals in region " + region + " by population (desc).");
            capitalList = runCapitalQuery(query, region);
        } catch (SQLException | InterruptedException | RuntimeException e) {
            System.out.println("Error generating capitals (region) report: " + e.getMessage());
        }
        return formatCapitalReport(capitalList);
    }

    /**
     * Top N capital cities in the world, ordered by population desc.
     */
    public String getTopNCapitalsWorldReport(int n) {
        if (n <= 0) return "No capital data found";
        String query =
                "SELECT ci.Name AS Capital, co.Name AS Country, ci.District AS District, ci.Population " +
                        "FROM city ci " +
                        "JOIN country co ON ci.ID = co.Capital " +
                        "ORDER BY ci.Population DESC " +
                        "LIMIT ?;";
        try {
            System.out.println("Report: Top " + n + " capitals in the world by population (desc).");
            capitalList = runCapitalQuery(query, n);
        } catch (SQLException | InterruptedException | RuntimeException e) {
            System.out.println("Error generating capitals (top N world) report: " + e.getMessage());
        }
        return formatCapitalReport(capitalList);
    }

    /**
     * Top N capital cities in a continent, ordered by population desc.
     */
    public String getTopNCapitalsInContinentReport(int n, String continent) {
        if (n <= 0) return "No capital data found";
        String query =
                "SELECT ci.Name AS Capital, co.Name AS Country, ci.District AS District, ci.Population " +
                        "FROM city ci " +
                        "JOIN country co ON ci.ID = co.Capital " +
                        "WHERE co.Continent = ? " +
                        "ORDER BY ci.Population DESC " +
                        "LIMIT ?;";
        try {
            System.out.println("Report: Top " + n + " capitals in continent " + continent + " by population (desc).");
            capitalList = runCapitalQuery(query, continent, n);
        } catch (SQLException | InterruptedException | RuntimeException e) {
            System.out.println("Error generating capitals (top N continent) report: " + e.getMessage());
        }
        return formatCapitalReport(capitalList);
    }

    /**
     * Top N capital cities in a region, ordered by population desc.
     */
    public String getTopNCapitalsInRegionReport(int n, String region) {
        if (n <= 0) return "No capital data found";
        String query =
                "SELECT ci.Name AS Capital, co.Name AS Country, ci.District AS District, ci.Population " +
                        "FROM city ci " +
                        "JOIN country co ON ci.ID = co.Capital " +
                        "WHERE co.Region = ? " +
                        "ORDER BY ci.Population DESC " +
                        "LIMIT ?;";
        try {
            System.out.println("Report: Top " + n + " capitals in region " + region + " by population (desc).");
            capitalList = runCapitalQuery(query, region, n);
        } catch (SQLException | InterruptedException | RuntimeException e) {
            System.out.println("Error generating capitals (top N region) report: " + e.getMessage());
        }
        return formatCapitalReport(capitalList);
    }
}
