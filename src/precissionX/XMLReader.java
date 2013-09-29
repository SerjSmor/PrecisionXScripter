package precissionX;

import java.io.File;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import lisa.FactoryPiece;
import lisa.Piece;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XMLReader {
	private File xmlFile;
	ArrayList<Piece> parts = new ArrayList<Piece>();

	public XMLReader(File xmlFile) {
		this.xmlFile = xmlFile;
	}

	public ArrayList<Piece> connections() throws Exception {
		try {
			FactoryPiece factory = new FactoryPiece();
			
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(this.xmlFile);

			doc.getDocumentElement().normalize();

			System.out.println("Root element :" + doc.getDocumentElement().getNodeName());

			NodeList nList = doc.getElementsByTagName("Shape");
			Element element = null;

			for (int shapeIndex = 0; shapeIndex < nList.getLength(); shapeIndex++) {

				Node node = nList.item(shapeIndex);

				if (node.getNodeType() == Node.ELEMENT_NODE) {
					element = (Element) node;
					
					String elementName = element.getAttribute("type");
					String id = element.getAttribute("id");
					Piece piece = factory.createPart(elementName);
					piece.setId(id);
					this.parts.add(piece);
					
					// every part is added with his neighbours and duplicate on the opposite site 
					Node neighbour = (Node) element.getElementsByTagName("Neighbours").item(0);
					NodeList sideList = neighbour.getChildNodes();
					
					for (int sideIndex = 0; sideIndex < sideList.getLength(); sideIndex ++) {
						Node sideNode = sideList.item(sideIndex);
						
						if (sideNode.getNodeType() == Node.ELEMENT_NODE) {
							Element sideElement = (Element) sideNode;
							
							String connectorOrientation = sideElement.getAttribute("name");
							String connectorToWhom = sideElement.getElementsByTagName("type").item(0).getTextContent();
							
							piece.getConnection().put(connectorOrientation, connectorToWhom);
						}
					}
				}
			}
			
			return parts;
		}

		catch (Exception e) {
			throw e;
		}
	}
}
