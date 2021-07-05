package learn.design.builder;

/**
 * @AUTHOR LYF
 * @DATE 2021/5/1
 * @VERSION 1.0
 * @DESC
 */
// 产品
class Product{
    private String name;
    private Integer price;
    private String des;

}

// 抽象建造者
abstract class Builder{

}

// 具体建造者，可多个，继承抽象建造者
class ABuilder extends Builder{

}

class BBuilder extends Builder{

}

// 指挥者
class Director{


}



public class BuilderModel {
}
