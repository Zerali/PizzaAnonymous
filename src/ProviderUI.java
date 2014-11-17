import java.util.Iterator;
import java.io.InputStream;

/**
 * The front end user interface for provider related actions.
 * 
 * @author Nick
 */
public class ProviderUI {
	
	// Constants for the menu options
	public static final int REQUEST_SERVICE_DIRECTORY = 1;
	public static final int VALIDATE_MEMBER = 2;
	public static final int CAPTURE_MEMBER_SERVICE = 3;
	public static final int VALIDATE_SERVICE = 4;
	public static final int EXIT_OPTION = 5;
	
	private Integer providerID;
	
	// used in testing
	public Integer getProviderID()
	{
		return providerID;
	}
	
	private PizzaAnonymous PAinstance;
	
	// The input stream that user input is taken from
	private InputStream userInputStream;
	
	// Whether we want to stay in the Provider UI or not
	private boolean stayInProvider = true;
	
	// testing constructor used for Junit
	public ProviderUI(InputStream userInputStream, Boolean Test)
	{
		this.userInputStream = userInputStream;
		PAinstance = PizzaAnonymous.getInstance();
	}

	/**
	 * TODO Documentation(Nick)
	 * 
	 * @param userInputStream
	 */
	public ProviderUI(InputStream userInputStream) {
		this.userInputStream = userInputStream;
		PAinstance = PizzaAnonymous.getInstance();
		int menuOption; // The user's menu option
		
		// require login for terminal
		providerLogin();
		
		// if exit option was chosen in providerLogin exit the UI
		if (providerID == null)
		{
			System.out.println("Exiting Provider Terminal");
			return;
		}
		
		while(stayInProvider)
		{
			// Show the menu options
			runUI();

			// Get the user's menu option
			menuOption = BaseUI.getMenuInput(userInputStream, REQUEST_SERVICE_DIRECTORY, EXIT_OPTION);

			// Handle that option
			handleMenuOption(menuOption);
		}
	}
	
	/**
	 * Show the interface and present the options
	 */
	public void runUI()
	{
		System.out.println("The following options are available:");
		System.out.println(REQUEST_SERVICE_DIRECTORY + ": Print the service directory");
		System.out.println(VALIDATE_MEMBER + ": Validate a member");
		System.out.println(CAPTURE_MEMBER_SERVICE + ": Capture a member\'s service");
		System.out.println(VALIDATE_SERVICE + ": Validate a service");
		System.out.println(EXIT_OPTION + ": Exit");
	}
	
	/**
	 * Handle a menu option chosen by the user. 
	 * This method will accept an option assumed to be valid
	 * 
	 * @param option The user's menu option
	 */
	public void handleMenuOption(int option)
	{
		// Depending on the option, switch to a different menu
		switch(option)
		{
		case REQUEST_SERVICE_DIRECTORY:
			requestServiceDirectory();
			break;
			
		case VALIDATE_MEMBER:
			validateMember();
			break;
			
		case CAPTURE_MEMBER_SERVICE:
			captureMemberService();
			break;
			
		case VALIDATE_SERVICE:
			validateServiceSelection();
			break;
			
		case EXIT_OPTION:
			// Return to the base UI
			stayInProvider = false;
			new BaseUI(userInputStream);
			break;
			
		default:
			System.err.println("Invalid option passed to handleMenuOption");
			break;
		}
	}
	
	/**
	 * Provider login. Read an inputed integer and check if that
	 * integer is a valid provider login. Loop until valid login
	 * or exit option (-1) is inputed
	 */
	public void providerLogin()
	{
		int input = -1; // The provider login
		
		while(true)
		{
			// Get the user's input as an integer
			input = BaseUI.getIntegerInput(userInputStream, "Provider Login. Enter ID or -1 to quit: ");
			
			// if exit option (-1) break
			if(input == -1)
			{
				System.out.println("Logging out.");
				break;
			}
			
			// Make sure it's a valid ID
			if(PAinstance.getProvider(input) != null)
			{
				// Valid provider
				System.out.println("Login successful.");
				
				// set local providerID to input
				providerID = new Integer(input);
				
				break;
			} else {
				// invalid login ID print error and repeat
				System.out.println("Invalid login ID.");
			}
		}
	}
	
	/**
	 * Asks pizza anonymous for the directory of services and
	 * then prints the list to the console
	 */
	public void requestServiceDirectory()
	{
		// create an iterator and assign it to the directory
		Iterator<Service> directoryIter;
		Service currentService = new Service();
		
		// get the service directory from pizza anonymous
		directoryIter = PAinstance.lookupServiceDirectory();
		
		// print each item from the directory
		while(directoryIter.hasNext())
		{
			// get next service
			currentService = directoryIter.next();
			
			// print all of fields of service
			System.out.println(currentService.getID()+ " " + currentService.getName() + " " + currentService.getCost());
		}
	}
	
	/**
	 * Validate a service by ID
	 */
	public void validateServiceSelection()
	{
		Service service;
		int serviceID;
		boolean verify = false;
		
		// loop until valid service ID is entered
		while (!verify)
		{
			// Get the user's input as an integer
			serviceID = BaseUI.getIntegerInput(userInputStream, "Enter ID of service to lookup: ");
			
			// get the service from pizza anonymous
			service = PAinstance.getService(serviceID);
			
			// print if failed to find service
			if (service == null)
			{
				System.out.println("Could not find service with that ID!");
				continue;
			}
			
			// otherwise print details of service
			System.out.println(serviceID + " " + service.getName() + " " + service.getCost());
			
			// ask if right service
			verify = BaseUI.getConfirmation(userInputStream, "Is this the correct Service?: ");
		}
	}
	
	/**
	 * Take a int member ID and check to see if that is in the list of members
	 */
	public void validateMember()
	{
		int input = -1; // The provider login
		Member member;
		
		// read in member ID to validate
		while(true)
		{
			// Get the user's input as an integer
			input = BaseUI.getIntegerInput(userInputStream, "Enter member ID or -1 to quit: ");
			
			// if exit option (-1) break
			if(input == -1)
			{
				System.out.println("Exiting validate member.");
				break;
			}
			
			// get the member with id input
			member = PAinstance.getMember(input);
			
			// Make sure it's a valid ID
			if(member == null)
			{
				// Valid provider
				System.out.println("Failed to find member.");
			} else if (member.getValidStatus() == false) {
				// member found, print their name
				System.out.println("Member Suspended");
				break;
			} else
			{
				System.out.println("Validated");
				break;
			}
		}
	}
	
	/**
	 * Create a new service occasion in the list of service occasions by
	 * taking in new information and calling the 
	 */
	public void captureMemberService()
	{
		// values of a service occasion
		String dateServiced;
		int memberID;
		int serviceID;
		String comments;
		
		// get member ID and validate
		while(true)
		{
			// Get the user's input as an integer
			memberID = BaseUI.getIntegerInput(userInputStream, "Input member ID: ");
			
			// if no member exists prompt and repeat
			if (PAinstance.getMember(memberID) == null)
			{
				System.out.println("Error! No member with ID " + memberID + "!");
				System.out.flush();
				
				continue;
			}
			else
				System.out.println("Validated");
			break;
		}
		
		// get date of service
		while(true)
		{
			// Get the user's input as an string
			dateServiced = BaseUI.getStringInput(userInputStream, "Input Date as MM-DD-YYYY: ");
			
			// check if correct length for error checking purpose
			if (dateServiced.length() != 10)
			{
				System.out.println("Error! Invalid date format");
				System.out.flush();
				
				continue;
			}
			
			break;
		}
		
		// get the service provided
		while(true)
		{
			// ask if to display service directory and display if asked
			if (BaseUI.getConfirmation(userInputStream, "Display service directory?: "))
				requestServiceDirectory();
			
			// Get the serviceID as an integer
			serviceID = BaseUI.getIntegerInput(userInputStream, "Input service ID: ");
			
			// if no service exists, prompt and repeat process
			if (PAinstance.getService(serviceID) == null)
			{
				System.out.println("Error! No service with ID " + serviceID + "!");
				System.out.flush();
				
				continue;
			}
			else // if service exist, ask if it is the correct service
			{
				if(BaseUI.getConfirmation(userInputStream, "Is " + PAinstance.getService(serviceID).getName() +" the service provided? "))
					break;
			}
		}
		
		// get comments
		while(true)
		{
			comments = BaseUI.getStringInput(userInputStream, "Input comments up to 200 characters: ");
			
			// check if correct length for error checking purpose
			if (comments.length() > 200)
			{
				System.out.println("Error! Too long of a comment!");
				System.out.flush();

				continue;
			}

			break;
		}
		
		PAinstance.addServiceOccasion(dateServiced, providerID.intValue(), memberID, serviceID, comments);
		System.out.println("Successfully captured!");
	}
}
