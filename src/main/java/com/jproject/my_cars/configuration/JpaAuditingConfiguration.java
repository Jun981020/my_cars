package com.jproject.my_cars.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing
//baseEntity extend 를 위한 configuration
public class JpaAuditingConfiguration {
}
