package com.michael.test.multi.thread;

import org.junit.jupiter.api.Test;

import java.util.concurrent.CountDownLatch;

/**
 * @author Michael
 */
public class MultiThread {

    private CountDownLatch countDownLatch = new CountDownLatch(1);

    @Test
    public void test() {

        try {
//            int i = 1/0;
            countDownLatch.await();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("finally");
        }
    }


    class MyRunnable implements Runnable {
        @Override
        public void run() {

        }
    }




}
