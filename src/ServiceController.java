
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
	
	/**Methods**/
	//this method returns service directory
	public ServiceDirectory getServiceDirectory(){
		return serviceDirectory;
	}
	
	//this method returns the service occasion list
	public ServiceOccasionList getServiceOccasionList(){
		return serviceOccasionList;
	}
}
