package com.developpez.myapplication.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;

import com.developpez.myapplication.dao.IDao;
/**
 * This class serves as the Base class for all other Daos - namely to hold
 * common methods that they might all use. Can be used for standard CRUD
 * operations.</p>
 *
 */
public class BaseDaoHibernate <T> extends HibernateDAO implements IDao <T> 
{
    /**
     * logger
     */
    private static final Logger logger = Logger.getLogger(BaseDaoHibernate.class);
    
    private Class<T> type;
    
    /**
     * This method could be override to give the column names used to sort the list
     * 
     * @return the column used to sort the list of type T
     */
    public String[] getNaturalSortOrders()
    {
        return null;
    }
    

    /* (non-Javadoc)
     * @see com.developpez.myapplication.dao.IDao#findRestrictedList(int, int, java.lang.String, java.lang.String)
     */
    @SuppressWarnings("unchecked")
    public List<T> findRestrictedList (int startPosition, int nbElements, String orderBy, String orderSens )
    {
        Criteria criteria = getCurrentSession().createCriteria(type);

        criteria.setFirstResult(startPosition);
        criteria.setMaxResults(nbElements);
        if ( orderSens.equalsIgnoreCase("ASC") )
        {
            criteria.addOrder(Order.asc(orderBy));
        }
        else
        {
            criteria.addOrder(Order.desc(orderBy));
        }
        
        List<T> results = criteria.list();
        return results;
        
    }    

    /**
     * Constructeur avec le type d'objet interrogé par le DAO
     * @param type
     */
    public BaseDaoHibernate(Class<T> _type) {
        this.type = _type;
    }

    /* (non-Javadoc)
     * @see com.developpez.myapplication.dao.IDao#add(java.lang.Object)
     */
    public T add(T _o) {
        if ( logger.isDebugEnabled())
        {
        	logger.debug("add object of class " +type.getName() + " : " + _o);
        }
        getCurrentSession().save(_o);
        return _o;
    }

    /* (non-Javadoc)
     * @see com.developpez.myapplication.dao.IDao#update(java.lang.Object)
     */
    public T update(T _o) {
        if ( logger.isDebugEnabled())
        {
            logger.debug("update object of class " +type.getName() + " : " + _o);
        }
        getCurrentSession().saveOrUpdate(_o);
        return _o;
    }

    /* (non-Javadoc)
     * @see com.developpez.myapplication.dao.IDao#findById(java.io.Serializable)
     */
    @SuppressWarnings("unchecked")
    public T findById(Serializable _id) 
    {
        if ( logger.isDebugEnabled())
        {
            logger.debug("find object of class " +type.getName() + " with id : " + _id);
        }
        Session session = getCurrentSession();
        T o = (T) session.get(type, _id);
        if ( logger.isDebugEnabled())
        {
            logger.debug("found " +o);
        }        
        return o;        
    }

    /* (non-Javadoc)
     * @see com.developpez.myapplication.dao.IDao#findAll()
     */
    @SuppressWarnings("unchecked")
    public List<T> findAll() {
        String[] sortOrders = getNaturalSortOrders();
        
        Criteria crit = getCurrentSession().createCriteria(type);
        if ( sortOrders != null )
        {
            for (int i = 0 ; i < sortOrders.length ; i++)
            {
                crit.addOrder(Order.asc(sortOrders[i]));
            }
        }
        
        return crit.list();	
    }
    
    /* (non-Javadoc)
     * @see com.developpez.myapplication.dao.IDao#count()
     */
    public int count() {
        Criteria crit = getCurrentSession().createCriteria(type);
        crit.setProjection(Projections.rowCount());
        return ((Integer)crit.list().get(0)).intValue(); 
    }

    /* (non-Javadoc)
     * @see com.developpez.myapplication.dao.IDao#delete(java.io.Serializable)
     */
    public void delete(Serializable _id) {
        if ( logger.isDebugEnabled())
        {
            logger.debug("delete object of class : " +type.getName() + " with id : " + _id);
        }
        getCurrentSession().delete(findById(_id));
    }
    
	/* (non-Javadoc)
	 * @see com.developpez.myapplication.dao.IDao#getType()
	 */
	public Class<T> getType() {
		return type;
	}

    /* (non-Javadoc)
     * @see com.developpez.myapplication.dao.IDao#delete(java.lang.Object)
     */
    public void delete(T _o)
    {
        if ( logger.isDebugEnabled())
        {
            logger.debug("delete object of class : " +type.getName() + " : " + _o);
        }
        getCurrentSession().delete(_o);
    }
}
