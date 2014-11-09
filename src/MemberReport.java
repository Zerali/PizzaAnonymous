import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;


/**
 * A Member Report serves as a record of services provided to a member 
 * of Pizza Anonymous.
 * 
 * @author Blake
 *
 */
public class MemberReport extends Report {
	
	// Basic information about the Member this report concerns
	private String name;
	private int id;
	private String address;
	private String city;
	private String state;
	private String zip;
	
	/**
	 * A list of service records. Each service occasion is stored as a string array.
	 * The array consists of: The date of service, provider name, and service name.
	 */
	private List<String[]> serviceInfo = new LinkedList<String[]>();

	public MemberReport() {
		super();
	}

	@Override
	public String getFileName() {
		// Get the current date to append to the filename
		Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy HH-mm-ss");
        
        // Return the member name appended with the date
        return "./reports/" + name + " " + dateFormat.format(date) + ".txt";
	}
	
	/**
	 * Returns what the contents of the file should be. 
	 * Relies on the data information being set before this happens.
	 */
	@Override
	public String toString()
	{
		// Use a StringBuilder to append file contents
		StringBuilder content = new StringBuilder();
		
		// Append the member information
		content.append("Member Name:\t" + name + System.lineSeparator());
		content.append("Member Number:\t" + id + System.lineSeparator());
		content.append("Member Address:\t" + address + System.lineSeparator());
		content.append("Member City:\t" + city + System.lineSeparator());
		content.append("Member State:\t" + state + System.lineSeparator());
		content.append("Member ZIP Code:\t" + zip + System.lineSeparator());
		
		// Append a nice little header for service info
		content.append(System.lineSeparator());
		content.append("Service Information:" + System.lineSeparator());
		
		// Go through all of the service records for this member
		Iterator<String[]> it = serviceInfo.iterator();
		while(it.hasNext())
		{
			// The service occasion information
			String[] info = it.next();
			
			// Append that information
			content.append("\t" + "Date of Service:\t" + info[0] + System.lineSeparator());
			content.append("\t" + "Provider Name:\t\t" + info[1] + System.lineSeparator());
			content.append("\t" + "Service Name:\t\t" + info[2] + System.lineSeparator());
			
			// Give a tidy space between each service
			content.append(System.lineSeparator());
		}
		
		// Return the final string
		return content.toString();
	}

	// Begin accessors & mutators
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
	public String getZip() {
		return state;
	}
	public void setZip(String zip) {
		this.zip = zip;
	}

	public List<String[]> getServiceInfo() {
		return serviceInfo;
	}
	public void addServiceInfo(String date, String providerName, String serviceName) {
		serviceInfo.add(new String[] {date, providerName, serviceName});
	}
	// End accessors & mutators
	
}
