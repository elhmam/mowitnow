package fr.mowitnow.core.test.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import fr.mowitnow.core.model.Lawn;

/**
 * Test de la classe Lawn.
 * @author elhmam
 */
public class LawnTest {
    /**
     * Champ.
     */
    private Lawn lawn;

    /**
     * Inialisation du test.
     */
    @Before
    public final void initTest() {
        lawn = new Lawn(3, 4);
    }

    /**
     * Teste le cas ou la limite horizontal du champ est négative.
     */
    @Test(expected = IllegalArgumentException.class)
    public final void testSetXboundException() {
        lawn.setXbound(-1);
    }

    /**
     * Teste le cas ou la limite verticale du champ est négative.
     */
    @Test(expected = IllegalArgumentException.class)
    public final void testSetYboundException() {
        lawn.setYbound(-1);
    }

    /**
     * Teste le cas ou la limite horizontal du champ est positive.
     */
    @Test
    public final void testSetXbound() {
        lawn.setXbound(10);
        assertEquals(10, lawn.getXbound());
    }

    /**
     * Teste le cas ou la limite verticale du champ est positive.
     */
    @Test
    public final void testSetYbound() {
        lawn.setYbound(10);
        assertEquals(10, lawn.getYbound());
    }

}
