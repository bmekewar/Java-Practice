package com.bvm.session;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bvm.session.ExpirationListener;
import com.bvm.session.ExpiringObject;

public class Expirer<K, V> implements Runnable {

	private final Logger LOGGER = LoggerFactory.getLogger(Expirer.class);

	private final ReadWriteLock stateLock = new ReentrantReadWriteLock();

	private long timeToLiveMillis;

	private long expirationIntervalMillis;

	private boolean running = false;

	private final Thread expirerThread;

	private ConcurrentHashMap<K, ExpiringObject<K, V>> delegate;
	private CopyOnWriteArrayList<ExpirationListener<V>> expirationListeners;

//	public Expirer() {
//
//	}

	public Expirer(ConcurrentHashMap<K, ExpiringObject<K, V>> delegate,
			CopyOnWriteArrayList<ExpirationListener<V>> expirationListeners) {
		//this();
		//String userName = ((SessionContext)(delegate.get(1)).getValue()).getUserName();
		this.expirerThread = new Thread(this, "ExpiringMapExpirer-");//+userName);
		this.expirerThread.setDaemon(true);
		this.delegate = delegate;
		this.expirationListeners = expirationListeners;
	}

	public void run() {
		while (running) {
			processExpires();

			try {
				LOGGER.info("Expirer: No of threads running = "+this.delegate.mappingCount());
				//LOGGER.info("Expirer: Which threads running? = "+this.expirerThread.currentThread().getName());
				LOGGER.info("Expirer: Which threads running? = "+this.expirerThread.currentThread());
				Thread.sleep(expirationIntervalMillis);
			} catch (InterruptedException e) {
			}
		}
	}

	private void processExpires() {
		long timeNow = System.currentTimeMillis();

		for (ExpiringObject<K, V> o : delegate.values()) {

			//LOGGER.info("processExpires => " + o.toString());
			if (timeToLiveMillis <= 0) {
				continue;
			}

			long timeIdle = timeNow - o.getLastAccessTime();
			
			if (timeIdle >= timeToLiveMillis) {

				LOGGER.info("processExpires remove Object => " + o.toString());

				delegate.remove(o.getKey());

				for (ExpirationListener<V> listener : expirationListeners) {
					//LOGGER.info("Expirer: listener about to expire"+o.getValue());
					listener.expired(o.getValue());
				}
			}
		}
	}

	public boolean isRunning() {
		stateLock.readLock().lock();

		try {
			return running;
		} finally {
			stateLock.readLock().unlock();
		}
	}

	public int getTimeToLive() {
		stateLock.readLock().lock();

		try {
			return (int) timeToLiveMillis / 1000;
		} finally {
			stateLock.readLock().unlock();
		}
	}

	public void setTimeToLive(long timeToLive) {
		stateLock.writeLock().lock();

		try {
			this.timeToLiveMillis = timeToLive * 1000;
		} finally {
			stateLock.writeLock().unlock();
		}
	}

	public int getExpirationInterval() {
		stateLock.readLock().lock();

		try {
			return (int) expirationIntervalMillis / 1000;
		} finally {
			stateLock.readLock().unlock();
		}
	}

	public void setExpirationInterval(long expirationInterval) {
		stateLock.writeLock().lock();

		try {
			this.expirationIntervalMillis = expirationInterval * 1000;
		} finally {
			stateLock.writeLock().unlock();
		}
	}

	public void startExpiring() {
		stateLock.writeLock().lock();

		try {
			if (!running) {
				running = true;
				expirerThread.start();
			}
		} finally {
			stateLock.writeLock().unlock();
		}
	}

	public void startExpiringIfNotStarted() {
		stateLock.readLock().lock();
		try {
			if (running) {
				return;
			}
		} finally {
			stateLock.readLock().unlock();
		}

		stateLock.writeLock().lock();
		try {
			if (!running) {
				running = true;
				expirerThread.start();
				LOGGER.info("Expirer thread Started");
			}
		} finally {
			stateLock.writeLock().unlock();
		}
	}

	public void stopExpiring() {
		stateLock.writeLock().lock();

		try {
			if (running) {
				running = false;
				expirerThread.interrupt();
				LOGGER.info("Expirer thread Stopped");
			}
		} finally {
			stateLock.writeLock().unlock();
		}
	}

}
