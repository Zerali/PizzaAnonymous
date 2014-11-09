import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.hamcrest.CoreMatchers;
import org.junit.Test;

/**
 * Tests the methods of MemberReport. 
 * 
 * @author Blake
 *
 */
public class MemberReportTest {

	@Test
	public void testGetFileName() {
		MemberReport report = new MemberReport(); // The report we're going to test
		
		// Unexpected preconditions (name not set)
		assertThat(report.getFileName(), containsString("null"));
		
		// Expected preconditions (name set)
		report.setName("MemberReport");
		assertThat(report.getFileName(), containsString("MemberReport"));
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void testToString() {
		MemberReport report = new MemberReport(); // The report we're going to test
		
		// No fields set
		String contents = report.toString();
		int nullCount = contents.split("null").length - 1;
		int zeroCount = contents.split("0").length - 1;
		assertTrue(nullCount == 5 && zeroCount == 1);
		
		// All fields set
		report.setName("a_name");
		report.setId(42);
		report.setAddress("an_address");
		report.setCity("a_city");
		report.setState("a_state");
		report.setZip("a_zip");
		report.addServiceInfo("a_date", "a_provider", "a_service");
		
		contents = report.toString();
		assertThat(contents, CoreMatchers.allOf(
				containsString("a_name"), 
				containsString("42"), 
				containsString("an_address"),
				containsString("a_city"),
				containsString("a_state"),
				containsString("a_zip"),
				containsString("a_date"),
				containsString("a_provider"),
				containsString("a_service")));
	}

}
