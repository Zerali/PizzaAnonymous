/**
 * Still working on this. Not done completely.
 */

/**
 * TODO Documentation(Amila)
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
	 * TODO Documentation(Amila)
	 * 
	 * @return name
	 */
	//Accessor method to return the value of the private data member name
	public String getName()
	{
		return name;
	}
		
	/**
	 * TODO Documentation(Amila)
	 * 
	 * @return ID
	 */
	//Accessor method to return the value of the private data member ID	
	public int getID()
	{
		return ID;
	}

	/**
	 * TODO Documentation(Amila)
	 * 
	 * @return address
	 */
	//Accessor method to return the value of the private data member address
	public String getAddress()
	{
		return address;
	}

	/**
	 * TODO Documentation(Amila)
	 * 
	 * @return city
	 */
	//Accessor method to return the value of the private data member city
	public String getCity()
	{
		return city;
	}

	/**
	 * TODO Documentation(Amila)
	 * 
	 * @return state
	 */
	//Accessor method to return the value of the private data member state
	public String getState()
	{
		return state;
	}

	/**
	 * TODO Documentation(Amila)
	 * 
	 * @return ZIP
	 */
	//Accessor method to return the value of the private data member ZIP
	public int getZIP()
	{
		return ZIP;
	}

	/**
	 * TODO Documentation(Amila)
	 * 
	 * @return validstatus
	 */
	//Accessor method to return the value of the private data member //validstatus
	public boolean getValidStatus()
	{
		return validstatus;
	}

	/**
	 * TODO Documentation(Amila)
	 * 
	 * @param id
	 */
	//Mutator to set the value of the private data member ID
	public void setValidStatus(int id)
	{
		ID = id; 
	}

	/**
	 * TODO Documentation(Amila)
	 * 
	 * @param status
	 */
	//Mutator to set the value of the private data member validstatus
	public void setValidStatus(boolean status)
	{
		validstatus = status; 
	}

	/**
	 * TODO Documentation(Amila)
	 * 
	 * @param Name
	 */
	//Mutator to set the value of the private data member name
	public void setName(String Name)
	{
		name = Name;
	}

	/**
	 * TODO Documentation(Amila)
	 * 
	 * @param Address
	 */
	//Mutator to set the value of the private data member address
	public void setAddress(String Address)
	{
		address = Address;
	}

	/**
	 * TODO Documentation(Amila)
	 * 
	 * @param City
	 */
	//Mutator to set the value of the private data member city
	public void setCity(String City)
	{
		city = City; 
	}

	/**
	 * TODO Documentation(Amila)
	 * 
	 * @param State
	 */
	//Mutator to set the value of the private data member state
	public void setState(String State) 
	{
		state = State;
	}

	/**
	 * TODO Documentation(Amila)
	 * 
	 * @param zip
	 */
	//Mutator to set the value of the private data member ZIP
	public void setZIP(int zip)
	{
		ZIP = zip;
	}
}
