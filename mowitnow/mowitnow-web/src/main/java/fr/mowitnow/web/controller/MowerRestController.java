package fr.mowitnow.web.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MowerRestController {

    @RequestMapping("/")
    public String index() {
        return "Mowitnow";
    }

}
