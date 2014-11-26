package fr.mowitnow.web.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import fr.mowitnow.web.config.AppConfig;

@Configuration
@ComponentScan
@EnableAutoConfiguration
public class Application  {

	

    public static void main(String[] args) {
    	SpringApplication app = new SpringApplication(AppConfig.class);
		app.setLogStartupInfo(false);
		app.run(args);
//        SpringApplication.run(Application.class, args);
    }
    
    
}
