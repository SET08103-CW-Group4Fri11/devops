// File: `src/test/java/com/napier/integration/DbExtension.java`
package com.napier.integration;

import com.napier.sem.tools.DbTools;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.AfterAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

import java.util.concurrent.atomic.AtomicInteger;

public class DbExtension implements BeforeAllCallback, AfterAllCallback {
    private static final AtomicInteger activeClasses = new AtomicInteger(0);

    @Override
    public void beforeAll(ExtensionContext context) throws Exception {
        if (activeClasses.getAndIncrement() == 0) {
            DbTools.connect();
        }
    }

    @Override
    public void afterAll(ExtensionContext context) throws Exception {
        if (activeClasses.decrementAndGet() == 0) {
            DbTools.disconnect();
        }
    }
}
