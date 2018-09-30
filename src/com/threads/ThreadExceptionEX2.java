package com.threads;

public class ThreadExceptionEX2 {
	public static void main(String[] args) {
		Thread t1 = new Thread(new WorkerThread());
		t1.setName("T4");

		t1.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
			@Override
			public void uncaughtException(Thread t, Throwable e) {
				System.out.println(
						"Thread Exception Handler :Thread Name :" + t.getName() + " Message :" + e.getMessage());
			}
		});
		t1.start();

		//Group level thread exception handler
		ThreadGroup tr = new ThreadGroup("MyGroup") {
			@Override
			public void uncaughtException(Thread t, Throwable e) {
				System.out.println(
						"ThreadGroup Exception Handler :Thread Name :" + t.getName() + " Message :" + e.getMessage());
			}
		};
		
		Thread t2 = new Thread(tr, new WorkerThread());
		t2.setName("T2");
		t2.start();
		
		//Gloabl level handler
		Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler(){     
			 @Override
			 public void uncaughtException(Thread t, Throwable e){
			  System.out.println("Default Exception Handler :Thread Name :"+t.getName() + " Message :"+e.getMessage());
			 }
			});
	}

}

class WorkerThread extends Thread {
	public void run() {
		throw new RuntimeException("RuntimeException");
	}
}