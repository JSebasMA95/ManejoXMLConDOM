package ManejoXMLconDOM;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.File;

public class DeleteXML {
	public void deleteElement(String filePath) {
        try {
            File inputFile = new File(filePath);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();

            // Obtener todas las etiquetas "estado"
            NodeList nodeList = doc.getElementsByTagName("estado");

            // Usar un bucle while para eliminar nodos
            while (nodeList.getLength() > 0) {
                Node node = nodeList.item(0);
                node.getParentNode().removeChild(node);
            }

            // Escribir los cambios en un nuevo archivo
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("ciudades_eliminar.xml"));
            transformer.transform(source, result);

            System.out.println("Todas las etiquetas 'estado' han sido eliminadas y los cambios se han guardado en un nuevo archivo.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
