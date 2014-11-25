package fr.mowitnow.core.app;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;

import fr.mowitnow.core.behvior.MowerBehavior;
import fr.mowitnow.core.factory.ObjectFactory;
import fr.mowitnow.core.model.Lawn;
import fr.mowitnow.core.model.Mower;
import fr.mowitnow.core.parser.FileParserException;

public class App {

	public static void main(String[] args) throws IOException,
			FileParserException {

		if (args.length == 0) {
			throw new java.lang.ArrayIndexOutOfBoundsException(
					"Il faut renseigner le nom du fichier!!!");
		}

		@SuppressWarnings("unchecked")
		List<String> lines = FileUtils.readLines(new File(args[0]));

		for (String string : lines) {
			System.out.println(string);

		}

		MowerBehavior mowerBehavior = ObjectFactory.getMowerBehavior();

		List<Mower> mowers = new ArrayList<>();

		/**
		 * TODO : Code à factoriser, il s'agit là d'un test
		 */
		// vérification du fichier
		if (lines.isEmpty()) {
			throw new FileParserException("Le fichier ne doit pas etre vide");
		} else if (lines.size() % 2 == 0) { // 1 . le nombre de ligne doit etre
											// impair
			throw new FileParserException(
					"Le nombre de lignes du fichier ne doit pas etre pair");
		} else {
			Pattern pattern = Pattern.compile("^(\\d)\\s(\\d)$");
			Matcher matcher = pattern.matcher(lines.get(0));
			if (matcher.find()) {// 2 . vérifier le format de la premiere ligne
									// Lawn
				try {
					int width = Integer.parseInt(matcher.group(1));
					int height = Integer.parseInt(matcher.group(2));
					Lawn lawn = ObjectFactory.getLawn(width, height);

					for (int i = 1; i < lines.size(); i = i + 2) {
						// 3 . vérifier les deux lignes suivantes, les
						// coordonnées du Mower et son itineraire
						Pattern pattern1 = Pattern
								.compile("^(\\d)\\s(\\d)\\s([WENS])$");
						Matcher matcher1 = pattern1.matcher(lines.get(i));
						if (matcher1.find()) {
							try {
								int x = Integer.parseInt(matcher1.group(1));
								int y = Integer.parseInt(matcher1.group(2));
								char orientation = matcher1.group(3).charAt(0);
								System.out.println("Coordonnées --> " + x + " "
										+ y + " " + orientation);
								System.out.println("Itineraire --> "
										+ lines.get(i + 1));

								if (Pattern.matches("^([GAD])+$",
										lines.get(i + 1))) {
									try {
										Mower mower = ObjectFactory.getMower(x,
												y, orientation, lawn,
												lines.get(i + 1));
										mowers.add(mower);

									} catch (Exception e) {

									}
								} else {
									throw new FileParserException(
											"L'itineraire de la tondeuse n'est pas valide : "
													+ lines.get(i + 1));
								}

							} catch (Exception e) {

							}
						} else {
							throw new FileParserException(
									"Les coordonnées de la tondeuse ne sont pas valides : "
											+ lines.get(i));
						}
					}

				} catch (NumberFormatException e) {

				}
			} else {
				throw new FileParserException(
						"Les dimensions du gazon n'est pas valide  : "
								+ lines.get(0));
			}
		}

		for (Mower mower : mowers) {
			System.out.println("Mower Début "+ mower.toString());
			for (char c : mower.getItinerary().toCharArray()) {
				mowerBehavior.move(mower, c);
				System.out.println(c + " > " + mower.getX() + " - "
						+ mower.getY() + " - " + mower.getOrientation());
			}
			System.out.println("Mower Fin "+ mower.toString());
		}

	}

}
