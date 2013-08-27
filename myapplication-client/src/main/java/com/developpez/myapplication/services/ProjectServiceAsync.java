package com.developpez.myapplication.services;

import java.util.List;

import com.developpez.myapplication.model.Project;
import com.google.gwt.user.client.rpc.AsyncCallback;

public interface ProjectServiceAsync {

    public abstract void fetch (AsyncCallback<List<Project>> asyncCallback);

    public abstract void add (Project record, AsyncCallback<Project> asyncCallback);

    public abstract void update (Project record, AsyncCallback<Project> asyncCallback);

    public abstract void remove (Project record, AsyncCallback<Object> asyncCallback);
}