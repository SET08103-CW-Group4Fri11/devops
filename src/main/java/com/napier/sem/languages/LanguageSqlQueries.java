package com.napier.sem.languages;

public class LanguageSqlQueries {

    private LanguageSqlQueries() {  } // Prevent instantiation

    // SQL Queries for Language Reports

    public static final String CH_EN_HI_SP_AR_SPEAKERS_WORLD =
            "SELECT Language, SUM(Population * (Percentage / 100)) AS Speakers, " +
                    "(SUM(Population * (Percentage / 100)) / (SELECT SUM(Population) FROM country) * 100) AS Percentage " +
                    "FROM countrylanguage " +
                    "JOIN country ON countrylanguage.CountryCode = country.Code " +
                    "WHERE Language IN ('Chinese', 'English', 'Hindi', 'Spanish', 'Arabic') " +
                    "GROUP BY Language " +
                    "ORDER BY Speakers DESC;";
}
