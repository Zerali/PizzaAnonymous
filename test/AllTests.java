import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;


@RunWith(Suite.class)
@SuiteClasses({ 
	BaseUITest.class,
	BaseUITest2.class,
	ReportSuite.class,
	ProviderSuite.class,
	ServiceSuite.class
	})
public class AllTests {

}
