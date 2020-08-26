package com.michael.test.junit5.extention;

import com.alibaba.fastjson.JSON;
import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.support.AnnotationConsumer;
import org.junit.platform.commons.PreconditionViolationException;
import org.junit.platform.commons.util.Preconditions;
import org.springframework.util.StringUtils;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;
import java.util.stream.Stream;

/**
 * @author Michael
 */
class JsonFileArgumentsProvider implements ArgumentsProvider, AnnotationConsumer<JsonFileSource> {

    private final BiFunction<Class<?>, String, InputStream> inputStreamProvider;

    private JsonFileSource annotation;
    private String[] resources;
    private Charset charset;

    JsonFileArgumentsProvider() {
        this(Class::getResourceAsStream);
    }

    JsonFileArgumentsProvider(BiFunction<Class<?>, String, InputStream> inputStreamProvider) {
        this.inputStreamProvider = inputStreamProvider;
    }

    @Override
    public void accept(JsonFileSource annotation) {
        this.annotation = annotation;
        this.resources = annotation.resources();
        this.charset = getCharsetFrom(annotation);
    }

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context) throws Exception {
        return Arrays.stream(this.resources)
                .map(resource -> openInputStream(context, resource))
                .map(this::beginParsing)
                .flatMap(this::toStream);
    }

    private InputStream openInputStream(ExtensionContext context, String resource) {
        Preconditions.notBlank(resource, "Classpath resource [" + resource + "] must not be null or blank");
        Class<?> testClass = context.getRequiredTestClass();
        return Preconditions.notNull(inputStreamProvider.apply(testClass, resource),
                () -> "Classpath resource [" + resource + "] does not exist");
    }

    private String beginParsing(InputStream inputStream) {
        try {
            List<String> strings = IOUtils.readLines(inputStream, charset);
            return StringUtils.collectionToCommaDelimitedString(strings);
        } catch (Throwable throwable) {

        }
        return "";
    }

    private Stream<Arguments> toStream(String input) {
        List<User> users = JSON.parseArray(input, User.class);
        return users.stream().map(Arguments::arguments);
    }

    private Charset getCharsetFrom(JsonFileSource annotation) {
        try {
            return Charset.forName(annotation.encoding());
        }
        catch (Exception ex) {
            throw new PreconditionViolationException("The charset supplied in " + annotation + " is invalid", ex);
        }
    }
}
