// src/test/java/com/napier/integration/DatabaseConnectionIT.java
package com.napier.integration;

import com.napier.sem.tools.DbTools;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.sql.*;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(DbExtension.class)
public class DatabaseConnectionIT {

    private String dbUrl() {
        return System.getenv("DB_URL");
    }

    private String dbUser() {
        return System.getenv("DB_USER");
    }

    private String dbPass() {
        return System.getenv("DB_PASS");
    }
    @Test
    void environment_variables_set() {
        Assumptions.assumeTrue(dbUrl() != null && !dbUrl().isEmpty(), "DB_URL not set");
        Assumptions.assumeTrue(dbUser() != null && !dbUser().isEmpty(), "DB_USER not set");
        Assumptions.assumeTrue(dbPass() != null && !dbPass().isEmpty(), "DB_PASS not set");
    }

    @Test
    void connection_established() throws SQLException {
        assertTrue(DbTools.isConnected(), "Database connection not established");
    }

}