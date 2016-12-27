package edu.cqu.fly.crawler.dao;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface AbstractBaseDao {

	public <T> T readEntityById(Object id, Class<T> clazz);

	public <PK, T> List<T> readEntitiesByIds(Set<PK> ids, Class<T> clazz);

	public <T> T readEntityByProperty(String propertyName, Object value, Class<T> clazz);

	public <T> List<T> readEntitiesByProperty(String propertyName, Object value, Class<T> clazz);

	public <T> List<T> readEntitiesByProperties(String propertyName, Set<Object> values, Class<T> clazz);

	public <T> List<T> readAllEntities(Class<T> clazz);

	public void deleteEntityById(Object id, Class<?> clazz);

	public <T> T saveEntity(T entity);
	public <T> T updateEntity(T entity);
	
	public <T> T readEntityByJPQL(String jpql, Class<T> clazz);
	public <T> List<T> readEntitiesByJPQL(String jpql, Class<T> clazz, Object... params);
	public <T> List<T> readEntityByJPQL(String jpql,int start,int max,Class<T> clazz, Object... params);
	public int executeJPQL(String jpql);
	
	public List<Map<String, Object>> readSetBySql(String sql, Object... objs);
	public Map<String, Object> readSingleBySql(String sql, Object... objs);
	
	public int executeSql(String sql, List<Object> param);
	public int executeSql(String sql, Object... param);
	public int executeSql(String sql, Map<String, Object> param);
	public Object executeSqlReturnKey(String sql, Map<String, Object> param);
	public List queryObjectBySql(String sql);
	
}