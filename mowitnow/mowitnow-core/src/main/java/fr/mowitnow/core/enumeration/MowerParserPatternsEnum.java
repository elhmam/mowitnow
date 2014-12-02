package fr.mowitnow.core.enumeration;

/**
 * Enumération des patterns utilisés par le parser.
 * @author elhmam
 */
public enum MowerParserPatternsEnum {
    /**
     * Pattern du trajectoire.
     */
    PATTERN_ITINERARY("^([GAD])+$"),
    /**
     * Pattern du champ.
     */
    PATTERN_LAWN("^(\\d+)\\s(\\d+)$"),
    /**
     * Pattern du tondeuse.
     */
    PATTERN_MOWER("^(\\d+)\\s(\\d+)\\s([WENS])$");

    /**
     * Valeur de l'enumération.
     */
    private String value;

    /**
     * Constructeur de l'enumération.
     * @param pVal
     *            : Valeur de l'enumération
     */
    MowerParserPatternsEnum(String pVal) {
        this.value = pVal;
    }

    /**
     * Retourne la valeur de l'enumération.
     * @return value
     */
    public String getValue() {
        return value;
    }

}
