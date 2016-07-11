package com.sample.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * @author Josue Gontijo <>.
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.sample")
public class Configurator {
}
