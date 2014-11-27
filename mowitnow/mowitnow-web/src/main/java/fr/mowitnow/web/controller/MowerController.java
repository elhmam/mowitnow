package fr.mowitnow.web.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import fr.mowitnow.core.behvior.MowerBehavior;
import fr.mowitnow.core.behvior.MowerBehaviorImpl;
import fr.mowitnow.core.model.Mower;
import fr.mowitnow.core.parser.MowerParser;
import fr.mowitnow.core.parser.MowerParserImpl;

@Controller
@RequestMapping(value = "/home")
public class MowerController {
	

	private final static  String DATA = "data";

	private final static  String HOME = "home";
	
	private final static Logger LOGGER = LoggerFactory.getLogger(MowerController.class);
	
	private final static	StringBuilder data=new StringBuilder().append("5 5\n").append("1 2 N\n").append("GAGAGAGAA\n").append("3 3 E\n").append("AADAADADDA\n");		
	
	/**
	 * Affichage du formulaire
	 * @param model
	 * @return la page
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String mowitnowForm(Model model) {	
		model.addAttribute(DATA, data.toString());		
		return HOME;
	}

	/**
	 * Traitement du formulaire
	 * @param data 
	 * @param model
	 * @return la page
	 */
	@RequestMapping(method = RequestMethod.POST)
	public String mowitnowSubmit(@ModelAttribute(DATA) String data,
			Model model) {

		List<String> lines = new ArrayList<>();

		@SuppressWarnings("resource")
		Scanner s = new Scanner(data).useDelimiter("\\s*\n\\s*");
		while (s.hasNext()) {
			lines.add(s.nextLine());
		}
		
		LOGGER.info(lines.size() + " lignes à traiter");
		
		MowerParser mowerParser = new MowerParserImpl();

		List<String> errors = mowerParser.checkData(lines);
		
		List<String>  results=new ArrayList<>();

		if (errors.isEmpty()) {
			List<Mower> mowers = mowerParser.loadMowers(lines);
			MowerBehavior mowerBehavior = new MowerBehaviorImpl();
			for (Mower mower : mowers) {
				results.add("before : " + mower.showCoordinate());
				for (char c : mower.getItinerary().toCharArray()) {
					mowerBehavior.move(mower, c);
					results.add(c+" > "+mower.showCoordinate());
				}
				results.add("after : " + mower.showCoordinate());
			}
		}
		
		model.addAttribute("results", results);
		LOGGER.info(results.size() + " résultats");
		model.addAttribute("errors", errors);
		LOGGER.info(errors.size() + " erreurs");
		model.addAttribute(DATA, data.toString());	
		
		return HOME;
	}

}
