package fr.mowitnow.core.behvior;

import fr.mowitnow.core.enumeration.MovementEnum;
import fr.mowitnow.core.model.Mower;

/**
 * Implementation de l'interface comportement de la tondeuse.
 * @author elhmam
 */
public class MowerBehaviorImpl implements MowerBehavior {

    /*
     * (non-Javadoc)
     * @see
     * fr.mowitnow.core.behvior.MowerBehavior#move(fr.mowitnow.core.model.Mower
     * ,char)
     */
    @Override
    public Mower execute(Mower pMower, char pDirection) {

        boolean movement = true;
        for (MovementEnum orientationEnum : MovementEnum.values()) {
            if (orientationEnum.getDirection() == pDirection
                    && orientationEnum.getOrigin() == pMower.getOrientation()) {
                pMower.setOrientation(orientationEnum.getDestination());
                pMower.setDeltaX(orientationEnum.getDeltaX());
                pMower.setDeltaY(orientationEnum.getDeltaY());
                movement = false;
                break;
            }
        }

        if (movement) {
            pMower.setX(pMower.getX() + pMower.getDeltaX());
            pMower.setY(pMower.getY() + pMower.getDeltaY());
        }

        return pMower;
    }

}
