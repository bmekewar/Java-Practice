package com.bvm.session;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bvm.session.ExpirationListener;
import com.bvm.session.SessionContext;
import com.bvm.session.TokenManager;

public class SessionExpirationListener implements ExpirationListener<SessionContext> {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(SessionExpirationListener.class);

	@Override
	public void expired(Object expiredObject) {

		LOGGER.info("Session expired => " + expiredObject.toString());

		TokenManager.getInstance().removeUserSessionEntry(((SessionContext) expiredObject).getUserKey());
	}

}