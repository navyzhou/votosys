package com.yc.votesys.dao;

import java.util.List;

public interface IBaseDao<T>{
	public List<T> findAll(Class<?> c, String sqlId);
	
	public List<T> findAll(Class<?> c, Object obj, String sqlId);
	
	public T finds(Class<?> c, String sqlId);
	
	public T finds(Class<?> c, Object obj, String sqlId);
	
	public Double find(Class<?> c, String sqlId);
	
	public Double find(Class<?> c, Object obj, String sqlId);
	
	public Integer insert(Class<?> c, Object obj, String sqlId);
	
	public Integer update(Class<?> c, Object obj, String sqlId);
	
	public Integer delete(Class<?> c, Object obj, String sqlId);
	
	public Integer update(Class<?> c, List<Object> objs, List<String> sqlIds);
}
