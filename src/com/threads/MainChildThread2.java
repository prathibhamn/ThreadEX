package com.threads;

public class MainChildThread2 {
	public static void main(String ar[]) {
		System.out.println("Inside Main Thread");

		Thread thread = new Thread(new ThreadTask1());
		thread.setDaemon(true);
		thread.start();

		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("End of Main Thread");
	}
}

class ThreadTask1 implements Runnable {

	@Override
	public void run() {
		System.out.println("Inside Thread Task start");

		new Thread(new Runnable() {

			public void run() {
				Thread childThread = Thread.currentThread();
				while (true) {
					System.out.println("Inside Child Thread :" + childThread.getName());
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}

		}).start();

		System.out.println("Inside Thread Task end");
	}

}