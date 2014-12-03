package fr.mowitnow.web.test.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import fr.mowitnow.core.behvior.MowerBehavior;
import fr.mowitnow.core.exception.MowerParserException;
import fr.mowitnow.core.model.Lawn;
import fr.mowitnow.core.model.Mower;
import fr.mowitnow.core.parser.MowerParser;
import fr.mowitnow.web.controller.MowerController;
/**
 * Classe Test du controleur.
 * @author elhmam
 */
public class MowerControllerTest {
    /**
     * Objet MockMvc.
     */
    private MockMvc mockMvc;
    /**
     * Objet mock de la classe MowerController.
     */
    @InjectMocks
    private MowerController mowerController;
    /**
     * Objet mock de la classe MowerParser.
     */
    @Mock
    private MowerParser mowerParser;
    /**
     * Objet mock de la classe MowerBehavior.
     */
    @Mock
    private MowerBehavior mowerBehavior;
    /**
     * Liste des instructions.
     */
    private static final StringBuilder INSTRUCTIONS_LST = new StringBuilder()
    .append("5 5\n").append("1 2 N\n").append("GA\n").append("3 3 E\n")
    .append("AA\n");
    /**
     * Liste erronées des instructions.
     */
    private static final StringBuilder INSTRUCTIONS_LST_ERR = new StringBuilder()
    .append("5 U\n").append("1 2 N\n").append("GA\n").append("3 3 E\n")
    .append("AA\n");
    /**
     * Limite verticale du champ.
     */
    private static final String YLIMIT = "ylimit";
    /**
     * Limite horizontale du champ.
     */
    private static final String XLIMIT = "xlimit";
    /**
     * Message d'erreur.
     */
    private static final String ERROR = "error";
    /**
     * Sert à afficher la trajectoire de la tondeuse par étape Map <id du
     * tondeuse - liste tondeuses>.
     */
    private static final String MOWERS_MAP = "mowersMap";
    /**
     * Liste des instructions.
     */
    private static final String INSTRUCTIONS = "instructions";
    /**
     * Page du formulaire.
     */
    private static final String HOME = "home";
    /**
     * Initialisation du test.
     * @throws MowerParserException : MowerParserException
     */
    @Before
    public void setup() throws MowerParserException {
	MockitoAnnotations.initMocks(this);

	mockMvc = MockMvcBuilders.standaloneSetup(mowerController).build();

	Lawn lawn = new Lawn(5, 5);
	Mower mower1 = new Mower(1, 2, 'N', lawn, "GA");
	Mower mower2 = new Mower(3, 3, 'E', lawn, "AA");
	List<Mower> mowers = Arrays.asList(mower1, mower2);

	List<String> instructionLst = mowerController
		.getListFromInstructionString(INSTRUCTIONS_LST.toString());
	when(mowerParser.loadMowers(instructionLst)).thenReturn(mowers);

	List<String> instructionLstErr = mowerController
		.getListFromInstructionString(INSTRUCTIONS_LST_ERR.toString());
	when(mowerParser.loadMowers(instructionLstErr)).thenThrow(
		new MowerParserException("error"));

	Mower mower3 = new Mower(1, 2, 'W', lawn, "GA");
	Mower mower4 = new Mower(0, 2, 'W', lawn, "GA");
	Mower mower5 = new Mower(4, 3, 'E', lawn, "AA");
	Mower mower6 = new Mower(5, 3, 'E', lawn, "AA");

	when(mowerBehavior.execute(mower1, 'G')).thenReturn(mower3);
	when(mowerBehavior.execute(mower3, 'A')).thenReturn(mower4);
	when(mowerBehavior.execute(mower2, 'G')).thenReturn(mower5);
	when(mowerBehavior.execute(mower5, 'A')).thenReturn(mower6);

    }

    /**
     * Teste le traitement de la requete post.
     * @throws Exception
     *             : exception
     */
    @Test
    public void testMowitnowSubmit() throws Exception {
	this.mockMvc
		.perform(
			post("/").param(INSTRUCTIONS,
				INSTRUCTIONS_LST.toString()))
		.andExpect(status().isOk()).andExpect(view().name(HOME))
		.andExpect(model().attributeExists(INSTRUCTIONS))
		.andExpect(model().attributeExists(XLIMIT))
		.andExpect(model().attributeExists(YLIMIT))
		.andExpect(model().attributeExists(MOWERS_MAP));
    }

    /**
     * Teste le traitement de la requete post levant une erreur.
     * @throws Exception
     *             : exception
     */
    @Test
    public void testMowitnowSubmitErr() throws Exception {
	this.mockMvc
		.perform(
			post("/").param(INSTRUCTIONS,
				INSTRUCTIONS_LST_ERR.toString()))
		.andExpect(status().isOk()).andExpect(view().name(HOME))
		.andExpect(model().attributeExists(ERROR));
    }

    /**
     * Teste le traitement de la requete get.
     * @throws Exception
     *             : exception
     */
    @Test
    public void testMowitnowForm() throws Exception {
	this.mockMvc.perform(get("/")).andExpect(status().isOk())
		.andExpect(view().name(HOME));
    }

}
