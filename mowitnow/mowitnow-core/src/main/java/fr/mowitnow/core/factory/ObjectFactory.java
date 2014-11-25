package fr.mowitnow.core.factory;

import fr.mowitnow.core.behvior.MowerBehavior;
import fr.mowitnow.core.behvior.MowerBehaviorImpl;
import fr.mowitnow.core.model.Lawn;
import fr.mowitnow.core.model.Mower;
import fr.mowitnow.core.parser.MowerParser;
import fr.mowitnow.core.parser.MowerParserImpl;

public class ObjectFactory {
	
	private static MowerBehavior mowerBehavior;
	
	private static Lawn lawn;
	
	private static MowerParser mowerParser; 

	/**
	 * Prototype Mower 
	 * @param orientation : orientation de la tondeuse 
	 * @param x : position horizentale 
	 * @param y : position verticale 
	 * @param lawn : la grille
	 * @param itinerary : l'intineraire 
	 * @return une nouvelle instance de l'objet Mower
	 */
	public static Mower getMower(int x, int y, char orientation,Lawn lawn,String itinerary) {		
		return new Mower(x,  y,  orientation,lawn,itinerary);
	}
	/**
	 * Singleton Lawn 
	 * @return la meme instance de l'objet MowerBehavior
	 */
	public static Lawn getLawn(int xbound, int ybound) {
		if (lawn == null) {
			synchronized (Lawn.class) {
				if (lawn == null) {
					lawn = new Lawn(xbound,ybound);
				}
			}
		}
		return lawn;
	}
	/**
	 * Singleton MowerBehaviour 
	 * @return la meme instance de l'objet MowerBehavior
	 */
	public static MowerBehavior getMowerBehavior() {
		if (mowerBehavior == null) {
			synchronized (MowerBehavior.class) {
				if (mowerBehavior == null) {
					mowerBehavior = new MowerBehaviorImpl();
				}
			}
		}
		return mowerBehavior;
	}
	/**
	 * Singleton MowerParser 
	 * @return la meme instance de l'objet MowerParser
	 */
	public static MowerParser getMowerParser() {
		if (mowerParser == null) {
			synchronized (MowerParser.class) {
				if (mowerParser == null) {
					mowerParser = new MowerParserImpl();
				}
			}
		}
		return mowerParser;
	}

}
