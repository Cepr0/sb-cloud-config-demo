package io.github.cepr0.config.server;

import com.integralblue.log4jdbc.spring.Log4jdbcBeanPostProcessor;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;

@AutoConfigureOrder(Ordered.LOWEST_PRECEDENCE)
@ConditionalOnClass(Log4jdbcBeanPostProcessor.class)
@Configuration
public class Log4jdbcConfiguration {
    @Bean
    public Log4jdbcBeanPostProcessor log4jdbcBeanPostProcessor() {
        return new Log4jdbcBeanPostProcessor();
    }
}
