package com.sdx.lx.common.config;

import java.io.IOException;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.support.TransactionTemplate;

import com.sdx.lx.common.dal.DalClientImpl;

@EnableTransactionManagement
public class DaConfigurer {

	@Resource
	private DataSource ds01;

	@Bean
	public DalClientImpl dalClient() throws IOException {
		DalClientImpl dalClientImpl = new DalClientImpl();
		ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
		dalClientImpl.setDataSource(ds01);
		dalClientImpl.setResources(resolver
				.getResources("classpath*:conf/sqlMap/**/sqlMap_*.xml"));
		return dalClientImpl;
	}

	@Bean
	public DataSourceTransactionManager transactionManager() {
		DataSourceTransactionManager transactionManager = new DataSourceTransactionManager();
		transactionManager.setDataSource(ds01);
		transactionManager.setNestedTransactionAllowed(true);
		return transactionManager;
	}

	@Bean
	public TransactionTemplate transactionTemplate() {
		DataSourceTransactionManager transactionManager = new DataSourceTransactionManager();
		transactionManager.setDataSource(ds01);
		transactionManager.setNestedTransactionAllowed(true);
		return new TransactionTemplate(transactionManager);
	}

	@Bean
	public NamedParameterJdbcTemplate jdbcTemplate() {
		NamedParameterJdbcTemplate jdbcTemplate = new NamedParameterJdbcTemplate(
				ds01);
		return jdbcTemplate;
	}

}
