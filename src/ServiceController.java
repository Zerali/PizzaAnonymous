
/**
 * This class controls the happening of the services and service occasion list classes
 * It helps free up some of the functionality from Pizza Anonymous
 * 
 * @author Adam
 *
 */
public class ServiceController {
	/**Attributes **/
	//create two of our list classes
	private ServiceDirectory serviceDirectory = new ServiceDirectory();
	private ServiceOccasionList serviceOccasionList = new ServiceOccasionList();
	
	// Methods
	
	/**
	 * returns the Service Directory of the system
	 * 
	 * @return the service directory
	 */
	public ServiceDirectory getServiceDirectory(){
		return serviceDirectory;
	}
	
	/**
	 * returns the service occasion list of the system
	 * 
	 * @return the service occasions list
	 */
	public ServiceOccasionList getServiceOccasionList(){
		return serviceOccasionList;
	}
}
