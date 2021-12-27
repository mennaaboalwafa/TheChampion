package com.bankmasr.thechampion.service;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Optional;

//spring annotation
@Configuration
//spring jpa audit annotation
//annotation enables the auditing in jpa via annotation configuration
@EnableJpaAuditing(auditorAwareRef = "aware")
public class AuditConfig {
    @Bean
    public AuditorAware<String> aware() {
        return () -> Optional.of("Administrator");
    }
}


