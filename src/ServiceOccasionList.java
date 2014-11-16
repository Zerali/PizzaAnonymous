import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * this class handles all things related to the service occasions
 * 
 * @author Adam
 *
 */
public class ServiceOccasionList {
	//only attribute is a list of service occasions
	private List<ServiceOccasion> serviceOccasions = new LinkedList<ServiceOccasion>();
	
	
	// Methods
	
	/**
	 * creates a new service occasion and adds it to the list of occasions
	 * 
	 * @param date the date the service was provided
	 * @param provider the provider that provided the service
	 * @param member the member that received the service
	 * @param service the service that was provided
	 * @param comments and comments about the service (100 chars or less)
	 */
	public void addServiceOccasion(String date, int provider, int member, int service, String comments){
		
		Date currDate = new Date();
		DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy HH-mm-ss");
		
		//check if comments string is too long
		if(comments.length > 100)
			comments = comments.substring(0, 99);
		
		//Create new service occasion
		ServiceOccasion newOccasion = new ServiceOccasion(service, member, provider, date, dateFormat.format(currDate), comments);
		
		serviceOccasions.add(newOccasion);
	}
	
	/**
	 * creates an Iterator of the service occasions list and sends it off
	 * 
	 * @return an Iterator of serviceOccasions
	 */
	public Iterator<ServiceOccasion> getServiceOccasionIterator(){
		Iterator<ServiceOccasion> occasionIt = serviceOccasions.iterator();
		return occasionIt;
	}
}
