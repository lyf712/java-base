package practice.base.refection;

/**
 * @AUTHOR LYF
 * @DATE 2021/5/14
 * @VERSION 1.0
 * @DESC
 */

class Class1{
    void testMethod(){
        System.out.println("hello...");
    }
}
public class SimpleTest {
    public static void main(String[]args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {

         Class1 class1= (Class1) Class.forName("Class2").newInstance();
//        Class1 class1=
//                (Class1) ClassLoader.getSystemClassLoader().loadClass("Class2").
//               newInstance();

       class1.testMethod();
    }
}

