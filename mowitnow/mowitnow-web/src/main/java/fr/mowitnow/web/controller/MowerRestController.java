package fr.mowitnow.web.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MowerRestController {

	/**
	 * Affichage de la page d'accueil
	 * @return
	 */
    @RequestMapping("/")
    public String index() {
        return "Mini projet Mowitnow, <a href=\"home\">formulaire</a>";
    }

}
