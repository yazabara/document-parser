package com.yazabara.documentparser.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("parser")

@Data
public class AppConfig {

    private String path;
}
