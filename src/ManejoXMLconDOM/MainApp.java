package ManejoXMLconDOM;

import java.util.Scanner;

public class MainApp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String routeFile = "C:\\Users\\jmoliari\\eclipse-workspace\\ManejoXMLConDOM\\src\\Ciudades.xml";
        
		Scanner scanner = new Scanner(System.in);

        System.out.println("Seleccione una opción:");
        System.out.println("1. Leer XML");
        System.out.println("2. Modificar XML");
        System.out.println("3. Eliminar elemento del XML");
        System.out.println("4. Añadir etiqueta horarioUTC a cada ciudad");
        int opt = scanner.nextInt();

        switch (opt) {
            case 1:
                // Leer XML
                ReadXML reader = new ReadXML();
                reader.readXML(routeFile);
                break;
            case 2:
                // Modificar XML
                ModifyXML modifier = new ModifyXML();
                modifier.modifyXML(routeFile);
                break;
            case 3:
                // Eliminar elemento del XML
                DeleteXML deleter = new DeleteXML();
                deleter.deleteElement(routeFile);
                break;
            case 4:
                // Añadir etiqueta horarioUTC a cada ciudad
                AddElement addUTC = new AddElement();
                addUTC.addUTCTag(routeFile);
                break;
            default:
                System.out.println("Opción no válida.");
                break;
        }

        scanner.close();
	}

}