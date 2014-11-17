/**
 * This class controls the happening of the services and service occasion list
 * classes. It helps free up some of the functionality from Pizza Anonymous
 * 
 * @author Adam
 */
public class ServiceController {
	//create service directory list class
	private ServiceDirectory serviceDirectory = new ServiceDirectory();
	//create service occasion list class
	private ServiceOccasionList serviceOccasionList = new ServiceOccasionList();
		
	/**
	 * Returns the Service Directory of the system
	 * 
	 * @return the service directory
	 */
	public ServiceDirectory getServiceDirectory(){
		return serviceDirectory;
	}
	
	/**
	 * Returns the service occasion list of the system
	 * 
	 * @return The service occasions list
	 */
	public ServiceOccasionList getServiceOccasionList(){
		return serviceOccasionList;
	}
}
