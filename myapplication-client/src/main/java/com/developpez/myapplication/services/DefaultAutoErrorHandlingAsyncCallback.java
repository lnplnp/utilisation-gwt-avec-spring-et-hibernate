package com.developpez.myapplication.services;

import com.smartgwt.client.util.SC;

/**
 * Default implementation of {@link AutoErrorHandlingAsyncCallback} that display exception
 * 
 */
public abstract class DefaultAutoErrorHandlingAsyncCallback<T> extends AutoErrorHandlingAsyncCallback<T> 
{

    /* (non-Javadoc)
     * @see com.hakanai.services.AutoErrorHandlingAsyncCallback#onException(java.lang.Throwable)
     */
    @Override
    protected void onException(Throwable throwable)
    {
        SC.say(throwable.getMessage());
    }

    /* (non-Javadoc)
     * @see com.hakanai.services.AutoErrorHandlingAsyncCallback#onSecurityException(java.lang.Throwable)
     */
    @Override
    protected void onSecurityException(Throwable throwable)
    {
        SC.say("You do not have enough privilege to carry out the operation!");
    }
}