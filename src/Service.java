
/** 
 * Basic entity class - Service
 * @author Adam
 *
 */
public class Service {
	private int ID;				//Unique identifier number for service
	private String name;		//Name of the service
	private double cost;			//Service fee
	
	//Basic set and get functions for a base entity class
	
	/** Returns
	 * returns the ID of the service
	 * @return ID the ID of the particular service
	 */
	public int getID(){
		return ID;
	}
	
	/** 
	 * returns the name field of the service
	 * @return name the name of the service (20 characters or less)
	 */
	public String getName(){
		return name;
	}
	
	/** 
	 * returns the cost field of the service
	 * @return cost the cost of the particular service
	 */
	public double getCost(){
		return cost;
	}
	
	/** 
	 * function to set the name of the service
	 * @param newName the new name to be changed in the service
	 */
	public void setName(String newName){
		this.name = newName;
	}
	
	/** 
	 * function to set the cost of a service
	 * @param newCost the new cost of the service
	 */
	public void setCost(double newCost){
		this.cost = newCost;
	}
	
	/**
	 * function to set the ID field of the service
	 * @param newID the new ID of the service
	 */
	public void setID(int newID){
		this.ID = newID;
	}
}
