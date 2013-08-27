package com.developpez.myapplication.dao;

import java.io.Serializable;
import java.util.List;

/**
 * Data Access Object (Dao) interface.   This is an interface
 * used to tag our Dao classes and to provide common methods to all Daos.
 * (inspired by CSA)
 */
public interface IDao<T> {

    /**
     * Generic method used to get all objects of a particular type. This
     * is the same as lookup up all rows in a table.
     * @return List of populated objects
     */
    public List<T> findAll();

    /**
     * Generic method used to get the count of object.
     * @return List of populated objects
     */
    public int count();
    
    
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
    
    /**
     * Generic method to get an object based on class and identifier. An 
     * ObjectRetrievalFailureException Runtime Exception is thrown if 
     * nothing is found.
     * 
     * @param id the identifier (primary key) of the class
     * @return a populated object
     * @see org.springframework.orm.ObjectRetrievalFailureException
     */
    public T findById(Serializable _id);

    /**
     * Generic method to add an object.
     * @param entity the object to add
     */
    public T add(T _entity);

    /**
     * Generic method to update an object.
     * @param entity the object to save
     */
    public T update(T _entity);

    /**
     * Generic method to delete an object based on class and id
     * @param id the identifier (primary key) of the class
     */
    public void delete(Serializable _id);

    /**
     * Generic method to delete an object based on class and id
     * @param id the identifier (primary key) of the class
     */
    public void delete(T _o);
    
    /**
     * Return type of managed domain object.
     * @return the type of the object
     */
    public Class<T> getType();
}
