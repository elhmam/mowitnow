package fr.mowitnow.web.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class MowerController {

    @RequestMapping("/")
    public String index() {
        return "Greetings from Spring Boot! testing git";
    }

}
