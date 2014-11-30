package fr.mowitnow.core.parser;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

import org.junit.Before;
import org.junit.Test;

/**
 * Test de la classe MowerParserImpl
 * 
 * @author elhmam
 */
public class MowerParserImplTest implements MowerParserPatterns {

	MowerParser mowerParser;

	/**
	 * Initialisation du test
	 * 
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		mowerParser = new MowerParserImpl();
	}

	/**
	 * Teste si il n'y a aucune données fournies
	 * 
	 * @throws MowerParserException
	 */
	@Test(expected = MowerParserException.class)
	public final void testEmptyData() throws MowerParserException {
		List<String> emptyLines = new ArrayList<String>();
		mowerParser.loadMowers(emptyLines);
	}

	/**
	 * Teste si le nombre de lignes est valide
	 * 
	 * @throws MowerParserException
	 */
	@Test(expected = MowerParserException.class)
	public final void testInvalidLinesNumber() throws MowerParserException {
		List<String> instructions = Arrays.asList("5 5", "1 2 N", "3 3 E",
				"AADAADADDA");
		mowerParser.loadMowers(instructions);
	}

	/**
	 * Teste le format de la première ligne, dimensions du gazon
	 * 
	 * @throws MowerParserException
	 */
	@Test(expected = MowerParserException.class)
	public final void testInvalidLawnSize() throws MowerParserException {
		List<String> instructions = Arrays.asList(" 5", "1 2 N", "GAGAGAGAA",
				"3 3 E", "AADAADADDA");
		mowerParser.loadMowers(instructions);
	}

	/**
	 * Teste le format de la ligne coordonnées de la tondeuse
	 * 
	 * @throws MowerParserException
	 */
	@Test(expected = MowerParserException.class)
	public final void testMowerCoords() throws MowerParserException {
		List<String> instructions = Arrays.asList("5 5", "1 2 ", "GAGAGAGAA",
				"3 3 E", "AADAADADDA");
		mowerParser.loadMowers(instructions);
	}

	/**
	 * Teste la position de la tondeuse qui doit etre à l'interieur du gazon
	 * 
	 * @throws MowerParserException
	 */
	@Test(expected = MowerParserException.class)
	public final void testMowerPosition() throws MowerParserException {
		List<String> instructions = Arrays.asList("5 5", "1 2 ", "GAGAGAGAA",
				"6 3 E", "AADAADADDA");
		mowerParser.loadMowers(instructions);
	}

	/**
	 * Teste le format de la ligne instructions de la tondeuse
	 * 
	 * @throws MowerParserException
	 */
	@Test(expected = MowerParserException.class)
	public final void testMowerInstructions() throws MowerParserException {
		List<String> instructions = Arrays.asList("5 5", "1 2 N", "GAGAGOGAA",
				"3 3 E", "AADAADADDA");
		mowerParser.loadMowers(instructions);
	}

	/**
	 * Teste l'expression regulière PATTERN_LAWN
	 * 
	 * @throws MowerParserException
	 */
	@Test
	public final void testLawnPattern() {
		Pattern patternMower = Pattern.compile(PATTERN_LAWN);
		assertFalse(patternMower.matcher("5(").find());
		assertFalse(patternMower.matcher("55").find());
		assertFalse(patternMower.matcher("5E").find());
		assertFalse(patternMower.matcher("5 !").find());
		assertFalse(patternMower.matcher("EE").find());
		assertFalse(patternMower.matcher("5/6").find());
		assertFalse(patternMower.matcher("?55").find());
		assertTrue(patternMower.matcher("5 15").find());
		assertTrue(patternMower.matcher("5 5").find());
		assertFalse(patternMower.matcher("0 -1").find());
	}

	/**
	 * Teste l'expression regulière PATTERN_MOWER
	 * 
	 * @throws MowerParserException
	 */
	@Test
	public final void testMowerPattern() {
		Pattern patternMower = Pattern.compile(PATTERN_MOWER);
		assertFalse(patternMower.matcher("1 2 3").find());
		assertFalse(patternMower.matcher("55").find());
		assertFalse(patternMower.matcher("7ZZ").find());
		assertFalse(patternMower.matcher("5 !").find());
		assertFalse(patternMower.matcher("EE").find());
		assertFalse(patternMower.matcher("5/6").find());
		assertFalse(patternMower.matcher("1 8 Y").find());
		assertTrue(patternMower.matcher("1 12 N").find());
		assertTrue(patternMower.matcher("7 8 N").find());
		assertFalse(patternMower.matcher("0 -1").find());
	}

	/**
	 * Teste l'expression regulière PATTERN_INSTRUCTIONS
	 * 
	 * @throws MowerParserException
	 */
	@Test
	public final void testMowerInstructionPattern() {
		Pattern patternMower = Pattern.compile(PATTERN_INSTRUCTIONS);
		assertFalse(patternMower.matcher("1 2 3").find());
		assertFalse(patternMower.matcher("GADFGHJK").find());
		assertFalse(patternMower.matcher("GAADAAAGH").find());
		assertFalse(patternMower.matcher("5 !").find());
		assertFalse(patternMower.matcher("EE").find());
		assertFalse(patternMower.matcher("5/6").find());
		assertFalse(patternMower.matcher("1 8 Y").find());
		assertTrue(patternMower.matcher("GAAADAAG").find());
		assertTrue(patternMower.matcher("AAAGDDDAG").find());
		assertFalse(patternMower.matcher("AGGGGEDGGGG").find());
	}
	
	/**
	 * Teste le chargement des tondeuses
	 * 
	 * @throws MowerParserException 
	 */
	@Test
	public final void testCreateMower() throws MowerParserException {
		List<String> instructions = Arrays.asList("5 5", "1 2 N", "GAGAGDGAA",
				"3 3 E", "AADAADADDA");
		mowerParser.loadMowers(instructions);
	}

	/**
	 * Teste le chargement des tondeuses hors gazon
	 * 
	 * @throws MowerParserException 
	 */
	@Test(expected = MowerParserException.class)
	public final void testCreateMowerOutOfLawn() throws MowerParserException {
		List<String> instructions = Arrays.asList("5 5", "6 6 N", "GAGAGDGAA",
				"3 3 E", "AADAADADDA");
		mowerParser.loadMowers(instructions);
	}
	
	/**
	 * Teste le chargement des tondeuses dont la trajectoire n'est pas valide
	 * 
	 * @throws MowerParserException 
	 */
	@Test(expected = MowerParserException.class)
	public final void testCreateMowerWithInvalidItinary() throws MowerParserException {
		List<String> instructions = Arrays.asList("R 5", "6 6 N", "GAGAGDGAU",
				"3 3 E", "AADAADADDA");
		mowerParser.loadMowers(instructions);
	}
	
	
	
	
	
}
