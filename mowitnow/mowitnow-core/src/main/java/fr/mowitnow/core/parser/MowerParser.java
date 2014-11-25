package fr.mowitnow.core.parser;

import java.util.List;

import fr.mowitnow.core.model.Mower;

public interface MowerParser {

	/**
	 * Valider les données
	 * 
	 * @param lines : les lignes de données
	 * @return la liste des erreurs
	 */
	public abstract List<String> checkData(List<String> lines);

	/**
	 * Chargement des tondeuses
	 * 
	 * @param lines : les lignes de données
	 * @return la liste des tondeuses
	 */
	public abstract List<Mower> loadMowers(List<String> lines);

}