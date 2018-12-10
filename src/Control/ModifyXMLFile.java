package Control;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import Model.Consts;

public abstract class ModifyXMLFile {

		public static void modify()
		{
			try {
				String filepath = "src/utils/UCA.xml";
				DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
				DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
				Document doc = docBuilder.parse(filepath);

				Node rootNode = doc.getElementsByTagName("jdbcDataAdapter").item(0);

				// loop the staff child node
				NodeList list = rootNode.getChildNodes();
				List<Node> toRemove = new ArrayList<>();
				for (int i = 0; i < list.getLength(); i++) {

					Node node = list.item(i);

					// get the salary element, and update the value
					if ("url".equals(node.getNodeName())) {
						String dbPath = Consts.CONN_STR;
						node.setTextContent(dbPath);
					}

					// Remove all classpath nodes
					if ("classpath".equals(node.getNodeName())) {
						toRemove.add(node);
						rootNode.removeChild(node);
					}

				}
				
				for(Node n : toRemove)
				{
					rootNode.removeChild(n);
				}
				
				// Add classpath for ucanaccess
				
				File f = new File("lib\\UCanAccess-3.0.7-bin\\");
				
				for(String s : Consts.UCA_LIB)
				{
					Element n = doc.createElement("classpath");
					n.setTextContent(f.getAbsolutePath() + "\\" + s);
					rootNode.appendChild(n);
				}
				
				

				// write the content into xml file
				
				TransformerFactory transformerFactory = TransformerFactory.newInstance();
				Transformer transformer = transformerFactory.newTransformer();
				DOMSource source = new DOMSource(doc);
				StreamResult result = new StreamResult(new File(filepath));
				transformer.transform(source, result);

<<<<<<< HEAD
=======
				System.out.println("Done");
>>>>>>> Fixed

			} catch (ParserConfigurationException pce) {
				pce.printStackTrace();
			} catch (TransformerException tfe) {
				tfe.printStackTrace();
			} catch (IOException ioe) {
				ioe.printStackTrace();
			} catch (SAXException sae) {
				sae.printStackTrace();
			}
		}
}