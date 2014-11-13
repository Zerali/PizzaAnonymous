import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Test class for the ReportController. 
 * Will create artificial data objects and inject them into the 
 * lists, to simulate a working environment.
 * Also creates an artificial report object to make use of temporary file locations
 * 
 * @author Blake
 *
 */
public class ReportControllerTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		// Initialize the data objects, so they're ready to place in lists
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		// Destroy references to data objects
	}

	@Before
	public void setUp() throws Exception {
		// Runs before each test. Make sure the data lists are empty
	}

	@After
	public void tearDown() throws Exception {
		// Runs after each test.
	}

	@Test
	public void testCreateWeeklyReportsListOfMemberReportListOfProviderReportSummaryReportEFTReport() {
		fail("Not yet implemented");
	}

	@Test
	public void testMakeMemberSvcReportIntMemberReport() {
		fail("Not yet implemented");
	}

	@Test
	public void testMakeProviderSvcReportIntProviderReport() {
		fail("Not yet implemented");
	}

	@Test
	public void testMakeSummaryReportSummaryReport() {
		fail("Not yet implemented");
	}

	@Test
	public void testMakeEFTReportEFTReport() {
		fail("Not yet implemented");
	}

}
