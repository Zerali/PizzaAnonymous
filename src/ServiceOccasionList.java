import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * This class handles all things related to the service occasions
 * 
 * TODO Documentation(Adam) Add more info
 * 
 * @author Adam
 */
public class ServiceOccasionList {
	//only attribute is a list of service occasions
	private List<ServiceOccasion> serviceOccasions = new LinkedList<ServiceOccasion>();
	
	
	/**
	 * This adds a service occasion to the list
	 * 
	 * TODO Documentation(Adam) Add Parameter Info
	 * 
	 * @param date
	 * @param provider
	 * @param member
	 * @param service
	 * @param comments
	 */
	public void addServiceOccasion(String date, int provider, int member, int service, String comments){
		
		Date currDate = new Date();
		DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy HH-mm-ss");
		
		//Create new service occasion
		ServiceOccasion newOccasion = new ServiceOccasion(service, member, provider, date, dateFormat.format(currDate), comments);
		
		serviceOccasions.add(newOccasion);
	}
	
	/**
	 * This returns an iterator of the occasions list
	 * 
	 * @return Iterator of the Occasion list
	 */
	public Iterator<ServiceOccasion> getServiceOccasionIterator(){
		Iterator<ServiceOccasion> occasionIt = serviceOccasions.iterator();
		return occasionIt;
	}
}
