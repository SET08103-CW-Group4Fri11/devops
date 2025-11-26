package com.napier.sem.populationReports;

import com.napier.sem.tools.DbTools;
import java.sql.*;

public class SpecificPopulationReports {

    /** private function for running sql queries*/
    private Long runQuery(String sql, Object... params) throws SQLException {
        if (DbTools.getCon() == null) {
            throw new SQLException("No DB connection. Call DbTools.connect() first.");
        }
        try (PreparedStatement preStatement = DbTools.getCon().prepareStatement(sql)) {
            for (int i = 0; i < params.length; i++) {
                preStatement.setObject(i + 1, params[i]);
            }

            ResultSet rs = preStatement.executeQuery();
            if (rs.next()) {
                return rs.getLong(1);
            } else {
                return null;
            }
        }
    }
    /**
     * This method returns the population of the specified population area and the name of it.
     * -
     * Example Usage:
     * -
     * SpecificPopulationReports example = new SpecificPopulationReports();
     * String name = "Example Name";
     * String type = "Example Type";
     * try {
     *      Long pop = example.getPopulation(type, name);
     *      System.out.println("Population of " + name + " (" + type + "): " + pop);
     *} catch (SQLException e) {
     *      System.err.println("Error fetching population for " + name + ": " + e.getMessage());
     *}
     **/
    public Long getPopulation(String type, String name) throws SQLException {
        String sql;

        switch (type.toLowerCase()) {
            case "world":
                sql = "SELECT SUM(Population) FROM country";
                return runQuery(sql);
            case "continent":
                sql = "SELECT SUM(Population) FROM country WHERE Continent = ?";
                return runQuery(sql, name);
            case "region":
                sql = "SELECT SUM(Population) FROM country WHERE Region = ?";
                return runQuery(sql, name);
            case "country":
                sql = "SELECT Population FROM country WHERE Name = ?";
                return runQuery(sql, name);
            case "city":
                sql = "SELECT Population FROM city WHERE Name = ?";
                return runQuery(sql, name);
            default:
                throw new IllegalArgumentException("Unknown type: " + type);
        }
    }
}
