package fr.mowitnow.core.behvior;

import fr.mowitnow.core.model.MovementEnum;
import fr.mowitnow.core.model.Mower;

public class MowerBehaviorImpl implements MowerBehavior{	
	
	/**
	 * @see #MowerBehavior
	 */
	public void move(Mower mower, char direction){
		
		boolean movement=true;
		for (MovementEnum orientationEnum : MovementEnum.values()) {
			if (orientationEnum.getDirection() == direction
					&& orientationEnum.getOrigin() == mower.getOrientation()) {
				mower.setOrientation(orientationEnum.getDestination());
				mower.setDeltaX(orientationEnum.getDeltaX());
				mower.setDeltaY(orientationEnum.getDeltaY());
				movement=false;
				break;
			}
		}
		
		if (movement) {			
			mower.setX(mower.getX() + mower.getDeltaX());
			mower.setY(mower.getY() + mower.getDeltaY());
		}
		
	}


	
}
