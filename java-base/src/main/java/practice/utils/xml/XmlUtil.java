package practice.utils.xml;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

/**
 * @AUTHOR LYF
 * @DATE 2021/5/14
 * @VERSION 1.0
 * @DESC
 */
public class XmlUtil {
   public static Object getBean() throws ParserConfigurationException, IllegalAccessException, InstantiationException, ClassNotFoundException, IOException, SAXException {
        String configFile = "E:\\IdeaProjects\\java-base\\src\\main\\resources\\config\\FactoryConfig.xml";
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        Document document =documentBuilderFactory.newDocumentBuilder().parse(new File(configFile));
        String className= document.getElementsByTagName("factory").item(0).getFirstChild().getNodeValue();
       // className="practice.nju.design.models.create.factory."+className;
        Class c = Class.forName(className);
        return c.newInstance();
    }
}
