package fr.mowitnow.core.test.behvior;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import fr.mowitnow.core.behvior.MowerBehavior;
import fr.mowitnow.core.behvior.MowerBehaviorImpl;
import fr.mowitnow.core.model.Lawn;
import fr.mowitnow.core.model.Mower;

/**
 * Test de la classe MowerBehaviorImpl.
 * @author elhmam
 */
public class MowerBehaviorImplTest {
    /**
     * Champ.
     */
    private Lawn lawn;
    /**
     * Tondeuse.
     */
    private Mower mower;
    /**
     * Comportement de la tondeuse.
     */
    private MowerBehavior mowerBehavior;

    /**
     * Initialisation du test.
     */
    @Before
    public void setUp() {
        lawn = new Lawn(5, 5);
        mower = new Mower(1, 2, 'N', lawn, "GAGAGAGAA");
        mowerBehavior = new MowerBehaviorImpl();
    }

    /**
     * Testde la méthode execute.
     */
    @Test
    public void testexecute() {

        // G > 1 - 2 - W
        mowerBehavior.execute(mower, 'G');
        assertEquals(mower.getX(), 1);
        assertEquals(mower.getY(), 2);
        assertEquals(mower.getOrientation(), 'W');
        // A > 0 - 2 - W
        mowerBehavior.execute(mower, 'A');
        assertEquals(mower.getX(), 0);
        assertEquals(mower.getY(), 2);
        assertEquals(mower.getOrientation(), 'W');
        // G > 0 - 2 - S
        mowerBehavior.execute(mower, 'G');
        assertEquals(mower.getX(), 0);
        assertEquals(mower.getY(), 2);
        assertEquals(mower.getOrientation(), 'S');
        // A > 0 - 1 - S
        mowerBehavior.execute(mower, 'A');
        assertEquals(mower.getX(), 0);
        assertEquals(mower.getY(), 1);
        assertEquals(mower.getOrientation(), 'S');
        // G > 0 - 1 - E
        mowerBehavior.execute(mower, 'G');
        assertEquals(mower.getX(), 0);
        assertEquals(mower.getY(), 1);
        assertEquals(mower.getOrientation(), 'E');
        // A > 1 - 1 - E
        mowerBehavior.execute(mower, 'A');
        assertEquals(mower.getX(), 1);
        assertEquals(mower.getY(), 1);
        assertEquals(mower.getOrientation(), 'E');
        // G > 1 - 1 - N
        mowerBehavior.execute(mower, 'G');
        assertEquals(mower.getX(), 1);
        assertEquals(mower.getY(), 1);
        assertEquals(mower.getOrientation(), 'N');
        // A > 1 - 2 - N
        mowerBehavior.execute(mower, 'A');
        assertEquals(mower.getX(), 1);
        assertEquals(mower.getY(), 2);
        assertEquals(mower.getOrientation(), 'N');
        // A > 1 - 3 - N
        mowerBehavior.execute(mower, 'A');
        assertEquals(mower.getX(), 1);
        assertEquals(mower.getY(), 3);
        assertEquals(mower.getOrientation(), 'N');
    }

}
