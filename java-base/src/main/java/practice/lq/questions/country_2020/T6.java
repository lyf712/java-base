package practice.lq.questions.country_2020;

import java.util.*;

/**
 * @AUTHOR LYF
 * @DATE 2021/5/22
 * @VERSION 1.0
 * @DESC
 *
 * 1.蓝肽子序列
 */

public class T6 {

    static private List<String> s1 = new ArrayList<>();
    static private List<String> s2 = new ArrayList<>();
    static private String inp1;
    static private String inp2;
    static void changeToList(){//String s
        int recordStart=0;
        for(int i=0;i<inp1.length();i++){
            if(i==inp1.length()-1){
                s1.add(inp1.substring(recordStart));
                break;
            }
            if('a'<=inp1.charAt(i)&&inp1.charAt(i)<='z'
            &&'A'<=inp1.charAt(i+1)&&inp1.charAt(i+1)<='Z'){
                  s1.add(inp1.substring(recordStart,i+1));
                  recordStart=i+1;
            }
        }
        recordStart=0;
        for(int i=0;i<inp2.length();i++){
            if(i==inp2.length()-1){
                s2.add(inp2.substring(recordStart));
                break;
            }
            if('a'<=inp2.charAt(i)&&inp2.charAt(i)<='z'
                    &&'A'<=inp2.charAt(i+1)&&inp2.charAt(i+1)<='Z'){
                s2.add(inp2.substring(recordStart,i+1));
                recordStart=i+1;
            }
        }
    }
//    static int []record = new int[Math.min(s1.size(),s2.size())];

    static void handle(){
        int []record = new int[Math.min(s1.size(),s2.size())];

        int len =Math.min(s1.size(),s2.size());
        // 只需求解长度，记录
        //List<Integer> record = new LinkedList<>();
        Arrays.fill(record,-1);
        if(s1.size()<s2.size()){
            for(int i=0;i<s1.size();i++){
                for(int j=0;j<s2.size();j++){
                    if(s1.get(i).equals(s2.get(j))){
                          record[i]=j;
                        break;//记录第一次出现即可
                    }
                }
            }
        }else {
            for(int i=0;i<s2.size();i++){
                for(int j=0;j<s1.size();j++){
                    if(s2.get(i).equals(s1.get(j))){
                        record[i]=j;
                        break;//记录第一次出现即可
                    }
                }
            }
        }
        // 考虑使用流统计？
        int []maxRecord = new int[len];
        Arrays.fill(maxRecord,0);
        for(int i=0;i<len;i++){
            for(int j=0;j<i;j++){
                if(record[i]==-1){
                    break;//说明无该匹配的字符串，
                }
                if(record[j]!=-1&&record[i]>record[j]){
                    maxRecord[i]++;//
                }
            }
        }

        int tempMax = Arrays.stream(maxRecord).max().getAsInt();
        System.out.println("rs:"+(tempMax==0?0:tempMax+1));
    }
    public static void main(String[]args){
        Scanner scanner = new Scanner(System.in);
        inp1=scanner.next();
        inp2=scanner.next();
        changeToList();
        s1.stream().forEach(System.out::println);
        s2.stream().forEach(System.out::println);
        handle();
        // System.out.println("helo".substring(0,1));
    }
}
