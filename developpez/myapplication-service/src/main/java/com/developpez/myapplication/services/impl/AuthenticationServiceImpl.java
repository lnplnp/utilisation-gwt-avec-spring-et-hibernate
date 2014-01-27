package com.developpez.myapplication.services.impl;

import org.springframework.security.Authentication;
import org.springframework.security.GrantedAuthority;
import org.springframework.security.GrantedAuthorityImpl;
import org.springframework.security.context.SecurityContext;
import org.springframework.security.context.SecurityContextHolder;
import org.springframework.security.context.SecurityContextImpl;
import org.springframework.security.providers.UsernamePasswordAuthenticationToken;
import org.springframework.security.userdetails.User;

import com.developpez.myapplication.model.ReturnMemento;
import com.developpez.myapplication.services.AuthenticationService;

/**
 * {@link AuthenticationService} implementation.
 * 
 */
public class AuthenticationServiceImpl implements AuthenticationService {

	/* (non-Javadoc)
	 * @see com.hakanai.services.AuthenticationService#authenticate(java.lang.String, java.lang.String)
	 */
	public ReturnMemento authenticate(String username, String password) 
	{
	    // create a test case where admin have ROLE_ADMIN and ROLE_USER
	    if (username.equals("admin"))
	    {
    		// creating an authenticated user token for demo
    		// regardless of username and password values
    		GrantedAuthority[] authorities = new GrantedAuthority[] { new GrantedAuthorityImpl("ROLE_ADMIN"),new GrantedAuthorityImpl("ROLE_USER") };
    		User user = new User("xxx", "yyy", true, true, true, true, authorities);
    		Authentication auth = new UsernamePasswordAuthenticationToken(user, password, authorities);
    		SecurityContext sc = new SecurityContextImpl();
    		sc.setAuthentication(auth);
    		SecurityContextHolder.setContext(sc);
    
    		return new ReturnMemento(ReturnMemento.CODE_SUCCESS,ReturnMemento.CODE_SUCCESS);
	    }
	    // user only have ROLE_USER
	    else if (username.equals("user"))
	    {
            // creating an authenticated user token for demo
            // regardless of username and password values
            GrantedAuthority[] authorities = new GrantedAuthority[] { new GrantedAuthorityImpl("ROLE_USER") };
            User user = new User("xxx", "yyy", true, true, true, true, authorities);
            Authentication auth = new UsernamePasswordAuthenticationToken(user, password, authorities);
            SecurityContext sc = new SecurityContextImpl();
            sc.setAuthentication(auth);
            SecurityContextHolder.setContext(sc);
    
            return new ReturnMemento(ReturnMemento.CODE_SUCCESS,ReturnMemento.CODE_SUCCESS);
	    }
	    // other people can't log in
	    else
	    {
	        ReturnMemento rm = new ReturnMemento(ReturnMemento.CODE_ERROR,ReturnMemento.CODE_ERROR);
	        rm.getErrors().put("login","login or password incorrect");
	        rm.getErrors().put("password","login or password incorrect");
	        return rm;
	    }
	}

	/* (non-Javadoc)
	 * @see com.hakanai.services.AuthenticationService#logout()
	 */
	public void logout() 
	{
		SecurityContextHolder.clearContext();
	}

}
