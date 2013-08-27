
package com.developpez.core.services.impl;

import java.io.Serializable;
import java.util.List;

import com.developpez.core.services.ICommonService;
import com.developpez.myapplication.dao.IDao;

/**
 * Base class for Business Services - use this class for utility methods and
 * generic CRUD methods.
 * 
 */
public class ServiceImpl<T> implements ICommonService<T> {
    
    protected IDao<T> dao = null;

    
    /* (non-Javadoc)
     * @see com.developpez.myapplication.services.ICommonService#setDao(com.developpez.myapplication.dao.IDao)
     */
    public void setDao(IDao<T> _dao) {
        this.dao = _dao;
    }


    /* (non-Javadoc)
     * @see com.developpez.myapplication.services.ICommonService#getDao()
     */
    public IDao<T> getDao() {
        return dao;
    }    


    /* (non-Javadoc)
     * @see com.developpez.myapplication.services.ICommonService#findById(java.io.Serializable)
     */
    public T findById(Serializable _id){
        return dao.findById(_id);
    }
    

    /* (non-Javadoc)
     * @see com.developpez.myapplication.services.ICommonService#findAll()
     */
    public List<T> fetch(){
        return dao.findAll();
    }

    /* (non-Javadoc)
     * @see com.developpez.myapplication.services.ICommonService#count()
     */
    public int count() {
        return dao.count();
    }
    

    /* (non-Javadoc)
     * @see com.developpez.myapplication.services.ICommonService#remove(java.lang.Object)
     */
    public void remove(T _id) {
        dao.delete(_id);
    }
    

    /* (non-Javadoc)
     * @see com.developpez.myapplication.services.ICommonService#add(java.lang.Object)
     */
    public T add(T _o)  {
        return dao.add(_o);
    }
 

    /* (non-Javadoc)
     * @see com.developpez.myapplication.services.ICommonService#update(java.lang.Object)
     */
    public T update(T _o)  {
        return (T)dao.update(_o);
    }

	/* (non-Javadoc)
	 * @see com.developpez.myapplication.services.ICommonService#getType()
	 */
	public Class<T> getType() {
		return getDao().getType();
	}

    /* (non-Javadoc)
     * @see com.developpez.myapplication.services.ICommonService#findRestrictedList(int, int, java.lang.String, java.lang.String)
     */
    public List<T> findRestrictedList(int startPosition, int nbElements, String orderBy, String orderSens)
    {
        return dao.findRestrictedList(startPosition, nbElements, orderBy, orderSens);
    }
}
