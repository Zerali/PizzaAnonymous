import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ProviderControllerTest {
	
	private ProviderController testDirectory;
	
	@Before
	public void setUp() {
		testDirectory = new ProviderController();
	}

	@After
	public void tearDown() throws Exception {
		testDirectory = null;
	}
	
	/**
	 * test addProvider() in ProviderController
	 */
	@Test
	public void testAddProvider() {
		
		// successful add case
		assertTrue(testDirectory.addProvider(1, "jim", "112 st", "st cloud", "mn", 56301));
		
		// added provider already in testDirectory
		assertFalse(testDirectory.addProvider(1, "jim", "112 st", "st cloud", "mn", 56301));
	}
	
	/**
	 * test getProvider() in ProviderController
	 */
	@Test
	public void testGetProvider() {
		
		// example provider to be get'd
		testDirectory.addProvider(1, "jim", "112 st", "st cloud", "mn", 56301);
		
		// successful get case, test by checking against the name
		assertEquals("jim", testDirectory.getProvider(1).getName());
		
		// unsuccessful get case
		assertEquals(null, testDirectory.getProvider(0));
	}
	
	/**
	 * test deleteProvider() in ProviderController
	 */
	@Test
	public void testDeleteProvider() {
		
		// example provider to be get'd
		testDirectory.addProvider(1, "jim", "112 st", "st cloud", "mn", 56301);
		
		// successful delete case
		assertTrue(testDirectory.deleteProvider(1));
		
		// unsuccessful delete case
		assertFalse(testDirectory.deleteProvider(0));
	}
}
