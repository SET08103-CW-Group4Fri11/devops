
package com.napier.sem.capital;

import com.napier.sem.tools.DbTools;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CapitalReports {

    /* =========================
       Low-level query runner
       ========================= */
    public ArrayList<Capital> runCapitalQuery(String sql, Object... params)
            throws SQLException {

        if (DbTools.getCon() == null) {
            throw new SQLException("No DB connection. Call DbTools.connect() before executing queries.");
        }

        ArrayList<Capital> rows = new ArrayList<>();

        // No params → simple Statement
        if (params == null || params.length == 0) {
            try (Statement st = DbTools.getCon().createStatement();
                 ResultSet rs = st.executeQuery(sql)) {
                while (rs.next()) {
                    rows.add(new Capital(
                            rs.getString("Capital"),
                            rs.getString("Country"),
                            rs.getInt("Population")));
                }
            }
            return rows;
        }

        // With params → PreparedStatement
        try (PreparedStatement ps = DbTools.getCon().prepareStatement(sql)) {
            for (int i = 0; i < params.length; i++) {
                ps.setObject(i + 1, params[i]);
            }
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    rows.add(new Capital(
                            rs.getString("Capital"),
                            rs.getString("Country"),
                            rs.getInt("Population")));
                }
            }
        }
        return rows;
    }

    /* =========================
       Formatting (pure)
       ========================= */
    public String formatCapitalReport(List<Capital> rows) {
        if (rows == null || rows.isEmpty()) return "No capital data found";

        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%-30s %-30s %-12s%n", "Capital", "Country", "Population"));
        for (Capital r : rows) {
            if (r == null) continue;
            sb.append(String.format("%-30s %-30s %-12d%n", r.capital(), r.country(), r.population()));
        }
        return sb.toString();
    }

    /* =========================
       High-level report methods
       (wrap query + formatting)
       ========================= */

    public String getAllCapitalsWorldReport() {
        try {
            var rows = runCapitalQuery(CapitalSqlQueries.ALL_CAPITALS_WORLD);
            return formatCapitalReport(rows);
        } catch (SQLException e) {
            System.out.println("Error generating world capitals report: " + e.getMessage());
            return "Error generating world capitals report.";
        }
    }

    public String getAllCapitalsInContinentReport(String continent) {
        try {
            var rows = runCapitalQuery(CapitalSqlQueries.ALL_CAPITALS_CONTINENT, continent);
            return formatCapitalReport(rows);
        } catch (SQLException e) {
            System.out.println("Error generating continent capitals report: " + e.getMessage());
            return "Error generating continent capitals report.";
        }
    }

    public String getAllCapitalsInRegionReport(String region) {
        try {
            var rows = runCapitalQuery(CapitalSqlQueries.ALL_CAPITALS_REGION, region);
            return formatCapitalReport(rows);
        } catch (SQLException e) {
            System.out.println("Error generating region capitals report: " + e.getMessage());
            return "Error generating region capitals report.";
        }
    }

    public String getTopNCapitalsWorldReport(int n) {
        if (n <= 0) return "No capital data found";
        var sql = CapitalSqlQueries.ALL_CAPITALS_WORLD.replace(";", " LIMIT ?;");
        try {
            var rows = runCapitalQuery(sql, n);
            return formatCapitalReport(rows);
        } catch (SQLException e) {
            System.out.println("Error generating top N world capitals report: " + e.getMessage());
            return "Error generating top N world capitals report.";
        }
    }

    public String getTopNCapitalsInContinentReport(String continent, int n) {
        if (n <= 0) return "No capital data found";
        var sql = CapitalSqlQueries.ALL_CAPITALS_CONTINENT.replace(";", " LIMIT ?;");
        try {
            var rows = runCapitalQuery(sql, continent, n);
            return formatCapitalReport(rows);
        } catch (SQLException e) {
            System.out.println("Error generating top N continent capitals report: " + e.getMessage());
            return "Error generating top N continent capitals report.";
        }
    }

    public String getTopNCapitalsInRegionReport(String region, int n) {
        if (n <= 0) return "No capital data found";
        var sql = CapitalSqlQueries.ALL_CAPITALS_REGION.replace(";", " LIMIT ?;");
        try {
            var rows = runCapitalQuery(sql, region, n);
            return formatCapitalReport(rows);
        } catch (SQLException e) {
            System.out.println("Error generating top N region capitals report: " + e.getMessage());
            return "Error generating top N region capitals report.";
        }
    }
}
