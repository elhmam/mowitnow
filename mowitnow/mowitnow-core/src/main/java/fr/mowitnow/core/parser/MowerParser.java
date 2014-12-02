package fr.mowitnow.core.parser;

import java.util.List;

import fr.mowitnow.core.exception.MowerParserException;

import fr.mowitnow.core.model.Mower;

/**
 * Classe permettant de parser les données d'entrée.
 * @author elhmam
 */
public interface MowerParser {
    /**
     * Chargement des tondeuses.
     * @param pLines
     *            : données d'entrée.
     * @return la liste des tondeuses
     * @throws MowerParserException
     *             : Exception levée
     */
    List<Mower> loadMowers(List<String> pLines) throws MowerParserException;

}