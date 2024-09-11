package ManejoXMLconDOM;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class ReadXML {
	public void readXML(String routeFile) {
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
            
         // Imprimir el árbol del DOM XML
            printDOMTree(doc.getDocumentElement(), 0);
            System.out.println("-------------------------");
         
         // Obtener la lista de nodos de las ciudades
            NodeList listElements = doc.getElementsByTagName("ciudad");

         // Recorrer la lista de nodos
            for (int i = 0; i < listElements.getLength(); i++) {
                Node node = listElements.item(i);

                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;

                    // Obtener y mostrar los datos de cada ciudad
                    String id = element.getAttribute("id");
                    String nombre = element.getElementsByTagName("nombre").item(0).getTextContent();
                    String pais = element.getElementsByTagName("ubicacion").item(0).getAttributes().getNamedItem("pais").getTextContent();
                    String latitud = element.getElementsByTagName("latitud").item(0).getTextContent();
                    String longitud = element.getElementsByTagName("longitud").item(0).getTextContent();
                    String poblacion = element.getElementsByTagName("poblacion").item(0).getTextContent();

                    System.out.println("Ciudad ID: " + id);
                    System.out.println("Nombre: " + nombre);
                    System.out.println("País: " + pais);
                    System.out.println("Latitud: " + latitud);
                    System.out.println("Longitud: " + longitud);
                    System.out.println("Población: " + poblacion);
                    System.out.println("-------------------------");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	
	private void printDOMTree(Node node, int level) {
		// Imprimir el nodo actual con sangría según el nivel
        for (int i = 0; i < level; i++) {
            System.out.print("  ");
        }
        System.out.println(node.getNodeName());

        // Recorrer los nodos hijos
        NodeList nodeList = node.getChildNodes();
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node childNode = nodeList.item(i);
            if (childNode.getNodeType() == Node.ELEMENT_NODE) {
                printDOMTree(childNode, level + 1);
            }
        }
    }
}
