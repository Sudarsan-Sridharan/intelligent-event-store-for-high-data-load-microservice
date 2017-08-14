package com.deviceinsight.services;

import org.apache.commons.dbcp.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ResourceLoader;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@SuppressWarnings("ALL")
@Configuration
@ConditionalOnClass(HibernateTemplate.class)
@AutoConfigureAfter(DataSourceAutoConfiguration.class)
//@EnableConfigurationProperties(Hibernate5Properties.class)
public class Hibernate5AutoConfiguration {
    @Autowired
    private ResourceLoader resourceLoader;

    @Bean
    public LocalSessionFactoryBean hibernate5SessionFactory(DataSource dataSource) {
        LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();
        factoryBean.setDataSource(dataSource);
        return factoryBean;
    }

    @Bean
    @Primary
    public LocalSessionFactoryBean hibernateSessionFactory(DataSource dataSource) {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        sessionFactory.setPackagesToScan(new String[]{
                "com.deviceinsight.services.model",
                "com.deviceinsight.services.ecommerce"
        });
        return sessionFactory;
    }

    @Bean(name = "dataSource")
    public DataSource getDataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://127.0.0.1:3306/event_store?autoReconnect=true&useSSL=false");
        dataSource.setUsername("event_store");
        dataSource.setPassword("event_store");
        return dataSource;
    }

    @Bean
    public HibernateTemplate hibernate5Template(SessionFactory sessionFactory) {
        return new HibernateTemplate(sessionFactory);
    }

    @Bean
    @ConditionalOnMissingBean(PlatformTransactionManager.class)
    public HibernateTransactionManager hibernate5TransactionManager(SessionFactory sessionFactory) {
        return new HibernateTransactionManager(sessionFactory);
    }
}
