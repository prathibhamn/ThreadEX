package com.threads;

import java.util.concurrent.atomic.AtomicInteger;

public class NonVolatileVarMultiThreading {

	private static final Object lock = new Object();
	private String abc = "hello";

	final AtomicInteger i = new AtomicInteger();

	public void get1() {
		synchronized (lock) {

			try {
				abc = "Hello :" + i.incrementAndGet();
				Thread.sleep(2000);

				// Consider at this point
				// Thread t1 gets preempted and
				// Thread t2 gets executed.
				System.out.println("Changed :" + abc);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void get2() {
		System.out.println(abc);
	}

	public void get3() {
		synchronized (lock) {
			System.out.println(abc);
		}
	}

	public static void main(String args[]) {
		final NonVolatileVarMultiThreading s = new NonVolatileVarMultiThreading();

		new Thread(new Runnable() {
			public void run() {
				s.get1();
			}
		}, "t1").start();

		new Thread(new Runnable() {
			public void run() {
				s.get2();
			}
		}, "t2").start();

		new Thread(new Runnable() {
			public void run() {
				s.get3();
			}
		}, "t3").start();

	}
}
