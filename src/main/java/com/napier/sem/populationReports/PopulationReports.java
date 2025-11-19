package com.napier.sem.populationReports;
import com.napier.sem.cities.City;
import com.napier.sem.tools.DbTools;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
public class PopulationReports {
    //private ArrayList<Population> Populations;
    public ArrayList<Population> runPopulationQuery(String query, Object... params) throws SQLException, InterruptedException, RuntimeException {
        if (DbTools.getCon() == null) {
            throw new SQLException("No DB connection. Call DbTools.connect() before executing queries.");
        }
        ArrayList<Population> populations = new ArrayList<>();

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
                return populations;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } else {
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
                return populations;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public String getPopulationReport(String areaType, Connection con) throws SQLException{//remove

        if (areaType.equals("continent")){
            List<Population> continents = getContinentPopulationReport(con);
            for (Population p : continents) {
                p.displayReport();
            }
        }
        if (areaType.equals("region")){
            List<Population> region = getRegionPopulationReport(con);
            for (Population p : region) {
                p.displayReport();
            }
        }
        if (areaType.equals("country")){
            List<Population> country = getCountryPopulationReport(con);
            for (Population p : country) {
                p.displayReport();
            }
        }


    }
    public List<Population> getContinentPopulationReport(Connection con) throws SQLException {
        List<Population> list = new ArrayList<Population>();

        String sql =
                "SELECT continent, " +
                        "SUM(country.Population) AS totalPopulation, " +
                        "SUM(city.Population) AS cityPopulation, " +
                        "(SUM(country.Population) - SUM(city.Population)) AS nonCityPopulation " +
                        "FROM country " +
                        "LEFT JOIN city ON country.Code = city.CountryCode " +
                        "GROUP BY continent;";

        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(sql);

        while (rs.next()) {
            String name = rs.getString("continent");
            long total = rs.getLong("totalPopulation");
            long city = rs.getLong("cityPopulation");
            long nonCity = rs.getLong("nonCityPopulation");

            Population pop = new Population(name, total, city, nonCity);
            list.add(pop);
        }

        return list;
    }
    public List<Population> getRegionPopulationReport(Connection con) throws SQLException {
        List<Population> list = new ArrayList<Population>();

        String sql =
                "SELECT region, " +
                        "SUM(country.Population) AS totalPopulation, " +
                        "SUM(city.Population) AS cityPopulation, " +
                        "(SUM(country.Population) - SUM(city.Population)) AS nonCityPopulation " +
                        "FROM country " +
                        "LEFT JOIN city ON country.Code = city.CountryCode " +
                        "GROUP BY region;";

        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(sql);

        while (rs.next()) {
            String name = rs.getString("region");
            long total = rs.getLong("totalPopulation");
            long city = rs.getLong("cityPopulation");
            long nonCity = rs.getLong("nonCityPopulation");

            list.add(new Population(name, total, city, nonCity));
        }

        return list;
    }
    public List<Population> getCountryPopulationReport(Connection con) throws SQLException {
        List<Population> list = new ArrayList<Population>();

        String sql =
                "SELECT country.Name AS name, " +
                        "country.Population AS totalPopulation, " +
                        "SUM(city.Population) AS cityPopulation, " +
                        "(country.Population - SUM(city.Population)) AS nonCityPopulation " +
                        "FROM country " +
                        "LEFT JOIN city ON country.Code = city.CountryCode " +
                        "GROUP BY country.Code;";

        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(sql);

        while (rs.next()) {
            String name = rs.getString("name");
            long total = rs.getLong("totalPopulation");
            long city = rs.getLong("cityPopulation");
            long nonCity = rs.getLong("nonCityPopulation");

            list.add(new Population(name, total, city, nonCity));
        }

        return list;
    }


}

