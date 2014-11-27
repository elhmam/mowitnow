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
	

	private static final String HOME = "home";
	final static Logger LOGGER = LoggerFactory.getLogger(MowerController.class);
	

	@RequestMapping(method = RequestMethod.GET)
	public String mowitnowForm(Model model) {	
		model.addAttribute("hello", "Veuillez modifier le plan par défaut");
		StringBuilder str=new StringBuilder();
		str.append("5 5\n");
		str.append("1 2 N\n");
		str.append("GAGAGAGAA\n");
		str.append("3 3 E\n");
		str.append("AADAADADDA\n");		
		
		model.addAttribute("data", str.toString());
		
		return HOME;
	}


	@RequestMapping(method = RequestMethod.POST)
	public String mowitnowSubmit(@ModelAttribute("data") String data,
			Model model) {
		model.addAttribute("hello", "Veuillez modifier le plan par défaut");
		List<String> lines = new ArrayList<>();

		@SuppressWarnings("resource")
		Scanner s = new Scanner(data).useDelimiter("\\s*\n\\s*");
		while (s.hasNext()) {
			lines.add(s.nextLine());
		}

		MowerParser mowerParser = new MowerParserImpl();

		List<String> errors = mowerParser.checkData(lines);
		
		StringBuilder result=new StringBuilder();

		if (errors.isEmpty()) {
			List<Mower> mowers = mowerParser.loadMowers(lines);
			MowerBehavior mowerBehavior = new MowerBehaviorImpl();
			for (Mower mower : mowers) {
				LOGGER.info("before : " + mower.showCoordinate());
				for (char c : mower.getItinerary().toCharArray()) {
					mowerBehavior.move(mower, c);
					LOGGER.debug(c + " > " + mower.getX() + " - "
							+ mower.getY() + " - " + mower.getOrientation());
				}
				LOGGER.info("after : " + mower.showCoordinate());
				result.append(mower.getX()+"-"+mower.getY());
			}
		} else {
			for (String error : errors) {
				LOGGER.warn(error);
			}
		}

		model.addAttribute("result", result.toString());
		model.addAttribute("errors", errors);
		return HOME;
	}

}
