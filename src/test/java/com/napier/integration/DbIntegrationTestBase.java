// DbIntegrationTestBase.java
package com.napier.integration;

import com.napier.sem.tools.DbTools;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.assertTrue;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public abstract class DbIntegrationTestBase {

    @BeforeAll
    void connectToDb() throws Exception {
        DbTools.connect();
    }

    // Verify that the database connection is established
    @Test
    void connection_is_established() {
        assertTrue(DbTools.isConnected(), "Database connection not established");
    }

    @AfterAll
    void disconnectFromDb() {
        DbTools.disconnect();
    }
}
