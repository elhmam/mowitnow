package fr.mowitnow.core.test.model;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import fr.mowitnow.core.model.Lawn;
import fr.mowitnow.core.model.Mower;

/**
 * Test de la classe Mower.
 * 
 * @author elhmam
 */
public class MowerTest {
    /**
     * Tondeuse.
     */
    private Mower mower;

    /**
     * Inialisation du test.
     */
    @Before
    public final void initTest() {
        Lawn lawn = new Lawn(3, 4);
        mower = new Mower(1, 2, 'N', lawn, "GAGAGAGAA");
    }

    /**
     * Teste si les déplacements sont conformes à l'orientation de la tondeuse.
     */
    @Test
    public final void testMowerSetOrientation() {
        assertEquals(mower.getDeltaX(), 0);
        assertEquals(mower.getDeltaY(), 1);
        mower.setOrientation('E');
        assertEquals(mower.getDeltaX(), 1);
        assertEquals(mower.getDeltaY(), 0);
        mower.setOrientation('W');
        assertEquals(mower.getDeltaX(), -1);
        assertEquals(mower.getDeltaY(), 0);
        mower.setOrientation('S');
        assertEquals(mower.getDeltaX(), 0);
        assertEquals(mower.getDeltaY(), -1);
    }

    /**
     * Teste le cas ou la position verticale de la tondeuse est à l'intérieur du
     * champ.
     */
    @Test
    public final void testSetY() {
        mower.setY(4);
        assertEquals(mower.getY(), 4);
        mower.setY(1);
        assertEquals(mower.getY(), 1);
    }

    /**
     * Teste le cas ou la position verticale de la tondeuse est à l'extérieur du
     * champ.
     */
    @Test(expected = IllegalArgumentException.class)
    public final void testSetYSupException() {
        mower.setY(5);
    }

    /**
     * Teste le cas ou la position verticale de la tondeuse est à l'extérieur du
     * champ.
     */
    @Test(expected = IllegalArgumentException.class)
    public final void testSetYInfException() {
        mower.setY(-1);
    }

    /**
     * Teste le cas ou la position horizontale de la tondeuse est à l'intérieur
     * du champ.
     */
    @Test
    public final void testSetX() {
        mower.setX(3);
        assertEquals(mower.getX(), 3);
        mower.setX(2);
        assertEquals(mower.getX(), 2);
    }

    /**
     * Teste le cas ou la position horizontale de la tondeuse est à l'extérieur
     * du champ.
     */
    @Test(expected = IllegalArgumentException.class)
    public final void testSetXSupException() {
        mower.setX(5);
    }

    /**
     * Teste le cas ou la position horizontale de la tondeuse est à l'extérieur
     * du champ.
     */
    @Test(expected = IllegalArgumentException.class)
    public final void testSetXInfException() {
        mower.setX(-1);
    }

}
