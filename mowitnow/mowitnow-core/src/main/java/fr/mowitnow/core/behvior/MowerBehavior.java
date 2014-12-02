package fr.mowitnow.core.behvior;

import fr.mowitnow.core.model.Mower;

/**
 * Interface comportement de la tondeuse.
 * @author elhmam
 */
public interface MowerBehavior {
    /**
     * Déplacement d'une tondeuse dans son champ.
     * @param pMower
     *            : tondeuse
     * @param pDirection
     *            : direction
     */
    void move(Mower pMower, char pDirection);

}
