package com.michael.test.junit5.tool;

/**
 * @author Michael
 */
public class Calculator {

    public int add(int a, int b) {
        return a + b;
    }

    public int multiply(int a, int b) {
        return a * b;
    }

    int pow(int a, int b) {
        return (int) Math.pow(a, b);
    }
}
