package fr.mowitnow.web.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableAutoConfiguration
@EnableWebMvc
@ComponentScan(basePackages = { "com.mowitnow.web" })
@PropertySource("classpath:spring.properties")
@Import({ SpringMvcConfig.class,ThymeleafConfig.class })
public class AppConfig {

}
