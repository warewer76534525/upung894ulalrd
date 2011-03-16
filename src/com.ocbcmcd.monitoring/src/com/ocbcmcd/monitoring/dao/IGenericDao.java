package com.ocbcmcd.monitoring.dao;

import java.io.Serializable;

public interface IGenericDao<T, ID extends Serializable> {
	public T findById(final ID id);
	public void save(final T domain);
	public void update(final T domain);
	public void saveOrUpdate(final T domain);
	public void delete(final T domain);
}
