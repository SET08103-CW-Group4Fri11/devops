package com.napier.sem;

import com.napier.sem.capital.CapitalReports;
import com.napier.sem.cities.CityReports;
import com.napier.sem.countries.CountryReports;
import com.napier.sem.tools.DbTools;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class App {
    // private static boolean closeApp = false;
    public static void main(String[] args) {
        try {
            DbTools.connect(); // connect to the database

            // ensure disconnect on JVM shutdown
            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                try {
                    DbTools.disconnect();
                } catch (Exception e) {
                    System.err.println("Error during DB disconnect: " + e.getMessage());
                }
            }));

            App reportSystem = new App();
//            reportSystem.callCountryReports();
//            reportSystem.callCityReports();
            reportSystem.callCapitalReports();

            DbTools.disconnect(); // disconnect from the database

        } catch (SQLException | InterruptedException e) {
            System.err.println("Failed to connect to the database: " + e.getMessage());
            System.exit(1); // Exit if DB connection fails
        }


    }
    public void callCapitalReports() {
         CapitalReports capReport = new CapitalReports();
//         System.out.println(capReport.getAllCapitalsWorldReport());
//         System.out.println(capReport.getAllCapitalsInContinentReport("Europe"));
            System.out.println(capReport.getAllCapitalsInRegionReport("Eastern Europe"));
    }
    public void callCountryReports() {
        CountryReports cReport = new CountryReports();
        System.out.println(cReport.allCountriesInWorldReport());
        System.out.println(cReport.allCountriesInContinentReport("Europe"));
    }
    public void callCityReports() {
         CityReports cReport = new CityReports();
//         System.out.println(cReport.getAllCitiesInWorldReport());
//         System.out.println(cReport.getAllCitiesInContinentReport("Europe"));
//         System.out.println(cReport.getAllCitiesInRegionReport("Eastern Europe"));
//         System.out.println(cReport.getAllCitiesInCountryReport("Spain"));
//         System.out.println(cReport.getAllCitiesInDistrictReport("Andalusia"));
//         System.out.println(cReport.getTopNCitiesInWorldReport(10));
//         System.out.println(cReport.getTopNCitiesInContinentReport(10,"Africa"));
//         System.out.println(cReport.getTopNCitiesInRegionReport(10,"Eastern Europe"));
//         System.out.println(cReport.getTopNCitiesInCountryReport(10,"Canada"));
         System.out.println(cReport.getTopNCitiesInDistrictReport(3,"Andalusia"));
    }
//    public void mainMenu(){
//
//        int userInput;
//        while (!closeApp){
//            System.out.println("Welcome to the Report System");
//            System.out.println("Please select an option:");
//            System.out.println("1. View Country Reports");
//            System.out.println("2. View Capital Cities Reports");
//            System.out.println("3. View Cities Reports");
//            System.out.println("4. View Population Reports");
//            System.out.println("5. View Language Reports");
//            System.out.println("0. Exit Application");
//            try {
//                Scanner sc = new Scanner(System.in);
//                userInput = sc.nextInt();
//            }catch (InputMismatchException e){
//                System.out.println("Please enter an integer");
//                userInput = 10;
//            }
//            switch (userInput){
//                case 0:
//                    closeApp = true;
//                    break;
//                case 1:
//                    CountryReports countryReport1 = new CountryReports();
//                    countryReport1.reportSelectionScreen();
//                    break;
//                case 2:
//                    // Capital Cities Reports
//                    System.out.println("This feature is still in development");
//                    break;
//                case 3:
//                    // Cities Reports
//                    System.out.println("This feature is still in development");
//                    break;
//                case 4:
//                    // Population Reports
//                    System.out.println("This feature is still in development");
//                    break;
//                case 5:
//                    // Language Reports
//                    System.out.println("This feature is still in development");
//                    break;
//                default:
//                    System.out.println("Invalid choice. Try again.");
//                    break;
//            }
//        }
//    }
}
