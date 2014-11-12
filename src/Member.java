/**
 * 
 */

/**
 * @author Amila DeSilva
 *
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
	 * 
	 */
	public Member(String name, int ID, String address, String city, String state, int ZIP, boolean validStatus) 
	{
		// TODO Auto-generated constructor stub
		this.name = name;
		this.ID = ID;
		this.address = address;
		this.city = city;
		this.state = state;
		this.ZIP = ZIP;
		this.validStatus = validStatus;
	}

	/**
	 * 
	 * @return name
	 */
	//Accessor method to return the value of the private data member name
	public String getName()
	{
		return name;
	}
		
	/**
	 * 
	 * @return ID
	 */
	//Accessor method to return the value of the private data member ID	
	public int getID()
	{
		return ID;
	}

	/**
	 * 
	 * @return address
	 */
	//Accessor method to return the value of the private data member address
	public String getAddress()
	{
		return address;
	}

	/**
	 * 
	 * @return city
	 */
	//Accessor method to return the value of the private data member city
	public String getCity()
	{
		return city;
	}

	/**
	 * 
	 * @return state
	 */
	//Accessor method to return the value of the private data member state
	public String getState()
	{
		return state;
	}

	/**
	 * 
	 * @return ZIP
	 */
	//Accessor method to return the value of the private data member ZIP
	public int getZIP()
	{
		return ZIP;
	}

	/**
	 * 
	 * @return validstatus
	 */
	//Accessor method to return the value of the private data member //validstatus
	public boolean getValidStatus()
	{
		return validstatus;
	}

	/**
	 * 
	 * @param id
	 */
	//Mutator to set the value of the private data member ID
	public void setValidStatus(int id)
	{
		ID = id; 
	}

	/**
	 * 
	 * @param status
	 */
	//Mutator to set the value of the private data member validstatus
	public void setValidStatus(boolean status)
	{
		validstatus = status; 
	}

	/**
	 * 
	 * @param Name
	 */
	//Mutator to set the value of the private data member name
	public void setName(String Name)
	{
		name = Name;
	}

	/**
	 * 
	 * @param Address
	 */
	//Mutator to set the value of the private data member address
	public void setAddress(String Address)
	{
		address = Address;
	}

	/**
	 * 
	 * @param City
	 */
	//Mutator to set the value of the private data member city
	public void setCity(String City)
	{
		city = City; 
	}

	/**
	 * 
	 * @param State
	 */
	//Mutator to set the value of the private data member state
	public void setState(String State) 
	{
		state = State;
	}

	/**
	 * 
	 * @param zip
	 */
	//Mutator to set the value of the private data member ZIP
	public void setZIP(int zip)
	{
		ZIP = zip;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
}
