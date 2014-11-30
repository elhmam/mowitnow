package fr.mowitnow.core.parser;

import java.util.List;

import fr.mowitnow.core.model.Mower;

public interface MowerParser {

	/**
	 * Chargement des tondeuses
	 * 
	 * @param lines : les lignes de données
	 * @return la liste des tondeuses
	 * @throws MowerParserException 
	 */
	public abstract List<Mower> loadMowers(List<String> lines) throws MowerParserException;

}