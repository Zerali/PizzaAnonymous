import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.Assert.*;
import org.junit.Test;


public class BaseUITest2 {

	@Test
	public void testGetStringInput() {
		String testData; //String to store simulated user input
		InputStream testInput; //Stream that will simulate input
		
		testData = "Return this string.";
		testInput = new ByteArrayInputStream(testData.getBytes());
		assertEquals("Return this string.", BaseUI.getStringInput(testInput, "prompt"));
	}
	
	@Test
	public void testGetDoubleInput() {
		String testData; //String to store simulated user input
		InputStream testInput; //Stream that will simulate input
		
		testData = "47.13"; //Positive number
		testInput = new ByteArrayInputStream(testData.getBytes());
		assertEquals(47.13, BaseUI.getDoubleInput(testInput, "prompt"), .0001);
		
		testData = "-1234.5678"; //Negative number
		testInput = new ByteArrayInputStream(testData.getBytes());
		assertEquals(-1234.5678, BaseUI.getDoubleInput(testInput, "prompt"), .0001);
		
		testData = "3"; //A rounded number
		testInput = new ByteArrayInputStream(testData.getBytes());
		assertEquals(3, BaseUI.getDoubleInput(testInput, "prompt"), .0001);
		
		testData = "this is not a number" + System.lineSeparator() + "3"; //not a number
		testInput = new ByteArrayInputStream(testData.getBytes());
		assertEquals(3, BaseUI.getDoubleInput(testInput, "prompt"), .0001);
	}

}
