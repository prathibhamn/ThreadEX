package com.threads;

public class ThreadGroupExceptionHandler {
	public static void main(String[] args) {

		// Register ThreadGroup Exception Handler for all Threads that are part of
		// ThreadGroup.
		ThreadGroup tr = new ThreadGroup("MyGroup") {
			@Override
			public void uncaughtException(Thread t, Throwable e) {
				System.out.println(
						"ThreadGroup Exception Handler :Thread Name :" + t.getName() + " Message :" + e.getMessage());
			}
		};

		Thread t1 = new Thread(tr, new Runnable() {
			@Override
			public void run() {
				throw new RuntimeException("I am RuntimeException");
			}
		});
		t1.setName("T1");
		t1.start();

		Thread t2 = new Thread(tr, new Runnable() {
			@Override
			public void run() {
				throw new RuntimeException("I am RuntimeException");
			}
		});
		t2.setName("T2");
		t2.start();

	}

}
