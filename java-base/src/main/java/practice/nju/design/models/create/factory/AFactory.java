package practice.nju.design.models.create.factory;

/**
 * @AUTHOR LYF
 * @DATE 2021/5/14
 * @VERSION 1.0
 * @DESC
 */
public class AFactory implements Factory1{
    @Override
    public Product getProduct() {
        return new ProductA();
    }
}