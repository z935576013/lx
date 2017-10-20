package com.sdx.lx.common.dal;

import java.util.List;
import java.util.Map;

public interface DalClient {

	/**
	 * 
	 * 单表添加操作
	 * 
	 * @param obj
	 *            保存的对象
	 * @return Number 自增主键返回Number类型的主键, 指定主键返回则0
	 */
	Number persist(Object obj);

	/**
	 * 
	 * 单表添加操作
	 * 
	 * @param obj
	 *            保存的对象
	 * @param country
	 *            国家
	 * @return Number 自增主键返回Number类型的主键, 指定主键返回则0
	 */
	Number persist(Object obj, String country);

	/**
	 * 
	 * 单表修改操作 根据主键修改记录
	 * 
	 * @param obj
	 * @return int 修改的记录数
	 */
	int merge(Object obj);

	/**
	 * 
	 * 单表修改操作 根据主键修改记录
	 * 
	 * @param obj
	 * @param country
	 *            国家
	 * @return int 修改的记录数
	 */
	int merge(Object obj, String country);
	
	/**
	 * 
	 * 单表修改操作 根据主键修改记录,控制不处理
	 * 
	 * @param obj
	 * @return int 修改的记录数
	 */
	int mergeWithoutNull(Object obj);

	/**
	 * 
	 * 单表删除操作 删除记录（以obj对象属性值作为删除条件）
	 * 
	 * @param obj
	 *            需删除的对象
	 * @return int 删除记录数
	 */
	int remove(Object obj);

	/**
	 * 
	 * 单表删除操作 删除记录（以obj对象属性值作为删除条件）
	 * 
	 * @param obj
	 *            需删除的对象
	 * @param country
	 *            国家           
	 * @return int 删除记录数
	 */
	int remove(Object obj, String country);
	
	/**
	 * 
	 * 单表查询操作 查询记录（以obj对象属性值作为查询条件）
	 * 
	 * @param clazz
	 *            类型
	 * @param obj
	 *            查询的条件对象
	 * @return T 查询出的对象
	 */
	<T> T find(Class<T> clazz, Object obj);
	
	/**
	 * 
	 * 单表查询操作 查询记录（以obj对象属性值作为查询条件）
	 * 
	 * @param clazz
	 *            类型
	 * @param obj
	 *            查询的条件对象
	 * @param country
	 *            国家                      
	 * @return T 查询出的对象
	 */
	<T> T find(Class<T> clazz, Object obj, String country);

	/**
	 * 
	 * 根据sqlId查询单个对象，查不到或查询到多个返回null
	 * 
	 * @param s
	 *            sql编号
	 * @param map
	 *            查询条件
	 * @param clazz
	 *            查询对象的类型
	 * @return T 查询出的对象
	 */
	<T> T queryForObject(String s, Map<String, ?> map, Class<T> clazz);

	/**
	 * 
	 * 根据sqlId查询单个对象，返回Map，查不到或查询多个返回null
	 * 
	 * @param s
	 *            sql编号
	 * @param map
	 *            查询条件
	 * @return Map<String, Object> 查询出的map
	 */
	Map<String, Object> queryForMap(String s, Map<String, ?> paramMap);

	/**
	 * 
	 * 根据sqlId查询多个对象
	 * 
	 * @param s
	 *            sql编号
	 * @param map
	 *            查询条件
	 * @param clazz
	 *            查询对象的类型
	 * @return List<T> 查询出的对象
	 */
	<T> List<T> queryForList(String s, Map<String, ?> map, Class<T> clazz);

	/**
	 * 
	 * 根据sqlId查询多个对象，Map集合List
	 * 
	 * @param s
	 *            sql编号
	 * @param map
	 *            查询条件
	 * @return List<Map<String, Object>> 查询到的map
	 */
	List<Map<String, Object>> queryForList(String s, Map<String, ?> map);

	/**
	 * 
	 * 根据sqlId执行，返回执行成功的记录条数
	 * 
	 * @param s
	 *            sql编号
	 * @param map
	 *            查询条件
	 * @return int 执行记录数
	 */
	int execute(String s, Map<String, ?> map);

	/**
	 * 
	 * 根据sqlId执行，批量执行
	 * 
	 * @param s
	 *            sql编号
	 * @param map
	 *            查询条件
	 * @return int[] 执行记录数
	 */
	int[] batchUpdate(String s, Map<String, ?>[] map);
	
	/**
	 * 国家码
	 * @param country
	 * @return
	 */
	String getCountryCode(String country);
	
	String[] getCountryCodes();
	
	String[] getCountrys();
	
}
