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
	
	/** 
	 * Constructor
	 */
	public ServiceDirectory()
	{
		//Set the first ID number on creation
		nextServiceID = 1;
	}
	
	
	// Methods
	
	/**
	 * function that adds a service to the system by creating the service, incrementing the ID count
	 * then putting in the list of services held by this object
	 * @param name the name of the service to be added
	 * @param cost the cost of the service to be added
	 * @return return false if the service was not added for any reason, true if it was succesfully added
	 */
	public boolean addService(String name, double cost){
		//Create the new service and give it its attributes
		Service serviceToAdd = new Service();
		
		//ensure cost is a positive number
		if(cost < 0)
			serviceToAdd.setCost(cost);
		else
			return false;
		
		//ensure name is 20 characters or less
		if(name.length() < 21)
			serviceToAdd.setName(name);
		else
			return false;
					
		serviceToAdd.setID(nextServiceID);
		//increment nextServiceID so we don't overlap numbers
		nextServiceID++;
		
		//Add the service to the list
		return servicesList.add(serviceToAdd);
	}
	
	/**
	 * Function iterates through the list of services until it finds the designate
	 * service, then deletes that service from the list
	 * @param serviceID the ID of the service to be deleted
	 * @return true if the service was succesfully deleted, false if it was not
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
	 * This function makes the list of services into an iterator, and send that iterator off
	 * @return an iterator of the services in the system
	 */
	public Iterator<Service> getDirectoryIterator(){
		//create Iterator and send it
		Iterator<Service> serviceIt = servicesList.iterator();
		return serviceIt;
	}
	
	/**
	 * this functions finds a service in the list and returns that object
	 * @param serviceID the ID of the service to be found
	 * @return null if the service was not found, the service if it was successfully found
	 */
	public Service getService(int serviceID){
	
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
