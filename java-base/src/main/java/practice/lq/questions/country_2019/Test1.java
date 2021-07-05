package practice.lq.questions.country_2019;

import java.io.*;

/**
 * @AUTHOR LYF
 * @DATE 2021/5/15
 * @VERSION 1.0
 * @DESC
 * 1.递增序列
 * 考查点：矩阵（循环数组）+IO流
 *
 *
 */
public class Test1 {
    static String arr[][]=new String[30][50];
    static void readData() throws IOException {

        String path = "E:\\IdeaProjects\\java-base\\src\\main\\java\\practice\\lq\\questions\\country_2019\\inc.txt";
        InputStream inputStream = new FileInputStream(path);
        BufferedInputStream bis = new BufferedInputStream(inputStream);

        InputStreamReader inputStreamReader = new InputStreamReader(bis);
        BufferedReader bufferedReader= new BufferedReader(inputStreamReader);
        String temp;
        int row=0;
        while((temp=bufferedReader.readLine())!=null) {
            System.out.println(temp);
            for(int i =0;i<temp.length();i++)
            {
                arr[row][i]=temp.substring(i,i+1);
            }
            row++;
        }

        for(int i =0;i<30;i++){
            for(int j=0;j<50;j++){
                System.out.print(arr[i][j]);
            }
            System.out.println();
        }
    }


    static void handle(){

    }

    public static void main(String[]args) throws IOException {
        readData();
    }

}
