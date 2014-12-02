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
     *            : Tondeuse
     * @param pDirection
     *            : Direction
     * @return Mower : Etat final de la tondeuse
     */
    Mower execute(Mower pMower, char pDirection);

}
