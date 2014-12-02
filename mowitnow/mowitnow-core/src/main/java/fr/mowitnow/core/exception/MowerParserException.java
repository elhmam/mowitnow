package fr.mowitnow.core.exception;

/**
 * Classe Exception du parser.
 * @author elhmam
 */
public final class MowerParserException extends Exception {
    /**
     * serialVersionUID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Constructeur de la classe MowerParserException.
     * @param message
     *            : message d'erreur
     */
    public MowerParserException(String message) {
        super(message);
    }

}
