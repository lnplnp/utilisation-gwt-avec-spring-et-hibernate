package com.developpez.myapplication.services;

import com.developpez.myapplication.model.ReturnMemento;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * Authentication service.
 * 
 */
@RemoteServiceRelativePath("AuthenticationService.rpc")
public interface AuthenticationService extends RemoteService {

	/**
	 * Authenticates user.
	 * 
	 * @param username
	 * @param password
	 * @return whether authentication is successful
	 */
	ReturnMemento authenticate(String username, String password);

	/**
	 * Terminates a user's security session.
	 */
	void logout();
}
