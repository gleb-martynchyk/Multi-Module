package org.jazzteam.martynchyk.config;


import org.jazzteam.martynchyk.dao.implementation.CityDao;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "org.jazzteam.martynchyk.config")
public class CityDaoConfig {
    @Bean
    public CityDao cityDao() {
        return new CityDao();
    }
}
