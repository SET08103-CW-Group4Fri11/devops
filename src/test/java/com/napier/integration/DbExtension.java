// class to manage DB connections for integration tests with different scopes
// it helps to run tests faster by reusing the same connection for multiple tests when appropriate
// and ensures proper cleanup of resources.
package com.napier.integration;

import com.napier.sem.tools.DbTools;
import org.junit.jupiter.api.extension.*;

import java.lang.reflect.AnnotatedElement;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;

// JUnit 5 extension to manage database connections based on DbScope annotation
public class DbExtension implements BeforeAllCallback, AfterAllCallback, BeforeEachCallback, AfterEachCallback {

    // Track global connection state
    private static final AtomicBoolean GLOBAL_CONNECTED = new AtomicBoolean(false);
    private static final Object HOOK_LOCK = new Object();

    // Determine the DbScope for the current test context
    private DbScope.Scope resolveScope(ExtensionContext context) {
        Optional<AnnotatedElement> elemOpt = context.getElement();
        if (elemOpt.isPresent()) {
            DbScope ann = elemOpt.get().getAnnotation(DbScope.class);
            if (ann != null) return ann.value();
        }
        Optional<Class<?>> clsOpt = context.getTestClass();
        if (clsOpt.isPresent()) {
            DbScope ann = clsOpt.get().getAnnotation(DbScope.class);
            if (ann != null) return ann.value();
        }
        return DbScope.Scope.GLOBAL;
    }

    // Before all tests in the context
    @Override
    public void beforeAll(ExtensionContext context) throws Exception {
        if (resolveScope(context) == DbScope.Scope.GLOBAL) {
            if (GLOBAL_CONNECTED.compareAndSet(false, true)) {
                DbTools.connect();
                synchronized (HOOK_LOCK) {
                    // register a single shutdown hook to close DB once per JVM
                    Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                        if (GLOBAL_CONNECTED.compareAndSet(true, false)) {
                            try {
                                DbTools.disconnect();
                            } catch (Exception ignored) { }
                        }
                    }));
                }
            }
        }
    }

    // After all tests in the context
    @Override
    public void afterAll(ExtensionContext context) throws Exception {

    }

    // Before each test
    @Override
    public void beforeEach(ExtensionContext context) throws Exception {
        if (resolveScope(context) == DbScope.Scope.PER_TEST) {
            DbTools.connect();
        }
    }

    // After each test
    @Override
    public void afterEach(ExtensionContext context) throws Exception {
        if (resolveScope(context) == DbScope.Scope.PER_TEST) {
            DbTools.disconnect();
        }
    }
}
