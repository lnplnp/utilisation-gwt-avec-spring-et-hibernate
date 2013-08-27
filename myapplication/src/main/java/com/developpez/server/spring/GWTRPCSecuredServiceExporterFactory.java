package com.developpez.server.spring;

import net.sf.gilead.core.PersistentBeanManager;

import org.gwtwidgets.server.spring.RPCServiceExporter;
import org.gwtwidgets.server.spring.RPCServiceExporterFactory;


/**
 * Factory to use with {@link GWTRPCSecuredServiceExporter}
 *
 */
public class GWTRPCSecuredServiceExporterFactory implements RPCServiceExporterFactory
{
    private PersistentBeanManager manager = new PersistentBeanManager();


    /* (non-Javadoc)
     * @see org.gwtwidgets.server.spring.RPCServiceExporterFactory#create()
     */
    public RPCServiceExporter create()
    {
        GWTRPCSecuredServiceExporter exporter = new GWTRPCSecuredServiceExporter();
        exporter.setBeanManager(manager);
        return exporter;
    }

    
    /**
     * @return the manager
     */
    public PersistentBeanManager getManager()
    {
        return manager;
    }

    /**
     * @param manager the manager to set
     */
    public void setManager(PersistentBeanManager manager)
    {
        this.manager = manager;
    }    
}
