package practice.lq.completation.string;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Locale;

/**
 * @AUTHOR LYF
 * @DATE 2021/5/31
 * @VERSION 1.0
 * @DESC
 */
public class StringDemo {
    boolean isPalindrome(String s) {

        // 边界讨论

        String str=s.toUpperCase();
        int i=0,j=str.length()-1;

        while(i<j){

            while(str.charAt(i)>'Z'||str.charAt(i)<'A')
            {
                i++;
                if(i==str.length())
                    return true;
            }

            while(str.charAt(j)>'Z'||str.charAt(j)<'A')
            {
                j--;
                if(i==-1)
                    return true;
            }

            if(str.charAt(i)!=str.charAt(j))
                return false;
            i++;
            j--;
        }
        return true;
    }

    boolean isPalindrome2(String s){
        StringBuffer stringBuffer = new StringBuffer();
        s = s.toUpperCase();

        for(int i=0;i<s.length();i++){
            if(s.charAt(i)>='A'&&s.charAt(i)<='Z')
               stringBuffer.append(s.charAt(i));
        }
        System.out.println(stringBuffer+";"+stringBuffer.reverse());
//        StringBuffer temp = stringBuffer.reverse();
       // System.out.println(temp+";"+stringBuffer.reverse());
        return stringBuffer.toString().equals(stringBuffer.reverse().toString());
    }


    public static void main(String[]args){

        System.out.println(new StringDemo().isPalindrome2("A man, a plan, a canal: Panama"));

//        System.out.println(new StringDemo().isPalindrome("race a car"));
//
//        String str = "algorithm";
//        char[] chars = str.toCharArray();
//        Arrays.sort(chars);//,(e1,e2)->e1-e2
//        for(int i=0;i<chars.length;i++){
//            System.out.print(chars[i]+" ");
//        }
//        str.toUpperCase(Locale.ROOT);
//        str = str.toUpperCase();
//        System.out.println(str);

//        String str = "hello";
//        String str1 = "hello1";
//        str = str1;
//        System.out.println(str);

        String[] strings = {"str1","1312","12"};
  //      System.out.println(Arrays.stream(strings).sorted().min((e1,e2)->e1.length()-e2.length()).get());
        System.out.println(Arrays.stream(strings).sorted().min(Comparator.comparingInt(String::length)).get());

    }

}
