package practice.lq.questions.consolidate.dp.labuladong.review;

import java.util.*;

/**
 * @AUTHOR LYF
 * @DATE 2021/4/14
 * @VERSION 1.0
 * @DESC
 */
public class NQueen {

    static int N=4;
    static int[][] map=new int[N][N];//10

    static {
        for(int i =0;i<N;i++)
        {
            Arrays.fill(map[i],0);// 0未下棋
        }
    }

    boolean judge(int row,int col)
    {

        // 判断列
        for(int i=0;i<row;i++)
        {
            if(map[i][col]==1)
                return false;
        }
        // 判断行
        for(int i=0;i<col;i++)
        {
            if(map[row][i]==1)
                return false;
        }

        // 判断斜方向
//        int i=row,j=col;
//        while(i>0&&j>0){
//            if(map[--i][--j]==1)//左斜方向
//                return false;
//        }


        return true;

    }

    void dropMap(int i ){//下i行，j列
        if(i==N){
           for(int k=0;k<N;k++){
               for(int l=0;l<N;l++){
                   System.out.print(map[k][l]+" ");
               }
               System.out.println();
           }
            System.out.println("============");
        }else{
            for(int k =0;k<N;k++){
                if(judge(i,k)){
                    map[i][k]=1;//落子
                    dropMap(i+1);
                    map[i][k]=0;//回退
                }

            }
        }
    }
    //=> 排列组合、选取


    // 选取
    // 思路:
    // 集合存储选取个数的总和
    private Set<String> sets = new HashSet<>();
    private List<Set<String>> sumSets = new ArrayList<>();


//    int flag = 0;//计数
    String selectStr="";
    static Set<String> isSelected= new HashSet<>();

    static {

    }
    /**
     *
     * @param str 待选取的数字
     * @param start// 选取的起点
     * @param num// 选取的数量
     */
    void testSelect (String str,int start,int num){// 待选取的数
        if(isSelected.size()==num){
            sets.add(selectStr);
//            selectStr="";//归零
           // return;//选取的数量已达该数量则停止，，加入集合
        }else{
           for(int i =start;i<str.length();i++){
              if(!isSelected.contains(str.substring(i,i+1))){//未被选

                  isSelected.add(str.substring(i,i+1));//类似维护的棋盘
                  selectStr=selectStr+str.substring(i,i+1);
                  testSelect(str,i+1,num);//下一步递归
                  selectStr=selectStr.substring(0,selectStr.length()-1);
                  isSelected.remove(str.substring(i,i+1));//回退

              }

           }
        }
    }

    void test(){
//        String str = "abcdefg";
//        testSelect(str,0,2);
//        sets.stream().forEach((e)->System.out.println(e+"->"));
//        System.out.println(sets.size());

        for(int i =1;i<=7;i++){
          testSelect("abcdefg",0,i);
        }
        System.out.println(sets.size());

        sets.stream().forEach((e)-> System.out.println(e));
    }

    public static void main(String[]args){
        NQueen nQueen = new NQueen();
//        nQueen.dropMap(0);
        nQueen.test();
    }


}
