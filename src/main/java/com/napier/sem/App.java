package com.napier.sem;

import com.napier.sem.populationReports.SpecificPopulationReports;
import com.napier.sem.tools.DbTools;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.SQLException;

// Main application class
@SpringBootApplication
public class App {
    public static void main(String[] args) {

        // Start Spring Boot application
        SpringApplication.run(App.class, args);
        try {
            DbTools.connect(); // connect to the database
            SpecificPopulationReports specificPopulationReports = new SpecificPopulationReports();
            System.out.println(specificPopulationReports.getPopulation("world", ""));
            // ensure disconnect on JVM shutdown
            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                try {
                    DbTools.disconnect();
                } catch (Exception e) {
                    System.err.println("Error during DB disconnect: " + e.getMessage());
                }
            }));

        } catch (SQLException | InterruptedException e) {
            System.err.println("Failed to connect to the database: " + e.getMessage());
            System.exit(1); // Exit if DB connection fails
        }


    }
}
