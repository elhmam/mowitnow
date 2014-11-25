package fr.mowitnow.core.app;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;

import fr.mowitnow.core.behvior.MowerBehavior;
import fr.mowitnow.core.factory.ObjectFactory;
import fr.mowitnow.core.model.Mower;
import fr.mowitnow.core.parser.MowerParser;
import fr.mowitnow.core.parser.MowerParserException;

public class App {

	public static void main(String[] args) throws IOException,
			MowerParserException {

		if (args.length == 0) {
			throw new java.lang.ArrayIndexOutOfBoundsException(
					"Il faut renseigner le nom du fichier!!!");
		}

		@SuppressWarnings("unchecked")
		List<String> lines = FileUtils.readLines(new File(args[0]));

		MowerParser mowerParser=ObjectFactory.getMowerParser();
		
		List<String> errors=mowerParser.checkData(lines);
		
		if(errors.isEmpty()){
			List<Mower> mowers = mowerParser.loadMowers(lines);
			MowerBehavior mowerBehavior = ObjectFactory.getMowerBehavior();
			for (Mower mower : mowers) {
				System.out.println("Mower Début "+ mower.toString());
				for (char c : mower.getItinerary().toCharArray()) {
					mowerBehavior.move(mower, c);
					System.out.println(c + " > " + mower.getX() + " - "
							+ mower.getY() + " - " + mower.getOrientation());
				}
				System.out.println("Mower Fin "+ mower.toString());
			}
		}else {
			for (String error : errors) {
				System.out.println(error);
			}
		}
		
		

		

		
		

	}

}
