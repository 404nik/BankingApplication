package com.mindgate.main.configuration;

import java.time.Clock;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;


@Configuration
public class LoginConfiguration {

        @Bean
        public ViewResolver viewResolver() {
            InternalResourceViewResolver viewResolver = new InternalResourceViewResolver("/WEB-INF/views/", ".jsp");
            return viewResolver;
        }

        @Bean
        public Clock utcClock() {
            return Clock.systemUTC();
        }
}
