package ManejoXMLconDOM;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class ModifyXML {
	public void modifyXML(String routeFile) {
		try {
            File fileXML = new File(routeFile);
            
         // Crear una instancia de DocumentBuilderFactory
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
         // Crear un DocumentBuilder
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
         // Parsear el archivo XML y obtener el documento
            Document doc = dBuilder.parse(fileXML);
            
         // Normalizar el documento
            doc.getDocumentElement().normalize();

         // Obtener la lista de nodos de las ciudades
            NodeList listElements = doc.getElementsByTagName("ciudad");
         
         // Modificar las poblaciones de las ciudades
            for (int i = 0; i < listElements.getLength(); i++) {
                Element ciudad = (Element) listElements.item(i);
                Element poblacionElement = (Element) ciudad.getElementsByTagName("poblacion").item(0);
                int poblacion = Integer.parseInt(poblacionElement.getTextContent());
                poblacion += 100000; // Incrementar la población en 100,000
                poblacionElement.setTextContent(String.valueOf(poblacion));
            }
            
         // Guardar los cambios en el archivo XML
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("ciudades_modificado.xml"));
            transformer.transform(source, result);

            System.out.println("Archivo XML modificado y guardado como 'ciudades_modificado.xml'");
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
}
