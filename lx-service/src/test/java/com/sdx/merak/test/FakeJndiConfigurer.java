package com.sdx.merak.test;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

public class FakeJndiConfigurer {

    @Bean
    public DataSource ds01() {
        DriverManagerDataSource ds01 = new DriverManagerDataSource();
        ds01.setDriverClassName("com.mysql.jdbc.Driver");
        ds01.setUrl("jdbc:mysql://119.254.98.166:3306/merak?useUnicode=true&characterEncoding=UTF-8");
        ds01.setUsername("dbRoot");
        ds01.setPassword("zhenNanYin2015");
        return ds01;
    }


}
