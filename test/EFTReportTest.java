import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.junit.Test;

/**
 * Test the EFT Report class, similar to the others
 * 
 * @author Blake
 *
 */
public class EFTReportTest {

	@Test
	public void testGetFileName() {
		EFTReport report = new EFTReport(); // The report we're going to test
		
		// Expected preconditions (none really)
		assertThat(report.getFileName(), containsString("EFT Report"));
	}

	@Test
	public void testToString() {
		EFTReport report = new EFTReport(); // The report we're going to test
		
		// No fields set
		String contents = report.toString();
		assertThat(contents, equalTo("EFT Information:" + System.lineSeparator()));
		
		// All fields set
		report.addLine("a_provider", 60, 61.234);
		
		contents = report.toString();
		assertThat(contents, allOf(
				containsString("a_provider"), 
				containsString("60"),
				containsString("$61.23")));
	}

}
