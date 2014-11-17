import java.util.Iterator;

/**
 * Facade class between user interfaces and controllers
 * 
 * @author Neil, Blake, Adam
 */
public class PizzaAnonymous {
	
	/** Singleton instance of this class */
	private static PizzaAnonymous instance;
	
	/** The subcontroller dealing with XML functionality */
	private XMLController xmlController;
	
	/** The subcontroller dealing with Member functionality */
	private MemberController memberController;
	
	/** The subcontroller dealing with Provider functionality */
	private ProviderController providerController;
	
	/** The subcontroller dealing with Service functionality */
	private ServiceController serviceController;
	
	/** The subcontroller dealing with report functionality */
	private ReportController reportController;
	
	/**
	 * Constructor. When this object is first created, initialization is done.
	 * This includes the deserialization of XML data into data fields.
	 */
	private PizzaAnonymous()
	{
		// Create the XML controller since we'll load others from it
		xmlController = new XMLController();
		
		// Then load all the others from it
		memberController = xmlController.loadMembers();
		providerController = xmlController.loadProviders();
		serviceController = xmlController.loadServices();
		
		// Well, almost all the others. Report controller is on its own
		reportController = new ReportController();
	}
	
	/**
	 * Return the single instance of this class.
	 * Create it if it has not been created yet.
	 * 
	 * @return A reference to the PizzaAnonymous facade
	 */
	public static PizzaAnonymous getInstance()
	{
		if(instance == null)
		{
			instance = new PizzaAnonymous();
		}
		
		return instance;
	}
	
	/**
	 * Save data to XML.  Saves the data found in the Member, 
	 * Provider, and Service controllers.
	 */
	public void saveData()
	{
		// Tell the XML Controller to save all of the objects
		xmlController.saveMembers(memberController);
		xmlController.saveProviders(providerController);
		xmlController.saveServices(serviceController);
	}
	
	/**
	 * Get a member based on their ID number
	 * 
	 * @param memberID The unique ID number of the member
	 * @return A reference to the Member object, or null if nonexistant member
	 */
	public Member getMember(int memberID)
	{
		return memberController.getMember(memberID);
	}
	
	/**
	 * Get a member based on its service code
	 * 
	 * @param serviceID The unique service code
	 * @return A reference to the Service object, or null if nonexistant service
	 */
	public Service getService(int serviceID)
	{
		return serviceController.getServiceDirectory().getService(serviceID);
	}
	
	/**
	 * Get the service directory as an Iterator
	 * 
	 * @return Iterator of the service directory
	 */
	public Iterator<Service> lookupServiceDirectory()
	{
		return serviceController.getServiceDirectory().getDirectoryIterator();
	}
	
	/**
	 * 
	 * @param name : the name of the member
	 * @param ID : the id of the member
	 * @param address : the address of the member
	 * @param city : the city of the member
	 * @param state : the state of the member
	 * @param ZIP : the zip code of the member
	 * @param validStatus : whether the member is valid or not
	 * @return returns true if the add was successful, otherwise false
	 */
	public boolean addMember(String name, int ID, String address, String city, String state, int ZIP, boolean validStatus)
	// Add a member to the database
	{
		return memberController.addMember(name, ID, address, city, state, ZIP, validStatus);
	}
	
	/**
	 * Delete a member from the database
	 * @param ID : the ID of the member to be deleted
	 * @return returns a boolean value
	 */
	public boolean deleteMember(int ID)
	{
		return memberController.deleteMember(ID);
	}
	
	
	/**
	 * Add a service to the system
	 * 
	 * @param name the name of the service
	 * @param cost the cost of the service
	 * @return True if the service was added and false if it was not
	 */
	public boolean addService(String name, double cost)
	{
		return serviceController.getServiceDirectory().addService(name, cost);
	}
	
	/**
	 * Add a service occasion to the system
	 * 
	 * @param date when the provider provided the service
	 * @param provider ID of the provider adding the occasion
	 * @param member ID of the member that did the service
	 * @param service ID of the service provided
	 * @param comments comments on the service provided
	 */
	public void addServiceOccasion(String date, int provider, int member, int service, String comments)
	{
		serviceController.getServiceOccasionList().addServiceOccasion(date, provider, member, service, comments);
	}
	
	/**
	 * Delete service from the system
	 * 
	 * @param serviceID the ID of the service
	 * @return True if the service was deleted and false if it was not
	 */
	public boolean deleteService(int serviceID)
	{
		return serviceController.getServiceDirectory().deleteService(serviceID);
	}
	
	/**
	 * Delete provider from the system
	 * 
	 * @param providerID the ID of the provider
	 * @return True if the service was deleted and false if it was not
	 */
	public boolean deleteProvider(int providerID)
	{
		return providerController.deleteProvider(providerID);
	}
	
	/**
	 * Get a provider based on their ID number
	 * 
	 * @param providerID The unique ID number of the provider
	 * @return A reference to the Provider object, or null if nonexistant provider
	 */
	public Provider getProvider(int providerID)
	{
		return providerController.getProvider(providerID);
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
		return providerController.addProvider(ID, name, address, city, state, ZIP);
	}
	
	/**
	 * Obtain an iterator view of the Pizza Anonymous members
	 * 
	 * @return An iterator over all of the Members
	 */
	public Iterator<Member> getMemberList()
	{
		return memberController.getMemberList();
	}
	
	/**
	 * Obtain an iterator view of the Pizza Anonymous providers
	 * 
	 * @return An iterator over all of the Providers
	 */
	public Iterator<Provider> getProviderList()
	{
		return providerController.getProviderList();
	}
	
	/**
	 * Obtain an iterator view of the ServiceOccasion records
	 * 
	 * @return An iterator over all of the ServiceOccasions
	 */
	public Iterator<ServiceOccasion> getServiceOccasions()
	{
		return serviceController.getServiceOccasionList().getServiceOccasionIterator();
	}
	
	/**
	 * Create the weekly reports - ie the four types of reports
	 * 
	 * @return True if the report creation was successful
	 */
	public boolean createWeeklyReports()
	{
		return reportController.createWeeklyReports();
	}
	
	/**
	 * Create a report of services provided to the given member
	 * 
	 * @return True if the report creation was successful
	 */
	public boolean createMemberSvcReport(int memberID)
	{
		return reportController.makeMemberSvcReport(memberID);
	}
	
	/**
	 * Create a report of services provided by the given provider
	 * 
	 * @return True if the report creation was successful
	 */
	public boolean createProviderSvcReport(int providerID)
	{
		return reportController.makeProviderSvcReport(providerID);
	}
	
	/**
	 * Create an EFT report
	 * 
	 * @return True if the report creation was successful
	 */
	public boolean createEFTReport()
	{
		return reportController.makeEFTReport();
	}
	
	/**
	 * Create a report summarizing activities for the past week
	 * 
	 * @return True if the report creation was successful
	 */
	public boolean createSummaryReport()
	{
		return reportController.makeSummaryReport();
	}
}
