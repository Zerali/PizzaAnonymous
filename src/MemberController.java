import java.util.ArrayList;
import java.util.Iterator;

/**
 * TODO Documentation(Amila)
 * @author Amila DeSilva
 */
public class MemberController 
{
	ArrayList<Member> members;
	private int nextMemberID;
	
	/**
	 * TODO Documentation(Amila)
	 */
	public MemberController() 
	{
		members = new ArrayList<Member>();
	}

	/**
	 * TODO Documentation(Amila)
	 * 
	 * @param members
	 */
	public MemberController(ArrayList<Member> members) 
	{
		this.members = members;
	}

	
	/**
	 * This method adds a member if it does not exist, given the details such as name, address, etc, 
	 * and returns true, otherwise returns false
	 * 
	 * @param name Name of the Member
	 * @param ID Identification number of the member
	 * @param address Address of the member
	 * @param city City of the member
	 * @param state State of the member
	 * @param ZIP ZIP code of the member
	 * @param validStatus Status of the member
	 * @return True if member was added, otherwise false
	 */
	public boolean addMember(String name, int ID, String address, String city, String state, int ZIP, boolean validStatus)
	{
		// check to see if there is an existing Member with same details; if not, create new Member
		if (getMember(ID) == null)
		{
			members.add(new Member(name, ID, address, city, state, ZIP, validStatus));
			return true;
		}
		
		// if member exists, this function should fail
		return false;
	}

	/**
	 * This method adds a member if it does not exist, given the Member’s ID and returns true,
	 * otherwise returns false
	 * 
	 * @param ID Identification number of the member
	 * @return True if member was added, otherwise false
	 */
	public boolean deleteMember(int ID)
	{
		//check to see if a member with such an ID exists. If so, remove them
		for(Member m : members)
		{	
			if (m.getID() == ID)
			{
				members.remove(m);
				return true;
			}
		}
		
		//if no such member exists, fail this function
		return false;
	}
	
	/**
	 * This method returns a member object given the member ID and if the member exists, 
	 * otherwise returns NULL
	 * 		
	 * @param memberID
	 * @return Member if memberID exists, otherwise null
	 */
	public Member getMember(int memberID)
	{
		//check to see if such a member exists. If so return the member
		for (Member m : members)
		{
			if (m.getID() == memberID)
			{
				return m;
			}
		}
		
		//if a member with such an ID does not exists, return NULL
		return null;
	}

	/**
	 * This method returns the member list that has been created and updated till current time
	 * 
	 * @return Iterator of the member list
	 */
	public Iterator<Member> getMemberList()
	{
		return members.iterator();
	}

}
