package com.threads;

public class ThreadJoinEX1 {
	public static void main(String ar[]) {
		System.out.println("Inside Main Thread");

		Thread thread1 = new Thread(new ThreadTaskJoin());
		thread1.start();

		Thread thread2 = new Thread(new ThreadTaskJoin(thread1));
		thread2.start();

		Thread thread3 = new Thread(new ThreadTaskJoin(thread2));
		thread3.start();

		try {
			thread1.join();
			thread2.join();
			thread3.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("End of Main Thread");
	}
}

class ThreadTaskJoin implements Runnable {

	public ThreadTaskJoin() {
		System.out.println("empty constrcutor");
	}

	public ThreadTaskJoin(Thread threadToJoin) {
		try {
			threadToJoin.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		System.out.println("Start Thread :" + Thread.currentThread().getName());
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("End Thread :" + Thread.currentThread().getName());
	}
}