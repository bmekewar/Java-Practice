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

class Customers implements Comparable<Customers> {

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

	public Customers() {
		super();
	}

	public Customers(int arrivalTime, int procesOrderTime, int serveOrderTime,
			boolean prefferedCustomer) {
		super();
		this.arrivalTime = arrivalTime;
		this.procesOrderTime = procesOrderTime;
		this.serveOrderTime = serveOrderTime;
		this.prefferedCustomer = prefferedCustomer;
	}

	@Override
	public int compareTo(Customers customer) {
		return this.arrivalTime - customer.arrivalTime;
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

public class BiasedChef {

	public static void main(String[] args) {
		int totalCustomerVisitsInDay = 4;
		int chefOrderProcessTime = 0;

		Customers c1 = new Customers(3, 6, 7, false);
		Customers c2 = new Customers(2, 3, 14, true);
		Customers c3 = new Customers(0, 4, 4, false);
		Customers c4 = new Customers(2, 13, 15, true);
		// Customer c5 = new Customer(1, 3, 1, true);

		/*
		 * List<Customer> list = new ArrayList<Customer>(); list.add(c1);
		 * list.add(c2); list.add(c3); list.add(c4); // list.add(c5);
		 */
		PriorityBlockingQueue<Customers> queue = new PriorityBlockingQueue<Customers>(
				5, new Comparator<Customers>() {

					@Override
					public int compare(Customers o1, Customers o2) {
						return o2.compareTo(o1);
					}
				});
		queue.offer(c1);
		queue.offer(c2);
		queue.offer(c3);
		queue.offer(c4);
		// queue.offer(c5);

		List<Future<Customers>> futureList = new ArrayList<Future<Customers>>();
		ExecutorService executor = Executors
				.newFixedThreadPool(totalCustomerVisitsInDay);

		ChefController chef = ChefController.getInstance();
		chef.setBusy(false);
		chef.setChefOrderProcessTime(chefOrderProcessTime);
		System.out.println("Is Chef Busy : "+chef.isBusy());
		System.out.println("Chef pending order processing time: "+chef.getChefOrderProcessTime());
		for (Customers cusotmer : queue) {
			System.out.println(cusotmer);
			chef.setChefOrderProcessTime(chefOrderProcessTime + cusotmer.getServeOrderTime());
			Future<Customers> response = executor
					.submit(new Order(cusotmer, chef));
			futureList.add(response);
		}
		int totalPrefferedServed = 0;
		int totalNonPrefferedServed = 0;
		System.out.println("\n Processing ...");
		for (Future<Customers> response : futureList) {
			try {
				Customers c = response.get();
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

class Order implements Callable<Customers> {

	Customers customer;
	ChefController chef;
	int cheftime;

	public Order(Customers cust, ChefController chef) {
		customer = cust;
		this.chef = chef;
		this.cheftime = chef.getChefOrderProcessTime(); 
	}

	@Override
	public Customers call() throws Exception {

		Lock lock = new ReentrantLock();

		int timeUnit = customer.getArrivalTime() + customer.getServeOrderTime();
		int servetime = customer.getServeOrderTime();

		while (timeUnit > 0) {

			Thread.sleep(timeUnit * 1000);
			timeUnit--;
			cheftime--;
		}
		// Customer c = null;
		try {
			// lock.lock();
			// c = queue.poll();
			timeUnit = customer.getArrivalTime() + customer.getServeOrderTime();
			while (timeUnit > 0) {

				// this.wait(timeUnit * 1000);
				Thread.sleep(timeUnit * 1000);
				timeUnit--;
				cheftime--;
			}
			// notifyAll();

			customer.setWaitTime(customer.getProcesOrderTime() + customer.getServeOrderTime());
			customer.setServerd(true);
			customer.setLeaveTime(customer.getArrivalTime() + customer.getServeOrderTime());

			customer.setWaitTime(customer.getProcesOrderTime());
			customer.setServerd(false);
			customer.setLeaveTime(customer.getArrivalTime() + customer.getProcesOrderTime());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// lock.unlock();
		}
		return customer;
	}
}

class ChefController {

	final static ChefController INSTANCE = new ChefController();
	int chefOrderProcessTime;
	boolean isBusy;

	public int getChefOrderProcessTime() {
		return chefOrderProcessTime;
	}

	public void setChefOrderProcessTime(int chefOrderProcessTime) {
		this.chefOrderProcessTime = chefOrderProcessTime;
	}

	public boolean isBusy() {
		return isBusy;
	}

	public void setBusy(boolean isBusy) {
		this.isBusy = isBusy;
	}

	public static ChefController getInstance() {
		return INSTANCE;
	}
}
