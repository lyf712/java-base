package practice.lq.questions.country_2019;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @AUTHOR LYF
 * @DATE 2021/6/1
 * @VERSION 1.0
 * @DESC
 */
public class T4 {
    boolean flag =false;
    //
    void dfs(String val,boolean [][]visited,char[][]board,int x,int y,String word){
        if(val.length()==word.length()){
            if(val.equals(word)){
                flag = true;
            }
            return;
        }
        if(!val.equals(word.substring(0,val.length()))){
            return;
        }
        if(x<0||y<0||x>=board[0].length||y>=board.length){// 不合法递归截止
            return;
        }
        if(visited[y][x]){
            return;
        }
        visited[y][x]=true;
        // 四个方向进行搜索
        dfs(val+board[y][x],visited,board,x+1,y,word);//
        dfs(val+board[y][x],visited,board,x-1,y,word);//
        dfs(val+board[y][x],visited,board,x,y+1,word);//
        dfs(val+board[y][x],visited,board,x,y-1,word);//
        visited[y][x]=false;

    }

    public boolean exist(char[][] board, String word) {

        // dfs("",board,0,0,word);// 只能代表从0，0开始深搜
        int n = board.length*board[0].length;
        boolean[][] visited = new boolean[board.length][board[0].length];
        for(int i=0;i<n;i++){
            dfs("",visited,board,i%board[0].length,i/board[0].length,word);//,visited
            if(flag){//一旦搜索成功则
                return true;
            }
        }

        return false;

    }


    public void dfs(List<Integer> val,int pos,List<List<Integer>> res){
        if(pos==val.size()){//表明已选择完
            res.add(new ArrayList<>(val));
        }

        //
        for(int i=pos;i<val.size();i++){
            Collections.swap(val,i,pos);
            dfs(val,pos+1,res);//继续下一个
            Collections.swap(val,i,pos);
        }

    }

    public List<List<Integer>> permutation(int []nums){
        List<List<Integer>> res = new ArrayList<>();


        List<Integer> val = new ArrayList<>();
        for(Integer integer:nums){
            val.add(integer);
        }
        dfs(val,0,res);
        res.stream().forEach(e->{
            System.out.print(e+"-");
                    System.out.println();
        }
        );


        return res;
    }




    public static void main(String[]args){


        int[] nums = {1,2,3,4};
        new T4().permutation(nums);

//        char[] chars = new char[10];
//        // [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]]
//        char [][] board = {
//                {'A','B','C','E'},{'S','F','C','S'},{'A','D','E','E'}
//        };
//
//        new T4().exist(board,"ABCB");
//        chars[0]='1';
//        String str = "hello"+chars[0];
//        System.out.println(str);
    }
}
