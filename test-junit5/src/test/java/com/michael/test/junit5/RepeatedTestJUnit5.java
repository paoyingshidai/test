package com.michael.test.junit5;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;

/**
 *
 */
public class RepeatedTestJUnit5 {


    @RepeatedTest(10) //表示重复执行10次
    @DisplayName("重复测试")
    public void testRepeated() {
        Assertions.assertTrue(1 == 1);
    }
}
