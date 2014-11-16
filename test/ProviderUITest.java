import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.LinkedList;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;


public class ProviderUITest {
	
	@Rule
	
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
		testService.setCost(1.23F);
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

}
