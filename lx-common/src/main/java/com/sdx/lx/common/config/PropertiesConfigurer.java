package com.sdx.lx.common.config;

import java.io.IOException;
import java.util.Properties;

import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

public class PropertiesConfigurer {

	@Bean
	public Properties properties() throws IOException {
		PropertiesFactoryBean properties = new PropertiesFactoryBean();
		ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
		Resource[] resources = {
				resolver.getResource("classpath:conf/ftp/ftp_" + AppConfig.getEvnName() + ".properties"),
				resolver.getResource("classpath:conf/img/img_" + AppConfig.getEvnName() + ".properties"),
				resolver.getResource("classpath:conf/disk/disk_" + AppConfig.getEvnName() + ".properties"),
				resolver.getResource("classpath:conf/i18n/hotelFeature.properties"),
				resolver.getResource("classpath:conf/email/email_" + AppConfig.getEvnName() + ".properties"),
				resolver.getResource("classpath:conf/dubbo/dubbo_" + AppConfig.getEvnName() + ".properties"),
				resolver.getResource("classpath:conf/fdfs/" + AppConfig.getEvnName() + "_FastdfsClient.properties"),
				resolver.getResource("classpath:conf/mq/mq_" + AppConfig.getEvnName() + ".properties"),
				resolver.getResource("classpath:conf/email/rfp_email.properties"),
				resolver.getResource("classpath:conf/userProfile/userProfile.properties"),
				resolver.getResource("classpath:conf/i18n/emailTemplate.properties") };
		properties.setLocations(resources);
		properties.afterPropertiesSet();
		return properties.getObject();
	}

}
