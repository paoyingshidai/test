package com.michael.test.thread;

public class Client {

	public static void main(String[] args) {
		
		DefaultCountDownLatch latch = new DefaultCountDownLatch(1000);
		latch.isLog(false);
		latch.start();
	}
	
}
