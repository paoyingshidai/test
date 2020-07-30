package com.michael.test.junit5;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.platform.commons.util.StringUtils;

import java.util.stream.Stream;

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
        Assertions.assertTrue(StringUtils.isNotBlank(string));
    }


    @ParameterizedTest
    @MethodSource("method")    //指定方法名
    @DisplayName("方法来源参数")
    public void testWithExplicitLocalMethodSource(String name) {
        System.out.println(name);
        Assertions.assertNotNull(name);
    }

    static Stream<String> method() {
        return Stream.of("apple", "banana");
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
        Assertions.assertNotNull(name);
        Assertions.assertNotNull(age);
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
        Assertions.assertNotNull(name);
        Assertions.assertNotNull(age);
    }
}
