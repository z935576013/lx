package com.sdx.lx.web.admin.config;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SessionListener implements HttpSessionListener {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(SessionListener.class);

	@Override
	public void sessionCreated(HttpSessionEvent event) {
		// 8小时
		event.getSession().setMaxInactiveInterval(28800);
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent event) {
		LOGGER.info("session destory");
	}
}