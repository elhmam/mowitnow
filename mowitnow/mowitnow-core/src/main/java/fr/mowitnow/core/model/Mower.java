package fr.mowitnow.core.model;

public class Mower {

	private int x;
	private int y;
	private int deltaX;
	private int deltaY;
	private char orientation;
	private String itinerary;
	private Lawn lawn;

	public Mower(int x, int y, char orientation, Lawn lawn, String itinerary) {
		super();
		this.x = x;
		this.y = y;
		setOrientation(orientation);
		this.lawn = lawn;
		this.itinerary = itinerary;

	}

	public int getX() {
		return x;
	}

	public void setX(int nx) {
		this.x = ((nx < 0 || nx > getLawn().getXbound()) ? getX() : nx);
	}

	public int getY() {
		return y;
	}

	public void setY(int ny) {
		this.y = ((ny < 0 || ny > getLawn().getYbound()) ? getY() : ny);
	}

	public char getOrientation() {
		return orientation;
	}

	public void setOrientation(char orientation) {
		this.orientation = orientation;
		// initialisation de l'orientation
		for (MovementEnum orientationEnum : MovementEnum.values()) {
			if (orientationEnum.getDestination() == getOrientation()) {
				setDeltaX(orientationEnum.getDeltaX());
				setDeltaY(orientationEnum.getDeltaY());
				break;
			}
		}
	}

	public int getDeltaX() {
		return deltaX;
	}

	public void setDeltaX(int deltaX) {
		this.deltaX = deltaX;
	}

	public int getDeltaY() {
		return deltaY;
	}

	public void setDeltaY(int deltaY) {
		this.deltaY = deltaY;
	}

	public Lawn getLawn() {
		return lawn;
	}

	public void setLawn(Lawn lawn) {
		this.lawn = lawn;
	}

	public String getItinerary() {
		return itinerary;
	}

	public void setItinerary(String itinerary) {
		this.itinerary = itinerary;
	}

	@Override
	public String toString() {
		return String
				.format("Mower [x=%s, y=%s, deltaX=%s, deltaY=%s, orientation=%s, lawn=%s, itinerary=%s]",
						x, y, deltaX, deltaY, orientation, lawn, itinerary);
	}

}
