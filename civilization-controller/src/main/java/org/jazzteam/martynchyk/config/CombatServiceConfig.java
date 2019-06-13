package org.jazzteam.martynchyk.config;

import org.jazzteam.martynchyk.service.CombatService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "org.jazzteam.martynchyk.config")
public class CombatServiceConfig {
    @Bean
    public CombatService getCombatService() {
        return new CombatService();
    }
}
