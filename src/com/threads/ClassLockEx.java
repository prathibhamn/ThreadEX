package com.threads;

import java.lang.Thread.UncaughtExceptionHandler;

public class ClassLockEx {
	public static void main(String[] args) {
		final ClassLockEx threadDemo1 = new ClassLockEx();

		new Thread(new Runnable() {
			@Override
			public void run() {
				threadDemo1.getA();
			}
		}).start();

		new Thread(new Runnable() {
			@Override
			public void run() {
				threadDemo1.getB();
			}
		}).start();

		new Thread(new Runnable() {
			@Override
			public void run() {
				ClassLockEx.getC();
			}
		}).start();

		new Thread(new Runnable() {
			@Override
			public void run() {
				ClassLockEx.getD();
			}
		}).start();

	}

	/*** INSTANCE METHOD ***/
	public synchronized void getA() {
		System.out.println("Instance Lock.getA() :" + Thread.currentThread().getName() + " enetered");
		try {
			Thread.sleep(2000);
			wait();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Instance Lock.getA() :" + Thread.currentThread().getName() + " leaving");
	}

	public synchronized void getB() {
		System.out.println("Instance Lock.getB() :" + Thread.currentThread().getName() + " enetered");
		try {
			Thread.sleep(2000);
			notify();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Instance Lock.getB() :" + Thread.currentThread().getName() + " leaving");
	}

	/*** CLASS METHOD ***/
	public static synchronized void getC() {
		System.out.println("ClassLockEx.getC() :" + Thread.currentThread().getName() + " enetered");
		try {
			Thread.sleep(2000);
			ClassLockEx.class.wait();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("ClassLockEx.getC() :" + Thread.currentThread().getName() + " leaving");
	}

	public static synchronized void getD() {
		System.out.println("ClassLockEx.getD() :" + Thread.currentThread().getName() + " enetered");
		try {
			Thread.sleep(2000);
			Thread.setDefaultUncaughtExceptionHandler(new UncaughtExceptionHandler() {

				@Override
				public void uncaughtException(Thread t, Throwable e) {
					// TODO Auto-generated method stub
					
				}
				
				
				
			});
			ClassLockEx.class.notify();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("ClassLockEx.getD() :" + Thread.currentThread().getName() + " leaving");
	}

}
