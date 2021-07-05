package practice.lq.completation.string;

/**
 * @AUTHOR LYF
 * @DATE 2021/6/2
 * @VERSION 1.0
 * @DESC
 */
public class StringArr {
    public String countAndSay(int n) {
        // 统计
        if(n==1){
            return "1";
        }
        String str = countAndSay(n-1);
        String temp="";
        int count = 0;
        for(int i=0;i<str.length();i++)
        {
            Character cur = str.charAt(i);
            if(i==str.length()-1){
                count++;
                temp=temp+count+cur;
                count=0;//重新计数
                break;
            }
            Character next = str.charAt(i+1);
            count++;
            if (cur != next) {
                temp = temp + count + cur;
                count=0;//重新计数
            }

        }
        return temp;
    }
    public static void main(String[]args){
        new StringArr().countAndSay(4);
    }
}
