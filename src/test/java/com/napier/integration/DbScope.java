package com.napier.integration;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.annotation.ElementType;

// Annotation to specify the database connection scope for tests
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
public @interface DbScope {
    Scope value() default Scope.GLOBAL;

    enum Scope {
        GLOBAL,
        PER_TEST
    }
}
