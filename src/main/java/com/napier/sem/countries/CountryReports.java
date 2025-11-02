package com.napier.sem.countries;
import com.napier.sem.tools.DbTools;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class CountryReports {

    /* Attributes */
    private static ArrayList<Country> countryList;

    /**
     * Method that formats a country report from an ArrayList of countries
     * @param countries
     * @return String with the formatted report
     */
    /* Methods */
    public String formatCountryReport(ArrayList<Country> countries){
        StringBuilder countryReport = new StringBuilder();
        if (countries == null || countries.isEmpty()){
            countryReport.append("No countries found");
        }else  {
            countryReport.append(String.format("%-4s %-60s %-15s %-30s %-10s %-15s%n", "Code", "Name", "Continent", "Region", "Population", "Capital"));
            for (Country country : countries){
                if(country != null){
                    countryReport.append(String.format("%-4s %-60s %-15s %-30s %-10d %-15s%n", country.getCode(), country.getName(), country.getContinent(), country.getRegion(), country.getPopulation(), country.getCapital()));
                }
            }
        }
        return countryReport.toString();
    }

    /**
     * A method to run a SQL query that stores the different queried values in an ArrayList of countries
     * @param query
     * @return ArrayList of Country
     * @throws SQLException when it cannot connect to the DB
     * @throws InterruptedException when it cannot connect to the DB
     * @throws RuntimeException when the query is incorrect
     */
    public ArrayList<Country> runCountryQuery(String query) throws SQLException, InterruptedException, RuntimeException {
        try {
            DbTools.connect();
            try (Statement stmt = DbTools.getCon().createStatement()) {
                ResultSet rs = stmt.executeQuery(query);
                ArrayList<Country> countries = new ArrayList<Country>();
                while (rs.next()) {
                    countries.add(new Country(rs.getString("Code"), rs.getString("Name"), rs.getString("Continent"), rs.getString("Region"), rs.getInt("population"), rs.getString("Capital") ));
                }
                DbTools.disconnect();
                return countries;
            }catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } catch (SQLException | InterruptedException e) {
            System.out.println("Cant connect to database: "+e.getMessage());
            throw new RuntimeException(e);
        }
    }

    /**
     * Prints out a report with all the countries in the world, arranged by population, largest to smallest
     * @return a String containing a formatted report with all countries in the world
     */
    public String allCountriesInWorldReport(){
        String query = "Select c.code, c.name, c.continent, c.region, c.population, ci.name as Capital from country c join city ci on c.capital = ci.id order by c.population desc;";
        try {
            countryList = runCountryQuery(query);
        } catch (SQLException | InterruptedException | RuntimeException e) {
            System.out.println("Something went wrong with the query: " + e.getMessage());
        }
        return formatCountryReport(countryList);
    }
    /**
     * Prints out a report with all the countries in a continent, arranged by population, largest to smallest
     * @param continent A string with the name of a continent
     * @return a String containing a formatted report with all countries in the specified continent
     */
    public String allCountriesInContinentReport(String continent){
        String query = "Select c.code, c.name, c.continent, c.region, c.population, ci.name as Capital from country c join city ci on c.capital = ci.id where c.continent = "+ "'" +continent+ "'" +" order by c.population desc;";
        try {
            countryList = runCountryQuery(query);
        }catch (SQLException | InterruptedException | RuntimeException e) {
            System.out.println(e.getMessage());
        }
        return formatCountryReport(countryList);
    }

    /**
     * Prints out a report showing all countries in a region, arranged by population, largest to smallest
     * @param region A string containing the region
     */
    public void allCountriesInRegionReport(String region){
    }

    /**
     * Prints a report with the inputted number of countries, arranged by population
     * @param numberOfCountries Integer
     */
    public void topNCountriesInWorldReport(int numberOfCountries){
        System.out.println("A report with the top " + numberOfCountries + " countries in the world");
    }


    /**
     * Prints a report  of the top n countries belonging to a continent
     * @param numberOfCountries
     * @param continent
     */
    public void topNCountriesInContinentReport(int numberOfCountries, String continent){
        System.out.println("A report with the top " + numberOfCountries + " countries in a continent");
    }

    /**
     * Prints a report  of the top n countries belonging to a region
     * @param numberOfCountries
     * @param region
     */
    public void topNCountriesInRegionReport(int numberOfCountries, String region){
        System.out.println("A report with the top " + numberOfCountries + " countries in a region");
    }

    /**
     * This method prints a selection screen that the user can use to access whatever country reports they need
     */
    public void reportSelectionScreen() {
        boolean goBack = false;
        int userInput;

        while (!goBack) {
            System.out.println("Please select the type of report:");
            System.out.println("1. All the countries in the world organised by largest population to smallest.");
            System.out.println("2. All the countries in a continent organised by largest population to smallest.");
            System.out.println("3. All the countries in a region organised by largest population to smallest.");
            System.out.println("4. The top N populated countries in the world where N is provided by the user.");
            System.out.println("5. The top N populated countries in a continent where N is provided by the user.");
            System.out.println("6. The top N populated countries in a region where N is provided by the user.");
            System.out.println("0. Go back to main menu.");
//          Read user's input for the report selection
            try {
                Scanner countryReportsScanner = new Scanner(System.in);
                userInput = countryReportsScanner.nextInt();
            }catch (InputMismatchException e){
                System.out.println("thats not a numeric input");
                userInput = 10;
            }
            Scanner topScanner = new Scanner(System.in);
            switch (userInput) {
                case 1: allCountriesInWorldReport(); break;
                case 2:
                    /*Need to make this ask for a continent*/
                    allCountriesInContinentReport("Asia");
                break;
                case 3:
                    /*Need to make this ask for a region*/
                    allCountriesInRegionReport("Caribbean");
                break;
                case 4:
                    try { // Read number of countries for top, alert user if not an integer
                        System.out.println("Please enter the number of countries you'd like to see in the report:");
                        topNCountriesInWorldReport(topScanner.nextInt());
                    }catch (InputMismatchException e){
                        System.out.println("Please enter an integer");
                    }finally {
                        break;
                    }
                case 5:
                    try { // Read number of countries for top, alert user if not an integer
                        System.out.println("Please enter the number of countries you'd like to see in the report:");
                        /*Need to make this ask for a continent*/
                        topNCountriesInContinentReport(topScanner.nextInt(),"Asia");
                    }catch (InputMismatchException e){
                        System.out.println("Please enter the number of countries you'd like to see in the report:");
                        System.out.println("Please enter an integer");
                    }finally {
                        break;
                    }
                case 6:
                    try { // Read number of countries for top, alert user if not an integer
                        /*Need to make this ask for a region*/
                        topNCountriesInRegionReport(topScanner.nextInt(),"Caribbean");
                    }catch (InputMismatchException e){
                        System.out.println("Please enter an integer");
                    }finally {
                        break;
                    }
                case 0: goBack = true; break;
                default: System.out.println("Invalid choice. Try again."); break;
            }
        }
    }

    @Override
    public String toString() {
        String finalString = "";
        for( Country country : countryList){
            finalString += country.toString() + "\n";
        }
        return finalString;
    }
}
