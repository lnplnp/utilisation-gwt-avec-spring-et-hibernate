package com.developpez.myapplication.client;

import com.developpez.myapplication.client.datasources.ProjectDS;
import com.developpez.myapplication.services.AuthenticationService;
import com.developpez.myapplication.services.AuthenticationServiceAsync;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;
import com.smartgwt.client.types.ImageStyle;
import com.smartgwt.client.types.ListGridEditEvent;
import com.smartgwt.client.types.RowEndEditAction;
import com.smartgwt.client.types.Side;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.Img;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.Layout;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.tab.Tab;
import com.smartgwt.client.widgets.tab.TabSet;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Application implements EntryPoint
{

    /**
     * This is the entry point method.
     */
    public void onModuleLoad()
    {
        Widget mainWidget = null;

        mainWidget = createMainFrame();

        RootPanel.get().add(mainWidget);
    }

    /**
     * @return the main frame
     */
    private Widget createMainFrame()
    {
        // layout principal
        VLayout mainLayout = new VLayout();
        mainLayout.setWidth100();
        mainLayout.setHeight100();

        // création de la bannière + bouton login etc...
        Layout banner = createBanner();

        // création du contenu
        Layout content = createContent();

        mainLayout.addMember(banner);
        mainLayout.addMember(content);
        // mainLayout.draw();
        return mainLayout;
    }

    /**
     * @return the main layout content
     */
    private Layout createContent()
    {
        HLayout layout = new HLayout();
        layout.setWidth100();

        final Canvas tab = createTabPanel();

        layout.addMember(tab);

        return layout;

    }

    private TabSet createTabPanel()
    {
        final TabSet topTabSet = new TabSet();
        topTabSet.setTabBarPosition(Side.TOP);
        topTabSet.setHeight100();

        Tab tabProjects = new Tab("Projects", "folder.png");
        Canvas projects = createProjectCanvas();
        tabProjects.setPane(projects);

        topTabSet.addTab(tabProjects);
        return topTabSet;
    }

    private Canvas createProjectCanvas()
    {
        Canvas canvas = new Canvas();

        final ListGrid countryGrid = new ListGrid();
        countryGrid.setWidth100();  
        countryGrid.setHeight(400);  
        countryGrid.setAlternateRecordStyles(true);
        countryGrid.setShowAllRecords(true);
        countryGrid.setCellHeight(22);
        countryGrid.setDataSource(ProjectDS.getInstance());

        countryGrid.setAutoFetchData(true);
        countryGrid.setCanEdit(true);
        countryGrid.setEditEvent(ListGridEditEvent.DOUBLECLICK);
        countryGrid.setListEndEditAction(RowEndEditAction.NEXT);  

        IButton addButton = new IButton("Add New");
        addButton.setTop(440);
        //button.setLeft(0);
        addButton.addClickHandler(new ClickHandler()
        {
            public void onClick(ClickEvent event)
            {
                countryGrid.startEditingNew();
            }
        });          
        
        IButton removeFirst = new IButton("Remove First");
        removeFirst.addClickHandler(new ClickHandler()
        {
            public void onClick(ClickEvent event)
            {
                countryGrid.removeData(countryGrid.getRecord(0));
            }
        });
        removeFirst.setLeft(0);
        removeFirst.setTop(410);
        removeFirst.setWidth(145);

        IButton removeSelected = new IButton("Remove First Selected");
        removeSelected.addClickHandler(new ClickHandler()
        {
            public void onClick(ClickEvent event)
            {
                ListGridRecord selectedRecord = countryGrid.getSelectedRecord();
                if (selectedRecord != null)
                {
                    countryGrid.removeData(selectedRecord);
                } else
                {
                    SC.say("Select a record before performing this action");
                }
            }

        });
        removeSelected.setLeft(160);
        removeSelected.setTop(410);
        removeSelected.setWidth(150);

        IButton removeAll = new IButton("Remove All Selected");
        removeAll.addClickHandler(new ClickHandler()
        {
            public void onClick(ClickEvent event)
            {
                ListGridRecord[] selectedRecords = countryGrid.getSelection();
                for (ListGridRecord rec : selectedRecords)
                {
                    countryGrid.removeData(rec);
                }
            }
        });
        removeAll.setLeft(320);
        removeAll.setTop(410);
        removeAll.setWidth(140);

        canvas.addChild(countryGrid);
        canvas.addChild(removeFirst);
        canvas.addChild(removeSelected);
        canvas.addChild(removeAll);
        canvas.addChild(addButton);

        return canvas;
    }

    private Layout createBanner()
    {
        // Bannière du haut
        HLayout hLayout = new HLayout();
        hLayout.setHeight(100);
        hLayout.setWidth100();
        hLayout.setStyleName("bannerHkn");

        // image dans le canvas du haut
        String image = "logo.gif";
        Img starImg1 = new Img(image, 100, 100);
        starImg1.setImageType(ImageStyle.CENTER);

        Label spacer = new Label();
        spacer.setWidth("*");

        HLayout canvas = new HLayout();
        canvas.setMembersMargin(20);
        final IButton profile = new IButton();
        profile.setTitle("Profile");
        profile.setShowRollOver(true);
        profile.setShowDisabled(true);
        profile.setShowDown(true);
        profile.setIcon("Profile.png");

        final IButton disconnect = new IButton();
        disconnect.setWidth(150);
        disconnect.setTitle("Disconnect");
        disconnect.setShowRollOver(true);
        disconnect.setShowDisabled(true);
        disconnect.setShowDown(true);
        disconnect.setIcon("logout.png");

        disconnect.addClickHandler(new ClickHandler()
        {

            public void onClick(ClickEvent event)
            {
                AuthenticationServiceAsync service = GWT.create(AuthenticationService.class);
                service.logout(new AsyncCallback<Object>()
                {

                    public void onSuccess(Object result)
                    {
                        // the reload should redirect us to the login page
                        Window.Location.reload();
                    }

                    public void onFailure(Throwable arg0)
                    {
                        SC.say("error : " + arg0);
                    }
                });
            }

        });

        canvas.addMember(profile);
        canvas.addMember(disconnect);
        canvas.setWidth("10");

        hLayout.addMember(starImg1);
        hLayout.addMember(spacer);
        hLayout.addMember(canvas);
        return hLayout;
    }
}
