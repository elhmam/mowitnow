package fr.mowitnow.core.behvior;

import fr.mowitnow.core.model.Mower;

public interface MowerBehavior {
	
	/**
	 * Déplacement d'une tondeuse
	 * @param mower
	 * @param direction
	 */
	void move(Mower mower, char direction);


	
}
