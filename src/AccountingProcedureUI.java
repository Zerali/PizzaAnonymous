/**
 * Front end user interface for running the accounting procedure. 
 * In other words, an interface for the scheduler to run weekly reports.
 * 
 * @author Blake
 *
 */
public class AccountingProcedureUI {
	
	// Constants for the menu options
	public static final int WEEKLY_OPTION = 1;
	public static final int EXIT_OPTION = 2;
	
	public AccountingProcedureUI() {
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

}
