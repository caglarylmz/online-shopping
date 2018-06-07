package com.oriontech.shoppingbackend.config;

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

@Configuration
@ComponentScan(basePackages={"com.oriontech.shoppingbackend.dto"})
@EnableTransactionManagement
public class HibernateConfig {
	//DBMS
	private final static String DB_URL="jdbc:h2:tcp://localhost/~/onlineshopping";
	private final static String DB_DRIVER="org.h2.Driver";
	private final static String DB_DIALECT="org.hibernate.dialect.H2Dialect";
	private final static String DB_USERNAME="sa";
	private final static String DB_PASSWORD="";
	//Providing db connection info
	@Bean
	public DataSource getDataSource() {
		BasicDataSource dataSource = new BasicDataSource();
		//provide db conncetion information
		dataSource.setDriverClassName(DB_DRIVER);
		dataSource.setUrl(DB_URL);
		dataSource.setUsername(DB_USERNAME);
		dataSource.setPassword(DB_PASSWORD);
		
		return dataSource;
	}
	
	//sessionFactory bean available
	@Bean
	public SessionFactory getSessionFactory(DataSource dataSource) {
		LocalSessionFactoryBuilder builder = new LocalSessionFactoryBuilder(dataSource);
		//Hibernate properties		
		builder.addProperties(getHibernateProperties());
		builder.scanPackages("com.oriontech.shoppingbackend.dto");
		return builder.buildSessionFactory();
	}	
	
	//properties
	private Properties getHibernateProperties() {
		Properties properties = new Properties();
		properties.put("hibernate.dialect", DB_DIALECT);
		properties.put("hibernate.show_sql", "true");
		properties.put("hibernate.format_sql", "true");
		return properties;
	}

	//transcation manager bean
	@Bean
	public HibernateTransactionManager getTransactionManager(SessionFactory sessionFactory) {
		HibernateTransactionManager transactionManager = new HibernateTransactionManager(sessionFactory);
		return transactionManager;		
	}
}
