package fr.mowitnow.core.parser;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import fr.mowitnow.core.exception.MowerParserException;
import fr.mowitnow.core.enumeration.MowerParserPatternsEnum;
import fr.mowitnow.core.model.Lawn;
import fr.mowitnow.core.model.Mower;

/**
 * Implementation de la classe MowerParser.
 * 
 * @author elhmam
 */
public class MowerParserImpl implements MowerParser {
    /**
     * Aucune données n'est fournie.
     */
    private static final String EMPTY_DATA = "Aucune donnée n'est fournie";
    /**
     * Le nombre de ligne n'est pas valide.
     */
    private static final String LINE_NUMBER_NOT_VALID = "Le nombre de lignes du ne doit pas etre pair";
    /**
     * Les dimensions de la tondeuse ne sont pas valides.
     */
    private static final String LAWN_DIM_NOT_VALID = "Ligne : 1 > Les dimensions du champ ne sont pas valides : %s";
    /**
     * Les coordonnées de la tondeuse ne sont pas complétes.
     */
    private static final String MOWER_COORDS_NOT_VALID = "Ligne : %s "
            + " > Les coordonnées de la tondeuse ne sont"
            + " pas valides : %s ";
    /**
     * Les coordonnées de la tondeuse ne sont pas valides.
     */
    private static final String MOWER_POSITION_NOT_VALID = "Ligne : %s  > La tondeuse ne peut etre placée à "
            + "l'exterieur du champ : %s";
    /**
     * La trajectoire de la tondeuse n'est pas valide.
     */
    private static final String ITINERARY_NOT_VALID = "Ligne : %s > La trajectoire de la tondeuse "
            + "n'est pas valide : %s ";
    /**
     * Pour vérifier si le nombre de ligne est pair.
     */
    private static final int MAGIC_TWO = 2;
    /**
     * Index de l'orientation.
     */
    private static final int ORIENTATION_INDEX = 3;

    /*
     * (non-Javadoc)
     * @see fr.mowitnow.core.parser.MowerParser#loadMowers(java.util.List)
     */
    @Override
    public List<Mower> loadMowers(List<String> pLines)
            throws MowerParserException {
        if (pLines.isEmpty()) {
            // la liste d'entrée ne doit pas etre vide
            throw new MowerParserException(EMPTY_DATA);
        } else if (pLines.size() % MAGIC_TWO == 0) {
            // le nombre de ligne doit etre impair
            throw new MowerParserException(LINE_NUMBER_NOT_VALID);
        } else {
            // création du champ
            Lawn lawn = createLawn(pLines.get(0));

            // création des tondeuses
            List<Mower> mowers = new ArrayList<>();

            for (int i = 1; i < pLines.size(); i = i + MAGIC_TWO) {
                Mower mower = createMower(pLines.get(i), pLines.get(i + 1),
                        lawn, i);
                if (mower != null) {
                    mowers.add(mower);
                }
            }
            return mowers;
        }

    }

    /**
     * Crée la tondeuse.
     * @param pCoords
     *            : coordonnées de la tondeuse
     * @param pPath
     *            : trajectoire de la tondeuse
     * @param pLawn
     *            : objet champ
     * @param pLine
     *            : l'index de la ligne
     * @throws MowerParserException
     *             : Exception levée
     * @return objet tondeuse
     */
    private Mower createMower(String pCoords, String pPath, Lawn pLawn,
            int pLine) throws MowerParserException {

        Matcher matcher1 = getMatcher(pCoords,
                MowerParserPatternsEnum.PATTERN_MOWER.getValue());
        if (matcher1.find()) {
            int x = Integer.parseInt(matcher1.group(1));
            int y = Integer.parseInt(matcher1.group(MAGIC_TWO));
            if (x <= pLawn.getXbound() && y <= pLawn.getYbound()) {
                char orientation = matcher1.group(ORIENTATION_INDEX).charAt(0);
                if (Pattern.matches(
                        MowerParserPatternsEnum.PATTERN_ITINERARY.getValue(),
                        pPath)) {
                    return new Mower(x, y, orientation, pLawn, pPath);
                } else {
                    throw new MowerParserException(String.format(
                            ITINERARY_NOT_VALID, pLine + MAGIC_TWO, pPath));
                }
            } else {
                throw new MowerParserException(String.format(
                        MOWER_POSITION_NOT_VALID, pLine + 1, pCoords));
            }
        } else {
            throw new MowerParserException(String.format(
                    MOWER_COORDS_NOT_VALID, pLine + 1, pCoords));
        }

    }

    /**
     * Crée le champ.
     * @param pLine
     *            : la ligne à traiter
     * @throws MowerParserException
     *             : Exception levée
     * @return objet champ
     */
    private Lawn createLawn(String pLine) throws MowerParserException {
        // Créer le champ

        Matcher matcher = getMatcher(pLine,
                MowerParserPatternsEnum.PATTERN_LAWN.getValue());
        if (matcher.find()) {
            int xbound = Integer.parseInt(matcher.group(1));
            int ybound = Integer.parseInt(matcher.group(MAGIC_TWO));
            return new Lawn(xbound, ybound);
        } else {
            throw new MowerParserException(String.format(LAWN_DIM_NOT_VALID,
                    pLine));

        }

    }

    /**
     * Retourne Matcher du text passé en paramétre.
     * @param pStr
     *            : text à matcher
     * @param pPattern
     *            : le pattern à respecter
     * @return Matcher
     */
    private Matcher getMatcher(String pStr, String pPattern) {
        Pattern patternMower = Pattern.compile(pPattern);
        return patternMower.matcher(pStr);
    }
}
