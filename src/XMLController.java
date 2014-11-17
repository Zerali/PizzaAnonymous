import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.XStreamException;

/**
 * Controller for XML functions. This controller is responsible for saving and
 * loading the XML data
 * 
 * @author Blake
 */
public class XMLController {
	
	// The XStream instance from the XStream library
	private static XStream xStream = new XStream();
	
	/**
	 * Serialize member data to XML
	 * 
	 * @param object The MemberController which will be stored
	 */
	public void saveMembers(MemberController object)
	{
		// Utilize the XStream library
		writeXMLFile(object, "MemberData.xml");
	}

	/**
	 * Serialize provider data to XML
	 * 
	 * @param object The ProviderController which will be stored
	 */
	public void saveProviders(ProviderController object)
	{
		// Utilize the XStream library
		writeXMLFile(object, "ProviderData.xml");
	}

	/**
	 * Serialize service data to XML
	 * 
	 * @param object The ServiceController which will be stored
	 */
	public void saveServices(ServiceController object)
	{
		// Utilize the XStream library
		writeXMLFile(object, "ServiceData.xml");
	}

	/**
	 * Deserialize the member data from XML
	 * 
	 * @return A MemberController object from the XML data, with its data fields
	 *         restored
	 */
	public MemberController loadMembers()
	{
		try {
			// Utilize the XStream library. Try to deserialize
			return (MemberController) xStream.fromXML(new FileInputStream("MemberData.xml"));
		} catch (Exception e) {
			// If it fails, file probably doesn't exist yet. So create a new object instance instead
			System.err.println("Could not load MemberData.xml. Returning new MemberController object.");
			return new MemberController();
		}
	}

	/**
	 * Deserialize the provider data from XML
	 * 
	 * @return A ProviderController object from the XML data, with its data
	 *         fields restored
	 */
	public ProviderController loadProviders()
	{
		try {
			// Utilize the XStream library. Try to deserialize
			return (ProviderController) xStream.fromXML(new FileInputStream("ProviderData.xml"));
		} catch (Exception e) {
			// If it fails, file probably doesn't exist yet. So create a new object instance instead
			System.err.println("Could not load MemberData.xml. Returning new ProviderController object.");
			return new ProviderController();
		}
	}

	/**
	 * Deserialize the service data from XML
	 * 
	 * @return A ServiceController object from the XML data, with its data
	 *         fields restored
	 */
	public ServiceController loadServices()
	{
		try {
			// Utilize the XStream library. Try to deserialize
			return (ServiceController) xStream.fromXML(new FileInputStream("ServiceData.xml"));
		} catch (Exception e) {
			// If it fails, file probably doesn't exist yet. So create a new object instance instead
			System.err.println("Could not load MemberData.xml. Returning new ServiceController object.");
			return new ServiceController();
		}
	}
	
	/**
	 * Create and write to the file
	 * 
	 * @param object The object to serialize
	 * @param filename The name of the file to write to
	 */
	private void writeXMLFile(Object object, String filename)
	{
		try {
			FileOutputStream out = new FileOutputStream(filename);
			try {
				xStream.toXML(object, out);
				out.flush();
			} catch (XStreamException e) {
				System.err.println("writeXMLFile: Could not write " + filename);
			} finally {
				out.close();
			}
		} catch (IOException e) {
			System.err.println("writeXMLFile: Could not write " + filename);
		}
	}

}
