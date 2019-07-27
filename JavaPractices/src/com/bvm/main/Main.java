package com.bvm.main;

import java.util.Map;

import com.bvm.session.SessionContext;
import com.bvm.session.TokenManager;
import com.google.common.base.Optional;


public class Main {

	public static void main(String[] args) {
		
		SessionContext context = new SessionContext();
		context.setUserName("Bala");
		context.setTenantHostName("QXL");
		
		Optional<String> tokenResult = TokenManager.getInstance()
				.generateSingleUseToken(context);
		if (tokenResult.isPresent()) {
			System.out.println("token Value: "+tokenResult.get());
		} else {
			System.out.println("Error while generating token");
		}
		
		
		SessionContext context2= new SessionContext();
		context2.setUserName("pavan");
		context2.setTenantHostName("QXL");
		
		Optional<String> tokenResult2 = TokenManager.getInstance()
				.generateSingleUseToken(context2);
		if (tokenResult2.isPresent()) {
			System.out.println("token Value: "+tokenResult2.get());
		} else {
			System.out.println("Error while generating token");
		}
		
		
		SessionContext context3 = new SessionContext();
		context3.setUserName("venky");
		context3.setTenantHostName("QXL");
		
		Optional<String> tokenResult3 = TokenManager.getInstance()
				.generateSingleUseToken(context3);
		if (tokenResult3.isPresent()) {
			System.out.println("token Value: "+tokenResult3.get());
		} else {
			System.out.println("Error while generating token");
		}
				
		System.out.println("SessionStore= "+TokenManager.getInstance().getSessionStore());
		
		Thread t1 = new Thread("MainThread");
		t1.start();
		try {
			t1.wait(7200);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
