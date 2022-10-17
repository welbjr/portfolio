package com.example.api.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("portfolio")
public record PortfolioConfigurationProperties(String username, String password) {
}
