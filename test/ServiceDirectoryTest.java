import java.lang.reflect.Field;

import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.hamcrest.CoreMatchers.*;

import org.hamcrest.CoreMatchers;


public class ServiceDirectoryTest {

	@Rule
	private static ServiceController serviceController;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
		// Get the reference to the service controller (so we can call methods on it)
		Field serviceControllerField = PizzaAnonymous.getInstance().getClass().getDeclaredField("serviceController");
		serviceControllerField.setAccessible(true);
		serviceController = (ServiceController) serviceControllerField.get(PizzaAnonymous.getInstance());
	}
	
	
	@Test
	public void testAddService() {
		
		//Test for expected inputs
		assertTrue(serviceController.getServiceDirectory().addService("service1", 199.11));
		
		//Test with a negative number
		assertFalse(serviceController.getServiceDirectory().addService("service2", -1.11));
		
		//Test a name over 20 characters
		assertFalse(serviceController.getServiceDirectory().addService("This string is over 20 characters long", 199.11));
		
		//delete the added service
		serviceController.getServiceDirectory().deleteService(1);
		
	}
	
	@Test
	public void testGetService() {
		//Should be a test on an empty list (false test because it is returning null)
		assertFalse(serviceController.getServiceDirectory().getService(1));
		
		//add a service to the system to test get
		serviceController.getServiceDirectory().addService("getTestService", 100.19);
		
		//Should be a successful find, will match names to see
		assertThat(serviceController.getServiceDirectory().getService(1).getName(), containsString("getTestService"));
		
		//Should not be able to find this service(returning null)
		assertFalse(serviceController.getServiceDirectory().getService(18));
		
		//clear list
		serviceController.getServiceDirectory().deleteService(1);
	}
	
	@Test
	public void testDeleteService() {
		// add a service to the system for deleting purposes
		serviceController.getServiceDirectory().addService("deleteService", 1.23);
		
		//delete the first service in the list
		assertTrue(serviceController.getServiceDirectory().deleteService(1));
		
		//try to delete non existent service
		assertFalse(serviceController.getServiceDirectory().deleteService(18));
	}

}
