/** 
 * Basic entity class - Service
 * 
 * TODO Documentation(Adam) add more in depth info
 * 
 * @author Adam
 */
public class Service {
	//Unique identifier number for service
	private int ID;
	//Name of the service
	private String name;
	//Service fee
	private float cost;
	
	/** 
	 * Returns the ID of the service
	 **/
	public int getID(){
		return ID;
	}
	
	/**
	 * Returns the Name of the service
	 **/
	public String getName(){
		return name;
	}
	
	/**
	 * Returns the Cost of the Service
	 **/
	public float getCost(){
		return cost;
	}
	
	/**
	 * Sets the name attribute of the service
	 **/
	public void setName(String newName){
		this.name = newName;
	}
	
	/**
	 * Sets the cost attribute of the service
	 **/
	public void setCost(float newCost){
		this.cost = newCost;
	}
	
	/**
	 * Sets the ID of the service, should only be accessed by directory
	 **/
	public void setID(int newID){
		this.ID = newID;
	}
}
