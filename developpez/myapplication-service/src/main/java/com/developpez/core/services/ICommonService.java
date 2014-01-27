package com.developpez.core.services;

import java.io.Serializable;
import java.util.List;

import com.developpez.myapplication.dao.IDao;

/**
 * Contrat que doit remplir un service par défaut
 *
 */
public interface ICommonService<T>{
    
    /**
     * Get Dao object
     * @param dao
     */
    public IDao<T> getDao();
    
    /**
     * Return the domain class serviced.
     * 
     * @return class
     *      The domain class type serviced.
     */
    public Class<T> getType();
    
    /**
     * Expose the setDao method for testing purposes
     * @param dao
     */
    public void setDao(IDao<T> _dao);
    
    /**
     * Generic method used to get a all objects of a particular type. 
     * @return List of populated objects
     */
    public List<T> fetch() ;
    
    /**
     * Generic method used to get total count.  Should be used in sequence
     * with findAll.
     *  
     * @return int number of object found
     */
    public int count();

    
    /**
     * Generic method to get an object based on an identifier. 
     * 
     * @param id the identifier (primary key) of the class
     * @return a populated object 
     */
    public T findById(Serializable _id);

    /**
     * Generic method to save an object.
     * @param o the object to save
     * @return the object created
     */
    public T add(T _o) ;
    
    /**
     * Generic method to update an object.
     * @param o the object to save
     *  @return the object updated
     */
    public T update(T _o) ;


    /**
     * Generic method to delete an object based on id
     * @param id the identifier
     */
    public void remove(T _id) ;
    
    /**
     * Return a restricted list from startPosition to startPosition+nbElements and ordered by orderBy
     * 
     * restriction : only one orderby is allowed
     * 
     * @param startPosition
     * @param nbElements
     * @param orderBy
     * @param orderSens
     * @return a restricted list of results
     */
    public List<T> findRestrictedList (int startPosition, int nbElements, String orderBy, String orderSens );
    
}