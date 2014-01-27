package com.developpez.myapplication.client;

import com.developpez.myapplication.model.ReturnMemento;
import com.developpez.myapplication.services.AuthenticationService;
import com.developpez.myapplication.services.AuthenticationServiceAsync;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.data.fields.DataSourceBooleanField;
import com.smartgwt.client.data.fields.DataSourcePasswordField;
import com.smartgwt.client.data.fields.DataSourceTextField;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.tab.Tab;
import com.smartgwt.client.widgets.tab.TabSet;

public class LoginEntry implements EntryPoint
{

    public void onModuleLoad()
    {

        final TabSet theTabs = new TabSet();
        theTabs.setWidth(400);
        theTabs.setHeight(250);
        
        Tab item = new Tab();  
        item.setTitle("Sign in");  
        
        Tab register = new Tab();  
        register.setTitle("Sign up");  
        

        DataSource dataSource = new DataSource();

        DataSourceTextField login = new DataSourceTextField("login", "Username", 50, true);
        DataSourcePasswordField password = new DataSourcePasswordField("password", "Password", 50, true);
        DataSourceBooleanField rememberField = new DataSourceBooleanField("remember", "Remember me on this computer.", 50, false);

        dataSource.setFields(login, password, rememberField);

        final DynamicForm form = new DynamicForm();
        form.setDataSource(dataSource);
        form.setUseAllDataSourceFields(true);

        IButton validateItem = new IButton("Log in");
        validateItem.setIcon("connect.png");
        validateItem.addClickHandler(new ClickHandler()
        {
            public void onClick(ClickEvent event)
            {
                if (form.validate(false))
                {
                    AuthenticationServiceAsync service = GWT.create(AuthenticationService.class);
                    service.authenticate(form.getValueAsString("login"), form.getValueAsString("password"), new AsyncCallback<ReturnMemento>()
                    {

                        public void onSuccess(ReturnMemento result)
                        {
                            if (result.getCode() == ReturnMemento.CODE_SUCCESS)
                            {
                                String path = Window.Location.getPath();
                                String modulePath = "/com.developpez.myapplication.Login/Login.html";
                                int index = path.indexOf(modulePath);
                                String contextPath = path.substring(0,index);

                                Window.open(contextPath+"/com.developpez.myapplication.Application/Application.html", "_self", "");
                            }
                            else
                            {
                                form.setErrors(result.getErrors(), true);
                            }
                        }

                        public void onFailure(Throwable arg0)
                        {
                            SC.say("error : " + arg0);
                        }
                    });
                }

            }
        });

        VLayout formLayout = new VLayout(10);
        formLayout.addMember(form);
        formLayout.addMember(validateItem);

        item.setPane(formLayout);
        theTabs.setTabs(item,register);  

        
        VLayout layout = new VLayout(10);
        HLayout hlLayout = new HLayout();

        layout.addMember(hlLayout);
        layout.addMember(theTabs);
        
        layout.draw();
    }

}