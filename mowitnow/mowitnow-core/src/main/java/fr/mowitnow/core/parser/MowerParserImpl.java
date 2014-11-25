package fr.mowitnow.core.parser;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import fr.mowitnow.core.factory.ObjectFactory;
import fr.mowitnow.core.model.Lawn;
import fr.mowitnow.core.model.Mower;

public class MowerParserImpl implements MowerParser {

	private static final String PATTERN_ITINARY = "^([GAD])+$";
	private static final String PATTERN_LAWN = "^(\\d)\\s(\\d)$";
	private static final String PATTERN_MOWER = "^(\\d)\\s(\\d)\\s([WENS])$";

	/* (non-Javadoc)
	 * @see fr.mowitnow.core.parser.MowerParser#checkData(java.util.List)
	 */
	@Override
	public List<String> checkData(List<String> lines) {
		List<String> errors = new ArrayList<>();

		if (lines.isEmpty()) {
			errors.add("Aucune donnée n'est fournie");
		} else if (lines.size() % 2 == 0) { // 1 . le nombre de ligne doit etre
											// impair
			errors.add("Le nombre de lignes du ne doit pas etre pair");
		} else {
			Pattern patternLawn = Pattern.compile(PATTERN_LAWN);
			Matcher matcher = patternLawn.matcher(lines.get(0));
			// 2 . vérifier le format de la premiere ligne Lawn
			if (matcher.find()) {
				for (int i = 1; i < lines.size(); i = i + 2) {
					// 3 . vérifier les deux lignes suivantes, les
					// coordonnées du Mower et son itineraire
					Pattern patternMower = Pattern.compile(PATTERN_MOWER);
					Matcher matcher1 = patternMower.matcher(lines.get(i));
					if (matcher1.find()) {
						if (!Pattern.matches(PATTERN_ITINARY, lines.get(i + 1))) {
							errors.add("L'itineraire de la tondeuse n'est pas valide : "
									+ lines.get(i + 1));
						}
					} else {
						errors.add("Les coordonnées de la tondeuse ne sont pas valides : "
								+ lines.get(i));
					}
				}
			} else {
				errors.add("Les dimensions du gazon n'est pas valide  : "
						+ lines.get(0));
			}
		}

		return errors;
	}

	/* (non-Javadoc)
	 * @see fr.mowitnow.core.parser.MowerParser#loadMowers(java.util.List)
	 */
	@Override
	public List<Mower> loadMowers(List<String> lines) {
		List<Mower> mowers = new ArrayList<>();
		Pattern patternLawn = Pattern.compile(PATTERN_LAWN);		
		Matcher matcher = patternLawn.matcher(lines.get(0));
		if(matcher.find()){
			int width = Integer.parseInt(matcher.group(1));
			int height = Integer.parseInt(matcher.group(2));
			Lawn lawn = ObjectFactory.getLawn(width, height);
			for (int i = 1; i < lines.size(); i = i + 2) {
				Pattern patternMower = Pattern.compile(PATTERN_MOWER);
				Matcher matcher1 = patternMower.matcher(lines.get(i));
				if(matcher1.find()){
					int x = Integer.parseInt(matcher1.group(1));
					int y = Integer.parseInt(matcher1.group(2));
					char orientation = matcher1.group(3).charAt(0);
					if (Pattern.matches(PATTERN_ITINARY, lines.get(i + 1))) {
						Mower mower = ObjectFactory.getMower(x, y, orientation, lawn,
								lines.get(i + 1));
						mowers.add(mower);
					}
				}				
			}

		}
		
		return mowers;
	}
}
