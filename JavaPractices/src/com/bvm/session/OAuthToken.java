package com.bvm.session;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OAuthToken implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final Logger LOG = LoggerFactory
			.getLogger(OAuthToken.class.getName());
	
	private String token;

	private long validTo;

	public OAuthToken(String token) {
		super();
		this.token = token;
		calculateValidity();
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
		calculateValidity();
	};

	private void calculateValidity() {
		// C4C tokens have default validity as 3600 seconds
		this.validTo = (System.currentTimeMillis() / 1000L) + 3500;
	}

	public boolean isValid() {
		long current = System.currentTimeMillis() / 1000L;
		LOG.info("current => " + current + " / " + this.validTo);
		return current < this.validTo;
	}
}