package com.developpez.myapplication.dao.impl;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * 
 */
public class HibernateDAO extends HibernateDaoSupport {

	private Session session = null;

	public Session getCurrentSession() {
		if (session == null) {
			return getSession();
		} else {
			if (session.isOpen()) {
				return session;
			} else {
				throw new HibernateException("Session is closed " + session);
			}
		}
	}

	/**
	 * @param _session
	 *            the session to set
	 */
	public void setCurrentSession(Session _session) {
		session = _session;
	}

	/**
	 * 
	 */
	public void closeCurrentSession() {
		if (session != null && session.isOpen()) {
			session.close();
			session = null;
		}
	}

}
