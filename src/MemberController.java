/**
 * Still working on this. Not done ompletely.
 */
import java.util.ArrayList;

/**
 * @author Amila DeSilva
 *
 */
public class MemberController 
{
	ArrayList<Member> members;
	private int nextMemberID;
	
	/**
	 * 
	 */
	public MemberController() 
	{
		// TODO Auto-generated constructor stub
		members = new ArrayList<Member>();
	}

	public MemberController(ArrayList<Member> members) 
	{
		// TODO Auto-generated constructor stub
		this.members = members;
	}

	
	//This method adds a member if it does not exist, given the details such as name, address, etc, //and returns true, otherwise returns false
	public boolean addMember(String name, int ID, String address, String city, String state, int ZIP, boolean validStatus)
	{
		//check to see if there is an existing Member with same details; if not, create new
		//Member
		if (getMember(ID) == null)
			members.add(new Member(name, ID, address, city, state, ZIP, validStatus));
			return true;
		//if member exists, this function should fail
		else
			return false;
	}

	//This method adds a member if it does not exist, given the Member’s ID and returns true, //otherwise returns false
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
	
	//This method returns a member object given the member ID and if the member exists, //otherwise returns NULL		
	public Member getMember(int memberID)
	{
		//check to see if such a member exists. If so return the member
		for (Member m : Members)
			if (m.getID() == memberID)
				return m;
		//if a member with such an ID does not exists, return NULL
		return NULL;
	}

	//This method returns the member list that has been created and updated till current time
	public Iterator<Member> getMemberList()
	{
		Iterator<Member> m = new Iterator<Member>(members);
				return m;	
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub

	}

}
