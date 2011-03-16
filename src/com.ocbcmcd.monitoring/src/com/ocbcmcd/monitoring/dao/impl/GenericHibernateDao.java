package com.ocbcmcd.monitoring.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.ocbcmcd.monitoring.dao.IGenericDao;

public class GenericHibernateDao<T, ID extends Serializable> extends
	HibernateDaoSupport implements IGenericDao<T, ID> {
	
	@SuppressWarnings({ "rawtypes" })
	public Class domainClass;
	protected Log log = LogFactory.getLog(GenericHibernateDao.class);

	@SuppressWarnings({ "rawtypes" })
	public GenericHibernateDao() {
		this.domainClass = (Class) ((ParameterizedType) getClass()
				.getGenericSuperclass()).getActualTypeArguments()[0];
	}
	
	@SuppressWarnings("unchecked")
	public T findById(ID id) {
		try {
			final T domain = (T) getHibernateTemplate().load(domainClass, id);
			getHibernateTemplate().initialize(domain);
			return domain;
		} catch (org.springframework.orm.hibernate3.HibernateObjectRetrievalFailureException e) {
			return null;
		}

	}
	
	public void save(T domain) {
		getHibernateTemplate().save(domain);
		getHibernateTemplate().flush();
	}
	
	@Override
	public void saveOrUpdate(T domain) {
		getHibernateTemplate().saveOrUpdate(domain);
		getHibernateTemplate().flush();
	}

	@Override
	public void update(T domain) {
		getHibernateTemplate().update(domain);
		getHibernateTemplate().flush();
	}
	
	public void delete(T domain) {
		getHibernateTemplate().delete(domain);
	}
}
