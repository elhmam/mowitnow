/**
 * 
 */
package fr.mowitnow.core.behvior;

import static org.junit.Assert.*;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import fr.mowitnow.core.model.Lawn;
import fr.mowitnow.core.model.Mower;

/**
 * @author elhmam
 *
 */
public class MowerBehaviorImplTest {

	Lawn lawn;
	Mower mower1;
	Mower mower2;
	MowerBehavior mowerBehavior;
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		mower1 = new Mower(1, 2, 'N', lawn,"");
		lawn=new Lawn(5,5);
		mowerBehavior=new MowerBehaviorImpl();
	}

	/**
	 * Test method for {@link fr.mowitnow.core.behvior.MowerBehaviorImpl#move(fr.mowitnow.core.model.Mower, char)}.
	 */
	@Test
	public void testMove() {
				
		//G > 1 - 2 - W
		mowerBehavior.move(mower1,'G');
		assertEquals(mower1.getX(),1);
		assertEquals(mower1.getY(),2);
		assertEquals(mower1.getOrientation(),'W');
		//A > 0 - 2 - W
		//mowerBehavior.move(mower1,'A');
		//assertEquals(mower1.getX(),0);
		//G > 0 - 2 - S
		//A > 0 - 1 - S
		//G > 0 - 1 - E
		//A > 1 - 1 - E
		//G > 1 - 1 - N
		//A > 1 - 2 - N
		//A > 1 - 3 - N
	}

}
