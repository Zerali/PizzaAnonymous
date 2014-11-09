import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.*;

import org.hamcrest.CoreMatchers;
import org.junit.Test;


public class ProviderReportTest {

	@Test
	public void testGetFileName() {
		ProviderReport report = new ProviderReport(); // The report we're going to test

		// Unexpected preconditions (name not set)
		assertThat(report.getFileName(), containsString("null"));

		// Expected preconditions (name set)
		report.setName("ProviderReport");
		assertThat(report.getFileName(), containsString("ProviderReport"));
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testToString() {
		ProviderReport report = new ProviderReport(); // The report we're going to test
		
		// No fields set
		String contents = report.toString();
		int nullCount = contents.split("null").length - 1;
		int zeroCount = contents.split("0"+System.lineSeparator()).length - 1;
		int dollarCount = contents.split("0.00").length - 1;
		assertTrue(nullCount == 5 && zeroCount == 2 && dollarCount == 1);
		
		// All fields set
		report.setName("a_name");
		report.setId(42);
		report.setAddress("an_address");
		report.setCity("a_city");
		report.setState("a_state");
		report.setZip("a_zip");
		report.addServiceInfo("a_date1", "a_date2", "a_member", 50, 51, 52.333);
		report.setTotalConsultations(54);
		report.setTotalFee(55.666);
		
		contents = report.toString();
		assertThat(contents, CoreMatchers.allOf(
				containsString("a_name"), 
				containsString("42"), 
				containsString("an_address"),
				containsString("a_city"),
				containsString("a_state"),
				containsString("a_zip"),
				containsString("a_date1"),
				containsString("a_date2"),
				containsString("a_member"),
				containsString("50"),
				containsString("51"),
				containsString("$52.33"),
				containsString("54"),
				containsString("$55.67")));
	}

}
