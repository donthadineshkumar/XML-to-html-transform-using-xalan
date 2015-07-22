import java.nio.file.Path;
import java.nio.file.Paths;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;


public class App {

	public static void main(String[] args) {
		
		//StreamSource acts a holder for transformation source
		StreamSource xslStreamSource 
			= new StreamSource(Paths.get("src/main/resources/products.xsl").toAbsolutePath().toFile());
		
		StreamSource xmlStreamSource 
			= new StreamSource(Paths.get("src/main/resources/products.xml").toAbsolutePath().toFile());
		
		//there are many api for transforming an xml to XXX format files using its xsl
		
		TransformerFactory transformerFactory
			= TransformerFactory.newInstance("org.apache.xalan.processor.TransformerFactoryImpl", null);
		
		Path pathToHtmlfile = Paths.get("src/main/resources/myfile.html");
		
		//StreamSource acts a holder for transformation result
		StreamResult result = new StreamResult(pathToHtmlfile.toFile());
		
		try {
			Transformer transformer = transformerFactory.newTransformer(xslStreamSource);
			transformer.transform(xmlStreamSource, result);
			
			
		} catch (TransformerConfigurationException e) {
						e.printStackTrace();
		} catch (TransformerException e) {
			e.printStackTrace();
		}
		
		
	}
	
}
