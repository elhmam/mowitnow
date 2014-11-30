package fr.mowitnow.web.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
import fr.mowitnow.core.parser.MowerParserException;
import fr.mowitnow.core.parser.MowerParserImpl;

@Controller
@RequestMapping(value = "/home")
public class MowerController {

	private static final String YLIMIT = "ylimit";

	private static final String XLIMIT = "xlimit";

	private static final String ERROR = "error";

	private static final String MOWERS_MAP = "mowersMap";

	private static final String DATA = "data";

	private static final String HOME = "home";

	private static final Logger LOGGER = LoggerFactory
			.getLogger(MowerController.class);

	private static final StringBuilder data = new StringBuilder()
			.append("5 5\n").append("1 2 N\n").append("GAGAGAGAA\n")
			.append("3 3 E\n").append("AADAADADDA\n");

	/**
	 * Affichage du formulaire
	 * 
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
	 * 
	 * @param data
	 * @param model
	 * @return la page
	 */
	@RequestMapping(method = RequestMethod.POST)
	public String mowitnowSubmit(@ModelAttribute(DATA) String data, Model model) {

		List<String> lines = new ArrayList<>();

		@SuppressWarnings("resource")
		Scanner s = new Scanner(data).useDelimiter("\\s*\n\\s*");
		while (s.hasNext()) {
			lines.add(s.nextLine());
		}

		LOGGER.info(lines.size() + " lignes à traiter");

		MowerParser mowerParser = new MowerParserImpl();
		List<Mower> mowers =null;
		try {
			mowers = mowerParser.loadMowers(lines);
		} catch (MowerParserException e) {
			LOGGER.error(e.getMessage());
			model.addAttribute(ERROR, e.getMessage());
		}

		if(mowers!=null){
			Map<Integer, List<Mower>> mowersMap = new HashMap<>();

			
			MowerBehavior mowerBehavior = new MowerBehaviorImpl();
			for (int j = 0; j < mowers.size(); j++) {
				List<Mower> steps = new ArrayList<>();
				Mower mower = new Mower(mowers.get(j).getX(), mowers.get(j).getY(),
						mowers.get(j).getOrientation(),mowers.get(j).getLawn(),mowers.get(j).getItinerary());
				LOGGER.info("Tondeuse " + (j + 1) + " > " + mower.getX() + ","
						+ mower.getY() + "," + mower.getOrientation());
				steps.add(mower);
				for (char c : mowers.get(j).getItinerary().toCharArray()) {
					mowerBehavior.move(mowers.get(j), c);
					Mower mower1 = new Mower(mowers.get(j).getX(), mowers.get(j)
							.getY(), mowers.get(j).getOrientation(),mowers.get(j).getLawn(),mowers.get(j).getItinerary());
					LOGGER.info("Tondeuse " + (j + 1) + " > " + mower1.getX() + ","
							+ mower1.getY() + "," + mower1.getOrientation());
					steps.add(mower1);
				}
				mowersMap.put(j, steps);
			}
			model.addAttribute(MOWERS_MAP, mowersMap);	
		}
		

			
		model.addAttribute(DATA, data.toString());
		model.addAttribute(XLIMIT, lines.get(0).split(" ")[0]);
		model.addAttribute(YLIMIT, lines.get(0).split(" ")[1]);

		return HOME;
	}

}
