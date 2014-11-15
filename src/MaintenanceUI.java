import java.io.InputStream;


/**
 * The front end user interface for report related actions.
 * 
 * @author Amila DeSilva, Adam Defoe
 *
 */

public class MaintenanceUI {

	
	// Constants for the menu options
	public static final int MEMBER_OPTION = 1;
	public static final int PROVIDER_OPTION = 2;
	public static final int SERVICE_OPTION = 3;
	public static final int EXIT_OPTION = 4;
	
	
	//Constants for secondary menu options delete = 2 because service only has two options
	public static final int ADD_OPTION = 1;
	public static final int DELETE_OPTION = 2;
	public static final int EDIT_OPTION = 3;
	
	// The input stream that user input is taken from
	private InputStream userInputStream;
	
	// Whether we want to stay in the Maintenance UI
	private boolean stayInMaintenance = true;
	
	/**
	 * Create and show the maintenance interface
	 */
	public MaintenanceUI(InputStream userInputStream)
	{
		this.userInputStream = userInputStream;
		int menuOption; // The user's menu option
		
		while(stayInMaintenance)
		{
			// Show the menu options
			runUI();

			// Get the user's menu option
			menuOption = BaseUI.getMenuInput(userInputStream, MEMBER_OPTION, EXIT_OPTION);

			// Handle that option
			handleMenuOption(menuOption);
		}
	}
	
	public void runUI()
	{
		System.out.println("The following menus are available:");
		System.out.println(MEMBER_OPTION + ": Add, edit, or delete a member");
		System.out.println(PROVIDER_OPTION + ": Add, edit, or delete a provider");
		System.out.println(SERVICE_OPTION + ": Add or delete a service");
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
		case MEMBER_OPTION:
			runMembersMenu();
			break;
			
		case PROVIDER_OPTION:
			runProvidersMenu();
			break;
			
		case SERVICE_OPTION:
			runServicesMenu();
			break;
			
		case EXIT_OPTION:
			// Return to the base UI
			stayInMaintenance = false;
			new BaseUI(userInputStream);
			break;
			
		default:
			System.err.println("Invalid option passed to handleMenuOption");
			break;
		}
	}
	
	/**
	 * Runs the member part of the menu options
	 * Accepts input and decides an action
	 */
	public void runMembersMenu()
	{
		int menuOption;
		
		System.out.println("Choose an action to take on a Member:");
		System.out.println(ADD_OPTION + ": Add a member");
		System.out.println(DELETE_OPTION + ": Delete a member");
		System.out.println(EDIT_OPTION + ": Edit a member");
		System.out.println(EXIT_OPTION + ": Exit");
		
		// Get the user's menu option
		menuOption = BaseUI.getMenuInput(userInputStream, ADD_OPTION, EXIT_OPTION);
		
		// Depending on the option, switch to a different menu
			switch(menuOption)
			{
			case ADD_OPTION:
				addMember();
				break;
					
			case EDIT_OPTION:
				editMember();
				break;
					
			case DELETE_OPTION:
				deleteMember();
				break;
					
			case EXIT_OPTION:
				// Return to the base UI
				new BaseUI(userInputStream);
				break;
					
			default:
				System.err.println("Invalid option passed to handleMenuOption");
				break;
			}
		
	}
	
	/**
	 * Runs the provider part of the menu options
	 * Accepts input and decides an action
	 */
	public void runProvidersMenu()
	{
		int menuOption;
		
		System.out.println("Choose an action to take on a Provider:");
		System.out.println(ADD_OPTION + ": Add a provider");
		System.out.println(DELETE_OPTION + ": Delete a provider");
		System.out.println(EDIT_OPTION + ": Edit a provider");
		System.out.println(EXIT_OPTION + ": Exit");
		
		// Get the user's menu option
		menuOption = BaseUI.getMenuInput(userInputStream, ADD_OPTION, EXIT_OPTION);
		
		// Depending on the option, switch to a different menu
			switch(menuOption)
			{
			case ADD_OPTION:
				addProvider();
				break;
					
			case EDIT_OPTION:
				editProvider();
				break;
					
			case DELETE_OPTION:
				deleteProvider();
				break;
					
			case EXIT_OPTION:
				// Return to the base UI
				new BaseUI(userInputStream);
				break;
					
			default:
				System.err.println("Invalid option passed to handleMenuOption");
				break;
			}
		
	}
	
	/**
	 * Runs the service part of the menu options
	 * Accepts input and decides an action
	 */
	public void runServicesMenu()
	{
		int menuOption;
		
		System.out.println("Choose an action to take on a Service:");
		System.out.println(ADD_OPTION + ": Add a service");
		System.out.println(DELETE_OPTION + ": Delete a service");
		System.out.println(EDIT_OPTION + ": Exit");
		
		// Get the user's menu option
		menuOption = BaseUI.getMenuInput(userInputStream, ADD_OPTION, EDIT_OPTION);
		
		// Depending on the option, switch to a different menu
			switch(menuOption)
			{
			case ADD_OPTION:
				requestAddService();
				break;
					
			case DELETE_OPTION:
				requestDeleteService();
				break;
					
			case EDIT_OPTION:
				// Return to the base UI
				new BaseUI(userInputStream);
				break;
					
			default:
				System.err.println("Invalid option passed to handleMenuOption");
				break;
			}
		
	}
	
	public void addMember()
	{
		//TODO
	}
	public void editMember()
	{
		//TODO
	}
	public void deleteMember()
	{
		//TODO
	}
	public void addProvider()
	{
		//TODO
	}
	public void editProvider()
	{
		//TODO
	}
	public void deleteProvider()
	{
		//TODO
	}
	public void requestAddService()
	{
		String serviceName;
		float serviceCost;

		serviceName = BaseUI.getStringInput(userInputStream, "Enter service name: ");
		serviceCost = BaseUI.getFloatInput(userInputStream, "Enter service cost: ");
	
		
		//Try to add the service
		if(PizzaAnonymous.getInstance().addService(serviceName, serviceCost))
			System.out.println("Succesfully added");
		else
			System.out.println("Failed to add");
	}
	
	public void requestDeleteService()
	{
		int serviceToDelete;
		
		//Get service ID
		serviceToDelete = BaseUI.getIntegerInput(userInputStream, "Enter service ID: ");
		
		if(PizzaAnonymous.getInstance().deleteService(serviceToDelete))
			System.out.println("Succesfully deleted");
		else
			System.out.println("Failed to delete");
	}
	
}
