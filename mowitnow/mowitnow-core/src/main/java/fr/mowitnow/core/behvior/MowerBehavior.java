package fr.mowitnow.core.behvior;

import fr.mowitnow.core.model.Mower;

public interface MowerBehavior {
	
	/**
	 * Déplacement d'une tondeuse
	 * @param mower
	 * @param direction
	 * @return l'etat de la tondeuse apres le movement
	 */
	void move(Mower mower, char direction);


	
}
