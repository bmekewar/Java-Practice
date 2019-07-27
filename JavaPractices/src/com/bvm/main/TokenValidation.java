package com.bvm.main;

import javax.ws.rs.core.Response;

import com.bvm.session.SessionContext;
import com.bvm.session.TokenManager;
import com.google.common.base.Optional;

@javax.ws.rs.Path("/token")
public class TokenValidation {

	@javax.ws.rs.core.Context
	private javax.ws.rs.core.UriInfo uriInfo;
	
	@javax.ws.rs.POST
	@javax.ws.rs.Consumes({"application/json"})	
	@javax.ws.rs.Produces({ "application/json" })
	public Response post(
			@javax.validation.constraints.NotNull @javax.ws.rs.HeaderParam("c4c-tenant") final java.lang.String c4cTenant,
			@javax.validation.constraints.NotNull @javax.ws.rs.HeaderParam("c4c-username") final java.lang.String c4cUsername,
			@javax.validation.constraints.NotNull @javax.ws.rs.HeaderParam("c4c-nonce") final java.lang.String c4cNonce,
			@javax.validation.constraints.NotNull @javax.ws.rs.HeaderParam("c4c-signature") final java.lang.String c4cSignature,
			@javax.validation.constraints.NotNull @javax.ws.rs.HeaderParam("c4c-timestamp") final java.lang.String c4cTimestamp) {
		
		SessionContext context = new SessionContext();
		context.setUserName(c4cUsername);
		context.setTenantHostName(c4cTenant);
		//context.setCsrfToken("abcd");
		
		try {
			Optional<String> tokenResult = TokenManager.getInstance()
					.generateSingleUseToken(context);
			if (tokenResult.isPresent()) {
				
				// Successful single use-token
				return Response.status(Response.Status.ACCEPTED).entity(tokenResult.get())
						.header("single_use_token", tokenResult.get()).build();
			} else {
				return Response.status(Response.Status.FORBIDDEN)
						.entity(Utils.getError(403)).build();
			}
		} catch (Exception e) {
			return Response.status(Response.Status.FORBIDDEN)
					.entity(Utils.getError(403)).build();
		}
	}
}

