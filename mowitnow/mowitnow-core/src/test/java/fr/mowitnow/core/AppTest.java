package fr.mowitnow.core;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest {
	@Test
	public void testApp() {
		boolean b = Pattern.matches("^(\\d)\\s(\\d)$", "5 5");
		System.out.println("b " + b);

		Pattern pattern = Pattern.compile("^(\\d)\\s(\\d)$");
		Matcher matcher = pattern.matcher("5 5");
		if (matcher.find()) {
			try {
				int width = Integer.parseInt(matcher.group(1));
				int height = Integer.parseInt(matcher.group(2));
				System.out.println(width +" " +height);
				
				Pattern pattern1 = Pattern.compile("^(\\d)\\s(\\d)\\s([WENS])$");
				Matcher matcher1 = pattern1.matcher("1 2 N");
				System.out.println(Pattern.matches("^(\\d)\\s(\\d)\\s([WENS])$", "1 2 N"));
				if (matcher1.find()) {
					try {
						int x = Integer.parseInt(matcher1.group(1));
						int y = Integer.parseInt(matcher1.group(2));
						char orientation=matcher1.group(3).charAt(0);
						System.out.println(x +" " +y+ " "+orientation);
						
						System.out.println(Pattern.matches("^([GAD])+$", "GAAGAGGAGDA"));
						
					}catch(Exception e){
						
					}
				}	
			} catch (NumberFormatException e) {
				
			}
		} else {
			
		}
	}
}
