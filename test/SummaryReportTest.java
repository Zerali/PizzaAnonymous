import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.junit.Test;

/**
 * Unit test methods for the SummaryReport class's methods
 * 
 * @author Blake
 *
 */
public class SummaryReportTest {

	@Test
	public void testGetFileName() {
		SummaryReport report = new SummaryReport(); // The report we're going to test
		
		// Expected preconditions (none really)
		assertThat(report.getFileName(), containsString("Summary Report"));
	}

	@Test
	public void testToString() {
		SummaryReport report = new SummaryReport(); // The report we're going to test
		
		// No fields set
		String contents = report.toString();
		int zeroCount = contents.split("0"+System.lineSeparator()).length - 1;
		int dollarCount = contents.split("0.00").length - 1;
		assertTrue(zeroCount == 2 && dollarCount == 1);
		
		// All fields set
		report.addLine("a_provider", 70, 71.123);
		report.setTotalProviders(72);
		report.setTotalConsultations(73);
		report.setTotalFee(74.567);
		
		contents = report.toString();
		assertThat(contents, allOf(
				containsString("a_provider"), 
				containsString("70"), 
				containsString("$71.12"),
				containsString("72"),
				containsString("73"),
				containsString("$74.57")));
	}

}
