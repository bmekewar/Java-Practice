package com.bvm.interview2;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Customer implements Comparable<Customer> {

	private int arrivalTime;
	private int procesOrderTime;
	private int serveOrderTime;
	private boolean prefferedCustomer;
	private int leaveTime;
	private int waitTime;
	private boolean isServerd = false;

	public int getArrivalTime() {
		return arrivalTime;
	}

	public void setArrivalTime(int arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	public int getProcesOrderTime() {
		return procesOrderTime;
	}

	public void setProcesOrderTime(int procesOrderTime) {
		this.procesOrderTime = procesOrderTime;
	}

	public int getServeOrderTime() {
		return serveOrderTime;
	}

	public void setServeOrderTime(int serveOrderTime) {
		this.serveOrderTime = serveOrderTime;
	}

	public boolean isPrefferedCustomer() {
		return prefferedCustomer;
	}

	public void setPrefferedCustomer(boolean prefferedCustomer) {
		this.prefferedCustomer = prefferedCustomer;
	}

	public int getLeaveTime() {
		return leaveTime;
	}

	public void setLeaveTime(int leaveTime) {
		this.leaveTime = leaveTime;
	}

	public int getWaitTime() {
		return waitTime;
	}

	public void setWaitTime(int waitTime) {
		this.waitTime = waitTime;
	}

	public boolean isServerd() {
		return isServerd;
	}

	public void setServerd(boolean isServerd) {
		this.isServerd = isServerd;
	}

	public Customer() {
		super();
	}

	public Customer(int arrivalTime, int procesOrderTime, int serveOrderTime,
			boolean prefferedCustomer) {
		super();
		this.arrivalTime = arrivalTime;
		this.procesOrderTime = procesOrderTime;
		this.serveOrderTime = serveOrderTime;
		this.prefferedCustomer = prefferedCustomer;
	}

	@Override
	public int compareTo(Customer customer) {

		if (this.arrivalTime < customer.arrivalTime) {
			// if (this.prefferedCustomer)
			return 1;
			// else
			// return -1;
		} else if (this.arrivalTime > customer.arrivalTime) {
			// if (this.prefferedCustomer)
			return -1;
			// else
			// return 1;
		}
		return 0;
	}

	@Override
	public String toString() {
		return "Customer [arrivalTime=" + arrivalTime + ", procesOrderTime="
				+ procesOrderTime + ", serveOrderTime=" + serveOrderTime
				+ ", prefferedCustomer=" + prefferedCustomer + ", leaveTime="
				+ leaveTime + ", waitTime=" + waitTime + ", isServerd="
				+ isServerd + "]";
	}

}

public class Chef {

	public static void main(String[] args) {
		/*
		 * BufferedReader br = new BufferedReader(new
		 * InputStreamReader(System.in)); String line=""; try { line =
		 * br.readLine(); } catch (IOException e) { e.printStackTrace(); } int N
		 * = Integer.parseInt(line); String[] s = line.split(" ");
		 * 
		 * Customer [ ] waitingLine = new Customer [capacity];
		 * 
		 * for(int i=0;i<N;i++){
		 * 
		 * }
		 */
		int totalCustomerVisitsInDay = 4;

		Customer c1 = new Customer(3, 6, 7, false);
		Customer c2 = new Customer(2, 3, 14, true);
		Customer c3 = new Customer(0, 4, 4, false);
		Customer c4 = new Customer(2, 13, 15, true);
		// Customer c5 = new Customer(1, 3, 1, true);

		List<Customer> list = new ArrayList<Customer>();
		list.add(c1);
		list.add(c2);
		list.add(c3);
		list.add(c4);
		// list.add(c5);
		List<Future<Customer>> futureList = new ArrayList<Future<Customer>>();
		ExecutorService executor = Executors
				.newFixedThreadPool(totalCustomerVisitsInDay);
		for (Customer c : list) {
			System.out.println(c);
			Future<Customer> response = executor.submit(new ProcessOrder(c));
			futureList.add(response);
		}
		int totalPrefferedServed = 0;
		int totalNonPrefferedServed = 0;
		System.out.println("\n Processing ...");
		for (Future<Customer> response : futureList) {
			try {
				Customer c = response.get();
				if (c.isServerd()) {
					if (c.isPrefferedCustomer())
						totalPrefferedServed++;
					else
						totalNonPrefferedServed++;
				}
				System.out.println(c);
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}
		}

		System.out.println("Total Preffered Served : " + totalPrefferedServed);
		System.out.println("Total Non Preffered Served : "
				+ totalNonPrefferedServed);

		// queue.offer(c1, 3000, timeUnit);
	}
}

/*class ProcessOrder implements Callable<Customer> {

	PriorityBlockingQueue<Customer> queue = new PriorityBlockingQueue<Customer>(
			5, new Comparator<Customer>() {

				@Override
				public int compare(Customer o1, Customer o2) {
					return o2.compareTo(o1);
				}
			});

	Customer customer;

	public ProcessOrder(Customer c) {
		customer = c;
		queue.add(c);
	}

	@Override
	public Customer call() throws Exception {
		Lock lock = new ReentrantLock();
		Customer c = null;
		try {
			// lock.lock();
			c = queue.poll();
			int timeUnit = c.getArrivalTime();
			if (c.getProcesOrderTime() >= 3) {
				while (timeUnit > 0) {
					// this.wait(timeUnit * 1000);
					Thread.sleep(timeUnit * 100);
					timeUnit--;
				}
				// notifyAll();
				c.setWaitTime(c.getProcesOrderTime() + c.getServeOrderTime());
				c.setServerd(true);
				c.setLeaveTime(c.getArrivalTime() + c.getServeOrderTime());
			} else {
				c.setWaitTime(c.getProcesOrderTime());
				c.setServerd(false);
				c.setLeaveTime(c.getArrivalTime() + c.getProcesOrderTime());
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// lock.unlock();
		}
		return c;
	}
}*/

class ProcessOrder implements Callable<Customer> {

/*	PriorityBlockingQueue<Customer> queue = new PriorityBlockingQueue<Customer>(
			5, new Comparator<Customer>() {

				@Override
				public int compare(Customer o1, Customer o2) {
					return o2.compareTo(o1);
				}
			});*/

	Customer c;
	int cheftime=0;

	public ProcessOrder(Customer cust) {
		c = cust;
		cheftime +=c.getProcesOrderTime();
		//queue.add(c);
	}

	@Override
	public Customer call() throws Exception {
		Lock lock = new ReentrantLock();
		//Customer c = null;
		try {
			// lock.lock();
			//c = queue.poll();
			int timeUnit = c.getArrivalTime() + c.getServeOrderTime();
			while (timeUnit > 0) {
				
				// this.wait(timeUnit * 1000);
				Thread.sleep(timeUnit * 1000);
				timeUnit--;
				cheftime--;
			}
			// notifyAll();
			
			c.setWaitTime(c.getProcesOrderTime() + c.getServeOrderTime());
			c.setServerd(true);
			c.setLeaveTime(c.getArrivalTime() + c.getServeOrderTime());

			c.setWaitTime(c.getProcesOrderTime());
			c.setServerd(false);
			c.setLeaveTime(c.getArrivalTime() + c.getProcesOrderTime());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// lock.unlock();
		}
		return c;
	}
}
