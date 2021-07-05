package learn.base.core.chapter5;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @AUTHOR LYF
 * @DATE 2021/4/29
 * @VERSION 1.0
 * @DESC
 */
public class Reflection {


    public static void main(String[]args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, InvocationTargetException {
        People people = new People("Hello",18,1000);
        // 获取class
        Class p1 = Class.forName("learn.base.core.chapter5.People");
        Class p2= people.getClass();

        // 获取Field
        Field[] fields = p1.getDeclaredFields();
        for(Field f:fields){
            System.out.print(f.getName()+" ");
        }

        // 获取Method
        Method[] methods = p2.getDeclaredMethods();
        for(Method method:methods){
            System.out.print(method.getName()+" ");

            if(method.getName().equals("getAge")){
                System.out.print(method.invoke(people)+" ");
            }
        }

        Object p3 = p1.newInstance();



    }
}
