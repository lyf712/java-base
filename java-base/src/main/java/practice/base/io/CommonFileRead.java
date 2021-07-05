package practice.base.io;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

/**
 * @AUTHOR LYF
 * @DATE 2021/5/14
 * @VERSION 1.0
 * @DESC
 * 1.xml
 * 2.json
 * 3.properties读取
 */
public class CommonFileRead {
    private static String xmlFilePath = "E:\\IdeaProjects\\java-base\\src\\main\\java\\practice\\base\\io\\files\\XmlFile.xml";



    static void testXml() throws ParserConfigurationException, IOException, SAXException {

        // 获取实例
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db =dbf.newDocumentBuilder();
        Document document =db.parse(new File(xmlFilePath));
        NodeList nodeList =document.getElementsByTagName("bean");

        System.out.println(nodeList.item(0).getFirstChild().getNodeValue()+";"+
                nodeList.item(0).getAttributes());
    }

    public static void main(String[]args) throws IOException, SAXException, ParserConfigurationException {
        testXml();
    }

}
