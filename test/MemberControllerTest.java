import static org.junit.Assert.*;

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
		//A member with unique details is added to the list
		testMemberController.addMember("Mark", 15975, "10th Ave S.", "Saint Cloud", "MN", 56303, true);
		
		//A member with this ID exists, so can be deleted. Return True.
		assertTrue(testMemberController.deleteMember(15975));
		
		//No member with this ID exists; cannot be deleted. Return false.
		assertFalse(testMemberController.deleteMember(57951));
	}
	
	@Test
	/**
	 * Test case for editMember() in the MemberController
	 */
	public void testGetMember() 
	{
		//A member with unique details is added to the list
		testMemberController.addMember("Mary", 78945, "11th Ave S.", "Saint Cloud", "MN", 56304, true);
		
		//Check if the parameter and the name looked up by ID match up
		//Return Successful
		assertEquals("Mary", testMemberController.getMember(78945).getName());
		
		//Similar check; but return Failure
		assertNull("Marty", testMemberController.getMember(45789));
	}
	
}
