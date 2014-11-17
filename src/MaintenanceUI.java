import java.io.InputStream;


/**
 * The front end user interface for report related actions.
 * 
 * @author Amila DeSilva, Adam Defoe, Nick Anderson
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
	 * @param none
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
	
	/**
	 * TODO Documentation(Amila DeSilva, Adam Defoe)
	 */
	public void addMember()
	{
		//TODO
	}
	
	/**
	 * TODO Documentation(Amila DeSilva, Adam Defoe)
	 */
	public void editMember()
	{
		//TODO
	}
	
	/**
	 * TODO Documentation(Amila DeSilva, Adam Defoe)
	 */
	public void deleteMember()
	{
		//TODO
	}
	
	/**
	 * Add a provider to providerList by taking in input
	 */
	public void addProvider()
	{
		// get needed provider information
		String name = "";
		int ID = 0;
		String address = "";
		String city = "";
		String state = "";
		int ZIP = 0;

		// loop getting inputs until appropriate length is entered for all values
		while(name.length() > 25 || name.length() < 1)
		{
			name = BaseUI.getStringInput(userInputStream, "Enter name (25 char max): ");
			if (name.length() > 25 || name.length() < 1)
				System.out.println ("Error! Name not valid!");
		}
		while(ID > 999999999 || ID < 1)
		{
			ID = BaseUI.getIntegerInput(userInputStream, "Enter ID (9 digit max): ");
			if (ID > 999999999 || ID < 1)
				System.out.println ("Error! ID not valid");
			// IDs are unique
			if (PizzaAnonymous.getInstance().getService(ID) != null)
			{
				System.out.println ("Error! ID already taken!");
				continue;
			}
		}
		while(address.length() > 25 || address.length() < 1)
		{
			address = BaseUI.getStringInput(userInputStream, "Enter address (25 char max): ");
			if (address.length() > 25 || address.length() < 1)
				System.out.println ("Error! Address not valid");
		}
		while(city.length() > 14 || city.length() < 1)
		{
			city = BaseUI.getStringInput(userInputStream, "Enter city (14 char max): ");
			if (city.length() > 14 || city.length() < 1)
				System.out.println ("Error! City not valid");
		}
		while(state.length() > 2 || state.length() < 1)
		{
			state = BaseUI.getStringInput(userInputStream, "Enter state (2 char max): ");
			if (state.length() > 2 || state.length() < 1)
				System.out.println ("Error! State not valid");
		}
		while(ZIP > 99999 || ZIP < 1)
		{
			ZIP = BaseUI.getIntegerInput(userInputStream, "Enter ZIP (5 digit max): ");
			if (ZIP > 99999 || ZIP < 1)
				System.out.println ("Error! ZIP not valid");
		}

		// send information to PA using .addProvider(), enter fail condition on fail
		PizzaAnonymous.getInstance().addProvider(ID, name, address, city, state, ZIP);
	}
	
	/**
	 * Take in ID to find provider loop taking in options of a provider to edit
	 */
	public void editProvider()
	{
		String attributeInput = "";
		int ID;
		Provider provider;
		String name = "";
		String address = "";
		String city = "";
		String state = "";
		int ZIP = 0;

		// get provider ID
		ID = BaseUI.getIntegerInput(userInputStream, "Enter ID: ");

		// create pointer to provider
		provider = PizzaAnonymous.getInstance().getProvider(ID);

		// check if no provider of ID in providerList
		if (provider == null)
		{
			System.out.println ("Error! No provider with that ID!");
			return;
		}

		// loop taking in attributes to edit until exit is entered
		while (attributeInput != "exit")
		{
			// provider exists, get input on what to edit
			attributeInput = BaseUI.getStringInput(userInputStream, "Enter which attribute to edit (Name, Address, City, State, ZIP, exit): ");

			// enter new value for attributeInput
			switch (attributeInput)
			{
			case "Name":	// get needed provider information
				do
				{
					name = BaseUI.getStringInput(userInputStream, "Enter name (25 char max): ");
					if (name.length() > 25 || name.length() < 1)
						System.out.println ("Error! Name not valid!");
				} while(name.length() > 25 || name.length() < 1);
				// set new value for name
				provider.setName(name);
				break;
			case "Address": // get needed provider information
				do
				{
					address = BaseUI.getStringInput(userInputStream, "Enter address (25 char max): ");
					if (address.length() > 25 || address.length() < 1)
						System.out.println ("Error! Address not valid");
				} while(address.length() > 25 || address.length() < 1);
				// set new value for address
				provider.setAddress(address);
				break;
			case "City":	// get needed provider information
				do 
				{
					city = BaseUI.getStringInput(userInputStream, "Enter city (14 char max): ");
					if (city.length() > 14 || city.length() < 1)
						System.out.println ("Error! City not valid");
				}while(city.length() > 14 || city.length() < 1);
				// set new value for City
				provider.setCity(city);
				break;
			case "State":	// get needed provider information
				do
				{
					state = BaseUI.getStringInput(userInputStream, "Enter state (2 char max): ");
					if (state.length() > 2 || state.length() < 1)
						System.out.println ("Error! State not valid");
				} while(state.length() > 2 || state.length() < 1);
				// set new value for state
				provider.setState(state);
				break;
			case "ZIP":	// get needed provider information
				do
				{
					ZIP = BaseUI.getIntegerInput(userInputStream, "Enter ZIP (5 digit max): ");
					if (ZIP > 99999 || ZIP < 1)
						System.out.println ("Error! ZIP not valid");
				} while(ZIP > 99999 || ZIP < 1);
				// set new value for ZIP
				provider.setZIP(ZIP);
				break;
			case "exit": 	// don’t display anything, just exit
				break;
			default:	//error: invalid input
				System.out.println ("Error! Invalid input");
				break;
			}
		}

	}
	
	/**
	 * This puts in a request to delete a provider by ID
	 */
	public void deleteProvider()
	{
		int ID = 0;

		// get provider ID
		while(ID > 999999999 || ID < 1)
		{
			ID = BaseUI.getIntegerInput(userInputStream, "Enter ID (9 digit max): ");
			if (ID > 999999999 || ID < 1)
				System.out.println ("Error! ID not valid");
		}

		// send information to PA using .deleteProvider(), enter fail condition on fail
		if(!PizzaAnonymous.getInstance().deleteProvider(ID))
			System.out.println ("Error! No provider with that ID exists!");
	}
	
	/**
	 * TODO Documentation(Amila DeSilva, Adam Defoe)
	 */
	public void requestAddService()
	{
		String serviceName;
		double serviceCost;

		serviceName = BaseUI.getStringInput(userInputStream, "Enter service name: ");
		serviceCost = BaseUI.getDoubleInput(userInputStream, "Enter service cost: ");
		
		//Try to add the service
		if(PizzaAnonymous.getInstance().addService(serviceName, serviceCost))
			System.out.println("Succesfully added");
		else
			System.out.println("Failed to add");
	}
	
	/**
	 * TODO Documentation(Amila DeSilva, Adam Defoe)
	 */
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
