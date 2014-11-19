/**
 * Still working on this. Not done completely.
 */

/**
 * This class holds the members' attributes such as name, ID etc.
 * The responsibility of this class is to set and return the attributes
 * 
 * @author Amila DeSilva
 */
public class Member 
{
	private String name;
	private int ID;
	private String address;
	private String city;
	private String state;
	private int ZIP;
	private boolean validstatus;
	
	/**
	 * Constructor to create a Member
	 * 
	 * @param name Name of the Member
	 * @param ID Identification number of the member
	 * @param address Address of the member
	 * @param city City of the member
	 * @param state State of the member
	 * @param ZIP Zip code of the member
	 * @param validStatus Status of the member
	 */
	public Member(String name, int ID, String address, String city, String state, int ZIP, boolean validStatus) 
	{
		this.name = name;
		this.ID = ID;
		this.address = address;
		this.city = city;
		this.state = state;
		this.ZIP = ZIP;
		this.validstatus = validStatus;
	}

	/**
	 * Accessor: returns the name of a member
	 * 
	 * @return name
	 */
	public String getName()
	{
		return name;
	}
		
	/**
	 * Accessor: returns the id of a member
	 * 
	 * @return ID
	 */
	public int getID()
	{
		return ID;
	}

	/**
	 * Accessor: returns the address of a member
	 * 
	 * @return address
	 */
	public String getAddress()
	{
		return address;
	}

	/**
	 * Accessor: returns the city of a member
	 * 
	 * @return city
	 */
	public String getCity()
	{
		return city;
	}

	/**
	 * Accessor: the state of a member
	 * 
	 * @return state
	 */
	public String getState()
	{
		return state;
	}

	/**
	 * Accessor: the zip code of a member
	 * 
	 * @return ZIP
	 */
	public int getZIP()
	{
		return ZIP;
	}

	/**
	 * Accessor: the valid status of a member
	 * 
	 * @return validstatus
	 */
	public boolean getValidStatus()
	{
		return validstatus;
	}

	/**
	 * Mutator: sets the ID of a member
	 * 
	 * @param id
	 */
	public void setID(int id)
	{
		ID = id; 
	}

	/**
	 * Mutator: sets the valid status of a member
	 * 
	 * @param status
	 */
	public void setValidStatus(boolean status)
	{
		validstatus = status; 
	}

	/**
	 * Mutator: sets the name of a member 
	 * 
	 * @param Name
	 */
	public void setName(String Name)
	{
		name = Name;
	}

	/**
	 * Mutator: sets the address of a member
	 * 
	 * @param Address
	 */
	public void setAddress(String Address)
	{
		address = Address;
	}

	/**
	 * Mutator: sets the city of a member
	 * 
	 * @param City
	 */
	public void setCity(String City)
	{
		city = City; 
	}

	/**
	 * Mutator: sets the state of a member
	 * 
	 * @param State
	 */
	public void setState(String State) 
	{
		state = State;
	}

	/**
	 * Mutator: sets the ZIP code of a member
	 * 
	 * @param zip
	 */
	public void setZIP(int zip)
	{
		ZIP = zip;
	}
}
