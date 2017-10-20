package com.sdx.lx.common.config;

import java.io.IOException;
import java.util.Properties;

import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import redis.clients.jedis.JedisPoolConfig;

public class RedisConfigurer {

	@SuppressWarnings({ "rawtypes", "unchecked" })
    @Bean
	public RedisTemplate redisTemplate() throws IOException {
		PropertiesFactoryBean properties = new PropertiesFactoryBean();
		ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
		Resource[] resources = { resolver.getResource("classpath:conf/redis/"
				+ AppConfig.getEvnName() + "-redis.properties") };
		properties.setLocations(resources);
		properties.afterPropertiesSet();
		Properties propertie = properties.getObject();

		JedisPoolConfig redisProperties = new JedisPoolConfig();
		redisProperties.setMinIdle(Integer.valueOf(propertie
				.getProperty("redis.minIdle")));
		redisProperties.setMaxIdle(Integer.valueOf(propertie
				.getProperty("redis.maxIdle")));
		redisProperties.setMaxTotal(Integer.valueOf(propertie
				.getProperty("redis.maxActive")));
		redisProperties.setMaxWaitMillis(Integer.valueOf(propertie
				.getProperty("redis.maxWait")));
		redisProperties.setTestOnBorrow(true);
		JedisConnectionFactory jedisConnFactory = new JedisConnectionFactory();
		jedisConnFactory.setHostName(propertie.getProperty("redis.host"));
		jedisConnFactory.setPort(Integer.valueOf(propertie
				.getProperty("redis.port")));
		jedisConnFactory.setPassword(propertie.getProperty("redis.password"));
		jedisConnFactory.setUsePool(true);
		jedisConnFactory.setPoolConfig(redisProperties);
		jedisConnFactory.afterPropertiesSet();
		RedisTemplate redisTemplate = new RedisTemplate();
		redisTemplate.setConnectionFactory(jedisConnFactory);
		redisTemplate.setKeySerializer(new StringRedisSerializer());
		redisTemplate.setValueSerializer(new JdkSerializationRedisSerializer());
		redisTemplate
				.setHashValueSerializer(new JdkSerializationRedisSerializer());
		return redisTemplate;
	}

}
