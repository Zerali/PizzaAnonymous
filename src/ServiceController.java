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
	 * This method returns service directory
	 * 
	 * @return Service Directory Object
	 */
	public ServiceDirectory getServiceDirectory(){
		return serviceDirectory;
	}
	
	/**
	 * This method returns the service occasion list
	 * 
	 * @return Service Occasion List Object
	 */
	public ServiceOccasionList getServiceOccasionList(){
		return serviceOccasionList;
	}
}
