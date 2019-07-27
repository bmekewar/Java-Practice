package com.bvm.interview2;

import javax.swing.JOptionPane;
import java.util.Random;

class Foody {
	int serviceTime;
	int arrivalTime;
	int departureTime;
	int waitTime;
	int interArrivalTime;
	int totalTime;
	int totalWaitTime;

	public int getServiceTime() {
		return serviceTime;
	}

	public void setServiceTime(int serviceTime) {
		this.serviceTime = serviceTime;
	}

	public int getArrivalTime() {
		return arrivalTime;
	}

	public void setArrivalTime(int arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	public int getDepartureTime() {
		return departureTime;
	}

	public void setDepartureTime(int departureTime) {
		this.departureTime = departureTime;
	}

	public int getWaitTime() {
		return waitTime;
	}

	public void setWaitTime(int waitTime) {
		this.waitTime = waitTime;
	}

	public int getInterArrivalTime() {
		return interArrivalTime;
	}

	public void setInterArrivalTime(int interArrivalTime) {
		this.interArrivalTime = interArrivalTime;
	}

	public int getTotalTime() {
		return totalTime;
	}

	public void setTotalTime(int totalTime) {
		this.totalTime = totalTime;
	}

	public int getTotalWaitTime() {
		return totalWaitTime;
	}

	public void setTotalWaitTime(int totalWaitTime) {
		this.totalWaitTime = totalWaitTime;
	}

	public Foody() {
		super();
		// TODO Auto-generated constructor stub
	}
}

public class FastFoodSimulation {

	public static void main(String args[]) {
		int time;
		boolean reRun = true;
		int maxQueueLength;
		int customerCounter;
		int serviceTime;
		int arrivalTime;
		int departureTime;
		int waitTime;
		int interArrivalTime;
		int totalTime;
		int totalWaitTime;

		do {
			int minServiceTime = Integer
					.parseInt(JOptionPane
							.showInputDialog("Enter Minimum Service Time (in minutes)."));
			int maxServiceTime = Integer
					.parseInt(JOptionPane
							.showInputDialog("Enter Maximum Service Time (in minutes)."));
			int minInterArrivalTime = Integer
					.parseInt(JOptionPane
							.showInputDialog("Enter Minimum Time Between Customers(in minutes)."));
			int maxInterArrivalTime = Integer
					.parseInt(JOptionPane
							.showInputDialog("Enter Maximum Time Between Customers(in minutes)."));
			int numberOfCustomers = Integer.parseInt(JOptionPane
					.showInputDialog("Enter Number of Customers to Simulate."));

			time = 0;
			// LinkedQueue queue = new LinkedQueue();
			maxQueueLength = 0;
			customerCounter = 0;
			arrivalTime = 0;
			departureTime = 0;
			waitTime = 0;
			totalTime = 0;
			totalWaitTime = 0;

			while (customerCounter < numberOfCustomers) {
				serviceTime = randInt(minServiceTime, maxServiceTime);
				interArrivalTime = randInt(minInterArrivalTime,
						maxInterArrivalTime);
				arrivalTime = time + interArrivalTime;

				Foody cust = new Foody();
				cust.setServiceTime(serviceTime);
				cust.setInterArrivalTime(interArrivalTime);
				cust.setArrivalTime(arrivalTime);

				if (time == cust.getArrivalTime() && maxQueueLength == 0) {
					// queue.offer(cust);
					customerCounter++;
					departureTime = arrivalTime + serviceTime;
					totalTime = departureTime - arrivalTime;
					cust.setWaitTime(0);
					cust.setDepartureTime(departureTime);
					cust.setTotalTime(totalTime);
					// int length = queue.size();
					// if(length > maxQueueLength)
					// maxQueueLength = length;
					// queue.remove(cust);
					time++;
				} else if (time == cust.getArrivalTime() && maxQueueLength >= 1) {
					// queue.offer(cust);
					customerCounter++;
				}
			}
		} while (reRun);
	}

	/**
	 * Returns a psuedo-random number between min and max, inclusive. The
	 * difference between min and max can be at most Integer.MAX_VALUE - 1 By
	 * Greg Case @
	 * http://stackoverflow.com/questions/363681/generating-random-numbers
	 * -in-a-range-with-java
	 *
	 * @param min
	 *            Minimim value
	 * @param max
	 *            Maximim value. Must be greater than min.
	 * @return Integer between min and max, inclusive.
	 * @see java.util.Random#nextInt(int)
	 */
	public static int randInt(int min, int max) {

		// Usually this can be a field rather than a method variable
		Random rand = new Random();

		// nextInt is normally exclusive of the top value,
		// so add 1 to make it inclusive
		int randomNum = rand.nextInt((max - min) + 1) + min;

		return randomNum;
	}
}
