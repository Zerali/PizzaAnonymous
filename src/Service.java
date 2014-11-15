
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
	
	/** Returns the ID of the service **/
	public int getID(){
		return ID;
	}
	
	/** Returns the Name of the service**/
	public String getName(){
		return name;
	}
	
	/** Returns the Cost of the Service **/
	public double getCost(){
		return cost;
	}
	
	/** Sets the name attribute of the service **/
	public void setName(String newName){
		this.name = newName;
	}
	
	/** Sets the cost attribute of the service **/
	public void setCost(double newCost){
		this.cost = newCost;
	}
	
	/** Sets the ID of the service, should only be accessed by directory **/
	public void setID(int newID){
		this.ID = newID;
	}
}
