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

    /* Methods */
    /**
     * Prints out a report with all the countries in the world, arranged by population, largest to smallest
     */
    public void allCountriesInWorldReport(){
        countryList = new ArrayList<Country>();
        try {
            DbTools.connect();
            Statement stmt = DbTools.getCon().createStatement();
            ResultSet rs = stmt.executeQuery("Select code, name, continent, region, population, capital from country order by population desc;");
            countryList.clear();
            while (rs.next())
                countryList.add(new Country(rs.getString("Code"), rs.getString("Name"), rs.getString("Continent"), rs.getString("Region"), rs.getInt("population"), rs.getString("Capital") ));
        }catch (SQLException e){
            System.out.println("something went wrong with this");
            throw new RuntimeException(e.getMessage());
        } finally {
            System.out.println(countryList.toString());
            DbTools.disconnect();
        }
    }
    /**
     * Prints out a report with all the countries in a continent, arranged by population, largest to smallest
     */
    public void allCountriesInContinentReport(String continent){
        try {
            DbTools.connect();
            Statement stmt = DbTools.getCon().createStatement();
            ResultSet rs = stmt.executeQuery("Select code, name, continent, region, population, capital from country order by population desc;");
            countryList.clear();
            while (rs.next())
                countryList.add(new Country(rs.getString("Code"), rs.getString("Name"), rs.getString("Continent"), rs.getString("Region"), rs.getInt("population"), rs.getString("Capital") ));
        }catch (SQLException e){
            System.out.println("something went wrong with this");
            throw new RuntimeException(e.getMessage());
        } finally {
            System.out.println(countryList.toString());
            DbTools.disconnect();
        }
    }

    /**
     * Prints out a report showing all countries in a region, arranged by population, largest to smallest
     * @param region A string containing the region
     */
    public void allCountriesInRegionReport(String region){
        try {
            DbTools.connect();
            Statement stmt = DbTools.getCon().createStatement();
            ResultSet rs = stmt.executeQuery("Select code, name, continent, region, population, capital from country order by population desc;");
            countryList.clear();
            while (rs.next())
                countryList.add(new Country(rs.getString("Code"), rs.getString("Name"), rs.getString("Continent"), rs.getString("Region"), rs.getInt("population"), rs.getString("Capital") ));
        }catch (SQLException e){
            System.out.println("something went wrong with this");
            throw new RuntimeException(e.getMessage());
        } finally {
            System.out.println(countryList.toString());
            DbTools.disconnect();
        }
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
