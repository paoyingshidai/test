package com.michael.test.thread;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Michael
 * 
 *  用于模拟高并发的工具类
 */
public abstract class AbstractCountDownLatch {

	private int THREAD_NUM = 1;
	
	private long startTime = 1L;
	
	private Boolean isLog = Boolean.TRUE;

	private ExecutorService theadPool;

	public AbstractCountDownLatch() {
		super();
	}

	public AbstractCountDownLatch(int threadNum) {
		this.THREAD_NUM = threadNum;
		this.theadPool = Executors.newFixedThreadPool(THREAD_NUM);
	}

	public AbstractCountDownLatch(int threadNum, ExecutorService executorService) {
		this.THREAD_NUM = threadNum;
		this.theadPool = executorService;
	}
	
	public void start() {
		try {
			CountDownLatch countDownLatch = new CountDownLatch(1);
			for (int i = 0; i < THREAD_NUM; i++) {
				theadPool.execute(new Run(countDownLatch));	// 采用线程池并发更加完全
			}
			this.startTime = System.currentTimeMillis();
			countDownLatch.countDown();
		} catch (Exception e) {
			System.out.println("Exception: " + e);
		}
	}
	
	/**
	 * 子类可以实现这个方法，执行自己想要执行的任务
	 */
	protected void doWork(){};
	
	public void isLog(Boolean isLog) {
		this.isLog = isLog;
	}

	private class Run implements Runnable {
		
		private final CountDownLatch startLatch;

		public Run(CountDownLatch startLatch) {
			this.startLatch = startLatch;
		}

		@Override
		public void run() {
			try {
				startLatch.await();
				doWork();
				if (isLog) {
					System.out.println(Thread.currentThread().getName() + " 耗费时间 : " + 
										(System.currentTimeMillis() - startTime) + " 毫秒");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
