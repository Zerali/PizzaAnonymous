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
	private int totalConsultations;
	
	// The total fee this provider is owed by PA
	private double totalFee;
	
	public ProviderReport() {
		super();
	}

	@Override
	public String getFileName() {
		// Get the current date to append to the filename
		Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy HH-mm-ss");
        
        // Return the member name appended with the date
        return name + " " + dateFormat.format(date);
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
		content.append("Total number of consultations: " + totalConsultations);
		
		// Append the total fee to pay this provider
		content.append("Total fee: " + totalFee);
		
		// Return the final string
		return content.toString();
	}

	// Begin accessor & mutators //
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
		return zip;
	}
	public void setZip(String zip) {
		this.zip = zip;
	}

	public List<String[]> getServiceInfo() {
		return serviceInfo;
	}
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
				String.valueOf(fee)});
	}


	public int getTotalConsultations() {
		return totalConsultations;
	}
	public void setTotalConsultations(int totalConsultations) {
		this.totalConsultations = totalConsultations;
	}

	public double getTotalFee() {
		return totalFee;
	}
	public void setTotalFee(double totalFee) {
		this.totalFee = totalFee;
	}
	// End accessors & mutators //

}
