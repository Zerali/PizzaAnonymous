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
	/**
	 * Test case for addMember() in the MemberController
	 */
	public void testAddMember() 
	{
		//The first time a member with unique details is added to the list
		assertTrue(testMemberController.addMember("Tim", 11478, "9th Ave S.", "Saint Cloud", "MN", 56301, true));

		//If the member exists, the member is not added to the list
		assertFalse(testMemberController.addMember("Tim", 11478, "9th Ave S.", "Saint Cloud", "MN", 56301, true));
	}

	@Test
	/**
	 * Test case for deleteMember() in the MemberController
	 */
	public void testDeleteMember() 
	{
		
		testMemberController.addMember("Mark", 15975, "10th Ave S.", "Saint Cloud", "MN", 56303, true);
		
		assertTrue(testMemberController.deleteMember(15975));
		
		assertFalse(testMemberController.deleteMember(57951));
	}
	
	@Test
	public void test() {
		fail("Not yet implemented");
	}
}
