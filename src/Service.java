/** 
 * Basic entity class - Service
 * 
 * TODO Documentation(Adam) add more in depth info
 * 
 * @author Adam
 */
public class Service {
	// Unique identifier number for service
	private int ID;
	// Name of the service
	private String name;
	// Service fee
	private float cost;
	
	/**
	 * Returns the ID of the service
     *
	 * @return ID The ID of the particular service
	 */
	public int getID(){
		return ID;
	}
	
	/** 
	 * Returns the name field of the service
     *
	 * @return name The name of the service (20 characters or less)
	 */
	public String getName(){
		return name;
	}
	
	/** 
	 * Returns the cost field of the service
	 *
	 * @return cost The cost of the particular service
	 */
	public double getCost(){
		return cost;
	}
	
	/** 
	 * Function to set the name of the service
	 *
	 * @param newName The new name to be changed in the service
	 */
	public void setName(String newName){
		this.name = newName;
	}
	
	/** 
	 * Function to set the cost of a service
	 *
	 * @param newCost The new cost of the service
	 */
	public void setCost(double newCost){
		this.cost = newCost;
	}
	
	/**
	 * Function to set the ID field of the service
	 *
	 * @param newID The new ID of the service
	 */
	public void setID(int newID){
		this.ID = newID;
	}
}
