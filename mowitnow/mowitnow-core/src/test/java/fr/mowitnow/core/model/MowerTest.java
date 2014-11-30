package fr.mowitnow.core.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class MowerTest {
	
	Mower mower;
	
	@Before
	public void initTest(){
		Lawn lawn=new Lawn(3,4);
		mower=new Mower(1, 2, 'N', lawn, "GAGAGAGAA");		
	}
	
	@Test
	public void testMowerIntialisation() {
		assertEquals(mower.getDeltaX(),0);
		assertEquals(mower.getDeltaY(),1);
		mower.setOrientation('E');
		assertEquals(mower.getDeltaX(),1);
		assertEquals(mower.getDeltaY(),0);
		mower.setOrientation('W');
		assertEquals(mower.getDeltaX(),-1);
		assertEquals(mower.getDeltaY(),0);
		mower.setOrientation('S');
		assertEquals(mower.getDeltaX(),0);
		assertEquals(mower.getDeltaY(),-1);
	}

	@Test
	public void testSetY() {
		mower.setY(5);
		assertEquals(mower.getY(),2);
		mower.setY(4);
		assertEquals(mower.getY(),4);
		mower.setY(-1);
		assertEquals(mower.getY(),4);
	}

	@Test
	public void testSetX() {
		mower.setX(5);
		assertEquals(mower.getX(),1);
		mower.setX(3);
		assertEquals(mower.getX(),3);
		mower.setX(-1);
		assertEquals(mower.getX(),3);
	}

}
