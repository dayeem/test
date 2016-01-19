package com.concretepage.config;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import org.apache.commons.dbcp.BasicDataSource;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;
import org.springframework.orm.jpa.vendor.HibernateJpaSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import com.concretepage.dao.IPersonDao;
import com.concretepage.dao.PersonDao;
import com.concretepage.entity.Person;

@Configuration
@EnableTransactionManagement
public class AppConfig {
	@Bean
	public IPersonDao personDao() {
		return new PersonDao();
	}

	@Bean
	public HibernateTemplate hibernateTemplate() {
		return new HibernateTemplate(sessionFactory());
	}

	@Bean
	public SessionFactory sessionFactory() {
		SessionFactory sessionFactory = new LocalSessionFactoryBuilder(getDataSource())
		 .addAnnotatedClasses(Person.class)
		 .buildSessionFactory();
		
		
		
//		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
//
//		sessionFactory.setDataSource(getDataSource());
//		sessionFactory.setPackagesToScan(new String[] { "com" });
//
//		sessionFactory.setHibernateProperties(hibernateProperties());
//
//		return sessionFactory;
		 
		 
		 
//		  org.hibernate.cfg.Configuration configuration = new org.hibernate.cfg.Configuration();
//
//		  configuration.setProperties(hibernateProperties());
//
//		  configuration.addAnnotatedClass(Person.class);
//
//		  ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
//
//		  SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);
//		  
//		  sessionFactory.setDataSource(getDataSource());
		  
		  return sessionFactory;

	}

	@Bean
	public HibernateJpaSessionFactoryBean sessionFactory(EntityManagerFactory emf) {
		HibernateJpaSessionFactoryBean factory = new HibernateJpaSessionFactoryBean();
		factory.setEntityManagerFactory(emf);
		return factory;
	}

	private java.util.Properties hibernateProperties() {
		// TODO Auto-generated method stub
		java.util.Properties properties = new java.util.Properties();
		properties.setProperty("spring.jpa.database-platform", "org.hibernate.dialect.PostgreSQLDialect");
		properties.setProperty("spring.jpa.show-sql", "false");
		
	    properties.put(Environment.DRIVER,
	            com.mysql.jdbc.Driver.class.getName());
	    properties.put(Environment.USER, "test");
	    properties.put(Environment.PASS, "password");
	    properties.put(Environment.HBM2DDL_AUTO, "create");
	    properties.put(Environment.DIALECT, org.hibernate.dialect.PostgreSQL9Dialect.class.getName());
	    properties.put(Environment.URL, "jdbc:postgresql://localhost:5432/test");
	    properties.put(Environment.SHOW_SQL, true);
		return properties;
	}

	@Bean
	public DataSource getDataSource() {
		BasicDataSource dataSource = new BasicDataSource();
		/*
		 * dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		 * dataSource.setUrl("jdbc:mysql://localhost:3306/concretepage");
		 * dataSource.setUsername("root"); dataSource.setPassword("");
		 */

		dataSource.setDriverClassName("org.postgresql.Driver");
		dataSource.setUrl("jdbc:postgresql://localhost:5432/test");
		dataSource.setUsername("test");
		dataSource.setPassword("password");

		// spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect
		// spring.jpa.show-sql=false
		// spring.jpa.hibernate.ddl-auto=create
		//
		return dataSource;
	}

	@Bean
	public HibernateTransactionManager hibTransMan() {
		return new HibernateTransactionManager(sessionFactory());
	}
}