import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Abstract report class.  Defines methods that all reports need to implement themselves.
 * Contains the general code for saving a report to a file. 
 * All report type classes should be subclasses.
 * 
 * @author Blake
 *
 */
public abstract class Report {

	/**
	 * Determines and returns the file name for this report. 
	 * The information contained in the report is considered for the name, 
	 * and therefore this method requires the report's fields be initialized 
	 * before this will work.
	 * @return The file name the report should be given
	 */
	public abstract String getFileName();
	
	/**
	 * Saves the contents of the report to file. 
	 * The file is named by getFileName(). 
	 * The content of the file is determined via the toString() method. 
	 * This means subclasses must implement getFileName() and toString() 
	 * When the method returns, a file containing the report's contents should be created
	 */
	public void saveReport()
	{
		try {
			// Open the file for writing
			PrintWriter printWriter = new PrintWriter(new FileWriter(new File(getFileName())));
			
			// Write the contents of the report to the file
			printWriter.write(toString());
			
			// Close the file
			printWriter.flush();
			printWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
