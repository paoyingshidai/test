package com.michael.test.junit5;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.platform.commons.util.StringUtils;

import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.params.provider.Arguments.arguments;

/**
 * 参数化
 * https://zhuanlan.zhihu.com/p/111706639
 */
public class ParameterizedTestJUnit5 {


    @ParameterizedTest
    @ValueSource(strings = {"one", "two", "three"})
    @DisplayName("参数化测试1")
    public void parameterizedTest1(String string) {
        System.out.println(string);
        assertTrue(StringUtils.isNotBlank(string));
    }


    @ParameterizedTest
    @MethodSource("method")    //指定方法名
    @DisplayName("方法来源参数")
    public void testWithExplicitLocalMethodSource(String name) {
        System.out.println(name);
        assertNotNull(name);
    }

    static Stream<String> method() {
        return Stream.of("apple", "banana");
    }


    /**
     * https://junit.org/junit5/docs/current/user-guide/#writing-tests-parameterized-tests
     * @param str
     * @param num
     * @param list
     */
    @ParameterizedTest
    @MethodSource("stringIntAndListProvider")
    void testWithMultiArgMethodSource(String str, int num, List<String> list) {
        assertEquals(5, str.length());
        assertTrue(num >=1 && num <=2);
        assertEquals(2, list.size());
    }

    static Stream<Arguments> stringIntAndListProvider() {
        return Stream.of(
                arguments("apple", 1, Arrays.asList("a", "b")),
                arguments("lemon", 2, Arrays.asList("x", "y"))
        );
    }

    /**
     * TODO 自定义注解实现更复杂的注入
     * https://zhuanlan.zhihu.com/p/111706639
     */
    @DisplayName("创建咨询来源：成功创建")
    @ParameterizedTest
//    @FileSource
    public void testSaveConsultSource_SUCCESS() {

    }


    /**
     * classpath
     * delimiterString: 制定分割符
     * @param name
     * @param age
     */
    @ParameterizedTest
    @CsvSource(value = {"name,12", "Michael,13"}, delimiterString = ",")
    @DisplayName("参数化测试-csv文件")
    public void csvSource(String name, Integer age) {
        System.out.println("name:" + name + ",age:" + age);
        assertNotNull(name);
        assertNotNull(age);
    }

    /**
     * classpath
     * delimiterString: 制定分割符
     * @param name
     * @param age
     */
    @ParameterizedTest
    @CsvFileSource(resources = "/test.csv", delimiterString = " ")
    @DisplayName("参数化测试-csv文件")
    public void parameterizedTest2(String name, Integer age) {
        System.out.println("name:" + name + ",age:" + age);
        assertNotNull(name);
        assertNotNull(age);
    }


    @ParameterizedTest
    @EnumSource(ActivityLimitEnum.class)
    @DisplayName("封顶和不封顶")
    void test(ActivityLimitEnum activityLimitEnum) {
        if (ActivityLimitEnum.LIMIT.equals(activityLimitEnum)) {
            assertFalse(false);
        }
        else if (ActivityLimitEnum.UNLIMIT.equals(activityLimitEnum)) {
            assertTrue(true);
        }
    }

    enum ActivityLimitEnum {

        LIMIT(1,"封顶"),
        UNLIMIT(0,"上不封顶");

        private int code;
        private String name;

        ActivityLimitEnum(int code, String name) {
            this.code = code;
            this.name = name;
        }
    }


    @ParameterizedTest
    @ArgumentsSource(MyArgumentsProvider.class)
    void testWithArgumentsSource(String argument) {
        assertNotNull(argument);
    }
    public class MyArgumentsProvider implements ArgumentsProvider {

        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
            return Stream.of("apple", "banana").map(Arguments::of);
        }
    }


    @ParameterizedTest
    @ValueSource(strings = "SECONDS")
    void testWithImplicitArgumentConversion(ChronoUnit argument) {
        assertNotNull(argument.name());
    }


}
