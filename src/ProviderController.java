import java.util.ArrayList;
import java.util.Iterator;

/**
 * Controls the list of providers and handles all logic on the list
 * 
 * TODO Documentation(Nick) Add more in depth information
 * 
 * @author Nick
 */
public class ProviderController {
	ArrayList<Provider> providers;
	
	/**
	 *  Initialize without a list
	 */
	public ProviderController()
	{
		providers = new ArrayList<Provider>();
	}
	
	/**
	 * Initialize with a list
	 * 
	 * TODO Documentation(Nick) add info to parameters
	 * 
	 * @param providers Array list of providers to initialize the controller
     *                  with
	 */
	public ProviderController(ArrayList<Provider> providers)
	{
		this.providers = providers;
	}
	
	/**
	 * This adds a provider by ID to providerDirectory
	 * 
	 * TODO Documentation(Nick) add info to parameters
	 * 
	 * @param ID
	 * @param name
	 * @param address
	 * @param city
	 * @param state
	 * @param ZIP
	 * @return boolean True if successfully, fail if a provider with ID already
	 *                 exists
	 */
	public boolean addProvider(int ID, String name, String address, String city, String state, int ZIP)
	{
		// if there is no provider with id ID in providers, create new provider
		if (getProvider(ID) == null)
		{
			providers.add(new Provider(ID, name, address, city, state, ZIP));
			return true;
		}
		// already a provider with id ID, fail to create due to duplicate
		else
			return false;
	}

	/**
	 * This searches the provider directory by ID and returns the provider
	 * 
	 * @param providerID Provider identification number
	 * @return Provider of the Identification number
	 */ 
	public Provider getProvider(int providerID)
	{
		// check each provider in providers for an id equal to ID
		for (Provider p : providers)
			if (p.getID() == providerID)
				return p;
		// did not find provider
		return null;
	}

	/**
	 * This deletes a provider by ID from providerDirectory
	 * 
	 * @param ID Member identification number
	 * @return boolean if successfully deleted, can only fail if no provider of ID
	 */
	public boolean deleteProvider(int ID)
	{
		// check each provider in providers for an id equal to ID and delete if equal
		for (Provider p : providers)
			if (p.getID() == ID)
			{
				providers.remove(p);
				return true;
			}
		// did not find provider
		return false;
	}

	/**
	 * This returns a list of all providers
	 * 
	 * @return Iterator of providers
	 */
	public Iterator<Provider> getProviderList()
	{
		// create an iterator version of providers
		Iterator<Provider> newIterator = providers.iterator();
		return newIterator;
	}
}
