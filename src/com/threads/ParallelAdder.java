package com.threads;

import java.util.concurrent.Callable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;


public class ParallelAdder {
	public Long parallelSum() {
		 long t1 = System.currentTimeMillis();
		ExecutorService executor = Executors.newFixedThreadPool(10);
		List<Future<Long>> list = new ArrayList<>();
		Long count = 1l;
		Long prev = 0l;
		for (Long i = 0l; i < 1000000000l; i++) {
			if (count % 2 == 0)// grouping
			{
				//System.out.println("Prev :" + prev + " current: " + i);
				Future<Long> future = executor.submit(new CallableAdder(prev, i));
				list.add(future);
				count = 1l;
				continue;
			}
			prev = i;
			count++;
		}
		Long totsum = 0l;
		for (Future<Long> fut : list) {
			try {
				totsum = totsum + fut.get();
			} catch (InterruptedException e) {
				e.printStackTrace();
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println("Total Sum is " + totsum);
		 long t2 = System.currentTimeMillis();
		 System.out.println("Time taken by parallelSum " + (t2-t1));
		return totsum;
	}

	public Long sequentialSum() {
		 long t1 = System.currentTimeMillis();
		Long totsum = 0l;
		for (Long i = 0l; i < 1000000000l; i++) {
			totsum = totsum + i;
		}
		 long t2 = System.currentTimeMillis();
		System.out.println("sequentialSum Total Sum is " + totsum);
		 System.out.println("Time taken by sequentialSum " + (t2-t1));
		return totsum;
	}

	public static void main(String[] args) {
		ParallelAdder adder = new ParallelAdder();
		Long pSum = adder.parallelSum();
		Long sSum = adder.sequentialSum();
		System.out.println("parallel Sum equals to Sequential Sum ? ");
		System.out.println("Answer is :: " + (pSum == sSum));
	}
}
 class CallableAdder implements Callable<Long> {
	 Long operand1;
	 Long operand2;

	CallableAdder(Long operand1, Long operand2) {
		this.operand1 = operand1;
		this.operand2 = operand2;
	}

	public Long call() throws Exception {
		// TODO Auto-generated method stub
		/*System.out.println(Thread.currentThread().getName() + " says : partial Sum for " + operand1 + " and " + operand2
				+ " is " + (operand1 + operand2));*/
		return operand1 + operand2;
	}
}


