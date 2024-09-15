package com.nekhan.iot.event.processor;

import com.nekhan.iot.event.processor.config.ErrorWebFluxAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * The main entry point for the IoT Event Processor Spring Boot Application.
 * <p>
 * This class is responsible for bootstrapping the Spring Boot application.
 * </p>
 *
 * @author Neha Khan
 * @version 1.0
 */
@SpringBootApplication
public class IotEventProcessorApplication extends ErrorWebFluxAutoConfiguration {

    /**
     * The main method that serves as the entry point of the application.
     * <p>
     * This method initializes the Spring Boot application context.
     * </p>
     *
     * @param args command-line arguments
     */
    public static void main(String[] args) {
        SpringApplication.run(IotEventProcessorApplication.class, args);
    }

}
