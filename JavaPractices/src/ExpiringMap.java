
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExpiringMap<K, V> implements Map<K, V> {

	private static Logger LOGGER = LoggerFactory.getLogger(ExpiringMap.class);

	/**
	 * The default value, 60
	 */
	public static final int DEFAULT_TIME_TO_LIVE = 7200;

	/**
	 * The default value, 1
	 */
	public static final int DEFAULT_EXPIRATION_INTERVAL = 10;

	private static volatile int expirerCount = 1;

	private final ConcurrentHashMap<K, ExpiringObject> delegate;

	private final CopyOnWriteArrayList<ExpirationListener<V>> expirationListeners;

	private final Expirer expirer;

	public ExpiringMap() {
		this(DEFAULT_TIME_TO_LIVE, DEFAULT_EXPIRATION_INTERVAL);
	}

	public ExpiringMap(int timeToLive) {
		this(timeToLive, DEFAULT_EXPIRATION_INTERVAL);
	}

	public ExpiringMap(int timeToLive, int expirationInterval) {
		// times are in seconds
		this(new ConcurrentHashMap<K, ExpiringObject>(), new CopyOnWriteArrayList<ExpirationListener<V>>(), timeToLive,
				expirationInterval);
	}

	private ExpiringMap(ConcurrentHashMap<K, ExpiringObject> delegate,
			CopyOnWriteArrayList<ExpirationListener<V>> expirationListeners, int timeToLive, int expirationInterval) {
		this.delegate = delegate;
		this.expirationListeners = expirationListeners;

		this.expirer = new Expirer();
		expirer.setTimeToLive(timeToLive);
		expirer.setExpirationInterval(expirationInterval);
	}

	public V put(K key, V value) {
		ExpiringObject answer = delegate.put(key, new ExpiringObject(key, value, System.currentTimeMillis()));
		if (answer == null) {
			return null;
		}

		return answer.getValue();
	}

	public V get(Object key) {
		LOGGER.info("get called for key => " + key);
		ExpiringObject object = delegate.get(key);

		if (object != null) {
			// Get resets the expiry timer
			object.setLastAccessTime(System.currentTimeMillis());
			return object.getValue();
		}

		return null;
	}

	public V remove(Object key) {
		ExpiringObject answer = delegate.remove(key);
		if (answer == null) {
			return null;
		}

		return answer.getValue();
	}

	public boolean containsKey(Object key) {
		return delegate.containsKey(key);
	}

	public boolean containsValue(Object value) {
		return delegate.containsValue(value);
	}

	public int size() {
		return delegate.size();
	}

	public boolean isEmpty() {
		return delegate.isEmpty();
	}

	public void clear() {
		delegate.clear();
	}

	@Override
	public int hashCode() {
		return delegate.hashCode();
	}

	public Set<K> keySet() {
		return delegate.keySet();
	}

	@Override
	public boolean equals(Object obj) {
		return delegate.equals(obj);
	}

	public void putAll(Map<? extends K, ? extends V> inMap) {
		for (Entry<? extends K, ? extends V> e : inMap.entrySet()) {
			this.put(e.getKey(), e.getValue());
		}
	}

	public Collection<V> values() {
		List<ExpiringObject> expObjList = new ArrayList<ExpiringObject>(delegate.values());
		List<V> resultList = new ArrayList<V>();

		for (ExpiringObject e : expObjList) {
			resultList.add(e.getValue());
		}

		return resultList;
	}

	public Set<Map.Entry<K, V>> entrySet() {
		throw new UnsupportedOperationException();
	}

	public void addExpirationListener(ExpirationListener<V> listener) {
		expirationListeners.add(listener);
	}

	public void removeExpirationListener(ExpirationListener<V> listener) {
		expirationListeners.remove(listener);
	}

	public Expirer getExpirer() {
		return expirer;
	}

	public int getExpirationInterval() {
		return expirer.getExpirationInterval();
	}

	public int getTimeToLive() {
		return expirer.getTimeToLive();
	}

	public void setExpirationInterval(int expirationInterval) {
		expirer.setExpirationInterval(expirationInterval);
	}

	public void setTimeToLive(int timeToLive) {
		expirer.setTimeToLive(timeToLive);
	}

	private class ExpiringObject {
		private K key;

		private V value;

		private long lastAccessTime;

		private final ReadWriteLock lastAccessTimeLock = new ReentrantReadWriteLock();

		ExpiringObject(K key, V value, long lastAccessTime) {
			if (value == null) {
				throw new IllegalArgumentException("An expiring object cannot be null.");
			}

			this.key = key;
			this.value = value;
			this.lastAccessTime = lastAccessTime;
		}

		public long getLastAccessTime() {
			lastAccessTimeLock.readLock().lock();

			try {
				return lastAccessTime;
			} finally {
				lastAccessTimeLock.readLock().unlock();
			}
		}

		public void setLastAccessTime(long lastAccessTime) {
			lastAccessTimeLock.writeLock().lock();

			try {
				this.lastAccessTime = lastAccessTime;
			} finally {
				lastAccessTimeLock.writeLock().unlock();
			}
		}

		public K getKey() {
			return key;
		}

		public V getValue() {
			return value;
		}

		@Override
		public boolean equals(Object obj) {
			return value.equals(obj);
		}

		@Override
		public int hashCode() {
			return value.hashCode();
		}
	}

	public class Expirer implements Runnable {

		private final Logger LOGGER = LoggerFactory.getLogger(Expirer.class);

		private final ReadWriteLock stateLock = new ReentrantReadWriteLock();

		private long timeToLiveMillis;

		private long expirationIntervalMillis;

		private boolean running = false;

		private final Thread expirerThread;

		public Expirer() {
			expirerThread = new Thread(this, "ExpiringMapExpirer-" + expirerCount++);
			expirerThread.setDaemon(true);
		}

		public void run() {
			while (running) {
				processExpires();

				try {
					Thread.sleep(expirationIntervalMillis);
				} catch (InterruptedException e) {
				}
			}
		}

		private void processExpires() {
			long timeNow = System.currentTimeMillis();

			for (ExpiringObject o : delegate.values()) {

				// LOGGER.info("processExpires => " + o.toString());
				if (timeToLiveMillis <= 0) {
					continue;
				}

				long timeIdle = timeNow - o.getLastAccessTime();

				if (timeIdle >= timeToLiveMillis) {

					// LOGGER.info("processExpires remvoe Object => " + o.toString());

					delegate.remove(o.getKey());

					for (ExpirationListener<V> listener : expirationListeners) {
						listener.expired(o.getValue());
					}
				}
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
				}
			} finally {
				stateLock.writeLock().unlock();
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
	}
}
