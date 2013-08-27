package com.developpez.myapplication.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import net.sf.gilead.pojo.java5.LightEntity;

/**
 * 
 */
@Entity
@Table(name="PROJECT")
public class Project extends LightEntity implements Serializable 
{

    /**
     * serial id
     */
    private static final long serialVersionUID = -5922364316829478146L;
    
    /**
     * name of the project
     */
    @Column(name="NAME")
    private String name;

    /**
	 * description of the project
	 */
    @Column(name="DESCRIPTION")
    private String desc;
	
    /**
	 * url of the project
	 */
    @Column(name="URL")
	private String url;
    
    /**
     * list of associated authors
     */
    @OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
    @JoinTable(name="project_authors", joinColumns= @JoinColumn( name="PROJECT_ID"), inverseJoinColumns=@JoinColumn( name="AUTHOR_ID"))
    private List<Authors> authors = new ArrayList<Authors>();

    /**
	 * unique identifier
	 */
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    
    /**
     * Empty constructor
     */
    public Project() {

    }	
	/**
	 * Full constructor
	 * @param id
	 * @param name
	 * @param desc
	 */
	public Project(Integer id,String name,String desc)
	{
	    this.id = id;
	    this.name = name;
	    this.desc=desc;
	}
	
    /**
     * @return the list of authors
     */
    public List<Authors> getAuthors() {
        return authors;
    }   
    
    /**
     * @param the list of authors
     */
    public void setAuthors(List<Authors> authors) {
        this.authors = authors;
    }   	
	
	/**
	 * @return
	 */
	public String getName()
    {
        return name;
    }

    /**
     * @param name
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * @return
     */
    public String getDesc()
    {
        return desc;
    }

    /**
     * @param desc
     */
    public void setDesc(String desc)
    {
        this.desc = desc;
    }

    /**
     * @return
     */
    public String getUrl()
    {
        return url;
    }

    /**
     * @param url
     */
    public void setUrl(String url)
    {
        this.url = url;
    }



    /**
     * @param id
     */
    public void setId(Integer id)
    {
        this.id = id;
    }

    /**
     * @return
     */
    public Integer getId()
    {
        return id;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }
    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Project other = (Project) obj;
        if (id == null)
        {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

}