package com.michael.test.thread;

public class DefaultCountDownLatch extends AbstractCountDownLatch {

	
	public DefaultCountDownLatch() {
		super();
	}
	
	public DefaultCountDownLatch(int threadNum) {
		super(threadNum);
	}

	@Override
	protected void doWork() {
		System.out.println("hello  " + Thread.currentThread().getName());
//		super.doWork();
	}
	
}
