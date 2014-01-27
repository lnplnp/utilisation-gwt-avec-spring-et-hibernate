package com.developpez.myapplication.services.exception;


/**
 * Exceptions related to security 
 */
public class ServiceSecurityException extends RuntimeException
{

    /**
     * serial id
     */
    private static final long serialVersionUID = -147394471507593493L;
    
    /**
     * Empty constructor
     */
    public ServiceSecurityException()
    {
        super();
    }
    
    /**
     * @param msg
     */
    public ServiceSecurityException(String msg)
    {
        super(msg);
    }
    
}
