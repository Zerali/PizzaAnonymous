import java.io.FileOutputStream;

/**
 * Controller for XML functions. 
 * This controller is responsible for saving and loading the XML data
 * 
 * @author Blake
 *
 */
public class XMLController {
	
	/** The XStream instance from the XStream library */
	private static XStream xStream = new XStream();
	
	//TODO: More than likely some alias calls in a static initializer block

	/**
	 * Serialize member data to XML
	 * @param object The MemberController which will be stored
	 */
	public void saveMembers(MemberController object)
	{
		// Utilize the XStream library
		writeXMLFile(object, "MemberData.xml");
	}

	/**
	 * Serialize provider data to XML
	 * @param object The ProviderController which will be stored
	 */
	public void saveProviders(ProviderController object)
	{
		// Utilize the XStream library
		writeXMLFile(object, "ProviderData.xml");
	}

	/**
	 * Serialize service data to XML
	 * @param object The ServiceController which will be stored
	 */
	public void saveServices(ServiceController object)
	{
		// Utilize the XStream library
		writeXMLFile(object, "Service.xml");
	}

	/**
	 * Deserialize the member data from XML
	 * @return A MemberController object from the XML data, with its data fields restored
	 */
	public MemberController loadMembers()
	{
		// Utilize the XStream library
		return xStream.fromXML("MemberData.xml");
	}

	/**
	 * Deserialize the provider data from XML
	 * @return A ProviderController object from the XML data, with its data fields restored
	 */
	public ProviderController loadProviders()
	{
		// Utilize the XStream library
		return xStream.fromXML("ProviderData.xml");
	}

	/**
	 * Deserialize the service data from XML
	 * @return A ServiceController object from the XML data, with its data fields restored
	 */
	public ServiceController loadServices()
	{
		// Utilize the XStream library
		return xStream.fromXML("ServiceData.xml");
	}
	
	/**
	 * Actually create and write to the file. 
	 * Helper method to alleviate redundancy
	 * @param object The object to serialize
	 * @param filename The name of the file to write to
	 */
	private void writeXMLFile(Object object, String filename)
	{
		FileOutputStream out = new FileOutputStream(filename);
		try {
			xStream.toXML(object, out);
			out.flush();
		} finally {
			out.close();
		}
	}

}
