package practice.lq.questions.province_2019;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @AUTHOR LYF
 * @DATE 2021/4/7
 * @VERSION 1.0
 * @DESC
 * 2019年省赛A组（决赛C组？？）
 * 一、题型目录
 * 1.奇数的倍数
 *
 *
 */
public class TestA {

    boolean f1(int n){
        String temp = n+"";
        // char => int? 相减？
        for(int i =0;i<temp.length();i++){
          if(Integer.valueOf(temp.substring(i,i+1))%2==0){
              return false;
          }
        }
        return true;
    }
    //139311
    void test1(){
        for(int i =2019;;i+=2019){

            if(i%2019==0&&f1(i)){
                System.out.println(i);
                return;
            }
        }

    }

    // 递增序列
    // 思路将文件读入一个矩阵（二维数组之中）然后遍历（注意遍历规则）比较计数
    void test2() throws IOException {

        File file = new File("E:\\IdeaProjects\\java-base\\src\\think.lq\\questions\\province_2019\\inc.txt");

        InputStream ips = new FileInputStream("E:\\IdeaProjects\\java-base\\src\\think.lq\\questions\\province_2019\\inc.txt");

        InputStreamReader isr = new InputStreamReader(ips);

        char[] temp = new char[1];
        char[][] matrix = new char[30][50];
        AtomicInteger row = new AtomicInteger();
        AtomicInteger col = new AtomicInteger();
        row.set(0);
        col.set(0);

        BufferedReader bufferedReader = new BufferedReader(isr);
        String str =  new String();
        List<String> list1 = new ArrayList<>();

        while (bufferedReader.read()>0){
           str =  bufferedReader.readLine();
           list1.add(str);
        }

        System.out.println("测试1");
        char [][] m = new char[list1.size()][str.length()];
        AtomicInteger i = new AtomicInteger();
        i.set(0);
        list1.forEach((s)->{
            for(int k =0;k<s.length();k++){
                m[i.get()][k]=s.charAt(k);
            }
            i.set(i.get()+1);

        });
        for(int j=0;j<i.get();j++){
            for(int l=0;l<str.length();l++){
                System.out.print(m[j][l]);
            }
            System.out.println();
        }

        List<Character> list = new ArrayList<>();
        System.out.println("测试2");//前面已经读取就没有了？？
        while(isr.read(temp,0,1)>0){ //在char数组中的偏移量？？，总结char的数据类型转换
            System.out.print(temp[0]);
//            if(temp[0]=='\n'){
//                row++;
//                continue;
//            }
            list.add(temp[0]);

//            matrix[row][col]=temp[0];
//            col=(++col)%50;
        }

        System.out.println("测试matrix:"+row+";"+col);
        System.out.println("测试List:");
        list.stream().forEach(System.out::print);

//        list.stream().forEach(
//                (e)->{
//                    if(e=='\n'){
//                        row.set(row.get()+1);
//                        col.set(0);
//                    }
//                    matrix[row.get()][col.get()] = e;
//                    col.set(col.get()+1);
//                }
//        );
//
//        for(int i = 0 ;i<=row.get();i++){
//            for(int j = 0;j<50;j++){
//                System.out.print(matrix[i][j]);
//            }
//            System.out.println();
//        }
    }

    // 拆平方数
    void test3(){

    }
    // 切割
    void test4(){
        int n=1;
        int sum = 0;

        int temp = 0;
        int p=1;

        List<Integer> list = new ArrayList<>();
        for(int i =1;i<=60;i++){
            list.add(i);
        }
        // 需要的数
//        list.stream().forEach(e-> System.out.println(" "+e));
//        list.remove((Object)2);
//        list.stream().forEach(e-> System.out.println(" "+e));


        while(true){

            temp=f4(n);

            System.out.println("temp"+temp+"len:"+list.size());

            if(list.contains((Object)temp)){
                sum+=n;
                list.remove((Object)temp);
                list.stream().forEach(e->System.out.print("->"+e));
                System.out.println("record:"+temp+";"+sum);
                if(list.size()==0){
                    return;
                }
            }
            n++;
            System.out.println("n:"+n);
        }
    }
    // 序列求和
    // 遍历求解
    // 1 2 3 4  5  6  7 8   9
    // 1 2 4 6 16 18 64 66 100

    int f4(int n ){
        int sum = 0;
        for(int i =1;i<=n;i++){
            if(n%i==0)
                sum++;
        }
        return sum;
    }


    void test(){
        int i =1;
        while (i>=1){
            System.out.println(i+":"+f4(i));
            i++;
        }

    }




    public static void main(String[]args) throws IOException {
        TestA  ta = new TestA();
        ta.test4();
    }



}
