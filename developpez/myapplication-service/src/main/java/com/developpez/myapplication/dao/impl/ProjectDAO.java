package com.developpez.myapplication.dao.impl;

import com.developpez.myapplication.dao.IProjectDAO;
import com.developpez.myapplication.model.Project;

public class ProjectDAO extends BaseDaoHibernate <Project> implements IProjectDAO
{

    public ProjectDAO(Class<Project> _type)
    {
        super(_type);
    }
}
