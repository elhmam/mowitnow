package fr.mowitnow.core.app;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import fr.mowitnow.core.behvior.MowerBehavior;
import fr.mowitnow.core.model.Lawn;
import fr.mowitnow.core.model.Mower;
import fr.mowitnow.core.parser.MowerParser;
import fr.mowitnow.core.parser.MowerParserException;


public class AppTest {
	
	@Mock
    private MowerParser mowerParser;
	
	App app;
		
	List<String> instructions = Arrays.asList(" 5", "1 2 N", "GAGAGAGAA",
			"3 3 E", "AADAADADDA");
	
	List<Mower> mowers=new ArrayList<>();
	
	@Before
	public void initTest(){
		MockitoAnnotations.initMocks(this);
		Lawn lawn=new Lawn(5,5);
		Mower mower=new Mower(1,2,'N',lawn,"GAGAGAGAA");
		Mower mower1=new Mower(3,3,'E',lawn,"AADAADADDA");
		mowers=Arrays.asList(mower,mower1);

	}

	@Test
	public void testMain() throws MowerParserException, IOException {

		Mockito.when(mowerParser.loadMowers(instructions)).thenReturn(mowers);
			
		
		
	}

}
