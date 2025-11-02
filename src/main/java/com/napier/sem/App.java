package com.napier.sem;

import com.napier.sem.countries.CountryReports;
import java.util.InputMismatchException;
import java.util.Scanner;

public class App
{
    private static boolean closeApp = false;
    public static void main(String[] args)
    {
        App reportSystem = new App();
        reportSystem.callCountryReports();

    }
    public void callCountryReports()
    {
        CountryReports cReport = new CountryReports();
        System.out.println(cReport.allCountriesInWorldReport());
        System.out.println(cReport.allCountriesInContinentReport("Europe"));
    }
//    public void mainMenu()
//    {
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
