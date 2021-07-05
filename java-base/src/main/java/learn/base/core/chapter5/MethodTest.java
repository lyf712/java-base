package learn.base.core.chapter5;

import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.Calendar;

/**
 * @AUTHOR LYF
 * @DATE 2021/4/30
 * @VERSION 1.0
 * @DESC
 */


class Factory {

    static Factory getInstance(){
        return new Factory();
    }


}
public class MethodTest {
    public static void main(String[]args){

        // 工厂方法,工厂方法的作用：构造函数只能一个，但是工厂方法可以获取多种实例
        Calendar calendar = Calendar.getInstance();
        LocalDate localDate = LocalDate.of(2020,12,21);
        NumberFormat numberFormat = NumberFormat.getInstance();

        Factory factory = Factory.getInstance();

    }
}
