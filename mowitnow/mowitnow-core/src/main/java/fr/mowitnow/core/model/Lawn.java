package fr.mowitnow.core.model;

/**
 * Classe représentant le champ.
 * @author elhmam
 */
public class Lawn {
    /**
     * Limite horizontale du champ.
     */
    private int xbound;
    /**
     * Limite verticale du champ.
     */
    private int ybound;

    /**
     * Constructeur de la classe Lawn.
     * @param pXbound
     *            : Limite horizontale du champ.
     * @param pYbound
     *            : Limite verticale du champ.
     */
    public Lawn(final int pXbound, final int pYbound) {
	super();
	setXbound(pXbound);
	setYbound(pYbound);
    }

    /**
     * Retourne la limite horizontale du champ.
     * @return xbound
     */
    public final int getXbound() {
	return xbound;
    }

    /**
     * Retourne la limite verticale du champ.
     * @return ybound
     */
    public final int getYbound() {
	return ybound;
    }

    /**
     * Fixe la limite horizontale du champ.
     * @param pXbound
     *            : valeur de xbound
     */
    public final void setXbound(final int pXbound) {
	if (pXbound >= 0) {
	    this.xbound = pXbound;
	} else {
	    throw new IllegalArgumentException();
	}
    }

    /**
     * Fixe la limite verticale du champ.
     * @param pYbound
     *            : valeur de ybound
     */
    public final void setYbound(final int pYbound) {
	if (pYbound >= 0) {
	    this.ybound = pYbound;
	} else {
	    throw new IllegalArgumentException();
	}
    }

    @Override
    public final String toString() {
	return String.format("Lawn [xbound=%s, ybound=%s]", xbound, ybound);
    }

}
