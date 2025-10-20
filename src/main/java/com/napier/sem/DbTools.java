package com.napier.sem;
import java.sql.*;

/**
 * Allows for the use of tools to interact with the Database
 *
 */
public class DbTools {
    /**
     * Connection to MySQL database.
     */
    private Connection con = null;
    private String url;
    private String user;
    private String password;

    public DbTools() {
        // Priority: ENV -> system property -> fallback (works inside Docker compose)
        url = System.getenv("DB_URL");
        if (url == null) {
            url = System.getProperty("db.url", "jdbc:mysql://db:3306/world?allowPublicKeyRetrieval=true&useSSL=false");
        }

        user = System.getenv("DB_USER");
        if (user == null) {
            user = System.getProperty("db.user", "root");
        }

        password = System.getenv("DB_PASS");
        if (password == null) {
            password = System.getProperty("db.pass", "example");
        }
    }
    /**
     * Connect to the MySQL database.
     */
    public void connect()
    {
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

        int retries = 10;
        for (int i = 0; i < retries; ++i)
        {
            System.out.println("Connecting to database...");
            try
            {
                // Wait a bit for db to start
                Thread.sleep(10000);
                // Connect to database
                con = DriverManager.getConnection(url, user, password);
                System.out.println("Successfully connected");
                break;
            }
            catch (SQLException sqle)
            {
                System.out.println("Failed to connect to database attempt " + Integer.toString(i));
                System.out.println(sqle.getMessage());
            }
            catch (InterruptedException ie)
            {
                System.out.println("Thread interrupted? Should not happen.");
            }
        }
    }

    /**
     * Disconnect from the MySQL database.
     */
    public void disconnect()
    {
        if (con != null)
        {
            try
            {
                // Close connection
                con.close();
            }
            catch (Exception e)
            {
                System.out.println("Error closing connection to database");
            }
        }
    }
}
