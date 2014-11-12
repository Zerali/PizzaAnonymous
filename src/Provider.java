/**
 * Basic Provider entity class
 * @author nick
 *
 */
public class Provider {
	private int ID;
	private String name;
	private String address;
	private String city;
	private String state;
	private int ZIP;
	
	public Provider (int ID, String name, String address, String city, String state, int ZIP)
	{
		this.ID = ID;
		this.name = name;
		this.address = address;
		this.city = city;
		this.state = state;
		this.ZIP = ZIP;
	}
	
	public int getID()
	{
		return ID;
	}
	
	public String getName()
	{
		return name;
	}
	
	public String getAddress()
	{
		return address;
	}
	
	public String getCity()
	{
		return city;
	}
	
	public String getState()
	{
		return state;
	}
	
	public int getZIP()
	{
		return ZIP;
	}
	
	public void setName(String Name)
	{
		name = Name;
	}

	public void setAddress(String Address)
	{
		address = Address;
	}

	public void setCity(String City)
	{
		city = City;
	}

	public void setState(String State)
	{
		state = State;
	}

	public void setZIP(int ZIP)
	{
		this.ZIP = ZIP;
	}
}
