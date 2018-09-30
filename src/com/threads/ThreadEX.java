package com.threads;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ThreadEX {

	public static void main(String[] args) {
		// Lets create Task first to assign it to the Thread
		ThreadTask threadTask = new ThreadTask();
		// Lets create a Thread and assign task to it.
		// Way to assign task to a Thread is by passing task object(Runnable) to
		// Thread's constructor.
		Thread thread1 = new Thread(threadTask);

		// Start a thread
		thread1.start();
		thread1.start();
	}

}

class ThreadTask implements Runnable {

	@Override
	public void run()  {
		System.out.println("Started by Runnable task");

	}
}

class ThreadDemo extends Thread {

	@Override
	public void run() {

		System.out.println("Started by Thread extending");
		// Code present here will be executed in separate independent path.
	}

	public static void main(String[] args) {

		// Lets create Task first to assign it to the Thread
		ThreadDemo threadTask = new ThreadDemo();

		// Lets create a Thread and assign task to it.
		// Way to assign task to a Thread is by passing task object(Runnable) to
		// Thread's constructor.
		Thread thread1 = new Thread(threadTask);

		// Start a thread
		thread1.start();
	}
}

class ThreadDemo1 {
	public static void main(String[] args) {

		// Create a Thread Pool of size 2 (2 here is number of threads in Thread pool.)
		ExecutorService executorService = Executors.newFixedThreadPool(2);
		// After creating a pool, it internally starts a Thread, so no need to
		// explicitly start a thread as we did in other approach.
		// Remember only Threads are started but what task they will execute that will
		// be passed to thread using submit() method.
		// In this approach threads will be created and started but they will wait for
		// task to be assigned to them.

		// Create Task to assign it to Threads present in Thread pool.
		ThreadTaskCall threadTask = new ThreadTaskCall();

		// Submit a task to Threads present in Thread pool.
		Future<Result> resultObject = executorService.submit(threadTask);
		// Once a task is submitted to submit method, one of the Thread from the pool
		// will pick the task and execute run method of task.
		// Wait for the result Object(resultObject) that will be returned by Thread
		// after task execution.

		Result result = null;
		try {
			// get method will be blocked until the Thread doesn't complete it work and
			// return a result
			result = resultObject.get();
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
		System.out.println(result.code + " " + result.message);
		executorService.shutdown();
	}
}

class ThreadTaskCall implements Callable<Result> {

	// method where the thread execution takes place
	public Result call() throws Exception{
		// Code present here will be executed in separate independent path.
		Result response = new Result();
		response.code = 200;
		response.message = "SUCCESS";
		return response;
	}

}

class Result {
	public int code;
	public String message;
}