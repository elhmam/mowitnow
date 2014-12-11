package fr.mowitnow.core.app;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.mowitnow.core.behvior.MowerBehavior;
import fr.mowitnow.core.behvior.MowerBehaviorImpl;
import fr.mowitnow.core.exception.MowerParserException;
import fr.mowitnow.core.model.Mower;
import fr.mowitnow.core.parser.MowerParser;
import fr.mowitnow.core.parser.MowerParserImpl;

/**
 * Classe principale.
 * 
 * @author elhmam
 */
public class App {
    /**
     * LOGGER.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(App.class);

    /**
     * Constructeur privé.
     */
    private App() {
    }

    /**
     * Méthode principale.
     * 
     * @param args
     *            : arguments à passer au lancement du programme
     */
    @SuppressWarnings("unchecked")
    public static void main(String[] args) {

	if (args.length == 0) {
	    throw new ArrayIndexOutOfBoundsException();
	}

	List<String> lines = new ArrayList<>();
	try {
	    lines = FileUtils.readLines(new File(args[0]));
	} catch (IOException e1) {
	    LOGGER.error(e1.getMessage(), e1);
	}

	if (!lines.isEmpty()) {
	    MowerParser mowerParser = new MowerParserImpl();
	    List<Mower> mowers = new ArrayList<>();

	    try {
		mowers = mowerParser.loadMowers(lines);
	    } catch (MowerParserException e) {
		LOGGER.error(e.getMessage(), e);
	    }

	    MowerBehavior mowerBehavior = new MowerBehaviorImpl();

	    for (Mower mower : mowers) {
		LOGGER.info(String.format("Start : [%s, %s, %s]", mower.getX(),
			mower.getY(), mower.getOrientation()));
		for (char c : mower.getPath().toCharArray()) {
		    mowerBehavior.execute(mower, c);
		    LOGGER.debug(c + " > " + mower.getX() + " - "
			    + mower.getY() + " - " + mower.getOrientation());
		}
		LOGGER.info(String.format("End : [%s, %s, %s]", mower.getX(),
			mower.getY(), mower.getOrientation()));
		LOGGER.info("----------------------------------------------");
	    }
	}

    }

}
