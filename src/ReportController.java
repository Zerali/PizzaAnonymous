import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * The controller that deals with report related tasks. Takes care of creating
 * reports and placing information into the report. Has overloaded methods for
 * each, one that accepts report parameters for unit testing and another with
 * less parameters for normal usage.
 * 
 * @author Blake
 */
public class ReportController {
	
	/**
	 * Create all of the weekly reports.  This will create a MemberServiceReport
	 * for every member, ProviderServiceReport for every Provider, EFT Report,
	 * and Summary Report.
	 * 
	 * @param eftReport2 
	 * @param summaryReport2 
	 * @param providerReports2 
	 * @param memberReports2 
	 * @return True if this task is successful
	 */
	public boolean createWeeklyReports()
	{
		// Create each report we'll need
		List<MemberReport> memberReports = new LinkedList<MemberReport>();
		List<ProviderReport> providerReports = new LinkedList<ProviderReport>();
		SummaryReport summaryReport = new SummaryReport();
		EFTReport eftReport = new EFTReport();
		
		// Need to fill the lists
		Iterator<Member> memberIt = PizzaAnonymous.getInstance().getMemberList();
		while(memberIt.hasNext())
		{
			memberReports.add(new MemberReport());
		}
		
		Iterator<Provider> providerIt = PizzaAnonymous.getInstance().getProviderList();
		while(providerIt.hasNext())
		{
			providerReports.add(new ProviderReport());
		}
		
		// Then delegate the call
		return createWeeklyReports(memberReports, providerReports, summaryReport, eftReport);
	}
	
	/**
	 * Fill in all of the weekly reports. Takes each report as a parameter. The
	 * size of the report lists must match the size of the corresponding data
	 * list, and each report should be initialized. This method is to aid in
	 * unit testability
	 * 
	 * @return True if this task is successful
	 */
	public boolean createWeeklyReports(List<MemberReport> memberReports, 
			List<ProviderReport> providerReports, 
			SummaryReport summaryReport, 
			EFTReport eftReport)
	{
		boolean success = true; // Flag. True if all reports successful
		
		// Make a member report for each member
		Iterator<Member> memberIt = PizzaAnonymous.getInstance().getMemberList();
		Iterator<MemberReport> memberReportIt = memberReports.iterator();
		while(memberIt.hasNext() && memberReportIt.hasNext())
		{
			success &= makeMemberSvcReport(memberIt.next().getID(), memberReportIt.next());
		}
		
		// Make a provider report for each provider
		Iterator<Provider> providerIt = PizzaAnonymous.getInstance().getProviderList();
		Iterator<ProviderReport> providerReportIt = providerReports.iterator();
		while(providerIt.hasNext() && providerReportIt.hasNext())
		{
			success &= makeProviderSvcReport(providerIt.next().getID(), providerReportIt.next());
		}
		
		// Make the EFT report
		success &= makeEFTReport(eftReport);
		
		// Make the summary report
		success &= makeSummaryReport(summaryReport);
		
		return success;
	}
	
	/**
	 * Create a Member Service Report for the given member.
	 * 
	 * @param memberID Who to create the report for
	 * @return True if the task is successful
	 */
	public boolean makeMemberSvcReport(int memberID)
	{
		// Create a blank report to start with
		MemberReport report = new MemberReport();
		
		// Delegate to the overloaded method
		return makeMemberSvcReport(memberID, report);
	}
	
	/**
	 * Create a Member Service Report for the given member, placing the
	 * information in the provided report.
	 * 
	 * @param memberID Who to create the report for
	 * @param report The report the information will be placed into
	 * @return True if the task is successful
	 */
	public boolean makeMemberSvcReport(int memberID, MemberReport report)
	{
		if(report == null)
		{
			System.err.println("Must provide initialized MemberReport object");
			return false;
		}
		
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
		report.setZip(String.valueOf(member.getZIP()));

		// Look through service occasions for ones provided to the member
		Iterator<ServiceOccasion> serviceOccasionIt = PizzaAnonymous.getInstance().getServiceOccasions();
		while(serviceOccasionIt.hasNext())
		{
			// Pull out the ServiceOccasion from the iterator
			ServiceOccasion occasion = serviceOccasionIt.next();
			
			// Check to see if the service was provided to this member
			if(occasion.getMemberID() != member.getID())
			{
				// Not a match, skip over it
				continue;
			}
			
			// We need the Provider and Service for some info. Get references first
			Provider provider = PizzaAnonymous.getInstance().getProvider(occasion.getProviderID());
			Service service = PizzaAnonymous.getInstance().getService(occasion.getServiceID());
			
			// Null check them, in case they were deleted for some reason
			if(provider == null || service == null)
			{
				// One was null.. report the issue and stop
				System.err.println("makeMemberSvcReport: Null provider or service.");
				return false;
			}
			
			// Again there will be multiple fields to get and set
			report.addServiceInfo(occasion.getDateProvided(), provider.getName(), service.getName());
		}

		// Finally save the report to a file
		return report.saveReport();
	}
	
	/**
	 * Create a Provider Service Report for the given provider.
	 * 
	 * @param providerID Who to create the report for
	 * @return True if the task is successful
	 */
	public boolean makeProviderSvcReport(int providerID)
	{
		// Create a blank report to start with
		ProviderReport report = new ProviderReport();

		return makeProviderSvcReport(providerID, report);
	}
	
	/**
	 * Create a Provider Service Report for the given provider.
	 * 
	 * @param providerID Who to create the report for
	 * @param report The ProviderReport information will be placed in
	 * @return True if the task is successful
	 */
	public boolean makeProviderSvcReport(int providerID, ProviderReport report)
	{
		if(report == null)
		{
			System.err.println("Must provide initialized ProviderReport object");
			return false;
		}

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
		report.setZip(String.valueOf(provider.getZIP()));

		// Look through the services for ones provided by the provider
		Iterator<ServiceOccasion> serviceOccasionIt = PizzaAnonymous.getInstance().getServiceOccasions();
		while(serviceOccasionIt.hasNext())
		{
			// Pull out the ServiceOccasion from the iterator
			ServiceOccasion occasion = serviceOccasionIt.next();
			
			// Need to match up the occasion to the provider
			if(occasion.getProviderID() != provider.getID())
			{
				// Not a match, skip it
				continue;
			}
			
			// We need a Member and Service reference for some info. Get them up front
			Member member = PizzaAnonymous.getInstance().getMember(occasion.getMemberID());
			Service service = PizzaAnonymous.getInstance().getService(occasion.getServiceID());
			
			// Null check them, in case they were deleted for some reason
			if(member == null || service == null)
			{
				// One was null.. report the issue and stop
				System.err.println("makeMemberSvcReport: Null member or service.");
				return false;
			}
						
			// Again will be multiple lookups and assignments
			report.addServiceInfo(occasion.getDateProvided(), 
					occasion.getDateReceived(), 
					member.getName(), 
					occasion.getMemberID(), 
					occasion.getServiceID(), 
					service.getCost());
		}

		// Finally save the report to file
		return report.saveReport();
	}
	
	/**
	 * Create a Summary Report for this week.
	 * 
	 * @return True if the task is successful
	 */
	public boolean makeSummaryReport()
	{
		/** The report object */
		SummaryReport report = new SummaryReport();
		
		return makeSummaryReport(report);
	}
	
	/**
	 * Create a Summary Report for this week.
	 * 
	 * @param report The SummaryReport to put information into
	 * @return True if the task is successful
	 */
	public boolean makeSummaryReport(SummaryReport report)
	{
		if(report == null)
		{
			System.err.println("Must provided initialized SummaryReport object");
			return false;
		}
		
		/** Mapping from provider ID to summary information. This array is in order: 
		 *  String name, integer # consultations, double fee */
		Map<Integer, Object[]> map = new HashMap<Integer, Object[]>();
		
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
			
			// Also get a reference to the plain Service
			Service service = PizzaAnonymous.getInstance().getService(occasion.getServiceID());
			
			// Make sure the Provider is still around
			if(provider == null)
			{
				// Since they're gone, we're really left no choice but to skip this service
				System.err.println("makeSummaryReport: Missing provider (ID " + occasion.getProviderID() + ")");
				continue;
			}
			
			// Null checks. Can't break this code! (Did I just jinx it?)
			if(service == null)
			{
				System.err.println("makeSummaryReport: Missing service (ID " + occasion.getServiceID() + ")");
				continue;
			}

			// Put an entry in the map for the provider, if there isn’t already one
			if(!map.containsKey(provider.getID()))
			{
				map.put(provider.getID(), new Object[] {provider.getName(), 0, 0.0});
				
				// Increment since there's another provider thats done something
				totalProviders++;
			}

			// Get the entry from the map (we know it exists now)
			Object[] value = map.get(provider.getID());

			// Update the entry’s data
			value[1] = ((Integer) value[1]).intValue() + 1;
			totalConsults++;

			// Update the fee information
			value[2] = ((Double) value[2]).doubleValue() + service.getCost();
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
	 * 
	 * @return True if the task is successful
	 */
	public boolean makeEFTReport()
	{
		// Create an empty EFT report
		EFTReport report = new EFTReport();

		return makeEFTReport(report);
	}
	
	/**
	 * Create an EFT report for this week
	 * 
	 * @param report The EFTReport to place information into
	 * @return True if the task is successful
	 */
	public boolean makeEFTReport(EFTReport report)
	{
		if(report == null)
		{
			System.err.println("Must provide initialized EFTReport object");
			return false;
		}

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
				Service service = PizzaAnonymous.getInstance().getService(occasion.getServiceID());
				
				// Make sure the service info is still around
				if(service == null)
				{
					// It wasn't. Print an error line and skip past
					System.err.println("makeEFTReport: Missing service (ID " + occasion.getServiceID() + ")");
					continue;
				}
				
				// Add to the total fee
				fee += service.getCost();
			}

			// We can now add a line to the EFT report
			report.addLine(provider.getName(), provider.getID(), fee);
		}

		// Done populating data, time to save the report
		return report.saveReport();
	}
}
