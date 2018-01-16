package com.horizon.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import oshi.SystemInfo;

@Configuration
public class AppConfig {

    @Bean
    public SystemInfo systemInfo() {
        return new SystemInfo();
    }

}
