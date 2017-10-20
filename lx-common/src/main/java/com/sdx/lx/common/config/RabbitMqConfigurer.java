package com.sdx.lx.common.config;

import java.util.Properties;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

public class RabbitMqConfigurer {

	@Autowired
	Properties properties;

	@Bean
	public AmqpTemplate amqpTemplate() {
		CachingConnectionFactory connectionFactory = new CachingConnectionFactory(
				properties.getProperty("rabbitMq.host"),
				Integer.valueOf(properties.getProperty("rabbitMq.port")));
		connectionFactory.setVirtualHost(properties
				.getProperty("rabbitMq.virtualHost"));
		connectionFactory.setUsername(properties
				.getProperty("rabbitMq.username"));
		connectionFactory.setPassword(properties
				.getProperty("rabbitMq.password"));
		return new RabbitTemplate(connectionFactory);
	}

}
