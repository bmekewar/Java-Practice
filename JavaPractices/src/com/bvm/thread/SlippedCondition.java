package com.bvm.thread;

public class SlippedCondition {

	private boolean isLocked = true;

	public void lock() {
		synchronized (this) {
			while (isLocked) {
				try {
					this.wait();
				} catch (InterruptedException e) {
					// do nothing, keep waiting
				}
			}
		}

		synchronized (this) {
			isLocked = true;
		}
	}

	public synchronized void unlock() {
		isLocked = false;
		this.notify();
	}

}
