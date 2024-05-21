package com.project.tmc.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "com.project.tmc.repository")
public class DefaultRepositoryJpaConfiguration {
}
