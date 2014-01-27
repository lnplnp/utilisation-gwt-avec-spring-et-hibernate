package com.developpez.security.core;

import org.springframework.security.Authentication;
import org.springframework.security.AuthenticationException;
import org.springframework.security.providers.AuthenticationProvider;

/**
 * A dummy {@link AuthenticationProvider} implementation.
 * 
 * @author See Wah Cheng
 * @created 6 Jan 2009
 */
public class DummyAuthenticationProvider implements AuthenticationProvider {

	public Authentication authenticate(Authentication authentication) throws AuthenticationException {

		throw new IllegalStateException("This implementation is a dummy class, created purely so that "
				+ "spring security namespace tags can be used in application context, and this method should "
				+ "never be called");
	}

	@SuppressWarnings("unchecked")
	public boolean supports(Class clazz) {

		throw new IllegalStateException("This implementation is a dummy class, created purely so that "
				+ "spring security namespace tags can be used in application context, and this method should "
				+ "never be called");

	}
}
