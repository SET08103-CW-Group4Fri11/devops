package com.napier.sem.tools;
import com.napier.sem.countries.Country;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Allows for the use of tools to interact with the Database
 *
 */
public class DbTools {
    static String envUrl = System.getenv("DB_URL");
    static String envUser = System.getenv("DB_USER");
    static String envPass = System.getenv("DB_PASS");
    /**
     * Connection to MySQL database.
     */
    private static Connection con = null;
    /**
     * Connect to the MySQL database.
     */
    public static void connect() throws SQLException, InterruptedException {
        try
        {
            // Load Database driver
            Class.forName("com.mysql.cj.jdbc.Driver");
        }
        catch (ClassNotFoundException e)
        {
            System.out.println("Could not load SQL driver");
            System.exit(-1);
        }

        if(envUrl == null || envUser == null || envPass == null){
            envUrl = "jdbc:mysql://localhost:33060/world?allowPublicKeyRetrieval=true&useSSL=false";
            envUser = "root";
            envPass = "example";
        }
        int retries = 4;
        for (int i = 0; i < retries; ++i)
        {
            System.out.println("Connecting to database...");
            try
            {
                // Wait a bit for db to start
                Thread.sleep(10000);
                // Connect to database
                con = DriverManager.getConnection(envUrl, envUser, envPass);
                System.out.println("Successfully connected");
                break;
            }
            catch (SQLException sqle)
            {
                System.out.println("Failed to connect to database attempt " + i);
                System.out.println(sqle.getMessage());
                if(i == retries - 1) {
                    throw sqle;
                }
            }
            catch (InterruptedException ie)
            {
                System.out.println("Thread interrupted? Should not happen.");
                if(i == retries - 1) {
                    throw ie;
                }
            }
        }

    }

    public static boolean isConnected() {
        try {
            return con != null && !con.isClosed();
        } catch (SQLException e) {
            return false;
        }
    }

    /**
     * Disconnect from the MySQL database.
     */
    public static void disconnect()
    {
        if (con != null)
        {
            try
            {
                // Close connection
                System.out.println("Closing connection to database...");
                con.close();
                con = null;
            }
            catch (Exception e)
            {
                System.out.println("Error closing connection to database");
            }
        }
    }

//    connection getter
    public static Connection getCon() {
        return con;
    }
}
