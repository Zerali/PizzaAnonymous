/**
 * This class hold all the information needed to keep track of when a service is used by a member
 * The main purpose of this class is to be used as a reference when creating reports
 * 
 * @author Adam
 *
 */
public class ServiceOccasion {
	//self-explanatory attributes
	private int serviceID;
	private int memberID;
	private int providerID;
	private String dateProvided;
	private String dateReceived;
	private String comments;
	
	//constructor
	public ServiceOccasion(int service, int member, int provider, String date,
			String currdate, String comments2) {
		this.serviceID = service;
		this.memberID = member;
		this.providerID = provider;
		this.dateProvided = date;
		this.dateReceived = currdate;
		this.comments = comments2;
	}
	
	/**
	 * returns the serviceID of the objects
	 * @return serviceID the id of the service that was provided
	 */
	public int getServiceID(){
		return serviceID;
	}
	
	/**
	 * returns the memberID of the object
	 * @return memberID the member the service was provided to
	 */
	public int getMemberID(){
		return memberID;
	}
	
	/**
	 * returns the providerID of the object
	 * @return providerID the provider the service was provided from
	 */
	public int getProviderID(){
		return providerID;
	}
	
	/**
	 * returns the date the service was provided
	 * @return dateProvided the date the service was provided
	 */
	public String getDateProvided(){
		return dateProvided;
	}
	
	/**
	 * returns the date the service occasion was received in the system
	 * @return dateReceived the date the occasion was captured in the system
	 */
	public String getDateReceived(){
		return dateReceived;
	}
	
	/**
	 * returns the comments field of the service occasion
	 * @return comments any comments given during the capture of the service provision
	 */
	public String getComments(){
		return comments;
	}
}
