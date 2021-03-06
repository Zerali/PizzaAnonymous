import java.io.InputStream;
import java.util.Scanner;

/**
 * Initial User Interface that shows options to switch to the 
 * four other sub interfaces.
 * 
 * @author Blake, Nick, Adam
 */
public class BaseUI {
	
	// Constants for the menu options
	public static final int MAINTENANCE_OPTION = 1;
	public static final int PROVIDER_OPTION = 2;
	public static final int REPORT_OPTION = 3;
	public static final int ACCOUNT_OPTION = 4;
	public static final int SAVE_OPTION = 5;
	public static final int EXIT_OPTION = 6;
	
	// The input stream that user input is taken from
	InputStream userInputStream;
	
	// Whether we want to keep using the UI or not
	private boolean stayInUI = true;

	public static void main(String[] args) {
		new BaseUI(System.in);
	}
	
	/**
	 * Create the main program menu. From this menu, launch into other submenus. 
	 * Will repeatedly ask for a menu option and perform that action. 
	 * 
	 * @param userInputStream An opened input stream for user input. 
	 * Is not closed afterwards.
	 */
	public BaseUI(InputStream userInputStream)
	{
		this.userInputStream = userInputStream;
		int menuOption; // The user's menu option
		
		while(stayInUI)
		{
			// Show the menu options
			runUI();

			// Get the user's menu option
			menuOption = getMenuInput(userInputStream, MAINTENANCE_OPTION, EXIT_OPTION);

			// Handle that option
			handleMenuOption(menuOption);
		}
	}
	
	/**
	 * Prints out the interface options
	 */
	public void runUI()
	{
		System.out.println("------------------------------");
		System.out.println("Menu Options:");
		System.out.println(MAINTENANCE_OPTION + ": Maintenance");
		System.out.println(PROVIDER_OPTION + ": Provider");
		System.out.println(REPORT_OPTION + ": Report");
		System.out.println(ACCOUNT_OPTION + ": Accounting Procedure");
		System.out.println(SAVE_OPTION + ": Save Data");
		System.out.println(EXIT_OPTION + ": Exit");
		System.out.println("------------------------------");
	}
	
	/**
	 * Handle a menu option chosen by the user.This method will accept an
	 * option assumed to be valid
	 * 
	 * @param option The user's menu option
	 */
	private void handleMenuOption(int option)
	{
		// Depending on the option, switch to a different menu
		switch(option)
		{
		case MAINTENANCE_OPTION:
			new MaintenanceUI(userInputStream);
			break;
		case PROVIDER_OPTION:
			new ProviderUI(userInputStream);
			break;
		case REPORT_OPTION:
			new ReportUI(userInputStream);
			break;
		case ACCOUNT_OPTION:
			new AccountingProcedureUI(userInputStream);
			break;
		case SAVE_OPTION:
			PizzaAnonymous.getInstance().saveData();
			System.out.println("Saved data.");
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
	 * 
	 * @param userInput The InputStream that user input is accepted from
	 * @param lowerBound The lowest possible menu option
	 * @param upperBound The highest possible menu option
	 * @return A valid menu option
	 */
	public static int getMenuInput(InputStream userInput, int lowerBound, int upperBound)
	{
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(userInput); // Scanner to read input
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
		
		return input;
	}
	
	/**
	 * Prompt the user for an integer.  Validates that the input is indeed an
	 * integer.
	 * 
	 * @param userInput The InputStream user input is accepted from
	 * @param prompt The message to display to the user, asking for input
	 * @return An integer of the user's choosing
	 */
	public static int getIntegerInput(InputStream userInput, String prompt)
	{
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(userInput); // Scanner to read input
		int input = -1; // The integer the user inputs
		
		// Show a prompt
		System.out.print(prompt);
		System.out.flush();
		
		while(true)
		{
			// Get the user's input as an integer
			try {
				input = scanner.nextInt();
				
				// Break since input was a valid integer
				break;
			} catch(Exception e) {
				// Eat the invalid input
				scanner.nextLine();
				
				// Invalid, so prompt again and let us loop around
				System.out.print("Invalid input. Please enter a number: ");
				System.out.flush();
				
				continue;
			}
		}
		
		return input;
	}
	
	/**
	 * Get user confirmation for an action. Will display the prompt message and
	 * ask for a yes/no response.
	 * 
	 * @param userInput The InputStream user input is accepted from
	 * @param prompt The prompt message
	 * @return True if the answer is yes (confirmed)
	 */
	public static boolean getConfirmation(InputStream userInput, String prompt)
	{
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(userInput); // Scanner to read input
		boolean confirmed = false; // The user choice
		
		// Show a prompt
		System.out.print(prompt);
		System.out.print(" [y]es / [n]o: ");
		System.out.flush();
		
		while(true)
		{
			// Get the user's input, simplify it
			String input = scanner.nextLine().trim().toLowerCase();
			
			// Check to see if it matches y, yes, n, or no
			if(input.equals("y") || input.equals("yes"))
			{
				// Chose yes, so confirmed = true
				confirmed = true;
				
				break;
			}
			else if(input.equals("n") || input.equals("no"))
			{
				// Chose no, so confirmed = false;
				confirmed = false;
				
				break;
			}
			else {
				// Invalid response, try again
				System.out.print("Invalid response. Please answer [y]es or [n]o: ");
				System.out.flush();
				
				continue;
			}
		}
		
		return confirmed;
	}
	
	/**
	 * Prompt the user for a string.
	 * 
	 * @param userInput The InputStream user input is accepted from
	 * @param prompt The message to display to the user, asking for input
	 * @return An string of the user's choosing
	 */
	public static String getStringInput(InputStream userInput, String prompt)
	{
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(userInput); // Scanner to read input
		String input; // The integer the user inputs
		
		// Show a prompt
		System.out.print(prompt);
		System.out.flush();
		
		// Get the user's input

		input = scanner.nextLine();
		
		return input;
	}
	
	/**
	 * Prompt the user for a float and validates input
	 * 
	 * @param userInput The InputStream user input is accepted from
	 * @param prompt The message to display to the user, asking for input
	 * @return An float of the user's choosing
	 */
	public static double getDoubleInput(InputStream userInput, String prompt)
	{
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(userInput); // Scanner to read input
		double input = -1; // The float the user inputs
		
		// Show a prompt
		System.out.print(prompt);
		System.out.flush();
		
		while(true)
		{
			// Get the user's input as an float
			try {
				input = scanner.nextFloat();
				
				// Break since float was a valid integer
				break;
			} catch(Exception e) {
				// Eat the invalid input
				scanner.nextLine();
				
				// Invalid, so prompt again and let us loop around
				System.out.print("Invalid input. Please enter a number: ");
				System.out.flush();
				
				continue;
			}
		}
		
		return input;
	}
}
