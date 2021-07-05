package practice.lq.questions.consolidate.count;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @AUTHOR LYF
 * @DATE 2021/4/13
 * @VERSION 1.0
 * @DESC
 * 矩阵的填数以及遍历
 * 采用链表+String构成矩阵
 */
public class Count {

    static List<String> lists = new ArrayList<>();
    static {
        // 读入数据
        try{
            InputStream ips = new FileInputStream("E:\\IdeaProjects\\java-base\\src\\main\\java\\think.lq\\questions\\consolidate\\files\\Find2020");
            InputStreamReader isr = new InputStreamReader(ips);//读入输入流
            BufferedReader br = new BufferedReader(isr);//缓存读取

            String temp = null;
            while((temp=br.readLine())!=null){
                lists.add(temp);
            }

            // 验证结果
            System.out.println("读入数据为:");
            lists.stream().forEach(System.out::println);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    // 寻找2020
    // 遍历行列、斜方向
    // 关于斜方向如何遍历？
    // 截取从(0,0)-直到row为最大或者列为最大或者字符串长度判断

    // 判断字符串含有的2020的数量
    Integer count2020(String str){
        String regex = "2020";
        Pattern p = Pattern.compile(regex);
        Matcher m =p.matcher(str);
        int count =0;
        while(m.find())
        {
            count++;
            System.out.println("在"+m.start()+"匹配到2020");
        }
        return count;
    }

    // 进行总体计数
    void handleFind2020(){

        // 原子计数，Atomic了解
        AtomicReference<Integer> sum1 = new AtomicReference<>(0);
        // 每一行计数,即lists的遍历
        lists.stream().forEach((str)->{
            sum1.updateAndGet(v -> v + count2020(str));
        });

        // 处理列
        int len = lists.get(0).length();
        String[] rowStr = new String[len];
        Arrays.fill(rowStr,"");
        for(int i =0;i<lists.size();i++){
            for(int j=0;j<len;j++){
                rowStr[j]=rowStr[j]+lists.get(i).charAt(j);
            }
        }
        for(String str :rowStr){
            sum1.updateAndGet(v->v+count2020(str));//{}则有返回值
        }

        // 斜方向！！
        // 突然发现还是矩阵方便处理，于是转换为矩阵
        Character[][] map = new Character[lists.size()][len];
        int row=lists.size(),col=len;
        for(int i =0;i<lists.size();i++){
            for(int j=0;j<len;j++) {
                map[i][j] = lists.get(i).charAt(j);
            }
        }

        // 从左斜方向到右斜方向，确定起点和终点以及变化规则
        // 起点: (rowMax-字符串"2020"len,0) 终点:(0,colMax-"2020".length())
        // 规则:截取点开始到无法截取的长度，点的变化规则i--到0,j

        List<String> biasList=new ArrayList<>();

        int i=row-4,j=0;

        while(i!=0||j!=len-4){//字符串开始的点(i,j)，终点结束

            String temp ="";
            int vaI=i,vaJ=j;
            while(vaI<row&&vaJ<col){
                temp=temp+map[vaI++][vaJ++];
            }
            biasList.add(temp);

            //起点移动
            if(i!=0){
                i--;
            }else{
                j++;
            }
        }

        int vaI=i,vaJ=j;
        String temp=""; //最后一个点
        while(vaI<row&&vaJ<col){
            temp=temp+map[vaI++][vaJ++];
        }

        biasList.add(temp);
        System.out.println("测试:");
        biasList.stream().forEach(System.out::println);
    }

    // 相关补充
    // 蛇形填数
    int[][] map = new int[41][41];//方便验证
    void printRs(){
        System.out.println("验证:");
        for(int row=0;row<40;row++){
            for(int col=0;col<40;col++){
                System.out.print(map[row][col]+" ");
            }
            System.out.println();
        }
    }

    void handleSnake(){

        int i=0,j=0,count=1;

        while(i!=19||j!=19){
            map[i][j]=count;

            if(i==0){// 行为0则col++
                map[0][++j]=++count;
            }

            while(j>0){
                map[++i][--j]=++count;
                if(i==19&&j==19){
                   printRs();
                    return;
                }
            }

            if(j==0){
                map[++i][0]=++count;
            }

            while (i>0){
                map[--i][++j]=++count;
                if(i==19&&j==19){
                   printRs();
                    return;
                }
            }
        }
    }

    int len=5;// 矩阵长度
    int[][] map2=new int[len][len];
    void writeMap(int endRow,int endCol){
//        Arrays.fill(map2,0);
        for(int i =0;i<len;i++){
            Arrays.fill(map2[i],0);
        }
        int row=0,col=0,count=1;
        while(row!=endRow||col!=endCol){
            // 向右进行填数
            while(col<len&&map2[row][col]==0){//注意需要先进行边界判断
                map2[row][col++]=count++;
            }
            col--;//col回退
            row++;// 下一列
            while (row<len&&map2[row][col]==0){
                map2[row++][col]=count++;
            }
            row--;//row回退
            col--;//向左一列
            while (col>=0&&map2[row][col]==0){
                map2[row][col--]=count++;
            }
            col++;
            row--;
            while(row>=0&&map2[row][col]==0){
                map2[row--][col]=count++;
            }
            row++;
            col++;
        }
        // 处理最后一个数
        map2[endRow][endCol] =count;
        print();
    }
    void print(){
        for(int i =0;i<len;i++){
            for(int j=0;j<len;j++){
                System.out.print(map2[i][j]+" ");
            }
            System.out.println();
        }
    }
    // 回形填写矩阵
    void handleCircle(){

        // 与蛇形填数类似
        // 起点（0，0）
        // 终点（ 奇数则是 (len/2,len/2),偶数则是 (len/2-1,len/2-1)
        // 规则 当遇到未进行填写就递进（ col++直到下一个为已填或者边界，col保持不变，row++到已填或者边界，

        if(len%2==0){
            writeMap(len/2-1,len/2-1);
        }else{
            writeMap(len/2,len/2);
        }
    }

    public static void main(String[]args){
        Count c= new Count();
        c.handleCircle();
    }

}
