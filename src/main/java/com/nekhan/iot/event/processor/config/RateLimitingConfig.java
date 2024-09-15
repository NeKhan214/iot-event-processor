package com.nekhan.iot.event.processor.config;

import static com.nekhan.iot.event.processor.constants.ApiConstant.MAX_NUMBER_OF_REQUESTS_PER_MINUTE;

import java.time.Duration;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Refill;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration class for setting up rate limiting using Bucket4j.
 * This class defines a bean for a Bucket that limits the number of requests per minute.
 *
 * @author Neha Khan
 * @version 1.0
 */
@Configuration
public class RateLimitingConfig {

    /**
     * The maximum number of requests allowed per minute.
     * This value is injected from the application properties.
     */
    @Value("${" + MAX_NUMBER_OF_REQUESTS_PER_MINUTE + ":100}")
    private Long maxNumberOfRequestsPerMinute;

    /**
     * Creates a Bucket bean configured with the specified rate limit.
     * The Bucket uses a greedy refill strategy to allow up to the maximum number of requests per minute.
     *
     * @return a configured Bucket instance
     */
    @Bean
    public Bucket bucket() {
        Bandwidth limit = Bandwidth.classic(maxNumberOfRequestsPerMinute, Refill.greedy(maxNumberOfRequestsPerMinute,
                Duration.ofMinutes(1)));
        return Bucket.builder().addLimit(limit).build();
    }
}
