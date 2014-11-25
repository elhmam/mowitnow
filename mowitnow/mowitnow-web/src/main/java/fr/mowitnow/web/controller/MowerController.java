package fr.mowitnow.web.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import fr.mowitnow.core.behvior.MowerBehavior;
import fr.mowitnow.core.factory.ObjectFactory;
import fr.mowitnow.core.model.Mower;
import fr.mowitnow.core.parser.MowerParser;

@Controller
public class MowerController {

	@RequestMapping(value = "/mowitnow", method = RequestMethod.GET)
	public String mowitnowForm(Model model) {
		model.addAttribute("result", "Entrer une valeur");
		return "mowitnow";
	}

	@RequestMapping(value = "/mowitnow", method = RequestMethod.POST)
	public String greetingSubmit(@ModelAttribute("data") String data,
			Model model) {

		List<String> lines = new ArrayList<>();

		@SuppressWarnings("resource")
		Scanner s = new Scanner(data).useDelimiter("\\s*\n\\s*");
		while (s.hasNext()) {
			lines.add(s.nextLine());
		}

		MowerParser mowerParser = ObjectFactory.getMowerParser();

		List<String> errors = mowerParser.checkData(lines);
		
		StringBuilder result=new StringBuilder();

		if (errors.isEmpty()) {
			List<Mower> mowers = mowerParser.loadMowers(lines);
			MowerBehavior mowerBehavior = ObjectFactory.getMowerBehavior();
			for (Mower mower : mowers) {
				System.out.println("Début : " + mower.toString());
				for (char c : mower.getItinerary().toCharArray()) {
					mowerBehavior.move(mower, c);
					System.out.println(c + " > " + mower.getX() + " - "
							+ mower.getY() + " - " + mower.getOrientation());
				}
				System.out.println("Fin : " + mower.toString());
				result.append(mower.getX()+"-"+mower.getY()+"<br/>");
			}
		} else {
			for (String error : errors) {
				System.out.println(error);
			}
		}

		model.addAttribute("result", result.toString());
		return "mowitnow";
	}

}
