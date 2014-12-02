package fr.mowitnow.core.test.app;

import org.junit.Before;
import org.junit.Test;

import fr.mowitnow.core.app.App;

/**
 * Test de la classe principale.
 * @author elhmam
 */
public class AppTest {

    /**
     * Arguments à passer au lancement du programme.
     */
    private String[] args;

    /**
     * Initialisation du test.
     */
    @Before
    public void initTest() {
        args = new String[0];
    }

    /**
     * Teste le cas ou aucun paramétre n'est passé au programme.
     */
    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testNullParameterMain() {
        App.main(args);
    }

    /**
     * Teste le cas ou une chaine vide est passé au programme.
     */
    @Test
    public void testEmptyParameterMain() {
        args = new String[1];
        args[0] = "";
        App.main(args);
    }

    /**
     * Teste le cas ou le fichier envoyé au programme n'existe pas.
     */
    @Test
    public void testFileIsNotExistMain() {
        args = new String[1];
        args[0] = "instruction";
        App.main(args);
    }

    /**
     * Teste le cas ou le fichier envoyé au programme existe.
     */
    @Test
    public void testFileExistMain() {
        args = new String[1];
        args[0] = "instructions.txt";
        App.main(args);
    }

}
