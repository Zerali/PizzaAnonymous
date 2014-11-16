import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * A Provider Report serves as a record of services provided by a 
 * provider associated with Pizza Anonymous
 * 
 * @author Blake
 *
 */
public class ProviderReport extends Report {
	
	// Basic information about the Provider this report concerns
	private String name;
	private int id;
	private String address;
	private String city;
	private String state;
	private String zip;
	
	/**
	 * A list of service records. Each service occasion is stored as a string array.
	 * The array consists of:
	 * <li>Date of Service</li>
	 * <li>Date and time data was received by computer</li>
	 * <li>Member name</li>
	 * <li>Member number</li>
	 * <li>Service code</li>
	 * <li>Fee to be paid</li>
	 */
	private List<String[]> serviceInfo = new LinkedList<String[]>();
	
	// The total consultations this provider has provided
	private int totalConsultations = 0;
	
	// The total fee this provider is owed by PA
	private double totalFee = 0.0;
	
	/**
	 * Create an empty ProviderReport object. 
	 * All information fields are blank to begin with.
	 */
	public ProviderReport() {
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
		
		// Append the provider information
		content.append("Provider Name:\t" + name + System.lineSeparator());
		content.append("Provider Number:\t" + id + System.lineSeparator());
		content.append("Provider Address:\t" + address + System.lineSeparator());
		content.append("Provider City:\t" + city + System.lineSeparator());
		content.append("Provider State:\t" + state + System.lineSeparator());
		content.append("Provider ZIP Code:\t" + zip + System.lineSeparator());
		
		// Append a nice little header for service info
		content.append(System.lineSeparator());
		content.append("Service Information:" + System.lineSeparator());
		
		// Go through all of the service records for this provider
		Iterator<String[]> it = serviceInfo.iterator();
		while(it.hasNext())
		{
			// The service occasion information
			String[] info = it.next();
			
			// Append that information
			content.append("\t" + "Date of Service:\t" + info[0] + System.lineSeparator());
			content.append("\t" + "Date Received:\t\t" + info[1] + System.lineSeparator());
			content.append("\t" + "Member Name:\t\t" + info[2] + System.lineSeparator());
			content.append("\t" + "Member Number:\t\t" + info[3] + System.lineSeparator());
			content.append("\t" + "Service Code:\t\t" + info[4] + System.lineSeparator());
			content.append("\t" + "Fee to be Paid:\t\t" + info[5] + System.lineSeparator());
			
			// Give a tidy space between each service
			content.append(System.lineSeparator());
		}
		
		// Append the total # of consultations
		content.append("Total number of consultations: " + totalConsultations + System.lineSeparator());
		
		// Append the total fee to pay this provider
		content.append("Total fee: " + Report.FEE_FORMAT.format(totalFee) + System.lineSeparator());
		
		// Return the final string
		return content.toString();
	}

	// Begin accessor & mutators //
	
	/**
	 * @return Name of the provider this report is about
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * @param name The name of the provider this report is about
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return ID of the provider this report is about
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * @param id ID of the provider this report is about
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return Address describing physical location of provider
	 */
	public String getAddress() {
		return address;
	}
	
	/**
	 * @param address Address describing physical location of provider
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return City the provider is located in
	 */
	public String getCity() {
		return city;
	}
	
	/**
	 * @param city City the provider is located in
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * @return State the provider is located in
	 */
	public String getState() {
		return state;
	}
	
	/**
	 * @param state State the provider is located in
	 */
	public void setState(String state) {
		this.state = state;
	}

	/**
	 * @return ZIP code for the provider
	 */
	public String getZip() {
		return zip;
	}
	
	/**
	 * @param zip ZIP code for the provider
	 */
	public void setZip(String zip) {
		this.zip = zip;
	}

	/**
	 * Add information about a service provided by the Provider this report 
	 * is reflecting.
	 */
	public void addServiceInfo(String dateOfService, 
			String dateEntered, 
			String memberName,
			int memberID, 
			int serviceCode, 
			double fee)
	{
		serviceInfo.add(new String[] {dateOfService, 
				dateEntered, 
				memberName, 
				String.valueOf(memberID), 
				String.valueOf(serviceCode), 
				Report.FEE_FORMAT.format(fee)});
	}

	/**
	 * @return The total consults this provider has given
	 */
	public int getTotalConsultations() {
		return totalConsultations;
	}
	
	/**
	 * @param totalConsultations The total consults this provider has given
	 */
	public void setTotalConsultations(int totalConsultations) {
		this.totalConsultations = totalConsultations;
	}

	/**
	 * @return The total fee this provider is owed
	 */
	public double getTotalFee() {
		return totalFee;
	}
	
	/**
	 * @param totalFee The total fee this provider is owed
	 */
	public void setTotalFee(double totalFee) {
		this.totalFee = totalFee;
	}
	
	// End accessors & mutators //

}
