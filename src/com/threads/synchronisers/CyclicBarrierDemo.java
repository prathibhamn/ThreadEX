package com.threads.synchronisers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class CyclicBarrierDemo {

	private CyclicBarrier cyclicBarrier;
	private List<List<Integer>> partialResults = Collections.synchronizedList(new ArrayList<>());
	private Random random = new Random();
	private int NUM_PARTIAL_RESULTS;
	private int NUM_WORKERS;
	private Lock lock = new ReentrantLock();

	private void runSimulation(int numWorkers, int numberOfPartialResults) {
		NUM_PARTIAL_RESULTS = numberOfPartialResults;
		NUM_WORKERS = numWorkers;

		cyclicBarrier = new CyclicBarrier(NUM_WORKERS, new AggregatorThread());
		System.out.println("Spawning " + NUM_WORKERS + " worker threads to compute " + NUM_PARTIAL_RESULTS
				+ " partial results each");
		for (int i = 0; i < NUM_WORKERS; i++) {
			Thread worker = new Thread(new NumberCruncherThread());
			worker.setName("Thread " + i);
			worker.start();
		}
	}

	class NumberCruncherThread implements Runnable {

		@Override
		public void run() {
			String thisThreadName = Thread.currentThread().getName();
			List<Integer> partialResult = new ArrayList<>();
			for (int i = 0; i < NUM_PARTIAL_RESULTS; i++) {
				Integer num = random.nextInt(10);
				System.out.println(thisThreadName + ": Crunching some numbers! Final result - " + num);
				partialResult.add(num);
			}
			partialResults.add(partialResult);
			try {
				System.out.println(thisThreadName + " waiting for others to reach barrier.");
				cyclicBarrier.await();
			} catch (InterruptedException | BrokenBarrierException e) {
				e.printStackTrace();
			}
		}
	}

	class AggregatorThread implements Runnable {

		static final String abc = "bbbb";
		// static int i=0;

		@Override
		public void run() {
			String thisThreadName = Thread.currentThread().getName();
			System.out.println(thisThreadName + ": Computing final sum of " + NUM_WORKERS + " workers, having "
					+ NUM_PARTIAL_RESULTS + " results each.");
			int sum = 0;
			for (List<Integer> threadResult : partialResults) {
				System.out.print("Adding ");
				for (Integer partialResult : threadResult) {
					System.out.print(partialResult + " ");
					sum += partialResult;
				}
				System.out.println();
			}
			System.out.println(Thread.currentThread().getName() + ": Final result = " + sum);
		}

	}

	public static void main(String[] args) throws Exception {
		CyclicBarrierDemo play = new CyclicBarrierDemo();
		
		// play.runSimulation(5, 3);
		CountDownLatch countDownLatch = new CountDownLatch(5);
		try {
			play.clone();

		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			throw new Exception();
		} finally {
			System.out.println("In finally system");
		}
		// countDownLatch.awa
	}

}