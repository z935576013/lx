package com.sdx.lx.common.dal;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

public class DalClientImpl implements DalClient {
	/**
	 * xml解析
	 */
	private XmlParse xmlParse = null;

	private NamedParameterJdbcTemplate jdbcTemplate;

	/**
	 * {@inheritDoc}
	 */
	public Number persist(Object obj) {
		String sql = SqlParser.getSqlParser(obj.getClass()).getInsert();
		Map<String, Object> paramMap = ValueParser.parser(obj);
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(sql, new MapSqlParameterSource(paramMap), keyHolder);
		List<Map<String, Object>> keyList = keyHolder.getKeyList();
		if (keyList == null || keyList.isEmpty()) {
			return 0;
		}
		Iterator<Object> keyIter = keyList.get(0).values().iterator();
		Object key = keyIter.next();
		if (!(key instanceof Number)) {
			return 0;
		} else {
			return (Number) key;
		}
	}

	@Override
	public Number persist(Object obj, String country) {
		String countryCode = getCountryCode(country);
		if (StringUtils.isEmpty(countryCode)) {
			return persist(obj);
		} else {
			String sql = SqlParser.getSqlParser(obj.getClass()).getInsertWithCountrySql(countryCode);
			Map<String, Object> paramMap = ValueParser.parser(obj);
			KeyHolder keyHolder = new GeneratedKeyHolder();
			jdbcTemplate.update(sql, new MapSqlParameterSource(paramMap), keyHolder);
			List<Map<String, Object>> keyList = keyHolder.getKeyList();
			if (keyList == null || keyList.isEmpty()) {
				return 0;
			}
			Iterator<Object> keyIter = keyList.get(0).values().iterator();
			Object key = keyIter.next();
			if (!(key instanceof Number)) {
				return 0;
			} else {
				return (Number) key;
			}
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public int merge(Object obj) {
		String sql = SqlParser.getSqlParser(obj.getClass()).getUpdate();
		Map<String, Object> paramMap = ValueParser.parser(obj);
		return jdbcTemplate.update(sql, paramMap);
	}

	@Override
	public int merge(Object obj, String country) {
		String countryCode = getCountryCode(country);
		if (StringUtils.isEmpty(countryCode)) {
			return merge(obj);
		} else {
			String sql = SqlParser.getSqlParser(obj.getClass()).getUpdateSqlWithCountrySql(countryCode);
			Map<String, Object> paramMap = ValueParser.parser(obj);
			return jdbcTemplate.update(sql, paramMap);
		}
	}

	public int mergeWithoutNull(Object obj) {
		String sql = SqlParser.getSqlParser(obj.getClass()).getUpdateByField();
		Map<String, Object> paramMap = ValueParser.parserWithoutNull(obj);
		sql = StringTemplateLoader.proccessToString(paramMap, sql);
		return jdbcTemplate.update(sql, paramMap);
	}

	/**
	 * {@inheritDoc}
	 */
	public int remove(Object obj) {
		String sql = SqlParser.getSqlParser(obj.getClass()).getDelete();
		Map<String, Object> paramMap = ValueParser.parser(obj);
		return jdbcTemplate.update(sql, paramMap);
	}

	@Override
	public int remove(Object obj, String country) {
		String countryCode = getCountryCode(country);
		if (StringUtils.isEmpty(countryCode)) {
			return remove(obj);
		} else {
			String sql = SqlParser.getSqlParser(obj.getClass()).getDeleteSqlWithCountrySql(countryCode);
			Map<String, Object> paramMap = ValueParser.parser(obj);
			return jdbcTemplate.update(sql, paramMap);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public <T> T find(Class<T> clazz, Object obj) {
		String sql = SqlParser.getSqlParser(clazz).getSelect();
		Map<String, Object> paramMap = ValueParser.parser(obj);
		try {
			return (T) jdbcTemplate.queryForObject(sql, paramMap, new BeanPropertyRowMapper<T>(clazz));
		} catch (IncorrectResultSizeDataAccessException e) {
			return null;
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public <T> T find(Class<T> clazz, Object obj, String country) {
		String countryCode = getCountryCode(country);
		if (StringUtils.isEmpty(countryCode)) {
			return find(clazz, obj);
		} else {
			String sql = SqlParser.getSqlParser(clazz).getSelectSqlWithCountrySql(countryCode);
			Map<String, Object> paramMap = ValueParser.parser(obj);
			try {
				return (T) jdbcTemplate.queryForObject(sql, paramMap, new BeanPropertyRowMapper<T>(clazz));
			} catch (IncorrectResultSizeDataAccessException e) {
				return null;
			}
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public <T> T queryForObject(String s, Map<String, ?> map, Class<T> clazz) {
		String sql = this.xmlParse.getSql(s);
		sql = StringTemplateLoader.proccessToString(map, sql);
		try {
			return (T) jdbcTemplate.queryForObject(sql, map, new BeanPropertyRowMapper<T>(clazz));
		} catch (IncorrectResultSizeDataAccessException e) {
			return null;
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public Map<String, Object> queryForMap(String s, Map<String, ?> paramMap) {
		String sql = this.xmlParse.getSql(s);
		sql = StringTemplateLoader.proccessToString(paramMap, sql);
		try {
			return jdbcTemplate.queryForMap(sql, paramMap);
		} catch (IncorrectResultSizeDataAccessException e) {
			return null;
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public <T> List<T> queryForList(String s, Map<String, ?> map, Class<T> clazz) {
		String sql = this.xmlParse.getSql(s);
		sql = StringTemplateLoader.proccessToString(map, sql);
		return jdbcTemplate.query(sql, map, new BeanPropertyRowMapper<T>(clazz));
	}

	/**
	 * {@inheritDoc}
	 */
	public List<Map<String, Object>> queryForList(String s, Map<String, ?> map) {
		String sql = this.xmlParse.getSql(s);
		sql = StringTemplateLoader.proccessToString(map, sql);
		return jdbcTemplate.queryForList(sql, map);
	}

	/**
	 * {@inheritDoc}
	 */
	public int execute(String s, Map<String, ?> map) {
		String sql = this.xmlParse.getSql(s);
		sql = StringTemplateLoader.proccessToString(map, sql);
		return jdbcTemplate.update(sql, map);
	}

	/**
	 * {@inheritDoc}
	 */
	public int[] batchUpdate(String s, Map<String, ?>[] map) {
		String sql = this.xmlParse.getSql(s);
		Map<String, ?> paramMap = null;
		if ((map != null) && (map.length > 0)) {
			paramMap = map[0];
		}
		sql = StringTemplateLoader.proccessToString(paramMap, sql);
		return jdbcTemplate.batchUpdate(sql, map);
	}

	/**
	 * @param resources
	 */
	public void setResources(String locationPattern) throws IOException {
		xmlParse = new XmlParse();
		ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
		Resource[] resources = resolver.getResources(locationPattern);

		this.xmlParse.parse(resources);
	}

	/**
	 * @param resources
	 */
	public void setResources(Resource[] resources) {
		xmlParse = new XmlParse();
		this.xmlParse.parse(resources);
	}

	public void setDataSource(DataSource dataSource) {
		jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}

	public String getCountryCode(String country) {
		if (StringUtils.isEmpty(country)) {
			return "";
		}
		String countryCode = "";
		switch (country) {
		case "美国":
			countryCode = "_USA";
			break;
		case "英国":
			countryCode = "_GBR";
			break;
		case "德国":
			countryCode = "_DEU";
			break;
		case "澳大利亚":
			countryCode = "_AUS";
			break;
		case "印度":
			countryCode = "_IND";
			break;
		}
		return countryCode;
	}

	@Override
	public String[] getCountryCodes() {
		String[] country = { "", "_USA", "_DEU", "_GBR", "_AUS", "_IND" };
		return country;
	}

	@Override
	public String[] getCountrys() {
		String[] country = { "中国", "美国", "英国", "德国", "澳大利亚", "印度" };
		return country;
	}
}
