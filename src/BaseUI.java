import java.util.Scanner;

/**
 * Initial User Interface that shows options to switch to the 
 * four other sub interfaces.
 * @author Blake, Nick, Adam
 */
public class BaseUI {
	
	// Constants for the menu options
	public static final int MAINTENANCE_OPTION = 1;
	public static final int PROVIDER_OPTION = 2;
	public static final int REPORT_OPTION = 3;
	public static final int ACCOUNT_OPTION = 4;
	public static final int EXIT_OPTION = 5;

	public static void main(String[] args) {
		new BaseUI();
	}
	
	public BaseUI()
	{
		int menuOption; // The user's menu option
		
		// Show the menu options
		runUI();
		
		// Get the user's menu option
		menuOption = getMenuInput(MAINTENANCE_OPTION, EXIT_OPTION);
		
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
		System.out.println(MAINTENANCE_OPTION + ": Maintenance");
		System.out.println(PROVIDER_OPTION + ": Provider");
		System.out.println(REPORT_OPTION + ": Report");
		System.out.println(ACCOUNT_OPTION + ": Accounting Procedure");
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
		case MAINTENANCE_OPTION:
			new MaintenanceUI();
			break;
		case PROVIDER_OPTION:

			break;
		case REPORT_OPTION:

			break;
		case ACCOUNT_OPTION:

			break;
		case EXIT_OPTION:
			System.exit(0);
			break;
		default:
			System.err.println("Invalid option passed to handleMenuOption");
			break;
		}
	}
	
	/**
	 * Get a user's choice for a menu option. 
	 * This is a numerical value bounded on the possible options.
	 * @param lowerBound The lowest possible menu option
	 * @param upperBound The highest possible menu option
	 * @return A valid menu option
	 */
	public static int getMenuInput(int lowerBound, int upperBound)
	{
		Scanner scanner = new Scanner(System.in); // Scanner to read input
		int input = -1; // The menu option
		
		// Show a prompt
		System.out.print("Please enter your menu option: ");
		System.out.flush();
		
		while(true)
		{
			// Get the user's input as an integer
			try {
				input = scanner.nextInt();
			} catch(Exception e) {
				// Eat the invalid input
				scanner.nextLine();
				
				// Invalid, so prompt again and let us loop around
				System.out.print("Invalid option. Please enter another: ");
				System.out.flush();
				
				continue;
			}
			
			// Make sure it's within bounds
			if(lowerBound > input || input > upperBound)
			{
				// Invalid, so prompt again and let us loop around
				System.out.print("Invalid option. Please enter another: ");
				System.out.flush();
				
				continue;
			} else {
				// Within bounds, break the loop
				break;
			}
		}
		
		scanner.close();
		
		return input;
	}

}
