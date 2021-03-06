import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * A summary report contains information summarizing Pizza Anonymous activity
 * for the past week. This includes a summary of what services providers have
 * given, the count of providers that gave services, the total consultations
 * given, and the total fee.
 * 
 * @author Blake
 */
public class SummaryReport extends Report {
	
	/**
	 * A list of EFT data records.
	 * 
	 * The array consists of:
	 * <li>Provider Name</li>
	 * <li># Consultations</li>
	 * <li>Fee to be Paid</li>
	 */
	private List<String[]> providerLines = new LinkedList<String[]>();
	
	// The total number of providers that have given services
	private int totalProviders;
	
	// The total number of consultations given by all providers
	private int totalConsultations;
	
	// The total fee to be paid to all providers
	private double totalFee;

	/**
	 * TODO Documentation(Blake)
	 */
	public SummaryReport() {
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
		return "./reports/Summary Report" + " " + dateFormat.format(date) + ".txt";
	}
	
	/**
	 * Returns what the contents of the file should be. Relies on the data
	 * information being set before this happens.
	 */
	@Override
	public String toString()
	{
		// Use a StringBuilder to append file contents
		StringBuilder content = new StringBuilder();
		
		// Append a nice little header for provider summaries
		content.append("Provider Summaries:" + System.lineSeparator());
		
		// Go through all of the entries (for each provider)
		Iterator<String[]> it = providerLines.iterator();
		while(it.hasNext())
		{
			// The provider information
			String[] info = it.next();
			
			// Append that information
			content.append("Provider Name:\t\t" + info[0] + System.lineSeparator());
			content.append("Total Consultations:\t" + info[1] + System.lineSeparator());
			content.append("Total Fee:\t\t" + info[2] + System.lineSeparator());
			
			// Give a tidy space between each service
			content.append(System.lineSeparator());
		}
		
		// Append the total # of providers field
		content.append("Total Number of Providers: " + totalProviders + System.lineSeparator());

		// Append the total # of consultations field
		content.append("Total Number of Consultations: " + totalConsultations + System.lineSeparator());

		// Append the total fee
		content.append("Total Fee for the Week: " + Report.FEE_FORMAT.format(totalFee) + System.lineSeparator());
		
		// Return the final string
		return content.toString();
	}

	// Begin accessors & mutators //
	
	
	/**
	 * Add a summary of some provider's activities.
	 *
	 * @param providerName Name of the provider
	 * @param consults How many consultations they had
	 * @param fee The total fee they are to be paid
	 */
	public void addLine(String providerName, int consults, double fee)
	{
		// Add the data to the list of string arrays
		providerLines.add(new String[] {providerName, String.valueOf(consults), Report.FEE_FORMAT.format(fee)});
	}

	/**
	 * @return Total number of providers that have given services
	 */
	public int getTotalProviders() {
		return totalProviders;
	}
	
	/**
	 * @param totalProviders Total number of providers that have given services
	 */
	public void setTotalProviders(int totalProviders) {
		this.totalProviders = totalProviders;
	}

	/**
	 * @return Total number of consultations given by all providers, combined
	 */
	public int getTotalConsultations() {
		return totalConsultations;
	}
	
	/**
	 * @param totalConsultations Total number of consultations given by all providers, combined
	 */
	public void setTotalConsultations(int totalConsultations) {
		this.totalConsultations = totalConsultations;
	}

	/**
	 * @return Total fee to be paid to all providers, combined
	 */
	public double getTotalFee() {
		return totalFee;
	}
	
	/**
	 * @param totalFee Total fee to be paid to all providers, combined
	 */
	public void setTotalFee(double totalFee) {
		this.totalFee = totalFee;
	}
	
	// End accessors & mutators //
}
