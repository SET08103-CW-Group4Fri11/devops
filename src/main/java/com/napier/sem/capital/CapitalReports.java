package com.napier.sem.capital;

import com.napier.sem.tools.DbTools;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class CapitalReports {

    // Attributes
    private ArrayList<Capital> capitals;

    /* =========================
       Low-level query runner
       ========================= */

    /**
     * Method to run a capital city SQL query and return the results as an ArrayList of Capital objects
     * @param query The SQL query to execute
     * @param params Optional parameters for the PreparedStatement
     * @return ArrayList of Capital objects containing the query results
     * @throws SQLException if a database access error occurs
     * @throws InterruptedException if the operation is interrupted
     * @throws RuntimeException for other runtime exceptions
     */
    public ArrayList<Capital> runCapitalQuery(String query, Object... params)
            throws SQLException, InterruptedException, RuntimeException {

        if (DbTools.getCon() == null) {
            throw new SQLException("No DB connection. Call DbTools.connect() before executing queries.");
        }

        ArrayList<Capital> capitals = new ArrayList<>();

        // No params → simple Statement
        if (params == null || params.length == 0) {
            try (Statement st = DbTools.getCon().createStatement();
                 ResultSet rs = st.executeQuery(query)) {
                while (rs.next()) {
                    capitals.add(new Capital(
                            rs.getString("Name"),
                            rs.getString("Country"),
                            rs.getInt("Population")));
                }
                return capitals;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } else {
            // With params → PreparedStatement
            try (PreparedStatement ps = DbTools.getCon().prepareStatement(query)) {
                for (int i = 0; i < params.length; i++) {
                    ps.setObject(i + 1, params[i]);
                }
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    capitals.add(new Capital(
                            rs.getString("Name"),
                            rs.getString("Country"),
                            rs.getInt("Population")));
                }
                return capitals;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /* =========================
       Formatting (pure)
       ========================= */

    /**
     * Method to format a report of capitals
     * @param capitals ArrayList of capital cities
     * @return String with the formatted report
     */
    public String formatCapitalReport(ArrayList<Capital> capitals) {
        if (capitals == null || capitals.isEmpty()) return "No capital data found";

        StringBuilder capitalReport = new StringBuilder();
        capitalReport.append(String.format("%-30s %-30s %-12s%n", "Name", "Country", "Population"));
        for (Capital capital : capitals) {
            if (capital == null) continue;
            capitalReport.append(String.format("%-30s %-30s %-12d%n", capital.getName(), capital.getCountry(), capital.getPopulation()));
        }
        return capitalReport.toString();
    }

    /* =========================
       High-level report methods
       (wrap query + formatting)
       ========================= */

    /**
     * Print out a report of all capital cities in the world
     * @return String with the formatted report or error message
     */
    public String getAllCapitalsWorldReport() {
        String query = CapitalSqlQueries.ALL_CAPITALS_WORLD;
        try {
//            System.out.println("A report of all the capital cities in the world:");
            capitals = runCapitalQuery(query);
            return formatCapitalReport(capitals);
        } catch (SQLException | InterruptedException e) {
            System.out.println("Error generating world capitals report: " + e.getMessage());
            return "Error generating world capitals report.";
        }
    }

    /**
     * Print out a report of all capital cities in a continent
     * @param continent The continent to filter by
     * @return String with the formatted report or error message
     */
    public String getAllCapitalsInContinentReport(String continent) {
        String query = CapitalSqlQueries.ALL_CAPITALS_CONTINENT;
        try {
//            System.out.println("A report of all the capital cities in " + continent + ":");
            capitals = runCapitalQuery(query, continent);
            return formatCapitalReport(capitals);
        } catch (SQLException | InterruptedException e) {
            System.out.println("Error generating continent capitals report: " + e.getMessage());
            return "Error generating continent capitals report.";
        }
    }

    /**
     * Print out a report of all capital cities in a region
     * @param region The region to filter by
     * @return String with the formatted report or error message
     */
    public String getAllCapitalsInRegionReport(String region) {
        String query = CapitalSqlQueries.ALL_CAPITALS_REGION;
        try {
//            System.out.println("A report of all the capital cities in " + region + ":");
            capitals = runCapitalQuery(query, region);
            return formatCapitalReport(capitals);
        } catch (SQLException  | InterruptedException e) {
            System.out.println("Error generating region capitals report: " + e.getMessage());
            return "Error generating region capitals report.";
        }
    }

    /**
     * Print out a report of the top N capital cities in the world
     * @param n The number of top capitals to retrieve
     * @return String with the formatted report or error message
     */
    public String getTopNCapitalsWorldReport(int n) {
        if (n <= 0) return "No capital data found";
        String query = CapitalSqlQueries.ALL_CAPITALS_WORLD.replace(";", " LIMIT ?;");
        try {
//            System.out.println("A report of the top " + n + " capitals in the world:");
            var capitals = runCapitalQuery(query, n);
            return formatCapitalReport(capitals);
        } catch (SQLException | InterruptedException e) {
            System.out.println("Error generating top " + n + " world capitals report: " + e.getMessage());
            return "Error generating top " + n + " world capitals report.";
        }
    }

    /**
     * Print out a report of the top N capital cities in a continent
     * @param continent The continent to filter by
     * @param n The number of top capitals to retrieve
     * @return String with the formatted report or error message
     */
    public String getTopNCapitalsInContinentReport(String continent, int n) {
        if (n <= 0) return "No capital data found";
        String query = CapitalSqlQueries.ALL_CAPITALS_CONTINENT.replace(";", " LIMIT ?;");
        try {
//            System.out.println("A report of the top " + n + " capitals in " + continent + ":");
            capitals = runCapitalQuery(query, continent, n);
            return formatCapitalReport(capitals);
        } catch (SQLException | InterruptedException e) {
            System.out.println("Error generating top " + n + " continent capitals report: " + e.getMessage());
            return "Error generating top " + n + " continent capitals report.";
        }
    }

    /**
     * Print out a report of the top N capital cities in a region
     * @param region The region to filter by
     * @param n The number of top capitals to retrieve
     * @return String with the formatted report or error message
     */
    public String getTopNCapitalsInRegionReport(String region, int n) {
        if (n <= 0) return "No capital data found";
        String query = CapitalSqlQueries.ALL_CAPITALS_REGION.replace(";", " LIMIT ?;");
        try {
//            System.out.println("A report of the top " + n + " capitals in " + region + ":");
            capitals = runCapitalQuery(query, region, n);
            return formatCapitalReport(capitals);
        } catch (SQLException | InterruptedException e) {
            System.out.println("Error generating top " + n + " region capitals report: " + e.getMessage());
            return "Error generating top " + n + " region capitals report.";
        }
    }
}
