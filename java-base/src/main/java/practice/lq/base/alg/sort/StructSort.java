package practice.lq.base.alg.sort;

import java.util.*;

/**
 * @AUTHOR LYF
 * @DATE 2021/6/3
 * @VERSION 1.0
 * @DESC
 */
public class StructSort {
    class Node{
        int count, num;
        public Node(int num,int count){
            this.count = count;
            this.num = num;
        }
    }
    public int[] topKFrequent(int[] nums, int k) {
        //  int []hash = new int [nums.length];
        // 结构体排序
        Arrays.sort(nums);
        List<Node> list = new ArrayList<>();

        int counts = 1;
        for(int i=0;i<nums.length-1;i++){
            if(i==nums.length-1){
                list.add(new Node(nums[i],counts));
            }
            if(nums[i]!=nums[i+1]){
                list.add(new Node(nums[i],counts));
                counts=1;
            }else{
                counts++;
            }
        }

        Collections.sort(list, Comparator.comparingInt(e -> e.count));

        int [] arr = new int[k];
        return arr;

    }
    public void test(){
        List<Node> list = new ArrayList<>();
        list.add(new Node(1,3));
        list.add(new Node(2,1));
        list.stream().forEach(e->System.out.print(e.num+";"+e.count+" "));
        System.out.println();
        Collections.sort(list,(e1,e2)-> e2.count-e1.count);
        list.stream().forEach(e->System.out.print(e.num+";"+e.count+" "));
    }


    void dfs(char[][] grid,int row,int col){
        if(row<0||col<0||row>=grid.length||col>=grid[0].length){
            return;
        }
        if(grid[row][col]=='2'||grid[row][col]=='0'){// 已经搜索过或者为水
            return ;
        }
        grid[row][col]='2';
        dfs(grid,row+1,col);
        dfs(grid,row-1,col);
        dfs(grid,row,col+1);
        dfs(grid,row,col-1);
    }

    public int numIslands(char[][] grid) {
        int res = 0;
        for(int i=0;i<grid.length*grid[0].length;i++){
            if(grid[i/grid[0].length][i%grid[0].length]=='1'){
                res ++;
                dfs(grid,i/grid[0].length,i%grid[0].length);
            }
        }
        return res;
    }
    public static void main(String[]args){
        new StructSort().test();
    }
}
