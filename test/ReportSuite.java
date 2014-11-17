import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
	// The list of Test Classes that will run together
	ReportTest.class,
	MemberReportTest.class,
	ProviderReportTest.class,
	EFTReportTest.class,
	SummaryReportTest.class,
	ReportControllerTest.class
})
public class ReportSuite {

	// There's nothing here.
	// All this class is used for is the above annotations.

}
