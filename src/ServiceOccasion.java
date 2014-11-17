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
	 * TODO Documentation(Adam)
	 * 
	 * @return
	 */
	public int getServiceID(){
		return serviceID;
	}
	
	/**
	 * TODO Documentation(Adam)
	 * 
	 * @return
	 */
	public int getMemberID(){
		return memberID;
	}
	
	/**
	 * TODO Documentation(Adam)
	 * 
	 * @return
	 */
	public int getProviderID(){
		return providerID;
	}
	
	/**
	 * TODO Documentation(Adam)
	 * 
	 * @return
	 */
	public String getDateProvided(){
		return dateProvided;
	}
	
	/**
	 * TODO Documentation(Adam)
	 * 
	 * @return
	 */
	public String getDateReceived(){
		return dateReceived;
	}
	
	/**
	 * TODO Documentation(Adam)
	 * 
	 * @return
	 */
	public String getComments(){
		return comments;
	}
}
