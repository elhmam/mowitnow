package fr.mowitnow.core.enumeration;

/**
 * Enumération des déplacements de la tondeuse.
 * @author elhmam
 */
public enum MovementEnum {
    /**
     * Déplacement nord-est.
     */
    NORTH_EST('D', 'N', 'E', 1, 0),
    /**
     * Déplacement nord-ouest.
     */
    NORTH_WEST('G', 'N', 'W', -1, 0),
    /**
     * Déplacement sud-ouest.
     */
    SOUTH_WEST('D', 'S', 'W', -1, 0),
    /**
     * Déplacement sud-est.
     */
    SOUTH_EST('G', 'S', 'E', 1, 0),
    /**
     * Déplacement ouest-nord.
     */
    WEST_NORTH('D', 'W', 'N', 0, 1),
    /**
     * Déplacement ouest-sud.
     */
    WEST_SOUTH('G', 'W', 'S', 0, -1),
    /**
     * Déplacement est-nord.
     */
    EST_NORTH('D', 'E', 'S', 0, -1),
    /**
     * Déplacement est-sud.
     */
    EST_SOUTH('G', 'E', 'N', 0, 1);
    /**
     * direction de la tondeuse.
     */
    private char direction;
    /**
     * l'actuelle orientation de la tondeuse.
     */
    private char orientation;
    /**
     * la nouvelle orientation de la tondeuse.
     */
    private char newOrientation;
    /**
     * déplacement en horizental.
     */
    private int deltaX;
    /**
     * déplacement en vertical.
     */
    private int deltaY;

    /**
     * Constructeur de l'enumértion.
     * @param pDirection
     *            : direction
     * @param pOrientation
     *            : orientation
     * @param pNewOrientation
     *            : nouvelle orientation
     * @param pDeltaX
     *            : déplacement horizontal
     * @param pDeltaY
     *            : déplacement vertical
     */
    private MovementEnum(char pDirection, char pOrientation,
            char pNewOrientation, int pDeltaX, int pDeltaY) {
        this.direction = pDirection;
        this.orientation = pOrientation;
        this.newOrientation = pNewOrientation;
        this.deltaX = pDeltaX;
        this.deltaY = pDeltaY;
    }

    /**
     * Retourne la direction.
     * @return direction
     */
    public char getDirection() {
        return direction;
    }

    /**
     * Retourne l'actuelle orientation.
     * @return orientation
     */
    public char getOrigin() {
        return orientation;
    }

    /**
     * Retourne la nouvelle orientation.
     * @return newOrientation
     */
    public char getDestination() {
        return newOrientation;
    }

    /**
     * Retourne le déplacement horizontal.
     * @return deltaX
     */
    public int getDeltaX() {
        return deltaX;
    }

    /**
     * Retourne le déplacement vertical.
     * @return deltaY
     */
    public int getDeltaY() {
        return deltaY;
    }

}
