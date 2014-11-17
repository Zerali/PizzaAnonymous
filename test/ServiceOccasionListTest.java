import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.Test;
import static org.hamcrest.CoreMatchers.*;


public class ServiceOccasionListTest {

	public ServiceOccasionList testOccasions = new ServiceOccasionList();
	
	/**@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
		// Get the reference to the service controller (so we can call methods on it)
		Field serviceControllerField = PizzaAnonymous.getInstance().getClass().getDeclaredField("serviceController");
		serviceControllerField.setAccessible(true);
		serviceController = (ServiceController) serviceControllerField.get(PizzaAnonymous.getInstance());
	}**/
	
	@Test
	public void testAddServiceOccasion() {
		//things to be added to the service occasion
		String date = "4/19/1931";
		int provider = 902101;
		int member = 123456;
		int service = 123456;
		String comments = "comments field";
		Iterator<ServiceOccasion> testIter;
		
		ServiceOccasion testOccasion;
		
		//add an occasion to the list
		testOccasions.addServiceOccasion(date, provider, member, service, comments);
		
		comments = "MtabxInBkHf0CYaBFHcVboetBajrHsZ60xF4kTSikKXAMiGXaTReYm7vH7GUPo57mWytwuG2nlJ8XjoF5LW8IJXEXreB4tzrV8ZEh";
		//add an occasion with comments > 100 char
		testOccasions.addServiceOccasion(date, provider, member, service, comments);
		
		testIter = testOccasions.getServiceOccasionIterator();
		testOccasion = testIter.next();
		
		assertThat(testOccasion.getDateProvided(), containsString("4/19/1931"));
		assertTrue(testOccasion.getProviderID() == provider);
		assertTrue(testOccasion.getMemberID() == member);
		assertTrue(testOccasion.getServiceID() == service);
		assertThat(testOccasion.getComments(), containsString("comments field"));
		
		//Second occasion with comments longer than 100
		testOccasion = testIter.next();
		//Just asserting comments field since rest was the same
		//String being compared to is one character shorter than the provided string
		assertThat(testOccasion.getComments(), containsString("MtabxInBkHf0CYaBFHcVboetBajrHsZ60xF4kTSikKXAMiGXaTReYm7vH7GUPo57mWytwuG2nlJ8XjoF5LW8IJXEXreB4tzrV8ZE"));
		
	}

}
