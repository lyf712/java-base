package learn.base.core.chapter5;

/**
 * @AUTHOR LYF
 * @DATE 2021/4/29
 * @VERSION 1.0
 * @DESC
 *
 *
 */
public class ObjectDemo {

    private static void testHashCode(){
        People p1 = new People("people1",18,3000);
        People p2 = p1;
        People p3 = new People("people1",18,3000);
        System.out.println(p1.hashCode()+";"+p2.hashCode()+";"+p3.hashCode());// 2065951873;2065951873;1791741888

        // hashCode https://www.cnblogs.com/bronze-y/p/9448137.html
        // 比较HashCode(默认地址
        System.out.println("p1==p3?"+p1.equals(p3));// 2065951873;2065951873;1791741888

        if(p1==p3){
            System.out.println("YES");
        }else
        {
            System.out.println("NO");
        }

        String str1 = "TEST";
        String str2 = "TEST";// String定义了HashCode所以导致导出的hashCode一样
        System.out.println(str1.hashCode()+";"+str2.hashCode());


    }


    //
    public static void main(String[]args){
        // Test
        testHashCode();




    }


}
