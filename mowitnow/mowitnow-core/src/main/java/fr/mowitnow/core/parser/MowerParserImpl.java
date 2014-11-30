package fr.mowitnow.core.parser;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import fr.mowitnow.core.model.Lawn;
import fr.mowitnow.core.model.Mower;

public class MowerParserImpl implements MowerParser, MowerParserPatterns {

	
	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.mowitnow.core.parser.MowerParser#loadMowers(java.util.List)
	 */
	@Override
	public List<Mower> loadMowers(List<String> lines)
			throws MowerParserException {

		if (lines.isEmpty()) { // la liste d'entrée ne doit pas etre vide
			throw new MowerParserException("Aucune donnée n'est fournie");
		} else if (lines.size() % 2 == 0) { // le nombre de ligne doit etre
											// impair
			throw new MowerParserException(
					"Le nombre de lignes du ne doit pas etre pair");
		} else {
			// création du gazon
			Lawn lawn = createLawn(lines.get(0));

			// création des tondeuses
			List<Mower> mowers = new ArrayList<>();

			for (int i = 1; i < lines.size(); i = i + 2) {
				Mower mower = createMower(lines.get(i), lines.get(i + 1), lawn,
						i);
				if (mower != null) {
					mowers.add(mower);
				}
			}
			return mowers;
		}


	}

	/**
	 * @param lines
	 * @param lawn
	 * @param mowers
	 * @param i
	 * @throws MowerParserException
	 */
	private Mower createMower(String coords, String intinary, Lawn lawn,
			int line) throws MowerParserException {

		Matcher matcher1 = getMatcher(coords, PATTERN_MOWER);
		if (matcher1.find()) {
			int x = Integer.parseInt(matcher1.group(1));
			int y = Integer.parseInt(matcher1.group(2));
			if (x <= lawn.getXbound() && y <= lawn.getYbound()) {
				char orientation = matcher1.group(3).charAt(0);
				if (Pattern.matches(PATTERN_INSTRUCTIONS, intinary)) {
					return new Mower(x, y, orientation, lawn, intinary);
				} else {
					throw new MowerParserException(
							"Ligne : "
									+ (line + 2)
									+ " > La trajectoire de la tondeuse n'est pas valide : "
									+ intinary);
				}
			} else {
				throw new MowerParserException(
						"Ligne : "
								+ (line+1)
								+ " > La tondeuse ne peut etre placée à l'exterieur du gazon : "
								+ coords);
			}
		} else {
			throw new MowerParserException(
					"Ligne : "
							+ (line+1)
							+ " > Les coordonnées de la tondeuse ne sont pas valides : "
							+ coords);
		}

	}

	/**
	 * @param lines
	 * @return
	 * @throws MowerParserException
	 */
	private Lawn createLawn(String line) throws MowerParserException {
		// Créer le gazon

		Matcher matcher = getMatcher(line, PATTERN_LAWN);
		if (matcher.find()) {
			int xbound = Integer.parseInt(matcher.group(1));
			int ybound = Integer.parseInt(matcher.group(2));
			return new Lawn(xbound, ybound);
		} else {
			throw new MowerParserException(
					"Ligne : 1 > Les dimensions du gazon ne sont pas valides :"
							+ line);

		}

	}

	/**
	 * Retourne Matcher du text passé en paramétre
	 * 
	 * @param str
	 *            : text à matcher
	 * @param pattern
	 *            : le pattern à respecter
	 * @return Matcher
	 */
	private Matcher getMatcher(String str, String pattern) {
		Pattern patternMower = Pattern.compile(pattern);
		return patternMower.matcher(str);
	}
}
