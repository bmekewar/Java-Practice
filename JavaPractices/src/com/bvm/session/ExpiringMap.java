package com.bvm.session;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bvm.session.ExpirationListener;
import com.bvm.session.ExpiringMap;
import com.bvm.session.Expirer;
import com.bvm.session.ExpiringObject;

public class ExpiringMap<K, V> implements Map<K, V> {

	private static Logger LOGGER = LoggerFactory.getLogger(ExpiringMap.class);

	public static final int DEFAULT_TIME_TO_LIVE = 60;
	public static final int DEFAULT_EXPIRATION_INTERVAL = 10;

	private final ConcurrentHashMap<K, ExpiringObject<K, V>> delegate;

	private final CopyOnWriteArrayList<ExpirationListener<V>> expirationListeners;

	private final Expirer<K,V> expirer;
	
	private static volatile int expirerCount = 1;

	// set all the required constructors

	public ExpiringMap() {
		this(DEFAULT_TIME_TO_LIVE, DEFAULT_EXPIRATION_INTERVAL);
		LOGGER.info("Expiring Map Initiated## C1 Default");
	}

	public ExpiringMap(int timeToLive) {
		this(timeToLive, DEFAULT_EXPIRATION_INTERVAL);
		LOGGER.info("Expiring Map Initiated## C2 ");
	}

	public ExpiringMap(int timeToLive, int expirationInterval) {
		this(new ConcurrentHashMap<K, ExpiringObject<K, V>>(),
				new CopyOnWriteArrayList<ExpirationListener<V>>(), timeToLive,
				expirationInterval);
		LOGGER.info("Expiring Map Initiated ## C3");
	}

	private ExpiringMap(ConcurrentHashMap<K, ExpiringObject<K, V>> delegate,
			CopyOnWriteArrayList<ExpirationListener<V>> expirationListeners,
			int timeToLive, int expirationInterval) {
		this.delegate = delegate;
		this.expirationListeners = expirationListeners;

		this.expirer = new Expirer<K,V>(this.delegate, this.expirationListeners);
		expirer.setTimeToLive(timeToLive);
		expirer.setExpirationInterval(expirationInterval);
		LOGGER.info("Expiring Map Initiated## C4");
		LOGGER.info("Expirer## Daemon thread## ExpiringMapExpirer-" + this.expirerCount++);
	}

	// override all the existing methods of map (concurrentHashmap)
	@Override
	public V get(Object key) {
		LOGGER.info("get called for key => " + key);
		ExpiringObject<K, V> existingExpiringObject = delegate.get(key);

		if (existingExpiringObject != null) {
			// Get resets the expiry timer
			existingExpiringObject
					.setLastAccessTime(System.currentTimeMillis());
			return existingExpiringObject.getValue();
		}
		return null;
	}

	@Override
	public V put(K key, V value) {
		LOGGER.info("Expiring Map Added new Entry["+ key+","+value+"]");
		ExpiringObject<K, V> newExpiringObject = new ExpiringObject<K, V>(key,
				value, System.currentTimeMillis());
		ExpiringObject<K, V> result = delegate.put(key, newExpiringObject);
		if (result == null)
			return null;

		LOGGER.info("Expiring Map :Expiring Object "+result);

		return result.getValue();
	}

	@Override
	public V remove(Object key) {
		ExpiringObject<K, V> result = delegate.remove(key);
		LOGGER.info("Expiring Map Entry Removed ["+ key+"]");
		return result.getValue();
	}

	@Override
	public int size() {
		return delegate.size();
	}

	@Override
	public boolean isEmpty() {
		return delegate.isEmpty();
	}

	@Override
	public boolean containsKey(Object key) {
		return delegate.containsKey(key);
	}

	@Override
	public boolean containsValue(Object value) {
		return delegate.containsValue(value);
	}

	@Override
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
		List<ExpiringObject<K, V>> expObjList = (List<ExpiringObject<K, V>>) delegate
				.values();
		List<V> resultList = new ArrayList<V>();

		for (ExpiringObject<K, V> e : expObjList) {
			resultList.add(e.getValue());
		}

		return resultList;
	}

	public Set<Map.Entry<K, V>> entrySet() {
		throw new UnsupportedOperationException();
	}

	// default setter getter for instance variables
	public Expirer<K,V> getExpirer() {
		return expirer;
	}

	// Other methods to fetch all properties
	public void addExpirationListener(ExpirationListener<V> listener) {
		expirationListeners.add(listener);
	}

	public void removeExpirationListener(ExpirationListener<V> listener) {
		expirationListeners.remove(listener);
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

	@Override
	public String toString() {
		return "ExpiringMap [delegate=" + delegate + ", expirationListeners="
				+ expirationListeners + ", expirer=" + expirer + "]";
	}
	
}
