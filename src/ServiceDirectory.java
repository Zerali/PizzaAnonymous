import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Class to handle and hold all the services created by the system
 * 
 * @author Adam
 *
 */
public class ServiceDirectory {
	/**Data attributes **/
	private List<Service> servicesList = new LinkedList<Service>();		//List of all the services aka "Provider Directory"
	private int nextServiceID;											//Integer that keeps the next ID of created Service
	
	/** Constructor **/
	public ServiceDirectory()
	{
		//Set the first ID number on creation
		nextServiceID = 1;
	}
	
	
	/** Methods **/
	
	//Function to add a service to the list
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
	
	//Returns an iterator of the provider directory
	public Iterator<Service> getDirectoryIterator(){
		//create Iterator and send it
		Iterator<Service> serviceIt = servicesList.iterator();
		return serviceIt;
	}
	
	//Returns a service from the list by service ID
	public Service getService(int serviceID){
		//if empty list return nothing
		if(servicesList.isEmpty())
			return null;
		//find the service by ID within the list and return it
		for(int i = 0; i < servicesList.size(); i++){
			if(servicesList.get(i).getID() == serviceID){
				return servicesList.get(i);
			}
		}
		//no service found
		return null;
	}
}
