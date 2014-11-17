/**
 * This class hold all the information needed to keep track of when a service is
 * used by a member. The main purpose of this class is to be used as a reference
 * when creating reports
 * 
 * @author Adam
 */
public class ServiceOccasion {
	//self-explanatory attributes
	private int serviceID;
	private int memberID;
	private int providerID;
	private String dateProvided;
	private String dateReceived;
	private String comments;
	
	/**
	 * Constructor
	 * 
	 * TODO Documentation(Adam) Add Parameter Info
	 * 
	 * @param service
	 * @param member
	 * @param provider
	 * @param date
	 * @param currdate
	 * @param comments2
	 */
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
	 * Returns the serviceID of the objects
	 *
	 * @return serviceID the id of the service that was provided
	 */
	public int getServiceID(){
		return serviceID;
	}

	/**
	 * Returns the memberID of the object
	 *
	 * @return memberID The member the service was provided to
	 */
	public int getMemberID(){
		return memberID;
	}
	
	/**
	 * Returns the providerID of the object
	 *
	 * @return providerID The provider the service was provided from
	 */
	public int getProviderID(){
		return providerID;
	}
	
	/**
	 * Returns the date the service was provided
	 *
	 * @return dateProvided the date the service was provided
	 */
	public String getDateProvided(){
		return dateProvided;
	}

	/**
	 * Returns the date the service occasion was received in the system
	 *
	 * @return dateReceived the date the occasion was captured in the system
	 */
	public String getDateReceived(){
		return dateReceived;
	}

	/**
	 * Returns the comments field of the service occasion
	 *
	 * @return Any comments given during the capture of the service provision
	 */
	public String getComments(){
		return comments;
	}
}
