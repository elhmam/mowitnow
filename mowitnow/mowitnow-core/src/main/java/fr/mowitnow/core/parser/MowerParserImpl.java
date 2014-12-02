package fr.mowitnow.core.parser;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import fr.mowitnow.core.exception.MowerParserException;

import fr.mowitnow.core.enumeration.MowerParserPatternsEnum;
import fr.mowitnow.core.model.Lawn;
import fr.mowitnow.core.model.Mower;

/**
 * Implementation de la classe MowerParser.
 * @author elhmam
 */
public class MowerParserImpl implements MowerParser {

    /**
     * Index de l'orientation.
     */
    private static final int ORIENTATION_INDEX = 3;

    /*
     * (non-Javadoc)
     * @see fr.mowitnow.core.parser.MowerParser#loadMowers(java.util.List)
     */
    @Override
    public final List<Mower> loadMowers(final List<String> pLines)
	    throws MowerParserException {

	if (pLines.isEmpty()) { // la liste d'entrée ne doit pas etre vide
	    throw new MowerParserException("Aucune donnée n'est fournie");
	} else if (pLines.size() % 2 == 0) { // le nombre de ligne doit etre
					     // impair
	    throw new MowerParserException(
		    "Le nombre de lignes du ne doit pas etre pair");
	} else {
	    // création du champ
	    Lawn lawn = createLawn(pLines.get(0));

	    // création des tondeuses
	    List<Mower> mowers = new ArrayList<>();

	    for (int i = 1; i < pLines.size(); i = i + 2) {
		Mower mower = createMower(pLines.get(i), pLines.get(i + 1),
			lawn, i);
		if (mower != null) {
		    mowers.add(mower);
		}
	    }
	    return mowers;
	}

    }

    /**
     * Créée la tondeuse.
     * @param pCoords
     *            : coordonnées de la tondeuse
     * @param pPath
     *            : trajectoire de la tondeuse
     * @param pLawn
     *            : objet champ
     * @param pLine
     *            : l'index de la ligne
     * @throws MowerParserException
     *             : Exception levée
     * @return objet tondeuse
     */
    private Mower createMower(final String pCoords, final String pPath,
	    final Lawn pLawn, final int pLine) throws MowerParserException {

	Matcher matcher1 = getMatcher(pCoords,
		MowerParserPatternsEnum.PATTERN_MOWER.getValue());
	if (matcher1.find()) {
	    int x = Integer.parseInt(matcher1.group(1));
	    int y = Integer.parseInt(matcher1.group(2));
	    if (x <= pLawn.getXbound() && y <= pLawn.getYbound()) {
		char orientation = matcher1.group(ORIENTATION_INDEX).charAt(0);
		if (Pattern.matches(
			MowerParserPatternsEnum.PATTERN_ITINERARY.getValue(),
			pPath)) {
		    return new Mower(x, y, orientation, pLawn, pPath);
		} else {
		    throw new MowerParserException("Ligne : " + (pLine + 2)
			    + " > La trajectoire de la tondeuse "
			    + "n'est pas valide : " + pPath);
		}
	    } else {
		throw new MowerParserException("Ligne : " + (pLine + 1)
			+ " > La tondeuse ne peut etre placée à "
			+ "l'exterieur du champ : " + pCoords);
	    }
	} else {
	    throw new MowerParserException("Ligne : " + (pLine + 1)
		    + " > Les coordonnées de la tondeuse ne sont"
		    + " pas valides : " + pCoords);
	}

    }

    /**
     * Créée le champ.
     * @param pLine
     *            : la ligne à traiter
     * @throws MowerParserException
     *             : Exception levée
     * @return objet champ
     */
    private Lawn createLawn(final String pLine) throws MowerParserException {
	// Créer le champ

	Matcher matcher = getMatcher(pLine,
		MowerParserPatternsEnum.PATTERN_LAWN.getValue());
	if (matcher.find()) {
	    int xbound = Integer.parseInt(matcher.group(1));
	    int ybound = Integer.parseInt(matcher.group(2));
	    return new Lawn(xbound, ybound);
	} else {
	    throw new MowerParserException(
		    "Ligne : 1 > Les dimensions du champ ne sont pas valides :"
			    + pLine);

	}

    }

    /**
     * Retourne Matcher du text passé en paramétre.
     * @param pStr
     *            : text à matcher
     * @param pPattern
     *            : le pattern à respecter
     * @return Matcher
     */
    private Matcher getMatcher(final String pStr, final String pPattern) {
	Pattern patternMower = Pattern.compile(pPattern);
	return patternMower.matcher(pStr);
    }
}
