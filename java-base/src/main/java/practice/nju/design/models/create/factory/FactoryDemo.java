package practice.nju.design.models.create.factory;

import org.xml.sax.SAXException;
import practice.utils.xml.XmlUtil;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

/**
 * @AUTHOR LYF
 * @DATE 2021/5/14
 * @VERSION 1.0
 * @DESC
 */

interface Factory1{
    Product getProduct();
}

// 借用简单工厂的产品
//interface Product1{
//    void display();
//}

//class AFactory implements Factory1{
//
//    @Override
//    public Product getProduct() {
//        return new ProductA();
//    }
//}

class BFactory implements Factory1{

    @Override
    public Product getProduct() {
      return new ProductB();
    }
}



public class FactoryDemo {
    public static void main(String[]args) throws IllegalAccessException, InstantiationException, IOException, SAXException, ParserConfigurationException, ClassNotFoundException {
        Factory1 factory;
        Product product;
        //factory = new AFactory();
        factory = (Factory1) XmlUtil.getBean();
        product=factory.getProduct();
        product.display();
    }
}
