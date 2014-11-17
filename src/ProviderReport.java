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
	 * A list of service records. Each service occasion is stored as a string
	 * array.
	 * 
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
	 * TODO Documentation(Blake)
	 */
	public ProviderReport() {
		super();
	}

	/**
	 * TODO Documentation(Blake)
	 */
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
	 * TODO Documentation(Blake)
	 * 
	 * @return
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * TODO Documentation(Blake)
	 * 
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * TODO Documentation(Blake)
	 * 
	 * @return
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * TODO Documentation(Blake)
	 * 
	 * @param id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * TODO Documentation(Blake)
	 * 
	 * @return
	 */
	public String getAddress() {
		return address;
	}
	
	/**
	 * TODO Documentation(Blake)
	 * 
	 * @param address
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * TODO Documentation(Blake)
	 * 
	 * @return
	 */
	public String getCity() {
		return city;
	}
	
	/**
	 * TODO Documentation(Blake)
	 * 
	 * @param city
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * TODO Documentation(Blake)
	 * 
	 * @return
	 */
	public String getState() {
		return state;
	}
	
	/**
	 * TODO Documentation(Blake)
	 * 
	 * @param state
	 */
	public void setState(String state) {
		this.state = state;
	}

	/**
	 * TODO Documentation(Blake)
	 * 
	 * @return
	 */
	public String getZip() {
		return zip;
	}
	
	/**
	 * TODO Documentation(Blake)
	 * 
	 * @param zip
	 */
	public void setZip(String zip) {
		this.zip = zip;
	}

	/**
	 * TODO Documentation(Blake)
	 * 
	 * @return
	 */
	public List<String[]> getServiceInfo() {
		return serviceInfo;
	}
	
	/**
	 * TODO Documentation(Blake)
	 *  
	 * @param dateOfService
	 * @param dateEntered
	 * @param memberName
	 * @param memberID
	 * @param serviceCode
	 * @param fee
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
	 * TODO Documentation(Blake)
	 * 
	 * @return
	 */
	public int getTotalConsultations() {
		return totalConsultations;
	}
	
	/**
	 * TODO Documentation(Blake)
	 * 
	 * @param totalConsultations
	 */
	public void setTotalConsultations(int totalConsultations) {
		this.totalConsultations = totalConsultations;
	}

	/**
	 * TODO Documentation(Blake)
	 * 
	 * @return
	 */
	public double getTotalFee() {
		return totalFee;
	}
	
	/**
	 * TODO Documentation(Blake)
	 * 
	 * @param totalFee
	 */
	public void setTotalFee(double totalFee) {
		this.totalFee = totalFee;
	}
	// End accessors & mutators //

}
