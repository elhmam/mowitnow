package fr.mowitnow.core.model;

public enum MovementEnum {
	NORTH_EST('D', 'N', 'E', 1, 0), NORTH_WEST('G', 'N', 'W', -1, 0), SOUTH_WEST(
			'D', 'S', 'W', -1, 0), SOUTH_EST('G', 'S', 'E', 1, 0), WEST_NORTH(
			'D', 'W', 'N', 0, 1), WEST_SOUTH('G', 'W', 'S', 0, -1), EST_NORTH(
			'D', 'E', 'S', 0, -1), EST_SOUTH('G', 'E', 'N', 0, 1);
	/**
	 * direction de la tondeuse
	 */
	char direction;
	/**
	 * l'actuelle orientation de la tondeuse
	 */
	char orientation;
	/**
	 * la nouvelle orientation de la tondeuse
	 */
	char newOrientation;
	/**
	 * déplacement en horizental
	 */
	int deltaX;
	/**
	 * déplacement en vertical
	 */
	int deltaY;

	private MovementEnum(char direction, char orientation, char newOrientation,
			int deltaX, int deltaY) {
		this.direction = direction;
		this.orientation = orientation;
		this.newOrientation = newOrientation;
		this.deltaX = deltaX;
		this.deltaY = deltaY;
	}

	public char getDirection() {
		return direction;
	}

	public char getOrigin() {
		return orientation;
	}

	public char getDestination() {
		return newOrientation;
	}

	public int getDeltaX() {
		return deltaX;
	}

	public int getDeltaY() {
		return deltaY;
	}

}
