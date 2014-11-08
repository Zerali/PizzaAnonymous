/**
 * Facade class between user interfaces and controllers
 * 
 * @author Neil
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
	 * Create the weekly reports - ie the four types of reports
	 * @return True if the report creation was successful
	 */
	public boolean createWeeklyReports()
	{
		reportController.createWeeklyReports();
	}
	
	/**
	 * Create a report of services provided to the given member
	 * @return True if the report creation was successful
	 */
	public boolean createMemberSvcReport(int memberID)
	{
		reportController.createMemberSvcReport(memberID);
	}
	
	/**
	 * Create a report of services provided by the given provider
	 * @return True if the report creation was successful
	 */
	public boolean createProviderSvcReport(int providerID)
	{
		reportController.createProviderSvcReport(providerID);
	}
	
	/**
	 * Create an EFT report
	 * @return True if the report creation was successful
	 */
	public boolean createEFTReport()
	{
		reportController.createEFTReport();
	}
	
	/**
	 * Create a report summarizing activities for the past week
	 * @return True if the report creation was successful
	 */
	public boolean createSummaryReport()
	{
		reportController.createSummaryReport();
	}

}
