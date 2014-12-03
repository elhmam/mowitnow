package fr.mowitnow.web.controller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
/**
 * Classe Application
 * @author elhmam
 */
@Configuration
@EnableAutoConfiguration
@ComponentScan
public class Application {    
    /**
     * Méthode principale.
     * @param args : arguments
     */
    public static void main(String[] args) {	
	SpringApplication.run(Application.class, args);
    }

}
