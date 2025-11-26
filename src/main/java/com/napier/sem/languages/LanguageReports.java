package com.napier.sem.languages;

import com.napier.sem.tools.DbTools;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class LanguageReports {

    // Attributes
    private ArrayList<Language> languages;

    /**
     * Method to format a report of languages into a string
     * @param languages The list of languages to include in the report
     * @return A formatted string report of the languages
     */
    public String formatLanguageReport(ArrayList<Language> languages) {
        StringBuilder languageReport = new StringBuilder();
        if (languages == null || languages.isEmpty()) {
            languageReport.append("No languages found");
        } else {
            languageReport.append(String.format("%-20s %-15s %-15s%n", "Language", "Speakers", "Percentage"));
            for (Language language : languages) {
                if (language != null) {
                    languageReport.append(String.format("%-20s %-15d %-15.2f%n", language.getLanguage(), language.getSpeakers(), language.getPercentage()));
                }
            }
        }
        return languageReport.toString();
    }

    /**
     * A method to run a SQL query that retrieves language data and returns it as an ArrayList of Language objects
     * @param query The SQL query to execute
     * @param params Optional parameters for the PreparedStatement
     * @return ArrayList of Language objects containing the query results
     * @throws SQLException if a database access error occurs
     * @throws InterruptedException if the operation is interrupted
     * @throws RuntimeException for other runtime exceptions
     */
    public ArrayList<Language> runLanguageQuery(String query, Object... params) throws SQLException, InterruptedException, RuntimeException{

        if (DbTools.getCon() == null) {
            throw new SQLException("No DB connection. Call DbTools.connect() before executing queries.");
        }

        ArrayList<Language> languages = new ArrayList<>();

        // No params → simple Statement
        if (params == null || params.length == 0) {
            try (Statement st = DbTools.getCon().createStatement();
                 ResultSet rs = st.executeQuery(query)) {
                while (rs.next()) {
                    languages.add(new Language(
                            rs.getString("Language"),
                            rs.getInt("Speakers"),
                            rs.getDouble("Percentage")
                    ));
                }
                return languages;
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
                    languages.add(new Language(
                            rs.getString("Language"),
                            rs.getInt("Speakers"),
                            rs.getDouble("Percentage")));
                }
                return languages;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * Print out a report that provides the number of people who speak Chinese, English, Hindi, Spanish, and Arabic as a percentage of the world population from the greatest number to smallest.
     * @return A formatted string report of the languages
     */
    public String getLanguageReport() {
        String query = LanguageSqlQueries.LANGUAGES_BY_SPEAKERS;
        try {
            languages = runLanguageQuery(query);
            return formatLanguageReport(languages);
        } catch (SQLException | InterruptedException e) {
            System.out.println("Error retrieving language report: " + e.getMessage());
            return "Error retrieving language report.";
        }
        }
}
