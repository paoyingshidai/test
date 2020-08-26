package com.michael.test.junit5.extention;

import org.apiguardian.api.API;
import org.junit.jupiter.params.provider.ArgumentsSource;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static org.apiguardian.api.API.Status.EXPERIMENTAL;

@Target({ ElementType.ANNOTATION_TYPE, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@API(status = EXPERIMENTAL, since = "5.0")
@ArgumentsSource(JsonFileArgumentsProvider.class)
public @interface JsonFileSource {

    /**
     * The JSON classpath resources to use as the sources of arguments; must not be
     * empty.
     */
    String[] resources();

    /**
     * The encoding to use when reading the JSON files; must be a valid charset.
     *
     * <p>Defaults to {@code "UTF-8"}.
     *
     * @see java.nio.charset.StandardCharsets
     */
    String encoding() default "UTF-8";
}
