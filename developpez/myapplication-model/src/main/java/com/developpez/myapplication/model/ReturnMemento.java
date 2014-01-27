package com.developpez.myapplication.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Generic class used to encapsulate the result of a method
 * The map of errors could be used on client side with the dynamicForm and its 
 * method setErrors.
 * 
 */
public class ReturnMemento implements Serializable
{
    /**
     * serial id
     */
    private static final long serialVersionUID = 2451548934252076802L;

    /**
     * generic success code
     */
    public static final int CODE_SUCCESS=0;
    
    /**
     * generic error code
     */
    public static final int CODE_ERROR=1;
    
    
    /**
     * error code
     */
    private int code;

    /**
     * subcode used to add some details about the error
     */
    private int subCode;

    /**
     * a human readable message
     */
    private String message;

    /**
     * the map of errors that could be used for SmartGwt DynamicForm
     */
    private Map<String, String> errors = new HashMap<String, String>();

    /**
     * Empty constructor
     * needed if you want to implement {@link Serializable}: http://java.sun.com/j2se/1.4.2/docs/api/java/io/Serializable.html 
     */
    public ReturnMemento()
    {
        
    }
    
    /**
     * Constructor with code and subcode
     * @param code
     * @param subCode
     */
    public ReturnMemento (int code, int subCode)
    {
        this.code=code;
        this.subCode=subCode;
    }


    /**
     * @return the code
     */
    public int getCode()
    {
        return code;
    }

    /**
     * @param code the code to set
     */
    public void setCode(int code)
    {
        this.code = code;
    }

    /**
     * @return the subCode
     */
    public int getSubCode()
    {
        return subCode;
    }

    /**
     * @param subCode the subCode to set
     */
    public void setSubCode(int subCode)
    {
        this.subCode = subCode;
    }

    /**
     * @return the message
     */
    public String getMessage()
    {
        return message;
    }

    /**
     * @param message the message to set
     */
    public void setMessage(String message)
    {
        this.message = message;
    }

    /**
     * @return the errors
     */
    public Map<String, String> getErrors()
    {
        return errors;
    }

    /**
     * @param errors the errors to set
     */
    public void setErrors(Map<String, String> errors)
    {
        this.errors = errors;
    }
}
