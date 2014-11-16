import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;


@RunWith(Suite.class)
@SuiteClasses({ 
	ReportSuite.class,
	ProviderSuite.class,
	ServiceSuite.class
	})
public class AllTests {

}
