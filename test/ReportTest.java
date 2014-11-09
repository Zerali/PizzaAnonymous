import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

/**
 * Test the methods implemented in the Report class
 * 
 * @author Blake
 *
 */
public class ReportTest {
	
	@Rule
	public TemporaryFolder folder = new TemporaryFolder();

	@Test
	public void testNominalSaveReport() throws IOException {
		Report report; // The report we'll try to save
		
		// Set up the temporary save location
		File tmpFolder = folder.newFolder("tmp_report");
		String tmpPath = tmpFolder.getPath();
		
		// Nominal input values
		report = new Report() {

			@Override
			public String getFileName() {
				return tmpPath + "/NominalReport.txt";
			}
			
			@Override
			public String toString() {
				return "report content";
			}
			
		};
		
		// Make sure the report saves successfully and the file was created
		assertEquals(true, report.saveReport() && Files.exists(Paths.get(tmpPath + "/NominalReport.txt")));
	}
	
	@Test
	public void testInvalidFilenameSaveReport() throws IOException {
		Report report; // The report we'll try to save
		
		// Set up the temporary save location
		File tmpFolder = folder.newFolder("tmp_report");
		String tmpPath = tmpFolder.getPath();
		
		// Nominal input values
		report = new Report() {

			@Override
			public String getFileName() {
				return tmpPath + "/:_)(somanyinvalidchars.txt";
			}
			
			@Override
			public String toString() {
				return "report content";
			}
			
		};
		
		// Make sure the report was never saved (exception caught)
		assertEquals(false, report.saveReport());
	}
	
	@Test
	public void testDefaultToStringSaveReport() throws IOException {
		Report report; // The report we'll try to save
		
		// Set up the temporary save location
		File tmpFolder = folder.newFolder("tmp_report");
		String tmpPath = tmpFolder.getPath();
		
		// Nominal input values
		report = new Report() {

			@Override
			public String getFileName() {
				return tmpPath + "/DefaultReport.txt";
			}
			
		};
		
		// Make sure the report saves successfully and the file was created
		assertEquals(true, report.saveReport() && Files.exists(Paths.get(tmpPath + "/DefaultReport.txt")));
	}

}
