import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
/**
 * Parser class used to convert currency from XML file
 * Input: amount of the currency as double, name of the desired currency as String
 * Output: Converted currency as double
 * @author Tomasz Sochacki
 * */
public class Parser {
    private double output;
    public static final int DECPRECISION = 2; //decimal precision of the Exchanger return
    private Object Exception;

    public double exchanger (double amount, String currency){
        try {
            if(amount < 0 ){
                System.out.println("Amount should be greater than zero");
                throw new Exception();
            }
            File inputFile = new File("eurofxref-daily.xml"); //creates new file instance with pathname to the file placed inside project directory
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder(); //creating a DocumentBuilder

            Document doc = dBuilder.parse(inputFile); //parses file and returns DOM document
            doc.getDocumentElement().normalize(); //normalizes created document

            NodeList nodeList = doc.getElementsByTagName("Cube"); //returns all elements with tag "Cube"

            for (int n = 0; n < nodeList.getLength(); n ++) { //iterates through the node list
                Node node = nodeList.item(n);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;

                    if(element.getAttribute("currency").equalsIgnoreCase(currency)) { //searches for the desired currency
                        output = Math.round(amount * Double.parseDouble(element.getAttribute("rate")) * Math.pow(10, DECPRECISION)) / Math.pow(10, DECPRECISION);
                        break;
                    }
                }
                if (n==nodeList.getLength()-1){ //if the name exits bounds of the list, it is a wrong currency name
                    System.out.println("Wrong name of the currency");
                    throw (Throwable) Exception;
                }
            }
        }
        catch (Exception e){
            throw new IllegalArgumentException("Wrong input");
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return output;
    }
}



