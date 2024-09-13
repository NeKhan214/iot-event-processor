package com.nekhan.iot.event.processor.config;

import java.util.Optional;

import static com.nekhan.iot.event.processor.constants.ApiConstant.SYSTEM_USER;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing
public class AuditConfig {

    @Bean
    public AuditorAware<String> auditorAware() {
        return () -> Optional.of(SYSTEM_USER);
    }
}
