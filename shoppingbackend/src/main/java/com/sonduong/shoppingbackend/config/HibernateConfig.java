package com.sonduong.shoppingbackend.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author Son Duong
 * 
 * 29.10.2017
 */

@Configuration
@ComponentScan(basePackages={"com.sonduong.shoppingbackend.dto"})
@EnableTransactionManagement
public class HibernateConfig {
	
	private static final String DATABASE_URL ="jdbc:mysql://localhost:3306/onlineshopping";
	private static final String DATABASE_DRIVER ="com.mysql.jdbc.Driver";
	private static final String DATABASE_DIALECT ="org.hibernate.dialect.MySQL5Dialect";
	private static final String DATABASE_USERNAME ="root";
	private static final String DATABASE_PASSWORD ="password";
	
	//dataResource
	@Bean
	public DataSource getDataSource(){
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName(DATABASE_DRIVER);
		dataSource.setUrl(DATABASE_URL);
		dataSource.setUsername(DATABASE_USERNAME);
		dataSource.setPassword(DATABASE_PASSWORD);
		return dataSource;
	}
	
	//sessionFactory
	@Bean
	public SessionFactory getSessionFactory(DataSource dataSource){
		LocalSessionFactoryBuilder sessionBuilder = new LocalSessionFactoryBuilder(dataSource);
		sessionBuilder.addProperties(getHibernateProperty());
		sessionBuilder.scanPackages("com.sonduong.shoppingbackend.dto");
		return sessionBuilder.buildSessionFactory();
	}

	@Bean
	public HibernateTransactionManager getTransactionManager(SessionFactory sessionFactory){
		HibernateTransactionManager transactionManager = new HibernateTransactionManager(sessionFactory);
		return transactionManager;
	}
	//hibernate properties
	private Properties getHibernateProperty() {
		Properties properties = new Properties();
		properties.put("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
		properties.put("hibernate.show_sql", "true");
		properties.put("hibernate.format_sql", "true");
		properties.put("hibernate.hbm2ddl.auto", "update");
		return properties;
	}
}
