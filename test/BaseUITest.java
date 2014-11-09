import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import org.junit.Test;

/**
 * JUnit4 Test Class for methods in BaseUI
 * 
 * @author Blake
 *
 */
public class BaseUITest {

	@Test
	public void testGetMenuInput() {
		String testData; // String to store user input string in
		InputStream testInput; // InputStream that will simulate user input
		
		// Middle input
		testData = "2";
		testInput = new ByteArrayInputStream(testData.getBytes());
		assertEquals(2, BaseUI.getMenuInput(testInput, 0, 5));
		
		// Lower boundary input
		testData = "0";
		testInput = new ByteArrayInputStream(testData.getBytes());
		assertEquals(0, BaseUI.getMenuInput(testInput, 0, 5));

		// Upper boundary input
		testData = "5";
		testInput = new ByteArrayInputStream(testData.getBytes());
		assertEquals(5, BaseUI.getMenuInput(testInput, 0, 5));
		
		// Outside upper boundary (should ask again - give 3)
		testData = "-1" + System.lineSeparator() + "3";
		testInput = new ByteArrayInputStream(testData.getBytes());
		assertEquals(3, BaseUI.getMenuInput(testInput, 0, 5));
		
		// Outside lower boundary (should ask again - give 3)
		testData = "6" + System.lineSeparator() + "3";
		testInput = new ByteArrayInputStream(testData.getBytes());
		assertEquals(3, BaseUI.getMenuInput(testInput, 0, 5));

		// Not a number (should ask again - give 3)
		testData = "k" + System.lineSeparator() + "3";
		testInput = new ByteArrayInputStream(testData.getBytes());
		assertEquals(3, BaseUI.getMenuInput(testInput, 0, 5));
	}
	
	@Test
	public void testGetIntegerInput() {
		String testData; // String to store user input string in
		InputStream testInput; // InputStream that will simulate user input
		
		// Positive number
		testData = "47";
		testInput = new ByteArrayInputStream(testData.getBytes());
		assertEquals(47, BaseUI.getIntegerInput(testInput, "prompt"));

		// Negative number
		testData = "-12345678";
		testInput = new ByteArrayInputStream(testData.getBytes());
		assertEquals(-12345678, BaseUI.getIntegerInput(testInput, "prompt"));

		// Not an integer
		testData = "3.14" + System.lineSeparator() + "3";
		testInput = new ByteArrayInputStream(testData.getBytes());
		assertEquals(3, BaseUI.getIntegerInput(testInput, "prompt"));

		// Not a number
		testData = "this is not a number" + System.lineSeparator() + "3";
		testInput = new ByteArrayInputStream(testData.getBytes());
		assertEquals(3, BaseUI.getIntegerInput(testInput, "prompt"));
	}
	
	@Test
	public void testGetConfirmation() {
		String testData; // String to store user input string in
		InputStream testInput; // InputStream that will simulate user input
		
		// Confirmation
		testData = "y";
		testInput = new ByteArrayInputStream(testData.getBytes());
		assertEquals(true, BaseUI.getConfirmation(testInput, "prompt"));

		// Also confirmation
		testData = "yes";
		testInput = new ByteArrayInputStream(testData.getBytes());
		assertEquals(true, BaseUI.getConfirmation(testInput, "prompt"));

		// Declined
		testData = "n";
		testInput = new ByteArrayInputStream(testData.getBytes());
		assertEquals(false, BaseUI.getConfirmation(testInput, "prompt"));

		// Also declined
		testData = "no";
		testInput = new ByteArrayInputStream(testData.getBytes());
		assertEquals(false, BaseUI.getConfirmation(testInput, "prompt"));

		// Anything else should ask again
		testData = "anything else" + System.lineSeparator() + "yes";
		testInput = new ByteArrayInputStream(testData.getBytes());
		assertEquals(true, BaseUI.getConfirmation(testInput, "prompt"));
	}

}
