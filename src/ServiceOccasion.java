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
	
	//standard get functions
	public int getServiceID(){
		return serviceID;
	}
	public int getMemberID(){
		return memberID;
	}
	public int getProviderID(){
		return providerID;
	}
	public String getDateProvided(){
		return dateProvided;
	}
	public String getDateReceived(){
		return dateReceived;
	}
	public String getComments(){
		return comments;
	}
}
