package com.bvm.session;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bvm.main.Utils;
import com.bvm.session.ExpiringMap;
import com.bvm.session.SessionContext;
import com.bvm.session.SessionExpirationListener;
import com.bvm.session.Session;
import com.google.common.base.Optional;
import com.google.common.collect.Maps;

public class TokenManager {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(TokenManager.class);

	private static final String SHARED_SECRET = "SHARED_SECRET";

	// 64 byte strong Secure random key
	private String secretKey = null; // =
										// "rubNWG79FHt4JiM64LZcIyDQECRrbSafMtPEw5ULpGtUaQyv6nfqnlUHvg984hJu";

	// Map from Session Key -> Session Context
	private ExpiringMap<String, SessionContext> sessionStore = new ExpiringMap<String, SessionContext>();

	private Map<String, String> userSessionStore = new ConcurrentHashMap<String, String>();

	private static TokenManager _singleton = null;

	public static synchronized TokenManager getInstance() {
		if (_singleton == null) {
			_singleton = new TokenManager();
		}
		return _singleton;
	}

	private TokenManager() {
		super();
		//secretKey = System.getProperty(SHARED_SECRET);
		sessionStore.addExpirationListener(new SessionExpirationListener());
		sessionStore.getExpirer().startExpiringIfNotStarted();
		//LOGGER.info("TokenManager## sessionStore"+ sessionStore.toString());
	}
	
	public Optional<String> generateSingleUseToken(SessionContext sessionContext ) {
		String singleUseToken = null;

		// Invalidate the old token
		String singleUseToken_old = userSessionStore.get(sessionContext.getUserKey());
		if (singleUseToken_old != null) {
			userSessionStore.remove(sessionContext.getUserKey());
			sessionStore.remove(singleUseToken_old);
		}
		// Create a new token
		singleUseToken = Utils.csRandomAlphaNumericString(64);
		sessionStore.put(singleUseToken, sessionContext);
		userSessionStore.put(sessionContext.getUserKey(), singleUseToken);
		LOGGER.info("TokenManager## singleUseToken"+ singleUseToken);
		return Optional.of(singleUseToken);
	}

	public void removeUserSessionEntry(String userKey) {
		userSessionStore.remove(userKey);
	}
		
	public SessionContext getSessionContext(String token) {
		return sessionStore.get(token);
	}

	public boolean validateToken(Optional<String> token) {

		if (!token.isPresent()) {
			return false;
		}

		LOGGER.info("validateToken token => " + token.get());

		if (sessionStore.containsKey(token.get()))
			return true;

		return false;
	}
	
	public Optional<Map.Entry<String, Session>> getSessionEntry(String singleUseToken) {

		if (singleUseToken == null)
			return Optional.absent();

		SessionContext context = sessionStore.get(singleUseToken);

		if (context != null) {
			return Optional.of(Maps.immutableEntry(singleUseToken, context.createSession()));
		}

		return Optional.absent();

	}
	
	public Optional<Map.Entry<String, Session>> getSession(String token, String cookieValue) {

		return getSessionEntry(token);
	}

	public ExpiringMap<String, SessionContext> getSessionStore() {
		return sessionStore;
	}

	public void setSessionStore(ExpiringMap<String, SessionContext> sessionStore) {
		this.sessionStore = sessionStore;
	}

	public Map<String, String> getUserSessionStore() {
		return userSessionStore;
	}

	public void setUserSessionStore(Map<String, String> userSessionStore) {
		this.userSessionStore = userSessionStore;
	}
	
	public void printSessionStore(){

		
	}
	
}
