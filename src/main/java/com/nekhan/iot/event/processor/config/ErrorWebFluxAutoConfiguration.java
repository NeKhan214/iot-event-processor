package com.nekhan.iot.event.processor.config;

import com.nekhan.iot.event.processor.exception.handler.GlobalExceptionHandler;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.autoconfigure.condition.SearchStrategy;
import org.springframework.boot.autoconfigure.web.WebProperties;
import org.springframework.boot.autoconfigure.web.reactive.WebFluxAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.reactive.error.DefaultErrorAttributes;
import org.springframework.boot.web.reactive.error.ErrorAttributes;
import org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.web.reactive.config.WebFluxConfigurer;
import org.springframework.web.reactive.result.view.ViewResolver;

/**
 * {@link EnableAutoConfiguration Auto-configuration} to render errors through a WebFlux
 * {@link org.springframework.web.server.WebExceptionHandler}.
 *
 * @author Neha Khan
 */
@AutoConfiguration(before = WebFluxAutoConfiguration.class)
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.REACTIVE)
@ConditionalOnClass(WebFluxConfigurer.class)
@EnableConfigurationProperties({WebProperties.class})
public class ErrorWebFluxAutoConfiguration {

    /**
     * Provides a custom {@link ErrorWebExceptionHandler} bean that handles web exceptions in a reactive application.
     * This handler is only registered if no other {@code ErrorWebExceptionHandler} bean is present in the application context.
     *
     * <p>The handler is configured with the provided {@link ErrorAttributes}, {@link WebProperties}, {@link ViewResolver}s,
     * and {@link ServerCodecConfigurer} to manage error responses.</p>
     *
     * <p>Example usage:</p>
     * <pre>{@code
     * @Bean
     * @ConditionalOnMissingBean(value = ErrorWebExceptionHandler.class, search = SearchStrategy.CURRENT)
     * @Order(-1)
     * public ErrorWebExceptionHandler errorWebExceptionHandler(ErrorAttributes errorAttributes,
     *                                                          WebProperties webProperties, ObjectProvider<ViewResolver> viewResolvers,
     *                                                          ServerCodecConfigurer serverCodecConfigurer, ApplicationContext applicationContext) {
     *     GlobalExceptionHandler exceptionHandler = new GlobalExceptionHandler(errorAttributes,
     *             webProperties.getResources(), applicationContext);
     *     exceptionHandler.setViewResolvers(viewResolvers.orderedStream().toList());
     *     exceptionHandler.setMessageWriters(serverCodecConfigurer.getWriters());
     *     exceptionHandler.setMessageReaders(serverCodecConfigurer.getReaders());
     *     return exceptionHandler;
     * }
     * }</pre>
     *
     * @param errorAttributes       the error attributes to use
     * @param webProperties         the web properties to use
     * @param viewResolvers         the view resolvers to use
     * @param serverCodecConfigurer the server codec configurer to use
     * @param applicationContext    the application context
     * @return a configured {@link ErrorWebExceptionHandler} bean
     */
    @Bean
    @ConditionalOnMissingBean(value = ErrorWebExceptionHandler.class, search = SearchStrategy.CURRENT)
    @Order(-1)
    public ErrorWebExceptionHandler errorWebExceptionHandler(ErrorAttributes errorAttributes,
                                                             WebProperties webProperties, ObjectProvider<ViewResolver> viewResolvers,
                                                             ServerCodecConfigurer serverCodecConfigurer, ApplicationContext applicationContext) {
        GlobalExceptionHandler exceptionHandler = new GlobalExceptionHandler(errorAttributes,
                webProperties.getResources(), applicationContext);
        exceptionHandler.setViewResolvers(viewResolvers.orderedStream().toList());
        exceptionHandler.setMessageWriters(serverCodecConfigurer.getWriters());
        exceptionHandler.setMessageReaders(serverCodecConfigurer.getReaders());
        return exceptionHandler;
    }

    /**
     * Provides a {@link DefaultErrorAttributes} bean that supplies default error attributes.
     * This bean is only registered if no other {@link ErrorAttributes} bean is present in the application context.
     *
     * <p>The {@code DefaultErrorAttributes} class provides default implementations for the error attributes
     * that are used in error responses.</p>
     *
     * <p>Example usage:</p>
     * <pre>{@code
     * @Bean
     * @ConditionalOnMissingBean(value = ErrorAttributes.class, search = SearchStrategy.CURRENT)
     * public DefaultErrorAttributes errorAttributes() {
     *     return new DefaultErrorAttributes();
     * }
     * }</pre>
     *
     * @return a {@link DefaultErrorAttributes} bean
     */
    @Bean
    @ConditionalOnMissingBean(value = ErrorAttributes.class, search = SearchStrategy.CURRENT)
    public DefaultErrorAttributes errorAttributes() {
        return new DefaultErrorAttributes();
    }

}
