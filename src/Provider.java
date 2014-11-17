/**
 * Basic Provider entity class
 * 
 * TODO Documentation(Nick) add more information
 * 
 * @author Nick
 */
public class Provider {
	// Basic information about the Provider this report concerns
	private int ID;
	private String name;
	private String address;
	private String city;
	private String state;
	private int ZIP;
	
	/**
	 * TODO Documentation(Nick)
	 * 
	 * @param ID
	 * @param name
	 * @param address
	 * @param city
	 * @param state
	 * @param ZIP
	 */
	public Provider (int ID, String name, String address, String city, String state, int ZIP)
	{
		this.ID = ID;
		this.name = name;
		this.address = address;
		this.city = city;
		this.state = state;
		this.ZIP = ZIP;
	}
	
	/**
	 * TODO Documentation(Nick)
	 * 
	 * @return
	 */
	public int getID()
	{
		return ID;
	}
	
	/**
	 * TODO Documentation(Nick)
	 * 
	 * @return
	 */
	public String getName()
	{
		return name;
	}
	
	/**
	 * TODO Documentation(Nick)
	 * 
	 * @return
	 */
	public String getAddress()
	{
		return address;
	}
	
	/**
	 * TODO Documentation(Nick)
	 * 
	 * @return
	 */
	public String getCity()
	{
		return city;
	}
	
	public String getState()
	{
		return state;
	}
	
	/**
	 * TODO Documentation(Nick)
	 * 
	 * @return
	 */
	public int getZIP()
	{
		return ZIP;
	}
	
	/**
	 * TODO Documentation(Nick)
	 * 
	 * @param Name
	 */
	public void setName(String Name)
	{
		name = Name;
	}

	/**
	 * TODO Documentation(Nick)
	 * 
	 * @param Address
	 */
	public void setAddress(String Address)
	{
		address = Address;
	}

	/**
	 * TODO Documentation(Nick)
	 * 
	 * @param City
	 */
	public void setCity(String City)
	{
		city = City;
	}

	/**
	 * TODO Documentation(Nick)
	 * 
	 * @param State
	 */
	public void setState(String State)
	{
		state = State;
	}

	/**
	 * TODO Documentation(Nick)
	 * 
	 * @param ZIP
	 */
	public void setZIP(int ZIP)
	{
		this.ZIP = ZIP;
	}
}
