package com.developpez.myapplication.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import net.sf.gilead.pojo.java5.LightEntity;

@Entity
@Table(name="AUTHORS")
public class Authors extends LightEntity implements Serializable
{
    /**
     * serial id
     */
    private static final long serialVersionUID = -5791597632140578061L;

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
    
    /**
     * name of the project
     */
    @Column(name="NAME")
    private String name;

    /**
     * Empty constructor
     */
    public Authors()
    {
        
    }
    
    /**
     * @param name the name to set
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * @return the name
     */
    public String getName()
    {
        return name;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id)
    {
        this.id = id;
    }

    /**
     * @return the id
     */
    public Integer getId()
    {
        return id;
    }    
}
