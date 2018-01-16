package com.horizon.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * @author Amjad
 */
@Configuration
@EnableTransactionManagement
public class DatabaseConfig {

    @Value("${spring.jpa.properties.hibernate.hbm2ddl.auto}")
    private String auto;

    @Value("${spring.jpa.properties.hibernate.dialect}")
    private String dialect;

    @Value("${hibernate.cache.use_second_level_cache}")
    private String secondLevelCache;

    @Value("${hibernate.cache.region.factory_class}")
    private String factoryClass;

    @Value("${spring.datasource.driver}")
    private String driver;

    @Value("${spring.datasource.url}")
    private String url;

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource());
        em.setPackagesToScan(new String[] { "com.horizon.model" });

        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);

        Properties properties = new Properties();
        properties.setProperty("hibernate.hbm2ddl.auto", auto);
        properties.setProperty("hibernate.dialect", dialect);
        properties.setProperty("hibernate.cache.use_second_level_cache", secondLevelCache);
        properties.setProperty("hibernate.cache.region.factory_class", factoryClass);
        //properties.setProperty("hibernate.show_sql", "true");

        em.setJpaProperties(properties);
        return em;
    }

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(driver);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        return dataSource;
    }

}
