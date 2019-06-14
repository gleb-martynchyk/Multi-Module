package org.jazzteam.martynchyk.config;


import org.jazzteam.martynchyk.dao.implementation.CityDao;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan(basePackages = "org.jazzteam.martynchyk.config")
@EnableTransactionManagement
public class CityDaoConfig {
    @Bean
    public CityDao cityDao() {
        return new CityDao();
    }
}
