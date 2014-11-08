import java.util.Iterator;

/**
 * Facade class between user interfaces and controllers
 * 
 * @author Neil, Blake
 *
 */
public class PizzaAnonymous {
	
	/** Singleton instance of this class */
	private static PizzaAnonymous instance;
	
	/** The subcontroller dealing with report functionality */
	private ReportController reportController;
	
	/**
	 * Constructor. When this object is first created, initialization is done.
	 * This includes the deserialization of XML data into data fields.
	 */
	private PizzaAnonymous()
	{
		// TODO: XML stuff. Create XML Controller & load other controllers
	}
	
	/**
	 * Return the single instance of this class.
	 * Create it if it has not been created yet.
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
	 * Get a member based on their ID number
	 * @param memberID The unique ID number of the member
	 * @return A reference to the Member object, or null if nonexistant member
	 */
	public Member getMember(int memberID)
	{
		return memberController.getMember(memberID);
	}
	
	/**
	 * Get a member based on its service code
	 * @param serviceID The unique service code
	 * @return A reference to the Service object, or null if nonexistant service
	 */
	public Service getService(int serviceID)
	{
		return serviceController.getServiceDirectory().getService(serviceID);
	}
	
	/**
	 * Get a provider based on their ID number
	 * @param providerID The unique ID number of the provider
	 * @return A reference to the Provider object, or null if nonexistant provider
	 */
	public Provider getProvider(int providerID)
	{
		return providerController.getProvider(providerID);
	}
	
	/**
	 * Obtain an iterator view of the Pizza Anonymous members
	 * @return An iterator over all of the Members
	 */
	public Iterator<Member> getMemberList()
	{
		return memberController.getMemberList();
	}
	
	/**
	 * Obtain an iterator view of the Pizza Anonymous providers
	 * @return An iterator over all of the Providers
	 */
	public Iterator<Provider> getProviderList()
	{
		return providerController.getProviderList();
	}
	
	/**
	 * Obtain an iterator view of the ServiceOccasion records
	 * @return An iterator over all of the ServiceOccasions
	 */
	public Iterator<ServiceOccasion> getServiceOccasions()
	{
		return serviceController.getOccasionList().getServiceOccasionIterator();
	}
	
	/**
	 * Create the weekly reports - ie the four types of reports
	 * @return True if the report creation was successful
	 */
	public boolean createWeeklyReports()
	{
		return reportController.createWeeklyReports();
	}
	
	/**
	 * Create a report of services provided to the given member
	 * @return True if the report creation was successful
	 */
	public boolean createMemberSvcReport(int memberID)
	{
		return reportController.makeMemberSvcReport(memberID);
	}
	
	/**
	 * Create a report of services provided by the given provider
	 * @return True if the report creation was successful
	 */
	public boolean createProviderSvcReport(int providerID)
	{
		return reportController.makeProviderSvcReport(providerID);
	}
	
	/**
	 * Create an EFT report
	 * @return True if the report creation was successful
	 */
	public boolean createEFTReport()
	{
		return reportController.makeEFTReport();
	}
	
	/**
	 * Create a report summarizing activities for the past week
	 * @return True if the report creation was successful
	 */
	public boolean createSummaryReport()
	{
		return reportController.makeSummaryReport();
	}

}
