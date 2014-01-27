package com.developpez.myapplication.client.datasources;

import java.util.List;

import com.developpez.myapplication.model.Project;
import com.developpez.myapplication.services.ProjectService;
import com.developpez.myapplication.services.ProjectServiceAsync;
import com.developpez.myapplication.services.exception.ServiceSecurityException;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.smartgwt.client.data.DSRequest;
import com.smartgwt.client.data.DSResponse;
import com.smartgwt.client.data.fields.DataSourceIntegerField;
import com.smartgwt.client.data.fields.DataSourceTextField;
import com.smartgwt.client.rpc.RPCResponse;
import com.smartgwt.client.types.FieldType;
import com.smartgwt.client.util.JSOHelper;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.grid.ListGridRecord;

/**
 * 
 *
 */
public class ProjectDS extends GwtRpcDataSource
{

    private static ProjectDS instance = null;

    /**
     * @return
     */
    public static ProjectDS getInstance()
    {
        if (instance == null)
        {
            instance = new ProjectDS("project_ds");
        }


        return instance;
    }

    /**
     * @param id
     */
    public ProjectDS(String id)
    {
        setID(id);
        setTitleField("project");
        DataSourceIntegerField field = new DataSourceIntegerField("id", "ID");
        field.setPrimaryKey(true);
        // AutoIncrement on server.
        field.setCanEdit(false);
        field.setHidden(true);
        field.setRequired(false);
        addField(field);
        DataSourceTextField project = new DataSourceTextField("name", "Project");
        project.setRequired(true);
        addField(project);
        DataSourceTextField desc = new DataSourceTextField("desc", "Description");
        addField(desc);
        DataSourceTextField url = new DataSourceTextField("url", "Url");
        url.setType(FieldType.LINK);
        addField(url);
    }
    
    /* (non-Javadoc)
     * @see com.hakanai.client.model.datasources.GwtRpcDataSource#executeFetch(java.lang.String, com.smartgwt.client.data.DSRequest, com.smartgwt.client.data.DSResponse)
     */
    @Override
    protected void executeFetch (final String requestId, final DSRequest request, final DSResponse response) {
        // This can be used as parameter for server side sorting.
//        request.getSortBy ();

        // Finding which rows were requested
        // Normaly these two indexes should be passed to server
        // but for this example I will do "paging" on client side
        final int startIndex = (request.getStartRow () < 0)?0:request.getStartRow ();
        final int endIndex = (request.getEndRow () == null)?-1:request.getEndRow ();
        ProjectServiceAsync service = GWT.create (ProjectService.class);
        service.fetch (new AsyncCallback<List<Project>> () {
            /* (non-Javadoc)
             * @see com.google.gwt.user.client.rpc.AsyncCallback#onFailure(java.lang.Throwable)
             */
            public void onFailure (Throwable caught) {
                if (caught instanceof ServiceSecurityException) {
                    SC.say("You do not have enough privilege to carry out the operation!");
                }
                else
                {
                    SC.say("An exception occured on server side :"+caught.getMessage());
                }
                response.setStatus (RPCResponse.STATUS_FAILURE);
                processResponse (requestId, response);
            }
            /**
             * @param result
             */
            public void onSuccess (List<Project> result) {
                // Calculating size of return list
                int size = result.size ();
                if (endIndex >= 0) {
                    if (endIndex < startIndex) {
                        size = 0;
                    }
                    else {
                        size = endIndex - startIndex + 1;
                    }
                }
                // Create list for return - it is just requested records
                ListGridRecord[] list = new ListGridRecord[size];
                if (size > 0) {
                    for (int i = 0; i < result.size (); i++) {
                        if (i >= startIndex && i <= endIndex) {
                            ListGridRecord record = new ListGridRecord ();
                            copyValues (result.get (i), record);
                            list[i - startIndex] = record;
                        }
                    }
                }
                response.setData (list);
                // IMPORTANT: for paging to work we have to specify size of full result set
                response.setTotalRows (result.size ());
                processResponse (requestId, response);
            }
        });
    }

    /* (non-Javadoc)
     * @see com.hakanai.client.model.datasources.GwtRpcDataSource#executeAdd(java.lang.String, com.smartgwt.client.data.DSRequest, com.smartgwt.client.data.DSResponse)
     */
    @Override
    protected void executeAdd (final String requestId, final DSRequest request, final DSResponse response) {
        // Retrieve record which should be added.
        JavaScriptObject data = request.getData ();
        ListGridRecord rec = new ListGridRecord (data);
        Project testRec = new Project ();
        copyValues (rec, testRec);
        ProjectServiceAsync service = GWT.create (ProjectService.class);
        service.add (testRec, new AsyncCallback<Project> () {
            /* (non-Javadoc)
             * @see com.google.gwt.user.client.rpc.AsyncCallback#onFailure(java.lang.Throwable)
             */
            public void onFailure (Throwable caught) {
                response.setStatus (RPCResponse.STATUS_FAILURE);
                processResponse (requestId, response);
            }
            /**
             * @param result
             */
            public void onSuccess (Project result) {
                ListGridRecord[] list = new ListGridRecord[1];
                ListGridRecord newRec = new ListGridRecord ();
                copyValues (result, newRec);
                list[0] = newRec;
                response.setData (list);
                processResponse (requestId, response);
            }
        });
    }

    /* (non-Javadoc)
     * @see com.hakanai.client.model.datasources.GwtRpcDataSource#executeUpdate(java.lang.String, com.smartgwt.client.data.DSRequest, com.smartgwt.client.data.DSResponse)
     */
    @Override
    protected void executeUpdate (final String requestId, final DSRequest request, final DSResponse response) {
        // Retrieve record which should be updated.
        // Next line would be nice to replace with line:
        // ListGridRecord rec = request.getEditedRecord ();
        ListGridRecord rec = getEditedRecord (request);
        Project testRec = new Project ();
        copyValues (rec, testRec);
        ProjectServiceAsync service = GWT.create (ProjectService.class);
        service.update (testRec, new AsyncCallback<Project> () {
            /* (non-Javadoc)
             * @see com.google.gwt.user.client.rpc.AsyncCallback#onFailure(java.lang.Throwable)
             */
            public void onFailure (Throwable caught) {
                response.setStatus (RPCResponse.STATUS_FAILURE);
                processResponse (requestId, response);
            }
            /**
             * @param result
             */
            public void onSuccess (Project result) {
                ListGridRecord[] list = new ListGridRecord[1];
                ListGridRecord updRec = new ListGridRecord ();
                copyValues (result, updRec);
                list[0] = updRec;
                response.setData (list);
                processResponse (requestId, response);
            }
        });
    }

    /* (non-Javadoc)
     * @see com.hakanai.client.model.datasources.GwtRpcDataSource#executeRemove(java.lang.String, com.smartgwt.client.data.DSRequest, com.smartgwt.client.data.DSResponse)
     */
    @Override
    protected void executeRemove (final String requestId, final DSRequest request, final DSResponse response) {
        // Retrieve record which should be removed.
        JavaScriptObject data = request.getData ();
        final ListGridRecord rec = new ListGridRecord (data);
        Project testRec = new Project ();
        copyValues (rec, testRec);
        ProjectServiceAsync service = GWT.create (ProjectService.class);
        service.remove (testRec, new AsyncCallback<Object> () {
            /* (non-Javadoc)
             * @see com.google.gwt.user.client.rpc.AsyncCallback#onFailure(java.lang.Throwable)
             */
            public void onFailure (Throwable caught) {
                response.setStatus (RPCResponse.STATUS_FAILURE);
                processResponse (requestId, response);
            }
            /**
             * @param result
             */
            public void onSuccess (Object result) {
                ListGridRecord[] list = new ListGridRecord[1];
                // We do not receive removed record from server.
                // Return record from request.
                list[0] = rec;
                response.setData (list);
                processResponse (requestId, response);
            }

        });
    }

    /**
     * @param from
     * @param to
     */
    private static void copyValues (ListGridRecord from, Project to) {
        to.setId (from.getAttributeAsInt ("id"));
        to.setName (from.getAttributeAsString ("name"));
        to.setDesc(from.getAttributeAsString ("desc"));
        to.setUrl(from.getAttributeAsString ("url"));
    }

    /**
     * @param from
     * @param to
     */
    private static void copyValues (Project from, ListGridRecord to) {
        to.setAttribute ("id", from.getId ());
        to.setAttribute ("name", from.getName ());
        to.setAttribute ("desc", from.getDesc());
        to.setAttribute ("url", from.getUrl());
    }

    /**
     * @param request
     * @return
     */
    private ListGridRecord getEditedRecord (DSRequest request) {
        // Retrieving values before edit
        JavaScriptObject oldValues = request.getAttributeAsJavaScriptObject ("oldValues");
        // Creating new record for combining old values with changes
        ListGridRecord newRecord = new ListGridRecord ();
        // Copying properties from old record
        JSOHelper.apply (oldValues, newRecord.getJsObj ());
        // Retrieving changed values
        JavaScriptObject data = request.getData ();
        // Apply changes
        JSOHelper.apply (data, newRecord.getJsObj ());
        return newRecord;
    }
    
}