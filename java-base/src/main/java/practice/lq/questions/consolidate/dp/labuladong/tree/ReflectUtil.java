package practice.lq.questions.consolidate.dp.labuladong.tree;

import java.lang.reflect.Method;

/**
 * @AUTHOR LYF
 * @DATE 2021/4/15
 * @VERSION 1.0
 * @DESC
 */
public class ReflectUtil {
    static int  executeMethod(int kind,int n){

        try{
           Method method =TreeLeetCodePart1.class.getDeclaredMethod("method"+kind, int.class);
          // Object o = new Object();
           Object rs = method.invoke( TreeLeetCodePart1.class.newInstance(),n);
           System.out.println(rs);

           return (int) rs;
        }catch (Exception e){
            e.printStackTrace();
        }
        return -1;
    }

    public static void main(String[]args){
      //  executeMethod(1,20);
    }
}
