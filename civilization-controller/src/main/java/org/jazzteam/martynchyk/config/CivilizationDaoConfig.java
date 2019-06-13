package org.jazzteam.martynchyk.config;


import org.jazzteam.martynchyk.dao.implementation.CivilizationDao;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "org.jazzteam.martynchyk.config")
public class CivilizationDaoConfig {
    @Bean
    public CivilizationDao civilizationDao() {
        return new CivilizationDao();
    }
}
