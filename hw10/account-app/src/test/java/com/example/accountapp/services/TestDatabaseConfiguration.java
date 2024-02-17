//package com.example.accountapp.services;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.sql.DataSource;
import java.util.Properties;

//@TestConfiguration
public class TestDatabaseConfiguration  {

//    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl("jdbc:h2:~/acc;"); // URL базы данных H2
        dataSource.setUsername("sa"); // Имя пользователя
        dataSource.setPassword(""); // Пароль (если есть)
        dataSource.setDriverClassName("org.h2.Driver"); // Класс драйвера
        return dataSource;
    }

//    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource);
        em.setPackagesToScan("com.example.accountapp.models"); // Пакеты для сканирования сущностей JPA
        em.setJpaVendorAdapter(new HibernateJpaVendorAdapter());

        // Установка свойств JPA/Hibernate
        Properties jpaProperties = new Properties();
        jpaProperties.setProperty("hibernate.hbm2ddl.auto", "update"); // Настройка ddl-auto
        jpaProperties.setProperty("hibernate.generate_ddl", "false"); // Настройка generate-ddl
        em.setJpaProperties(jpaProperties);

        return em;
    }
}
