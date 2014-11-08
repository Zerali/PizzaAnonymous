import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;


/**
 * The controller that deals with report related tasks. 
 * Takes care of creating reports and placing information into the report.
 * 
 * @author Blake
 *
 */
public class ReportController {
	
	/**
	 * Create all of the weekly reports.  This will create a 
	 * MemberServiceReport for every member, ProviderServiceReport for every Provider, 
	 * EFT Report, and Summary Report.
	 * @return True if this task is successful
	 */
	public boolean createWeeklyReports()
	{
		boolean success = true; // Flag. True if all reports successful
		
		// Make a member report for each member
		Iterator<Member> memberIt = PizzaAnonymous.getInstance().getMemberList();
		while(memberIt.hasNext())
		{
			success &= makeMemberSvcReport(memberIt.next().getID());
		}
		
		// Make a provider report for each provider
		Iterator<Provider> providerIt = PizzaAnonymous.getInstance().getProviderList();
		while(providerIt.hasNext())
		{
			success &= makeProviderSvcReport(providerIt.next().getID());
		}
		
		// Make the EFT report
		success &= makeEFTReport();
		
		// Make the summary report
		success &= makeSummaryReport();
		
		return success;
	}
	
	/**
	 * Create a Member Service Report for the given member. 
	 * @param memberID Who to create the report for
	 * @return True if the task is successful
	 */
	public boolean makeMemberSvcReport(int memberID)
	{
		MemberReport report;

		// Obtain the relevant member
		Member member = PizzaAnonymous.getInstance().getMember(memberID);
		
		// Make sure the member exists
		if(member == null)
		{
			return false;
		}

		// Will need to get and set multiple fields of data
		report.setName(member.getName());
		report.setId(member.getID());
		report.setAddress(member.getAddress());
		report.setCity(member.getCity());
		report.setState(member.getState());
		report.setZip(member.getZip());

		// Look through service occasions for ones provided to the member
		Iterator<ServiceOccasion> serviceOccasionIt = PizzaAnonymous.getInstance().getServiceOccasions();
		while(serviceOccasionIt.hasNext())
		{
			// Pull out the ServiceOccasion from the iterator
			ServiceOccasion occasion = serviceOccasionIt.next();
			
			// Again there will be multiple fields to get and set
			report.addServiceInfo(occasion.getDateProvided(), 
					PizzaAnonymous.getInstance().getProvider(occasion.getProviderID()).getName(), 
					PizzaAnonymous.getInstance().getService(occasion.getServiceID()).getName());
		}

		// Finally save the report to a file
		return report.saveReport();
	}
	
	/**
	 * Create a Provider Service Report for the given provider. 
	 * @param providerID Who to create the report for
	 * @return True if the task is successful
	 */
	public boolean makeProviderSvcReport(int providerID)
	{
		ProviderReport report;

		// Obtain a reference to the provider
		Provider provider = PizzaAnonymous.getInstance().getProvider(providerID);
		
		// Make sure the provider exists
		if(provider == null)
		{
			return false;
		}

		// Will need to get and set multiple fields
		report.setName(provider.getName());
		report.setId(provider.getID());
		report.setAddress(provider.getAddress());
		report.setCity(provider.getCity());
		report.setState(provider.getState());
		report.setZip(provider.getZip());

		// Look through the services for ones provided by the provider
		Iterator<ServiceOccasion> serviceOccasionIt = PizzaAnonymous.getInstance().getServiceOccasions();
		while(serviceOccasionIt.hasNext())
		{
			// Pull out the ServiceOccasion from the iterator
			ServiceOccasion occasion = serviceOccasionIt.next();
						
			// Again will be multiple lookups and assignments
			report.addServiceInfo(occasion.getDateProvided(), 
					occasion.getDateReceived(), 
					PizzaAnonymous.getInstance().getMember(occasion.getMemberID()).getName(), 
					occasion.getMemberID(), 
					occasion.getServiceID(), 
					PizzaAnonymous.getInstance().getService(occasion.getServiceID()).getFee());
		}

		// Finally save the report to file
		return report.saveReport();
	}
	
	/**
	 * Create a Summary Report for this week. 
	 * @return True if the task is successful
	 */
	public boolean makeSummaryReport()
	{
		/** The report object */
		SummaryReport report;
		
		/** Mapping from provider ID to summary information. This array is in order: 
		 *  String name, integer # consultations, double fee */
		Map<Integer, Object[]> map;
		
		// Total figures used at end of report
		int totalProviders = 0;
		int totalConsults = 0;
		double totalFee = 0.00;

		// Look through the services to tally up data
		Iterator<ServiceOccasion> serviceOccasionIt = PizzaAnonymous.getInstance().getServiceOccasions();
		while(serviceOccasionIt.hasNext())
		{
			// Pull out the reference to the service occasion
			ServiceOccasion occasion = serviceOccasionIt.next();
			
			// Obtain a reference to the Provider that gave the service
			Provider provider = PizzaAnonymous.getInstance().getProvider(occasion.getProviderID());

			// Put an entry in the map for the provider, if there isn’t already one
			if(!map.containsKey(provider.getID()))
			{
				map.put(provider.getID(), new Object[] {provider.getName(), 0, 0});
				
				// Increment since there's another provider thats done something
				totalProviders++;
			}

			// Get the entry from the map (we know it exists now)
			Object[] value = map.get(provider.getID());

			// Update the entry’s data
			value[1] = ((Integer) value[1]).intValue() + 1;
			totalConsults++;

			// Update the fee information
			Service service = PizzaAnonymous.getService(occasion.getServiceID());
			value[2] += service.getCost();
			totalFee += service.getCost();
		}

		// Add a line to the report for all of the entries
		for(Entry<Integer, Object[]> entry : map.entrySet())
		{	
			// Values are stored as an Object array, but addLine expects primitives. Cast & let Java unbox
			report.addLine((String) entry.getValue()[0], (Integer) entry.getValue()[1], (Double) entry.getValue()[2]);
		}

		// Assign the cumulative information (multiple fields)
		report.setTotalProviders(totalProviders);
		report.setTotalConsultations(totalConsults);
		report.setTotalFee(totalFee);

		// Finally save the report to file
		return report.saveReport();
	}
	
	/**
	 * Create an EFT report for this week
	 * @return True if the task is successful
	 */
	public boolean makeEFTReport()
	{
		EFTReport report;

		// Look through all of the providers
		Iterator<Provider> providerIt = PizzaAnonymous.getInstance().getProviderList();
		while(providerIt.hasNext())
		{
			Provider provider = providerIt.next();
			
			// Keep track of how much we owe this provider
			double fee = 0.00;

			// And look through each service occasion as well
			Iterator<ServiceOccasion> serviceOccasionIt = PizzaAnonymous.getInstance().getServiceOccasions();
			while(serviceOccasionIt.hasNext())
			{
				ServiceOccasion occasion = serviceOccasionIt.next();
				
				fee += PizzaAnonymous.getInstance().getService(occasion.getServiceID()).fee;
			}

			// We can now add a line to the EFT report
			report.addLine(provider.getName(), provider.getID(), fee);
		}

		// Done populating data, time to save the report
		return report.saveReport();
	}

}
