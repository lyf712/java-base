package practice.offer;

/**
 * @AUTHOR LYF
 * @DATE 2021/5/25
 * @VERSION 1.0
 * @DESC
 * 题目描述 题目描述：将一个字符串中的空格替换成“%20”。例如：当字符串为 We Are
 * Happy.则经过替换之后的字符串为 We%20Are%20Happy。
 * 思路 思路：从后往前复制，数组长度会增加，或使用 StringBuilder、StringBuffer 类
 * 8
 * 代码实现 代码实现：时间复杂度：O(n)，空间复杂度：O(n)
 */
public class ReplaceStr {
    boolean replaceF(String str){
        StringBuffer stringBuffer = new StringBuffer();
        for(int i=0;i<str.length();i++){
            if(str.charAt(i)==' '){
                stringBuffer.append("%20");
            }else{
                stringBuffer.append(str.charAt(i));
            }
        }
        System.out.println(stringBuffer.toString());
        return true;
    }
    public static void main(String[]args){
        String str = "We Are Happy";
        ReplaceStr replaceStr = new ReplaceStr();
        replaceStr.replaceF(str);
    }
}
