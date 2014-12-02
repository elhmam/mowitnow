package fr.mowitnow.core.model;

import fr.mowitnow.core.enumeration.MovementEnum;

/**
 * Classe représentant la tondeuse.
 * @author elhmam
 */
public class Mower {
    /**
     * Position horizontale de la tondeuse.
     */
    private int x;
    /**
     * Position verticale de la tondeuse.
     */
    private int y;
    /**
     * Déplacement horizontal de la tondeuse.
     */
    private int deltaX;
    /**
     * Déplacement vertical de la tondeuse.
     */
    private int deltaY;
    /**
     * Orientation de la tondeuse.
     */
    private char orientation;
    /**
     * Trajectoire de la tondeuse.
     */
    private String path;
    /**
     * Champ de déplacement de la tondeuse.
     */
    private Lawn lawn;

    /**
     * Constructeur de la classe Mower.
     * @param pX
     *            : Position horizontale de la tondeuse.
     * @param pY
     *            : Position verticale de la tondeuse.
     * @param pOrientation
     *            : Orientation de la tondeuse.
     * @param pLawn
     *            : Champ de déplacement de la tondeuse.
     * @param pPath
     *            : Trajectoire de la tondeuse.
     */
    public Mower(final int pX, final int pY, final char pOrientation,
	    final Lawn pLawn, final String pPath) {
	super();
	setLawn(pLawn);
	setOrientation(pOrientation);
	setX(pX);
	setY(pY);
	setPath(pPath);
    }

    /**
     * Retourne la position horizontale de la tondeuse.
     * @return x
     */
    public final int getX() {
	return x;
    }

    /**
     * Fixe la nouvelle position horizontale de la tondeuse. la tondeuse doit
     * etre placée à l'intérieure du champ de déplacement
     * @param pX
     *            : position horizontale de la tondeuse.
     */
    public final void setX(final int pX) {
	if (pX >= 0 && pX <= getLawn().getXbound()) {
	    this.x = pX;
	} else {
	    throw new IllegalArgumentException();
	}
    }

    /**
     * Retourne la position verticale de la tondeuse.
     * @return y
     */
    public final int getY() {
	return y;
    }

    /**
     * Fixe la nouvelle position verticale de la tondeuse. la tondeuse doit etre
     * placée à l'intérieure du champ de déplacement
     * @param pY
     *            : position verticale de la tondeuse.
     */
    public final void setY(final int pY) {
	if (pY >= 0 && pY <= getLawn().getYbound()) {
	    this.y = pY;
	} else {
	    throw new IllegalArgumentException();
	}
    }

    /**
     * Retourne l'orientation de la tondeuse de la tondeuse.
     * @return orientation
     */
    public final char getOrientation() {
	return orientation;
    }

    /**
     * Fixe la nouvelle orientation de la tondeuse, et ses déplacements.
     * @param pOrientation
     *            : orientation de la tondeuse.
     */
    public final void setOrientation(final char pOrientation) {
	this.orientation = pOrientation;
	// initialisation de l'orientation
	for (MovementEnum orientationEnum : MovementEnum.values()) {
	    if (orientationEnum.getDestination() == getOrientation()) {
		setDeltaX(orientationEnum.getDeltaX());
		setDeltaY(orientationEnum.getDeltaY());
		break;
	    }
	}
    }

    /**
     * Retourne le déplacement horizontal de la tondeuse.
     * @return deltaX
     */
    public final int getDeltaX() {
	return deltaX;
    }

    /**
     * Fixe le déplacement horizontal de la tondeuse.
     * @param pDeltaX
     *            : déplacement horizontal de la tondeuse.
     */
    public final void setDeltaX(final int pDeltaX) {
	this.deltaX = pDeltaX;
    }

    /**
     * Retourne le déplacement vertical de la tondeuse.
     * @return deltaY
     */
    public final int getDeltaY() {
	return deltaY;
    }

    /**
     * Fixe le déplacement vertical de la tondeuse.
     * @param pDeltaY
     *            : déplacement vertical de la tondeuse.
     */
    public final void setDeltaY(final int pDeltaY) {
	this.deltaY = pDeltaY;
    }

    /**
     * Retourne le champ de déplacement de la tondeuse.
     * @return lawn
     */
    public final Lawn getLawn() {
	return lawn;
    }

    /**
     * Fixe le champ de déplacement de la tondeuse.
     * @param pLawn
     *            : champ de déplacement de la tondeuse.
     */
    public final void setLawn(final Lawn pLawn) {
	this.lawn = pLawn;
    }

    /**
     * Retourne la trajectoire de la tondeuse.
     * @return path
     */
    public final String getPath() {
	return path;
    }

    /**
     * Fixe la trajectoire de déplacement de la tondeuse.
     * @param pPath
     *            : trajectoire de déplacement de la tondeuse.
     */
    public final void setPath(final String pPath) {
	this.path = pPath;
    }

    @Override
    public final String toString() {
	return String.format("Mower [x=%s, y=%s, deltaX=%s, deltaY=%s, "
		+ "orientation=%s, lawn=%s, path=%s]", x, y, deltaX, deltaY,
		orientation, lawn, path);
    }

}
