/**
 * The front end user interface for report related actions.
 * 
 * @author Blake
 *
 */
public class ReportUI {
	
	// Constants for the menu options
	public static final int WEEKLY_OPTION = 1;
	public static final int MEMBER_OPTION = 2;
	public static final int PROVIDER_OPTION = 3;
	public static final int EFT_OPTION = 4;
	public static final int SUMMARY_OPTION = 5;
	public static final int EXIT_OPTION = 6;

	public ReportUI() {
		int menuOption; // The user's menu option
		
		// Show the menu options
		runUI();
		
		// Get the user's menu option
		menuOption = BaseUI.getMenuInput(WEEKLY_OPTION, EXIT_OPTION);
		
		// Handle that option
		handleMenuOption(menuOption);
	}
	
	/**
	 * Show the interface.
	 * Presents the options
	 */
	public void runUI()
	{
		System.out.println("The following menus are available:");
		System.out.println(WEEKLY_OPTION + ": Run weekly report procedure");
		System.out.println(MEMBER_OPTION + ": Create a Member Service Report");
		System.out.println(PROVIDER_OPTION + ": Create a Provider Service Report");
		System.out.println(EFT_OPTION + ": Create an EFT Report");
		System.out.println(SUMMARY_OPTION + ": Create a Summary Report for the past week");
		System.out.println(EXIT_OPTION + ": Exit");
	}
	
	/**
	 * Handle a menu option chosen by the user. 
	 * This method will accept an option assumed to be valid
	 * @param option The user's menu option
	 */
	private void handleMenuOption(int option)
	{
		// Depending on the option, switch to a different menu
		switch(option)
		{
		case WEEKLY_OPTION:
			createWeeklyReports();
			break;
			
		case MEMBER_OPTION:
			createMemberSvcReport();
			break;
			
		case PROVIDER_OPTION:
			createProviderSvcReport();
			break;
			
		case EFT_OPTION:
			createEFTReport();
			break;
			
		case SUMMARY_OPTION:
			createSummaryReport();
			break;
			
		case EXIT_OPTION:
			// Return to the base UI
			new BaseUI();
			break;
			
		default:
			System.err.println("Invalid option passed to handleMenuOption");
			break;
		}
	}
	
	/**
	 * Create the reports for the week. 
	 * Will create reports for all members and providers.
	 * Also will create the EFT and Summary report.
	 * Status messages will be shown, and the files should be created.
	 */
	private void createWeeklyReports()
	{
		// Confirm action
		if(BaseUI.getConfirmation("Are you sure you want to create all weekly reports?"))
		{
			// Pass through to underlying system, have it done
			if(PizzaAnonymous.getInstance().createWeeklyReports())
			{
				// Success message, since the call was true
				System.out.println("Weekly reports created successfully");
			} else {
				// Failure message, since call returned false
				System.out.println("Could not create weekly reports");
			}
		}
	}
	
	/**
	 * Create a service report for a specific member. 
	 * Prompts for the ID of the member.
	 * On completion, a file should have been created
	 */
	private void createMemberSvcReport()
	{
		// Ask for the Member ID
		int memberID = BaseUI.getIntegerInput("Enter the ID of the member to create a report for: ");
		
		// Confirm action
		if(BaseUI.getConfirmation("Are you sure you want to create a report for member " + memberID + "?"))
		{
			// Pass through to underlying system, have it done
			if(PizzaAnonymous.getInstance().createMemberSvcReport(memberID))
			{
				// Success message, since the call was true
				System.out.println("Member report for member #" + memberID + " created successfully");
			} else {
				// Failure message, since call returned false
				System.out.println("Could not create member report for member #" + memberID);
			}
		}
	}
	
	/**
	 * Create a service report for a specific provider. 
	 * Prompts for the ID of the provider.
	 * On completion, a file should have been created
	 */
	private void createProviderSvcReport()
	{
		// Ask for the Provider ID
		int providerID = BaseUI.getIntegerInput("Enter the ID of the provider to create a report for: ");

		// Confirm action
		if(BaseUI.getConfirmation("Are you sure you want to create a report for provider " + providerID + "?"))
		{
			// Pass through to underlying system, have it done
			if(PizzaAnonymous.getInstance().createProviderSvcReport(providerID))
			{
				// Success message, since the call was true
				System.out.println("Provider report for provider #" + providerID + " created successfully");
			} else {
				// Failure message, since call returned false
				System.out.println("Could not create provider report for provider #" + providerID);
			}
		}
	}
	
	/**
	 * Create an EFT report. 
	 * On completion, a file should store the information.
	 */
	private void createEFTReport()
	{
		// Confirm action
		if(BaseUI.getConfirmation("Are you sure you want to create the EFT report?"))
		{
			// Pass through to underlying system, have it done
			if(PizzaAnonymous.getInstance().createEFTReport())
			{
				// Success message, since the call was true
				System.out.println("EFT report created successfully");
			} else {
				// Failure message, since call returned false
				System.out.println("Could not create EFT report");
			}
		}
	}
	
	/**
	 * Create a summary report for the past week.
	 * On completion, a file should store the report
	 */
	private void createSummaryReport()
	{
		// Confirm action
		if(BaseUI.getConfirmation("Are you sure you want to create the week's summary report?"))
		{
			// Pass through to underlying system, have it done
			if(PizzaAnonymous.getInstance().createSummaryReport())
			{
				// Success message, since the call was true
				System.out.println("Summary report created successfully");
			} else {
				// Failure message, since call returned false
				System.out.println("Could not create summary report");
			}
		}
	}

}
