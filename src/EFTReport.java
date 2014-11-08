import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * An EFT Report contains information on electronic funds transfer data. 
 * This is essentially a billing detail, concerning what money 
 * should be sent where.
 * 
 * @author Blake
 *
 */
public class EFTReport extends Report {
	
	/**
	 * A list of EFT data records.
	 * The array consists of:
	 * <li>Provider Name</li>
	 * <li>Provider Number</li>
	 * <li>Payment Amount</li>
	 */
	private List<String[]> eftInfo = new LinkedList<String[]>();

	public EFTReport() {
		super();
	}

	@Override
	public String getFileName() {
		// Get the current date to append to the filename
		Date date = new Date();
		DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy HH-mm-ss");

		// Return the member name appended with the date
		return "EFT Report" + " " + dateFormat.format(date);
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
		
		// Append a nice little header for service info
		content.append("EFT Information:" + System.lineSeparator());
		
		// Go through all of the EFT records (for each provider)
		Iterator<String[]> it = eftInfo.iterator();
		while(it.hasNext())
		{
			// The EFT information
			String[] info = it.next();
			
			// Append that information
			content.append("Provider Name:\t" + info[0] + System.lineSeparator());
			content.append("Provider Number:\t" + info[1] + System.lineSeparator());
			content.append("Payment Amount:\t" + info[2] + System.lineSeparator());
			
			// Give a tidy space between each service
			content.append(System.lineSeparator());
		}
		
		// Return the final string
		return content.toString();
	}
	
	// Begin accessors & mutators //
	public void addLine(String providerName, int providerID, double amount)
	{
		eftInfo.add(new String[] {providerName, String.valueOf(providerID), Report.FEE_FORMAT.format(amount)});
	}
	public List<String[]> getEftInfo()
	{
		return eftInfo;
	}
	// End accessors & mutators //

}
