package org.jazzteam.martynchyk.config;


import org.jazzteam.martynchyk.dao.implementation.UnitDao;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "org.jazzteam.martynchyk.config")
public class UnitDaoConfig {
    @Bean
    public UnitDao unitDao() {
        return new UnitDao();
    }
}
