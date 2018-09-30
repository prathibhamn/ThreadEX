package com.threads;

public class ThreadJoinEX2 extends Thread {
	static ThreadJoinEX2 thread1;

	public void run() {
		try {
			synchronized (thread1) {
				System.out.println(Thread.currentThread().getName() + " acquired a lock on thread1 in run method");
				Thread.sleep(5000);
				thread1.join();
				System.out.println(Thread.currentThread().getName() + " completed in run method");
			}
		} catch (InterruptedException e) {
		}
	}

	public static void main(String[] ar) throws Exception {
		
		Thread a = new Thread( new A());
		 a.start();
		/*
		thread1 = new ThreadJoinEX2();
		thread1.setName("thread1");
		thread1.start();
		Thread.sleep(4000);
		synchronized (thread1) {
			System.out.println(Thread.currentThread().getName() + " acquired a lock on thread1 in main method");
			Thread.sleep(1000);
			//thread1.join();
			System.out.println(Thread.currentThread().getName() + " completed in main method");
		}
	*/}
}


class A implements Runnable
{

    void start()
    {
        System.err.println( "dummy start" );
        //return 2;
    }

    @Override
    public void run()
    {
    	 System.err.println( " run" );

    }
}