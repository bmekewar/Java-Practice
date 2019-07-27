package com.bvm.main;

import javax.ws.rs.core.Feature;
import javax.ws.rs.core.FeatureContext;

public class ApplicationFeature implements Feature
{
	@Override
	public boolean configure(final FeatureContext context)
	{
		context.register(TokenValidation.class);
		return true;
	}
}
