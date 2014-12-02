package fr.mowitnow.core.model;

/**
 * Classe représentant le champ.
 * 
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
     * 
     * @param pXbound
     *            : Limite horizontale du champ.
     * @param pYbound
     *            : Limite verticale du champ.
     */
    public Lawn(int pXbound, int pYbound) {
        super();
        setXbound(pXbound);
        setYbound(pYbound);
    }

    /**
     * Retourne la limite horizontale du champ.
     * @return xbound
     */
    public int getXbound() {
        return xbound;
    }

    /**
     * Retourne la limite verticale du champ.
     * @return ybound
     */
    public int getYbound() {
        return ybound;
    }

    /**
     * Fixe la limite horizontale du champ.
     * @param pXbound
     *            : valeur de xbound
     */
    public void setXbound(int pXbound) {
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
    public void setYbound(int pYbound) {
        if (pYbound >= 0) {
            this.ybound = pYbound;
        } else {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public String toString() {
        return String.format("Lawn [xbound=%s, ybound=%s]", xbound, ybound);
    }

}
