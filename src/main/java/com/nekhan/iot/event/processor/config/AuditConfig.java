package com.nekhan.iot.event.processor.config;

import static com.nekhan.iot.event.processor.constants.ApiConstant.SYSTEM_USER;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.ReactiveAuditorAware;
import org.springframework.data.r2dbc.config.EnableR2dbcAuditing;
import reactor.core.publisher.Mono;

/**
 * The {@code AuditConfig} class represents the configuration settings for auditing.
 *
 * <p>This class is part of the auditing framework and is used to manage audit settings
 * for various components of the application.</p>
 *
 * @author Neha Khan
 * @version 1.0
 */
@Configuration
@EnableR2dbcAuditing
public class AuditConfig {

    /**
     * Provides a {@link ReactiveAuditorAware} bean that supplies the current auditor.
     * This implementation returns a constant system user.
     *
     * <p>This bean is used in reactive auditing to automatically populate the auditor
     * fields (like createdBy and lastModifiedBy) in entities.</p>
     *
     * <p>Example usage:</p>
     * <pre>{@code
     * @Bean
     * public ReactiveAuditorAware<String> auditorAware() {
     *     return () -> Mono.just(SYSTEM_USER);
     * }
     * }</pre>
     *
     * @return a {@link ReactiveAuditorAware} that supplies the system user as the auditor
     */
    @Bean
    public ReactiveAuditorAware<String> auditorAware() {
        return () -> Mono.just(SYSTEM_USER);
    }
}
