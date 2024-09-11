package ManejoXMLconDOM;

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

import java.io.File;


public class AddElement {
	public void addUTCTag(String filePath) {
        try {
            File inputFile = new File(filePath);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();

            // Obtener todas las etiquetas "ciudad"
            NodeList nodeList = doc.getElementsByTagName("ciudad");
            for (int i = 0; i < nodeList.getLength(); i++) {
                Element ciudad = (Element) nodeList.item(i);
                Element horarioUTC = doc.createElement("horarioUTC");

                // Asignar un valor de ejemplo para cada ciudad
                switch (ciudad.getElementsByTagName("nombre").item(0).getTextContent()) {
                    case "Bogotá":
                        horarioUTC.setTextContent("-05:00");
                        break;
                    case "Buenos Aires":
                        horarioUTC.setTextContent("-03:00");
                        break;
                    case "Ciudad de México":
                        horarioUTC.setTextContent("-06:00");
                        break;
                    case "Lima":
                        horarioUTC.setTextContent("-05:00");
                        break;
                    case "Santiago":
                        horarioUTC.setTextContent("-04:00");
                        break;
                    default:
                        horarioUTC.setTextContent("UTC");
                        break;
                }

                ciudad.appendChild(horarioUTC);
            }

            // Escribir los cambios en un nuevo archivo
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("ciudades_agregar_etiqueta.xml"));
            transformer.transform(source, result);

            System.out.println("La etiqueta 'horarioUTC' ha sido añadida a todas las ciudades y los cambios se han guardado en un nuevo archivo.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
