import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

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
	
	@Rule
	public TemporaryFolder folder = new TemporaryFolder();
	
	// A temporary folder to have files put in
	private static File tmpFolder;
	
	// Objects we need to create an artificial service occasion through & through
	private static Member testMember;
	private static Provider testProvider;
	private static Service testService;
	private static ServiceOccasion testServiceOccasion;
	
	// We're gonna need the ReportController
	private static ReportController reportController;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		// Initialize the data objects, so they're ready to place in lists
		testMember = new Member("m.name", 1, "m.address", "m.city", "m.state", 56301, true);
		testProvider = new Provider(1, "p.name", "p.address", "p.city", "p.state", 56302);
		testService = new Service();
		testService.setID(1);
		testService.setName("s.name");
		testService.setCost(1.23);
		testServiceOccasion = new ServiceOccasion(1, 1, 1, "o.date", "o.cur_date", "o.comments");
		
		// Get the reference to the report controller (so we can call methods on it)
		Field reportControllerField = PizzaAnonymous.getInstance().getClass().getDeclaredField("reportController");
		reportControllerField.setAccessible(true);
		reportController = (ReportController) reportControllerField.get(PizzaAnonymous.getInstance());
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		// Destroy references to data objects
		testMember = null;
		testProvider = null;
		testService = null;
		testServiceOccasion = null;
	}

	@Before
	public void setUp() throws Exception {
		// Runs before each test. Make sure the data lists are empty
		clearMembers();
		clearProviders();
		clearServices();
		clearServiceOccasions();
		
		// Create a temporary folder for the test
		if(tmpFolder == null)
		{
			tmpFolder = folder.newFolder("tmp_report");
		}
	}

	@After
	public void tearDown() throws Exception {
		// Runs after each test. Remove the temporary folder
		tmpFolder = null;
	}

	@Test
	public void testCreateWeeklyReports() {

		/* Test when everything is there */
		try {
			// Need some temporary reports to work with
			List<MemberReport> memberReports = new ArrayList<MemberReport>();
			memberReports.add(getTempMemberReport(5));
			List<ProviderReport> providerReports = new ArrayList<ProviderReport>();
			providerReports.add(getTempProviderReport(5));
			SummaryReport summaryReport = getTempSummaryReport(5);
			EFTReport eftReport = getTempEFTReport(5);
			
			try {
				// Start with all of the data
				injectMember();
				injectProvider();
				injectService();
				injectServiceOccasion();
				
				// Now the call should return true. Then a file should exist
				assertTrue(reportController.createWeeklyReports(memberReports, providerReports, summaryReport, eftReport));
			} catch (Exception e1) {
				fail("testMakeMemberSvcReport: Injection failed: " + e1.getMessage());
			}
		} catch (IOException e) {
			fail("testMakeMemberSvcReport: Temp Report Creation failed: " + e.getMessage());
		}
		
		
		/* Test when there are no providers */
		try {
			// Need some temporary reports to work with
			List<MemberReport> memberReports = new ArrayList<MemberReport>();
			memberReports.add(getTempMemberReport(6));
			List<ProviderReport> providerReports = new ArrayList<ProviderReport>();
			SummaryReport summaryReport = getTempSummaryReport(6);
			EFTReport eftReport = getTempEFTReport(6);
			
			try {
				// Same as before, but remove the provider
				clearProviders();
				
				// Now the call should return false. (Member report needs a provider)
				assertFalse(reportController.createWeeklyReports(memberReports, providerReports, summaryReport, eftReport));
			} catch (Exception e1) {
				fail("testMakeMemberSvcReport: Injection failed: " + e1.getMessage());
			}
		} catch (IOException e) {
			fail("testMakeMemberSvcReport: Temp Report Creation failed: " + e.getMessage());
		}
		
		/* Test when there is minimal data */
		try {
			// Need some temporary reports to work with
			List<MemberReport> memberReports = new ArrayList<MemberReport>();
			List<ProviderReport> providerReports = new ArrayList<ProviderReport>();
			SummaryReport summaryReport = getTempSummaryReport(7);
			EFTReport eftReport = getTempEFTReport(7);
			
			try {
				// Have no data
				clearProviders();
				clearMembers();
				clearServices();
				clearServiceOccasions();
				
				// Should still be true. Since none of the reports NEED data
				assertTrue(reportController.createWeeklyReports(memberReports, providerReports, summaryReport, eftReport));
			} catch (Exception e1) {
				fail("testMakeMemberSvcReport: Injection failed: " + e1.getMessage());
			}
		} catch (IOException e) {
			fail("testMakeMemberSvcReport: Temp Report Creation failed: " + e.getMessage());
		}
		
	}

	@Test
	public void testMakeMemberSvcReport() {
		
		/* Test when data exists properly */
		try {
			// Need a temporary report to work with
			MemberReport tempReport = getTempMemberReport(1);
			
			try {
				// First gotta fill in the data
				injectMember();
				injectProvider();
				injectService();
				injectServiceOccasion();
				
				// Now the call should return true. Then a file should exist
				assertTrue(reportController.makeMemberSvcReport(1, tempReport));
				assertTrue(Files.exists(Paths.get(tempReport.getFileName())));
			} catch (Exception e1) {
				fail("testMakeMemberSvcReport: Injection failed: " + e1.getMessage());
			}
		} catch (IOException e) {
			fail("testMakeMemberSvcReport: Temp Report Creation failed: " + e.getMessage());
		}
		
		
		/* Test when there are no matching service occasions */
		try {
			// Need a temporary report to work with
			MemberReport tempReport = getTempMemberReport(2);
			
			try {
				// We assume previous but remove the Occasion
				clearServiceOccasions();
				
				// Now the call should return true. Then a file should still exist
				assertTrue(reportController.makeMemberSvcReport(1, tempReport));
				assertTrue(Files.exists(Paths.get(tempReport.getFileName())));
			} catch (Exception e1) {
				fail("testMakeMemberSvcReport: Injection failed: " + e1.getMessage());
			}
		} catch (IOException e) {
			fail("testMakeMemberSvcReport: Temp Report Creation failed: " + e.getMessage());
		}
		
		
		/* Test missing service branch */
		try {
			// Need a temporary report to work with
			MemberReport tempReport = getTempMemberReport(3);
			
			try {
				// Add the Occasion back, remove the Service
				injectServiceOccasion();
				clearServices();
				
				// Now the call should return false. Then no file should exist
				assertFalse(reportController.makeMemberSvcReport(1, tempReport));
				assertFalse(Files.exists(Paths.get(tempReport.getFileName())));
			} catch (Exception e1) {
				fail("testMakeMemberSvcReport: Injection failed: " + e1.getMessage());
			}
		} catch (IOException e) {
			fail("testMakeMemberSvcReport: Temp Report Creation failed: " + e.getMessage());
		}
		
		
		/* Test missing member branch */
		try {
			// Need a temporary report to work with
			MemberReport tempReport = getTempMemberReport(4);
			
			try {
				// Add the Service back, but use an invalid member id
				injectService();
				
				// Now the call should return false. Then no file should exist
				assertFalse(reportController.makeMemberSvcReport(-1, tempReport));
				assertFalse(Files.exists(Paths.get(tempReport.getFileName())));
			} catch (Exception e1) {
				fail("testMakeMemberSvcReport: Injection failed: " + e1.getMessage());
			}
		} catch (IOException e) {
			fail("testMakeMemberSvcReport: Temp Report Creation failed: " + e.getMessage());
		}
		
	}

	@Test
	public void testMakeProviderSvcReport() {
		
		/* Test when data exists properly */
		try {
			// Need a temporary report to work with
			ProviderReport tempReport = getTempProviderReport(1);
			
			try {
				// First gotta fill in the data
				injectMember();
				injectProvider();
				injectService();
				injectServiceOccasion();
				
				// Now the call should return true. Then a file should exist
				assertTrue(reportController.makeProviderSvcReport(1, tempReport));
				assertTrue(Files.exists(Paths.get(tempReport.getFileName())));
			} catch (Exception e1) {
				fail("testMakeMemberSvcReport: Injection failed: " + e1.getMessage());
			}
		} catch (IOException e) {
			fail("testMakeMemberSvcReport: Temp Report Creation failed: " + e.getMessage());
		}
		
		
		/* Test when there are no matching Service Occasions */
		try {
			// Need a temporary report to work with
			ProviderReport tempReport = getTempProviderReport(2);
			
			try {
				// Remove the service occasion
				clearServiceOccasions();
				
				// Now the call should return true. Then a file should exist
				assertTrue(reportController.makeProviderSvcReport(1, tempReport));
				assertTrue(Files.exists(Paths.get(tempReport.getFileName())));
			} catch (Exception e1) {
				fail("testMakeMemberSvcReport: Injection failed: " + e1.getMessage());
			}
		} catch (IOException e) {
			fail("testMakeMemberSvcReport: Temp Report Creation failed: " + e.getMessage());
		}
		
		
		/* Test missing service branch */
		try {
			// Need a temporary report to work with
			ProviderReport tempReport = getTempProviderReport(3);
			
			try {
				// Add occasion back, remove service
				injectServiceOccasion();
				clearServices();
				
				// Now the call should return true. Then a file should exist
				assertFalse(reportController.makeProviderSvcReport(1, tempReport));
				assertFalse(Files.exists(Paths.get(tempReport.getFileName())));
			} catch (Exception e1) {
				fail("testMakeMemberSvcReport: Injection failed: " + e1.getMessage());
			}
		} catch (IOException e) {
			fail("testMakeMemberSvcReport: Temp Report Creation failed: " + e.getMessage());
		}
		
		
		/* Test missing provider branch */
		try {
			// Need a temporary report to work with
			ProviderReport tempReport = getTempProviderReport(4);
			
			try {
				// Add service back, but call with an invalid provider ID
				injectService();
				
				// Now the call should return true. Then a file should exist
				assertFalse(reportController.makeProviderSvcReport(-1, tempReport));
				assertFalse(Files.exists(Paths.get(tempReport.getFileName())));
			} catch (Exception e1) {
				fail("testMakeMemberSvcReport: Injection failed: " + e1.getMessage());
			}
		} catch (IOException e) {
			fail("testMakeMemberSvcReport: Temp Report Creation failed: " + e.getMessage());
		}
		
	}

	@Test
	public void testMakeSummaryReport() {
		
		/* Test when data exists properly */
		try {
			// Need a temporary report to work with
			SummaryReport tempReport = getTempSummaryReport(1);
			
			try {
				// Place all of the data
				injectMember();
				injectProvider();
				injectService();
				injectServiceOccasion();
				
				// Now the call should return true. Then a file should exist
				assertTrue(reportController.makeSummaryReport(tempReport));
				assertTrue(Files.exists(Paths.get(tempReport.getFileName())));
			} catch (Exception e1) {
				fail("testMakeMemberSvcReport: Injection failed: " + e1.getMessage());
			}
		} catch (IOException e) {
			fail("testMakeMemberSvcReport: Temp Report Creation failed: " + e.getMessage());
		}
		
		
		/* Test when bad data is encountered */
		try {
			// Need a temporary report to work with
			SummaryReport tempReport = getTempSummaryReport(2);
			
			try {
				// Remove provider (Ie make the provider ID bad)
				clearProviders();
				
				// Now the call should return true. Then a file should exist
				assertTrue(reportController.makeSummaryReport(tempReport));
				assertTrue(Files.exists(Paths.get(tempReport.getFileName())));
			} catch (Exception e1) {
				fail("testMakeMemberSvcReport: Injection failed: " + e1.getMessage());
			}
		} catch (IOException e) {
			fail("testMakeMemberSvcReport: Temp Report Creation failed: " + e.getMessage());
		}
		
		
		/* Test when there is essentially no data */
		try {
			// Need a temporary report to work with
			SummaryReport tempReport = getTempSummaryReport(3);
			
			try {
				// Put provider back, remove service occasions
				injectProvider();
				clearServiceOccasions();
				
				// Now the call should return true. Then a file should exist
				assertTrue(reportController.makeSummaryReport(tempReport));
				assertTrue(Files.exists(Paths.get(tempReport.getFileName())));
			} catch (Exception e1) {
				fail("testMakeMemberSvcReport: Injection failed: " + e1.getMessage());
			}
		} catch (IOException e) {
			fail("testMakeMemberSvcReport: Temp Report Creation failed: " + e.getMessage());
		}
	}

	@Test
	public void testMakeEFTReport() {
		
		/* Test when data exists properly */
		try {
			// Need a temporary report to work with
			EFTReport tempReport = getTempEFTReport(1);
			
			try {
				// Place all of the data
				injectMember();
				injectProvider();
				injectService();
				injectServiceOccasion();
				
				// Now the call should return true. Then a file should exist
				assertTrue(reportController.makeEFTReport(tempReport));
				assertTrue(Files.exists(Paths.get(tempReport.getFileName())));
			} catch (Exception e1) {
				fail("testMakeMemberSvcReport: Injection failed: " + e1.getMessage());
			}
		} catch (IOException e) {
			fail("testMakeMemberSvcReport: Temp Report Creation failed: " + e.getMessage());
		}
		
		
		/* Test when bad data is encountered */
		try {
			// Need a temporary report to work with
			EFTReport tempReport = getTempEFTReport(2);
			
			try {
				// Remove service (Service becomes the bad data)
				clearServices();
				
				// Now the call should return true. Then a file should exist
				assertTrue(reportController.makeEFTReport(tempReport));
				assertTrue(Files.exists(Paths.get(tempReport.getFileName())));
			} catch (Exception e1) {
				fail("testMakeMemberSvcReport: Injection failed: " + e1.getMessage());
			}
		} catch (IOException e) {
			fail("testMakeMemberSvcReport: Temp Report Creation failed: " + e.getMessage());
		}
		
		
		/* Test when there is little data */
		try {
			// Need a temporary report to work with
			EFTReport tempReport = getTempEFTReport(3);
			
			try {
				// restore service, remove service occasions
				injectService();
				clearServiceOccasions();
				
				// Now the call should return true. Then a file should exist
				assertTrue(reportController.makeEFTReport(tempReport));
				assertTrue(Files.exists(Paths.get(tempReport.getFileName())));
			} catch (Exception e1) {
				fail("testMakeMemberSvcReport: Injection failed: " + e1.getMessage());
			}
		} catch (IOException e) {
			fail("testMakeMemberSvcReport: Temp Report Creation failed: " + e.getMessage());
		}
		
		
		/* Test when there is no data */
		try {
			// Need a temporary report to work with
			EFTReport tempReport = getTempEFTReport(4);
			
			try {
				// Test when there is no data
				clearProviders();
				clearMembers();
				
				// Now the call should return true. Then a file should exist
				assertTrue(reportController.makeEFTReport(tempReport));
				assertTrue(Files.exists(Paths.get(tempReport.getFileName())));
			} catch (Exception e1) {
				fail("testMakeMemberSvcReport: Injection failed: " + e1.getMessage());
			}
		} catch (IOException e) {
			fail("testMakeMemberSvcReport: Temp Report Creation failed: " + e.getMessage());
		}
	}
	
	/**
	 * Clear the MemberController's member list
	 * @throws Exception If Reflection fails
	 */
	@SuppressWarnings("unchecked")
	private void clearMembers() throws Exception
	{
		// Pull out the MemberController object
		Field membContField = PizzaAnonymous.getInstance().getClass().getDeclaredField("memberController");
		membContField.setAccessible(true);
		MemberController mC = (MemberController) membContField.get(PizzaAnonymous.getInstance());

		// Pull out the member array list
		Field membListField = MemberController.class.getDeclaredField("members");
		membListField.setAccessible(true);
		ArrayList<Member> mL = (ArrayList<Member>) membListField.get(mC);

		// Clear the list
		mL.clear();
	}

	/**
	 * Place the testMember into the member list
	 * @throws Exception If Reflection fails
	 */
	@SuppressWarnings("unchecked")
	private void injectMember() throws Exception
	{
		// Pull out the MemberController object
		Field membContField = PizzaAnonymous.getInstance().getClass().getDeclaredField("memberController");
		membContField.setAccessible(true);
		MemberController mC = (MemberController) membContField.get(PizzaAnonymous.getInstance());

		// Pull out the member array list
		Field membListField = MemberController.class.getDeclaredField("members");
		membListField.setAccessible(true);
		ArrayList<Member> mL = (ArrayList<Member>) membListField.get(mC);

		// Place our member object into it
		mL.add(testMember);
	}
	
	
	/**
	 * Clear the ProviderController's provider list
	 * @throws Exception If Reflection fails
	 */
	@SuppressWarnings("unchecked")
	private void clearProviders() throws Exception
	{
		// Pull out the ProviderController object
		Field provContField = PizzaAnonymous.getInstance().getClass().getDeclaredField("providerController");
		provContField.setAccessible(true);
		ProviderController pC = (ProviderController) provContField.get(PizzaAnonymous.getInstance());

		// Pull out the member array list
		Field provListField = ProviderController.class.getDeclaredField("providers");
		provListField.setAccessible(true);
		ArrayList<Provider> pL = (ArrayList<Provider>) provListField.get(pC);

		// Clear the list
		pL.clear();
	}

	/**
	 * Place the testProvider into the provider list
	 * @throws Exception If Reflection fails
	 */
	@SuppressWarnings("unchecked")
	private void injectProvider() throws Exception
	{
		// Pull out the ProviderController object
		Field provContField = PizzaAnonymous.getInstance().getClass().getDeclaredField("providerController");
		provContField.setAccessible(true);
		ProviderController pC = (ProviderController) provContField.get(PizzaAnonymous.getInstance());

		// Pull out the member array list
		Field provListField = ProviderController.class.getDeclaredField("providers");
		provListField.setAccessible(true);
		ArrayList<Provider> pL = (ArrayList<Provider>) provListField.get(pC);

		// Place our member object into it
		pL.add(testProvider);
	}
	
	/**
	 * Clear the ServiceDirectory's service list
	 * @throws Exception If Reflection fails
	 */
	@SuppressWarnings("unchecked")
	private void clearServices() throws Exception
	{
		// Pull out the ServiceController object
		Field servContField = PizzaAnonymous.getInstance().getClass().getDeclaredField("serviceController");
		servContField.setAccessible(true);
		ServiceController sC = (ServiceController) servContField.get(PizzaAnonymous.getInstance());
		
		// Pull out the ServiceDirectory
		Field servDirField = ServiceController.class.getDeclaredField("serviceDirectory");
		servDirField.setAccessible(true);
		ServiceDirectory sD = (ServiceDirectory) servDirField.get(sC);

		// Pull out the service linked list
		Field servListField = ServiceDirectory.class.getDeclaredField("servicesList");
		servListField.setAccessible(true);
		LinkedList<Service> sL = (LinkedList<Service>) servListField.get(sD);

		// Clear the list
		sL.clear();
	}

	/**
	 * Place the testService into the service list
	 * @throws Exception If Reflection fails
	 */
	@SuppressWarnings("unchecked")
	private void injectService() throws Exception
	{
		// Pull out the ServiceController object
		Field servContField = PizzaAnonymous.getInstance().getClass().getDeclaredField("serviceController");
		servContField.setAccessible(true);
		ServiceController sC = (ServiceController) servContField.get(PizzaAnonymous.getInstance());

		// Pull out the ServiceDirectory
		Field servDirField = ServiceController.class.getDeclaredField("serviceDirectory");
		servDirField.setAccessible(true);
		ServiceDirectory sD = (ServiceDirectory) servDirField.get(sC);

		// Pull out the service linked list
		Field servListField = ServiceDirectory.class.getDeclaredField("servicesList");
		servListField.setAccessible(true);
		LinkedList<Service> sL = (LinkedList<Service>) servListField.get(sD);

		// Place our member object into it
		sL.add(testService);
	}
	
	/**
	 * Clear the ServiceOccasionList's service occasion list
	 * @throws Exception If Reflection fails
	 */
	@SuppressWarnings("unchecked")
	private void clearServiceOccasions() throws Exception
	{
		// Pull out the ServiceController object
		Field servContField = PizzaAnonymous.getInstance().getClass().getDeclaredField("serviceController");
		servContField.setAccessible(true);
		ServiceController sC = (ServiceController) servContField.get(PizzaAnonymous.getInstance());
		
		// Pull out the ServiceDirectory
		Field servOccField = ServiceController.class.getDeclaredField("serviceOccasionList");
		servOccField.setAccessible(true);
		ServiceOccasionList sD = (ServiceOccasionList) servOccField.get(sC);

		// Pull out the service linked list
		Field occListField = ServiceOccasionList.class.getDeclaredField("serviceOccasions");
		occListField.setAccessible(true);
		LinkedList<ServiceOccasion> sL = (LinkedList<ServiceOccasion>) occListField.get(sD);

		// Clear the list
		sL.clear();
	}

	/**
	 * Place the testService into the service list
	 * @throws Exception If Reflection fails
	 */
	@SuppressWarnings("unchecked")
	private void injectServiceOccasion() throws Exception
	{
		// Pull out the ServiceController object
		Field servContField = PizzaAnonymous.getInstance().getClass().getDeclaredField("serviceController");
		servContField.setAccessible(true);
		ServiceController sC = (ServiceController) servContField.get(PizzaAnonymous.getInstance());

		// Pull out the ServiceDirectory
		Field servOccField = ServiceController.class.getDeclaredField("serviceOccasionList");
		servOccField.setAccessible(true);
		ServiceOccasionList sD = (ServiceOccasionList) servOccField.get(sC);

		// Pull out the service linked list
		Field occListField = ServiceOccasionList.class.getDeclaredField("serviceOccasions");
		occListField.setAccessible(true);
		LinkedList<ServiceOccasion> sL = (LinkedList<ServiceOccasion>) occListField.get(sD);

		// Place our member object into it
		sL.add(testServiceOccasion);
	}
	
	/**
	 * @param unique An integer to make the filename unique, so it doesn't conflict with another file
	 * @return A MemberReport object where the filename points to a temporary folder
	 * @throws IOException The temporary file could not be created
	 */
	private MemberReport getTempMemberReport(int unique) throws IOException
	{
		// Set up the temporary save location
		String tmpPath = tmpFolder.getPath();

		// Anonymous class, lets us save it in a temporary place
		return new MemberReport() {

			@Override
			public String getFileName() {
				return tmpPath + "/TestMemberReport" + unique + ".txt";
			}

		};
	}
	
	/**
	 * @param unique An integer to make the filename unique, so it doesn't conflict with another file
	 * @return A ProviderReport object where the filename points to a temporary folder
	 * @throws IOException The temporary file could not be created
	 */
	private ProviderReport getTempProviderReport(int unique) throws IOException
	{
		// Set up the temporary save location
		String tmpPath = tmpFolder.getPath();

		// Anonymous class, lets us save it in a temporary place
		return new ProviderReport() {

			@Override
			public String getFileName() {
				return tmpPath + "/TestProviderReport" + unique + ".txt";
			}

		};
	}
	
	/**
	 * @param unique An integer to make the filename unique, so it doesn't conflict with another file
	 * @return An EFTReport object where the filename points to a temporary folder
	 * @throws IOException The temporary file could not be created
	 */
	private EFTReport getTempEFTReport(int unique) throws IOException
	{
		// Set up the temporary save location
		String tmpPath = tmpFolder.getPath();

		// Anonymous class, lets us save it in a temporary place
		return new EFTReport() {

			@Override
			public String getFileName() {
				return tmpPath + "/TestEFTReport" + unique + ".txt";
			}

		};
	}
	
	/**
	 * @param unique An integer to make the filename unique, so it doesn't conflict with another file
	 * @return A SummaryReport object where the filename points to a temporary folder
	 * @throws IOException The temporary file could not be created
	 */
	private SummaryReport getTempSummaryReport(int unique) throws IOException
	{
		// Set up the temporary save location
		String tmpPath = tmpFolder.getPath();

		// Anonymous class, lets us save it in a temporary place
		return new SummaryReport() {

			@Override
			public String getFileName() {
				return tmpPath + "/TestSummaryReport" + unique + ".txt";
			}

		};
	}

}
