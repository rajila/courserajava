import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


import org.w3c.dom.Element;

public class Filexmlcustom {
	private final String _pathApp = System.getProperty("user.dir"); // Path of project
	private final String _nameFile = "book.xml";

//	private File file=new File("C:\\Users\\barce\\Documents\\NetBeansProjects\\ManejadorFicheroxml\\Fichero2.xml");
//	private File fileModificado=new File("C:\\Users\\barce\\Documents\\NetBeansProjects\\ManejadorFicheroxml\\Fichero2.xml");

	private File file = new File(_pathApp + File.separator + _nameFile);
	private File fileModificado = new File(_pathApp + File.separator + _nameFile);

	private void modificar() throws Exception {
		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();

//		docFactory.setValidating(true);
//		docFactory.setExpandEntityReferences(false);

		DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
		Document doc = docBuilder.parse(file);

		//Get the root element
		Node root = doc.getFirstChild();

		//Get the staff element by tag name directly
		Node staff = doc.getElementsByTagName("book").item(0);

		//update Libro attribute
		NamedNodeMap attr = staff.getAttributes();
		Node nodeAttr = attr.getNamedItem("publicacion");
		nodeAttr.setTextContent("2020");

//		Node _attrMsn = doc.createAttribute("msn");
//		_attrMsn.setTextContent("Book one");
//		staff.appendChild(_attrMsn);

		((Element)staff).setAttribute("msn", "Book one"); // Create or edit

		Element _b = (Element)doc.createElement("book");
		_b.setAttribute("publicacion", "2010");
		_b.setAttribute("msn", "Book two");

		int _lengthBook = doc.getElementsByTagName("book").getLength();
		Attr _at = doc.createAttribute("id");
		_at.setTextContent(String.valueOf(_lengthBook));
		_b.setAttributeNode(_at);
		_b.setIdAttributeNode(_at, true);

		Node _a = doc.createElement("autor");
		Node _aValue = doc.createTextNode("Espol");
		_a.appendChild(_aValue);
		Node _t = doc.createElement("titulo");
		Node _tValue = doc.createTextNode("Matematicas");
		_t.appendChild(_tValue);
		_b.appendChild(_a);
		_b.appendChild(_t);
//		root.appendChild(_b);

//		#1
		Node _elOne = doc.getElementsByTagName("book").item(0);
		System.out.println("getNodeName -> "+ _elOne.getNodeName());
		System.out.println("getNodeValue -> "+ _elOne.getNodeValue());
		System.out.println("getTextContent -> "+ _elOne.getTextContent()); // all content
//		_elOne.setTextContent(""); // Delete all content
//		_elOne.setNodeValue("Hi - Hi"); // No procede
		Node _nodeTextOne = _elOne.getFirstChild(); // Node of type Text
		System.out.println("getNodeName -> " + _nodeTextOne.getNodeName());
		System.out.println("getNodeValue -> "+ _nodeTextOne.getNodeValue());
		System.out.println("getTextContent -> "+ _nodeTextOne.getTextContent());

		Node _autor = ((Element)_elOne).getElementsByTagName("autor").item(0);
		System.out.println("getNodeName -> " + _autor.getNodeName());
		System.out.println("getNodeValue -> "+ _autor.getNodeValue());
		System.out.println("getTextContent -> "+ _autor.getTextContent());
		Node _nodeTextAutor = _autor.getFirstChild(); // Node of type Text
		System.out.println("getNodeName -> " + _nodeTextAutor.getNodeName());
		System.out.println("getNodeValue -> "+ _nodeTextAutor.getNodeValue());
		System.out.println("getTextContent -> "+ _nodeTextAutor.getTextContent());
		_nodeTextAutor.setNodeValue(_nodeTextAutor.getNodeValue() + String.valueOf(_lengthBook)); // Si procede para el Nodo de tipo TEXT

//		Fix
//		Element _bookId2 = doc.getElementById("10");
//		System.out.println(_bookId2.getElementsByTagName("autor").item(0).getNodeValue());
//		System.out.println(_bookId2.getElementsByTagName("autor").item(0).getTextContent());
		Node _bId = getBookId((Element)root, "5");
		if (_bId != null) {
			System.out.println("Find for ID: 5");
			System.out.println(((Element)_bId).getElementsByTagName("autor").item(0).getFirstChild().getNodeValue());
			// Using Node
			String _txtAutor = _bId.getFirstChild().getFirstChild().getNodeValue();
			System.out.println("Autor -> "+_txtAutor);
			String _txtAutr = _bId.getChildNodes().item(0).getChildNodes().item(0).getNodeValue();
			System.out.println("Autor -> "+_txtAutr);
			String _txtTitle = _bId.getFirstChild().getFirstChild().getNodeValue();
			System.out.println("Title -> "+_txtTitle);
			_bId.getFirstChild().getFirstChild().setNodeValue(_bId.getFirstChild().getFirstChild().getNodeValue()+_lengthBook);
		}


		//write the content into xml file
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource source = new DOMSource(doc);
		StreamResult result = new StreamResult(fileModificado);
		transformer.transform(source, result);

		System.out.println("OK, Modificado");

	}

	private Node getBookId(Element __root, String __id) {
		NodeList _elements = __root.getElementsByTagName("book"); // Only elements <book/>
		Node _element = null;
		for (int _i=0; _i<_elements.getLength(); _i++) {
			if(((Element)_elements.item(_i)).getAttribute("id").compareTo(__id)==0) {
				_element = _elements.item(_i);
				break;
			}
		}
		return _element;
	}

	private void leer() throws Exception {
		Document doc=null;//Representa al árbol DOM
		DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
		//Indica que el modelo DOM no debe contemplar los comentarios //que tenga el XML.
		factory.setIgnoringComments(true);
		//Ignora los espacios en blanco que tenga el documento
		factory.setIgnoringElementContentWhitespace(true);
		//Se crea un objeto DocumentBuilder para cargar en él la //estructura de árbol DOM a partir del XML
		//seleccionado.
		DocumentBuilder builder=factory.newDocumentBuilder();
		//Interpreta (parsear) el documento XML (file) y genera el DOM //equivalente.
		doc=builder.parse(file);
		//Ahora doc apunta al árbol DOM listo para ser recorrido.
		String xml=recorrerDOMyMostrar(doc);
		System.out.println(xml);
	}

	public String recorrerDOMyMostrar(Document doc) {
		String datos_nodo[]=null;
		String salida="";
		Node node;
		Element _el;
		//Obtiene el primero nodo del DOM (primer hijo)
		Node raiz=doc.getFirstChild();

		Element _raiz = (Element)doc.getFirstChild(); // root
		Element _bookF = (Element)_raiz.getElementsByTagName("book").item(0);
		int _publicacion = Integer.parseInt(_bookF.getAttribute("publicacion"));
		String _titulo = _bookF.getElementsByTagName("titulo").item(0).getTextContent();
		String _autor = _bookF.getElementsByTagName("autor").item(0).getTextContent();
		System.out.println("Publicacion: " + _publicacion + ", titulo: "+_titulo+ ", autor: " + _autor);
		NodeList _bookList = _raiz.getElementsByTagName("book");
		System.out.println(_bookList.getLength());

		//Obtiene una lista de nodos con todos los nodos hijo del ra�z.
		NodeList nodelist=raiz.getChildNodes(); // Get all nodes such as: nodo_text & nodo_element
		//Procesa los nodos hijo
		for (int i=0; i<nodelist.getLength(); i++) {
			node = nodelist.item(i);
//			_el = (Element)nodelist.item(i);
//			System.out.println(node.getNodeType() + " -- " + Node.ELEMENT_NODE);
			if (node.getNodeType()==Node.ELEMENT_NODE) {
				//Es un nodo libro
				datos_nodo = procesarLibro(node);
				salida=salida + "\n " + "Publicado en: " + datos_nodo[0];
				salida=salida + "\n " + "El autor es: " + datos_nodo[2];
				salida=salida + "\n " + "El titulo es: " + datos_nodo[1];
			}
		}
		return salida;
	}

	protected String[] procesarLibro(Node n) {
		String datos[]= new String[3];
		Node ntemp=null;
		int contador=1;
		//Obtiene el valor del primer atributo del nodo (uno en este ejemplo)
		datos[0]=n.getAttributes().item(0).getNodeValue();
		//Obtiene los hijos del Libro (titulo y autor)
		NodeList nodos=n.getChildNodes();
		for (int i=0; i<nodos.getLength(); i++) {
			ntemp = nodos.item(i);
			if(ntemp.getNodeType()==Node.ELEMENT_NODE) {
				//IMPORTANTE: para obtener el texto con el t�tulo y autor se accede al nodo //TEXT hijo de ntemp y se saca su valor.
				datos[contador] = ntemp.getChildNodes().item(0).getNodeValue();
				contador++;
			}
		}
		return datos;
	}

	public static void main(String[] args) throws Exception {
		Filexmlcustom ficheroxml = new Filexmlcustom();
//		ficheroxml.leer();
		ficheroxml.modificar();
//		System.out.println("Hi from Eclipse!!\n");
		System.out.println(args.length);

	}
}
