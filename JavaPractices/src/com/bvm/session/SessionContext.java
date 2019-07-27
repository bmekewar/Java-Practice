package com.bvm.session;

import java.io.IOException;
import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bvm.session.OAuthToken;

public class SessionContext implements Serializable {
	private static final long serialVersionUID = 1L;

	private static final Logger LOG = LoggerFactory
			.getLogger(SessionContext.class.getName());

	private String userName;

	private String tenantHostName;

	private OAuthToken oauthToken;

	private String uriInfo;

	private String csrfToken;

	private String currentScenarioId;

	public String getCurrentScenarioId() {
		return currentScenarioId;
	}

	public void setCurrentScenarioId(String currentScenarioId) {
		this.currentScenarioId = currentScenarioId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getTenantHostName() {
		return tenantHostName;
	}

	public void setTenantHostName(String tenantHostName) {
		this.tenantHostName = tenantHostName;
	}

	public String getUserKey() {
		return this.userName + this.tenantHostName;
	}

	public String getUriInfo() {
		return uriInfo;
	}

	public void setUriInfo(String uriInfo) {
		this.uriInfo = uriInfo;
		LOG.info("SessionContext#setUriInfo => " + uriInfo);
	}

	public OAuthToken getOauthToken() throws IOException {
		// If the existing token is valid dont do anything
		if (oauthToken == null || !oauthToken.isValid()) {
			LOG.info("oauthToken is not valid !!");
			// OAuthSAMLClient client = new OAuthSAMLClient();
			// oauthToken = new OAuthToken(client.getAccessToken(this));
			oauthToken = new OAuthToken("Junk_Token_For_Testing");
		}
		LOG.info("SessionContext: Oauthtoken:"+oauthToken.getToken());
		return oauthToken;
	}

	@Override
	public String toString() {
		return "SessionContext [userName=" + userName + ", tenantHostName="
				+ tenantHostName + ", oauthToken=" + oauthToken + ", uriInfo="
				+ uriInfo + ", csrfToken=" + csrfToken + ", currentScenarioId="
				+ currentScenarioId + "]";
	}

	public String getCsrfToken() {
		return csrfToken;
	}

	public void setCsrfToken(String csrfToken) {
		this.csrfToken = csrfToken;
	}

	public Session createSession() {
		Session result = new Session();
		result.setC4cTenant(this.tenantHostName);
		result.setC4cUsername(this.userName);
		result.setUriInfo(this.uriInfo);
		LOG.info("SessionContext: Session"+result);
		return result;
	}
}
