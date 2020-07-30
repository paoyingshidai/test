package com.michael.test.junit5;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.time.Duration;

/**
 * 断言
 */
public class AssertTest {

    @Test
    @DisplayName("异常测试")
    public void exceptionTest() {
        ArithmeticException exception = Assertions.assertThrows(
                ArithmeticException.class, () -> System.out.println(1 % 0));
    }

    @Test
    @DisplayName("超时测试")
    public void timeoutTest() {
        Assertions.assertTimeout(Duration.ofMillis(1000), () -> Thread.sleep(500));
    }

    /**
     * 超时抛出异常
     * @throws InterruptedException
     */
    @Test
    @Timeout(1)
    public void testTimeout() throws InterruptedException {
        Thread.sleep(1000);
    }

}
