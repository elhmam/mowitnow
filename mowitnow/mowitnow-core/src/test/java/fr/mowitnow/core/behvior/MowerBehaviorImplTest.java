
package fr.mowitnow.core.behvior;

import static org.junit.Assert.assertEquals;

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
		lawn=new Lawn(5,5);
		mower1 = new Mower(1, 2, 'N', lawn,"GAGAGAGAA");		
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
		mowerBehavior.move(mower1,'A');
		assertEquals(mower1.getX(),0);
		assertEquals(mower1.getY(),2);
		assertEquals(mower1.getOrientation(),'W');
		//G > 0 - 2 - S
		mowerBehavior.move(mower1,'G');
		assertEquals(mower1.getX(),0);
		assertEquals(mower1.getY(),2);
		assertEquals(mower1.getOrientation(),'S');
		//A > 0 - 1 - S
		mowerBehavior.move(mower1,'A');
		assertEquals(mower1.getX(),0);
		assertEquals(mower1.getY(),1);
		assertEquals(mower1.getOrientation(),'S');
		//G > 0 - 1 - E
		mowerBehavior.move(mower1,'G');
		assertEquals(mower1.getX(),0);
		assertEquals(mower1.getY(),1);
		assertEquals(mower1.getOrientation(),'E');
		//A > 1 - 1 - E
		mowerBehavior.move(mower1,'A');
		assertEquals(mower1.getX(),1);
		assertEquals(mower1.getY(),1);
		assertEquals(mower1.getOrientation(),'E');
		//G > 1 - 1 - N
		mowerBehavior.move(mower1,'G');
		assertEquals(mower1.getX(),1);
		assertEquals(mower1.getY(),1);
		assertEquals(mower1.getOrientation(),'N');
		//A > 1 - 2 - N
		mowerBehavior.move(mower1,'A');
		assertEquals(mower1.getX(),1);
		assertEquals(mower1.getY(),2);
		assertEquals(mower1.getOrientation(),'N');
		//A > 1 - 3 - N
		mowerBehavior.move(mower1,'A');
		assertEquals(mower1.getX(),1);
		assertEquals(mower1.getY(),3);
		assertEquals(mower1.getOrientation(),'N');
	}

}
