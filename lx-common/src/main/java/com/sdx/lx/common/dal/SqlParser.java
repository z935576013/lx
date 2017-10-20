package com.sdx.lx.common.dal;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.apache.commons.lang3.StringUtils;

public class SqlParser {

	private static final String APPEND_STRING = " = :";

	private String table = null;

	private String id = null;

	private String idAppend = null;

	private boolean isGenerator = true;

	private List<String> columnList = new ArrayList<String>();

	private List<String> columnListAppend = new ArrayList<String>();

	private String insert = null;

	private String update = null;

	private String delete = null;

	private String select = null;

	private String updateByField = null;

	private String selectByFiled = null;

	private String selectCountByFiled = null;

	private String deleteByFiled = null;

	public SqlParser(Class<? extends Object> clazz) {
		setTable(clazz);
		setId(clazz);
		setColumnList(clazz);

		setInsertSql();
		setUpdateSql();
		setDeleteSql();
		setSelectSql();

		setDeleteByFiled(clazz);
		setSelectByFiledSql(clazz);
		setSelectCountByFiledSql(clazz);
		setUpdateByField(clazz);
	}

	public String getInsert() {
		return this.insert;
	}

	public String getUpdate() {
		return this.update;
	}

	public String getDelete() {
		return this.delete;
	}

	public String getSelect() {
		return this.select;
	}

	public String getUpdateByField() {
		return this.updateByField;
	}

	public String getDeleteByFiled() {
		return this.deleteByFiled;
	}

	public String getSelectByFiledSql() {
		return this.selectByFiled;
	}

	public String getSelectCountByFiled() {
		return this.selectCountByFiled;
	}

	public String getInsertWithCountrySql(String country) {
		StringBuffer sb = new StringBuffer("INSERT INTO ");
		sb.append(this.table).append(country).append("(");

		if (!this.isGenerator) {
			if ((this.idAppend != null) && (this.id != null)) {
				sb.append(this.idAppend);
			}

			if (this.columnListAppend.size() > 0) {
				sb.append(", ");
			}
		}

		for (int i = 0; i < this.columnListAppend.size(); i++) {
			sb.append((String) this.columnListAppend.get(i));
			if (i < this.columnListAppend.size() - 1) {
				sb.append(", ");
			}
		}

		sb.append(") VALUES (");
		if (!this.isGenerator) {
			if ((this.idAppend != null) && (this.id != null)) {
				sb.append(":").append(this.id);
			}

			if (this.columnList.size() > 0) {
				sb.append(", ");
			}
		}

		for (int i = 0; i < this.columnList.size(); i++) {
			sb.append(":").append((String) this.columnList.get(i));
			if (i < this.columnList.size() - 1) {
				sb.append(", ");
			}
		}

		sb.append(")");
		return sb.toString();
	}

	public String getUpdateSqlWithCountrySql(String country) {
		StringBuffer sb = new StringBuffer("UPDATE ");
		sb.append(this.table).append(country).append(" SET ");
		for (int i = 0; i < this.columnListAppend.size(); i++) {
			sb.append((String) this.columnListAppend.get(i))
					.append(APPEND_STRING)
					.append((String) this.columnList.get(i));
			if (i < this.columnListAppend.size() - 1) {
				sb.append(", ");
			}
		}
		sb.append(" WHERE ");
		sb.append(this.idAppend).append(APPEND_STRING).append(this.id);
		return sb.toString();
	}

	public String getDeleteSqlWithCountrySql(String country) {
		StringBuffer sb = new StringBuffer("DELETE FROM ");
		sb.append(this.table).append(country).append(" WHERE ");
		sb.append(this.idAppend).append(APPEND_STRING).append(this.id);
		return sb.toString();
	}

	public String getSelectSqlWithCountrySql(String country) {
		StringBuffer sb = new StringBuffer("SELECT ");
		for (int i = 0; i < this.columnListAppend.size(); i++) {
			sb.append((String) this.columnListAppend.get(i));
			if (i < this.columnListAppend.size() - 1) {
				sb.append(", ");
			}
		}
		sb.append(" FROM ").append(this.table).append(country).append(" WHERE ");
		sb.append(this.idAppend).append(APPEND_STRING).append(this.id);
		return sb.toString();
	}

	
	private void setInsertSql() {
		StringBuffer sb = new StringBuffer("INSERT INTO ");
		sb.append(this.table).append("(");

		if (!this.isGenerator) {
			if ((this.idAppend != null) && (this.id != null)) {
				sb.append(this.idAppend);
			}

			if (this.columnListAppend.size() > 0) {
				sb.append(", ");
			}
		}

		for (int i = 0; i < this.columnListAppend.size(); i++) {
			sb.append((String) this.columnListAppend.get(i));
			if (i < this.columnListAppend.size() - 1) {
				sb.append(", ");
			}
		}

		sb.append(") VALUES (");
		if (!this.isGenerator) {
			if ((this.idAppend != null) && (this.id != null)) {
				sb.append(":").append(this.id);
			}

			if (this.columnList.size() > 0) {
				sb.append(", ");
			}
		}

		for (int i = 0; i < this.columnList.size(); i++) {
			sb.append(":").append((String) this.columnList.get(i));
			if (i < this.columnList.size() - 1) {
				sb.append(", ");
			}
		}

		sb.append(")");
		this.insert = sb.toString();
	}

	private void setUpdateSql() {
		StringBuffer sb = new StringBuffer("UPDATE ");
		sb.append(this.table).append(" SET ");
		for (int i = 0; i < this.columnListAppend.size(); i++) {
			sb.append((String) this.columnListAppend.get(i))
					.append(APPEND_STRING)
					.append((String) this.columnList.get(i));
			if (i < this.columnListAppend.size() - 1) {
				sb.append(", ");
			}
		}
		sb.append(" WHERE ");
		sb.append(this.idAppend).append(APPEND_STRING).append(this.id);
		this.update = sb.toString();
	}

	private void setDeleteSql() {
		StringBuffer sb = new StringBuffer("DELETE FROM ");
		sb.append(this.table).append(" WHERE ");
		sb.append(this.idAppend).append(APPEND_STRING).append(this.id);
		this.delete = sb.toString();
	}

	private void setSelectSql() {
		StringBuffer sb = new StringBuffer("SELECT ");
		for (int i = 0; i < this.columnListAppend.size(); i++) {
			sb.append((String) this.columnListAppend.get(i));
			if (i < this.columnListAppend.size() - 1) {
				sb.append(", ");
			}
		}
		sb.append(" FROM ").append(this.table).append(" WHERE ");
		sb.append(this.idAppend).append(APPEND_STRING).append(this.id);
		this.select = sb.toString();
	}

	private void setDeleteByFiled(Class<? extends Object> clazz) {

		StringBuffer sb = new StringBuffer("DELETE ");
		sb.append(" FROM ").append(this.table).append(" WHERE   1=1");
		Method[] methods = clazz.getMethods();
		for (Method method : methods) {
			if ((method.isAnnotationPresent(Column.class))) {

				sb.append(" <#if ");
				sb.append(StringUtils.uncapitalize(method.getName()
						.substring(3)));
				sb.append(" ? exists>AND  ");
				sb.append(((Column) method.getAnnotation(Column.class)).name())
						.append(APPEND_STRING);
				sb.append(StringUtils.uncapitalize(method.getName()
						.substring(3)));
				sb.append(" </#if>  ");

			}
		}

		this.deleteByFiled = sb.toString();

	}

	private void setUpdateByField(Class<? extends Object> clazz) {

		Method[] methods = clazz.getMethods();

		StringBuffer sb = new StringBuffer("UPDATE ");
		sb.append(this.table).append(" SET ");

		for (Method method : methods) {
			if ((method.isAnnotationPresent(Column.class))
					&& (!method.isAnnotationPresent(Id.class))) {
				sb.append("<#if ");
				sb.append(method.getName());
				sb.append(" ? exists>  ");
				sb.append(((Column) method.getAnnotation(Column.class)).name())
						.append(APPEND_STRING);
				sb.append(method.getName());
				sb.append(",");
				sb.append("</#if>  ");

			}
		}

		sb.append(" UPDATE_DTTM=now() ");
		sb.append(" WHERE ");
		sb.append(this.idAppend).append(APPEND_STRING).append(this.id);
		this.updateByField = sb.toString();

	}

	private void setSelectByFiledSql(Class<? extends Object> clazz) {

		StringBuffer sb = new StringBuffer("SELECT ");
		Method[] methods = clazz.getMethods();
		for (Method method : methods) {
			if ((method.isAnnotationPresent(Column.class))) {

				String columnName = ((Column) method
						.getAnnotation(Column.class)).name();

				sb.append(columnName).append(" ,");

			}
		}

		if (sb.lastIndexOf(",") == sb.length() - 1) {

			sb.delete(sb.length() - 1, sb.length());
		}

		sb.append(" FROM ").append(this.table).append(" WHERE 1=1 ");

		for (Method method : methods) {
			if ((method.isAnnotationPresent(Column.class))
					&& (!method.isAnnotationPresent(Id.class))) {

				sb.append("<#if ");
				sb.append(StringUtils.uncapitalize(method.getName()
						.substring(3)));
				sb.append(" ? exists>AND  ");
				sb.append(((Column) method.getAnnotation(Column.class)).name())
						.append(APPEND_STRING);
				sb.append(StringUtils.uncapitalize(method.getName()
						.substring(3)));
				sb.append("</#if>  ");

			}
		}

		sb.append("	<#if orderBy ? exists> ORDER BY ${orderBy} </#if> ");
		sb.append(" <#if orderByDesc ? exists> ${orderByDesc} </#if> ");
		sb.append(" <#if startIndex ? exists && maxCount ? exists> ");
		sb.append(" LIMIT :startIndex,:maxCount ");
		sb.append(" </#if> ");

		this.selectByFiled = sb.toString();

	}

	private void setSelectCountByFiledSql(Class<? extends Object> clazz) {

		StringBuffer sb = new StringBuffer("SELECT ");
		Method[] methods = clazz.getMethods();

		sb.append(" COUNT(1) NUM ");

		sb.append(" FROM ").append(this.table).append(" WHERE 1=1 ");

		for (Method method : methods) {
			if ((method.isAnnotationPresent(Column.class))
					&& (!method.isAnnotationPresent(Id.class))) {

				sb.append("<#if ");
				sb.append(StringUtils.uncapitalize(method.getName()
						.substring(3)));
				sb.append(" ? exists>AND  ");
				sb.append(((Column) method.getAnnotation(Column.class)).name())
						.append(APPEND_STRING);
				sb.append(StringUtils.uncapitalize(method.getName()
						.substring(3)));
				sb.append("</#if>  ");

			}
		}

		this.selectCountByFiled = sb.toString();

	}

	private void setTable(Class<? extends Object> clazz) {
		Entity entity = (Entity) clazz.getAnnotation(Entity.class);
		this.table = entity.name();
	}

	private void setId(Class<? extends Object> clazz) {
		Method[] methods = clazz.getMethods();
		for (Method method : methods) {
			if (method.isAnnotationPresent(Id.class)) {
				this.idAppend = ((Column) method.getAnnotation(Column.class))
						.name();
				this.id = method.getName();
				GeneratedValue generatedValue = (GeneratedValue) method
						.getAnnotation(GeneratedValue.class);
				if ((generatedValue != null)
						&& (generatedValue.strategy() != null)
						&& (generatedValue.strategy().compareTo(
								GenerationType.AUTO) != 0)) {
					this.isGenerator = false;
				} else {
					this.isGenerator = true;
				}
			}
		}
	}

	private void setColumnList(Class<? extends Object> clazz) {
		Method[] methods = clazz.getMethods();
		for (Method method : methods) {
			if ((method.isAnnotationPresent(Column.class))
					&& (!method.isAnnotationPresent(Id.class))) {
				this.columnListAppend.add(((Column) method
						.getAnnotation(Column.class)).name());
				this.columnList.add(method.getName());
			}
		}
	}

	private static Map<Class<? extends Object>, SqlParser> cache = new HashMap<Class<? extends Object>, SqlParser>();

	public static SqlParser getSqlParser(Class<? extends Object> clazz) {
		SqlParser sqlParser = (SqlParser) cache.get(clazz);
		if (sqlParser == null) {
			sqlParser = new SqlParser(clazz);
			synchronized (cache) {
				if (cache.get(clazz) == null) {
					cache.put(clazz, sqlParser);
				}
			}
		}
		return sqlParser;
	}

}