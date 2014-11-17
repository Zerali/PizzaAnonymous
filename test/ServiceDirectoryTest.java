import static org.junit.Assert.*;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;


public class ServiceDirectoryTest {

	public ServiceDirectory testDirectory = new ServiceDirectory();
	
	/**@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
		// Get the reference to the service controller (so we can call methods on it)
		Field serviceControllerField = PizzaAnonymous.getInstance().getClass().getDeclaredField("serviceController");
		serviceControllerField.setAccessible(true);
		serviceController = (ServiceController) serviceControllerField.get(PizzaAnonymous.getInstance());
	}**/
	
	
	@Test
	public void testAddService() {
		
		//Test for expected inputs
		assertTrue(testDirectory.addService("service1", 199.11));
		
		//Test with a negative number
		assertFalse(testDirectory.addService("service2", -1.11));
		
		//Test a name over 20 characters
		assertFalse(testDirectory.addService("This string is over 20 characters long", 199.11));
		
		//delete the added service
		testDirectory.deleteService(1);
		
	}
	
	@Test
	public void testGetService() {
		//Should be a test on an empty list (false test because it is returning null)
		assertTrue(testDirectory.getService(1) == null);
		
		//add a service to the system to test get
		testDirectory.addService("getTestService", 100.19);
		
		//Should be a successful find, will match names to see
		assertThat(testDirectory.getService(1).getName(), containsString("getTestService"));
		
		//Should not be able to find this service(returning null)
		assertTrue(testDirectory.getService(18) == null);
		
		//clear list
		testDirectory.deleteService(1);
	}
	
	@Test
	public void testDeleteService() {
		// add a service to the system for deleting purposes
		testDirectory.addService("deleteService", 1.23);
		
		//delete the first service in the list
		assertTrue(testDirectory.deleteService(1));
		
		//try to delete non existent service
		assertFalse(testDirectory.deleteService(18));
	}

}
