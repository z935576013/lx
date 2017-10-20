package com.sdx.merak.test;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionConfiguration;

import com.sdx.lx.common.config.AppConfig;



@ContextConfiguration(classes = { AppConfig.class,FakeJndiConfigurer.class})
@TransactionConfiguration(defaultRollback = false)
public abstract class BaseTest extends AbstractTransactionalTestNGSpringContextTests {

}