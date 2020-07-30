package com.michael.test.junit5;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.condition.DisabledOnOs;

/**
 * @author Michael
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class Junit5Test {

    @BeforeAll
    public void testBefore() {
        System.out.println("before");

    }

    @Test
//    @DisabledOnOs()
    public void test() {
        System.out.println("test");
    }

}
