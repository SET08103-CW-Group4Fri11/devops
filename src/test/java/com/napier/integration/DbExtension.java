// java
package com.napier.integration;

import com.napier.sem.tools.DbTools;
import org.junit.jupiter.api.extension.*;

import java.lang.reflect.AnnotatedElement;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;

public class DbExtension implements BeforeAllCallback, AfterAllCallback, BeforeEachCallback, AfterEachCallback {

    private static final AtomicBoolean GLOBAL_CONNECTED = new AtomicBoolean(false);
    private static final Object HOOK_LOCK = new Object();

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

    @Override
    public void afterAll(ExtensionContext context) throws Exception {
        // Do not disconnect here for GLOBAL scope; shutdown hook will handle final disconnect.
        // PER_TEST disconnects in afterEach below.
    }

    @Override
    public void beforeEach(ExtensionContext context) throws Exception {
        if (resolveScope(context) == DbScope.Scope.PER_TEST) {
            DbTools.connect();
        }
    }

    @Override
    public void afterEach(ExtensionContext context) throws Exception {
        if (resolveScope(context) == DbScope.Scope.PER_TEST) {
            DbTools.disconnect();
        }
    }
}
