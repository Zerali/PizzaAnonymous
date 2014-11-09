import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
	// The list of Test Classes that will run together
	BaseUITest.class,
	ReportTest.class
})
public class TestSuite {

	// There's nothing here.
	// All this class is used for is the above annotations.

}
