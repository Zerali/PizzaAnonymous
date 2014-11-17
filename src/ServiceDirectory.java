import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Class to handle and hold all the services created by the system
 * 
 * @author Adam
 */
public class ServiceDirectory {
	// List of all the services aka "Provider Directory"
	private List<Service> servicesList = new LinkedList<Service>();
	// Integer that keeps the next ID of created Service
	private int nextServiceID;
	
	/**
	 * Service Directory Constructor
	 */
	public ServiceDirectory()
	{
		//Set the first ID number on creation
		nextServiceID = 1;
	}
	
	/**
	 * Function to add a service occasion to the list
	 * 
	 * @param name Name of the service to add to the list
	 * @param cost Cost of the service to add to the list
	 * @return True if service was added to the list, False otherwise
	 */
	public boolean addService(String name, float cost){
		//Create the new service and give it its attributes
		Service serviceToAdd = new Service();
		serviceToAdd.setCost(cost);
		serviceToAdd.setName(name);
		serviceToAdd.setID(nextServiceID);
		//increment nextServiceID so we don't overlap numbers
		nextServiceID++;
		
		//Add the service to the list
		return servicesList.add(serviceToAdd);
	}
	
	/**
	 * Function to delete a service occasion to the list
	 * 
	 * @param serviceID Identification number of the service
	 * @return True if service was removed, otherwise false
	 */
	public boolean deleteService(int serviceID)
	{
		for(int i = 0; i < servicesList.size(); i++)
		{
			if(servicesList.get(i).getID() == serviceID)
			{
				servicesList.remove(i);
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Returns an iterator of the provider directory
	 * 
	 * @return Iterator of the service list
	 */
	public Iterator<Service> getDirectoryIterator(){
		// create Iterator and return it
		Iterator<Service> serviceIt = servicesList.iterator();
		return serviceIt;
	}
	
	/**
	 * Returns a service from the list by service ID
	 * 
	 * @param serviceID Identification number of the service provided
	 * @return Service if it exists, otherwise null
	 */
	public Service getService(int serviceID){
		// if empty list return nothing
		if(servicesList.isEmpty())
			return null;
		
		// find the service by ID within the list and return it
		for(int i = 0; i < servicesList.size(); i++){
			if(servicesList.get(i).getID() == serviceID){
				return servicesList.get(i);
			}
		}
		
		// no service found
		return null;
	}
}
