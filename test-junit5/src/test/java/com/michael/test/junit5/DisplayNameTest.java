package com.michael.test.junit5;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

/**
 * @author Michael
 */
public class DisplayNameTest {

    /**
     * displayName 会在测试的目录栏显示
     * https://junit.org/junit5/docs/current/user-guide/#writing-tests-parameterized-tests-display-names
     * @param fruit
     * @param rank
     */
    @DisplayName("Display name of container")
    @ParameterizedTest(name = "{index} ==> the rank of ''{0}'' is {1}")
    @CsvSource({ "apple, 1", "banana, 2", "'lemon, lime', 3" })
    void testWithCustomDisplayNames(String fruit, int rank) {
    }

}
