package practice.nju.design.models.create.factory;

import com.alibaba.fastjson.JSONReader;
import com.alibaba.fastjson.parser.JSONReaderScanner;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

/**
 * @AUTHOR LYF
 * @DATE 2021/5/12
 * @VERSION 1.0
 * @DESC
 */
public class ReadXML {
    public static void main(String[]args) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory documentBuilderFactory= DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        Document doc;
        doc = documentBuilder.parse(new File("E:\\IdeaProjects\\java-base\\src\\main\\java\\practice\\nju\\design\\models\\create\\factory\\XmlFile.xml"));
        System.out.println(doc.getElementsByTagName("chartType").item(0).getFirstChild().getNodeValue());



    }
}
