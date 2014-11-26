package fr.mowitnow.core.app;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.mowitnow.core.behvior.MowerBehavior;
import fr.mowitnow.core.behvior.MowerBehaviorImpl;
import fr.mowitnow.core.model.Mower;
import fr.mowitnow.core.parser.MowerParser;
import fr.mowitnow.core.parser.MowerParserException;
import fr.mowitnow.core.parser.MowerParserImpl;

public class App {

	final static Logger LOGGER = LoggerFactory.getLogger(App.class);

	public static void main(String[] args) throws IOException,
			MowerParserException {

		if (args.length == 0) {
			LOGGER.warn("Veuillez entrer le nom du fichier des instructions !!!");
			System.exit(0);
		}

		@SuppressWarnings("unchecked")
		List<String> lines = FileUtils.readLines(new File(args[0]));

		MowerParser mowerParser = new MowerParserImpl();

		List<String> errors = mowerParser.checkData(lines);

		if (errors.isEmpty()) {
			List<Mower> mowers = mowerParser.loadMowers(lines);
			MowerBehavior mowerBehavior = new MowerBehaviorImpl();
			for (Mower mower : mowers) {
				LOGGER.info("Departure : " + mower.showCoordinate());
				for (char c : mower.getItinerary().toCharArray()) {
					mowerBehavior.move(mower, c);
					LOGGER.debug("Instruction " + c + " > " + mower.getX()
							+ " - " + mower.getY() + " - "
							+ mower.getOrientation());
				}
				LOGGER.info("Arrival : " + mower.showCoordinate());
				LOGGER.info("------------ ");
			}
		} else {
			for (String error : errors) {
				LOGGER.error(error);
			}
		}

	}

}
