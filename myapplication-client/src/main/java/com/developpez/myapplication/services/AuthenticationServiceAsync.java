package com.developpez.myapplication.services;

import com.developpez.myapplication.model.ReturnMemento;
import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * Authentication service async interface.
 * 
 */
public interface AuthenticationServiceAsync {

	public void authenticate(String username, String password, AsyncCallback<ReturnMemento> callback);

	public void logout(AsyncCallback<Object> callback);
}
