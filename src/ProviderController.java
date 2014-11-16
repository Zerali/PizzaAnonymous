/**
 * 
 * Controls the list of providers and handles all logic on the list
 * 
 * @author nick
 * 
 */
import java.util.ArrayList;
import java.util.Iterator;

public class ProviderController {
	ArrayList<Provider> providers;
	
	/**
	 *  intialize without a list
	 */
	public ProviderController()
	{
		providers = new ArrayList<Provider>();
	}
	
	/**
	 *  Initialize with a list
	 * @param providers
	 */
	public ProviderController(ArrayList<Provider> providers)
	{
		this.providers = providers;
	}
	
	/**
	 *  this adds a provider by ID to providerDirectory
	 * @param ID
	 * @param name
	 * @param address
	 * @param city
	 * @param state
	 * @param ZIP
	 * @return boolean if successfully added which can only fail if there is already
	 * 											a provider with the passed ID
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
	 *  this searches the provider directory by ID and returns the provider
	 * @param providerID
	 * @return Provider of ID
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
	 *  this deletes a provider by ID from providerDirectory
	 * @param ID
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
	 *  this returns a list of all providers
	 * @return Iterator of providers
	 */
	public Iterator<Provider> getProviderList()
	{
		// create an iterator version of providers
		Iterator<Provider> newIterator = providers.iterator();
		return newIterator;
	}

}
