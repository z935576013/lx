package com.sdx.meark.test;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;



@ContextConfiguration(locations = {"classpath*:spring-test-da.xml","classpath*:conf/spring/spring-redis.xml"})  
public abstract class PlatFromBaseTest extends AbstractTransactionalTestNGSpringContextTests{
	


}
