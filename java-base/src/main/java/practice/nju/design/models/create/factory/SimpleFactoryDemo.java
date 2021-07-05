package practice.nju.design.models.create.factory;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

/**
 * @AUTHOR LYF
 * @DATE 2021/5/13
 * @VERSION 1.0
 * @DESC
 */
abstract class Product{
    abstract void display();
}

 class ProductA extends Product{
    @Override
    void display() {
        System.out.println("这是A产品...");
    }
}
class ProductB extends Product{

    @Override
    void display() {
        System.out.println("这是B产品...");
    }
}

 class Factory{
    static Product product;
    private Factory(){
    }

    public static Product getProduct(String type) {
        if("A".equals(type)){
            product = new ProductA();
        }else if("B".equals(type)){
            product = new ProductB();
        }
        return product;
    }

}

public class SimpleFactoryDemo {

    private static String chartType() throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = factory.newDocumentBuilder();
        String path ="E:\\IdeaProjects\\java-base\\src\\main\\java\\practice\\nju\\design\\models\\create\\factory\\XmlFile.xml";
        Document document = documentBuilder.parse(new File(path));
        String val =
                document.getElementsByTagName("chartType").item(0).getFirstChild().getNodeValue();

        return val;
    }

    public static void main(String[]args) throws IOException, SAXException, ParserConfigurationException {

        Product product = Factory.getProduct(chartType());
        product.display();
    }
}
