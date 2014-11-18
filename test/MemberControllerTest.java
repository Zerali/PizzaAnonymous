import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * 
 */

/**
 * @author Amila DeSilva
 *
 */
public class MemberControllerTest {

	public MemberController testMemberController = new MemberController();

	@Test
	public void testAddMember() 
	{
		assertTrue(testMemberController.addMember("Tim", 11478, "9th Ave S.", "Saint Cloud", "MN", 56301, true));

		assertFalse(testMemberController.addMember("Tim", 11478, "9th Ave S.", "Saint Cloud", "MN", 56301, true));
	}

	@Test
	public void testEditMember() {
		fail("Not yet implemented");
	}
	
	@Test
	public void test() {
		fail("Not yet implemented");
	}
}
