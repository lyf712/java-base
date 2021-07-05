package practice.leetcode.learn.recursion;



import java.util.ArrayList;
import java.util.List;

/**
 * @AUTHOR LYF
 * @DATE 2021/6/1
 * @VERSION 1.0
 * @DESC
 */
public class SubSet {

    // 递归办法dfs
    static List<List<Integer>> lists = new ArrayList<List<Integer>>();
    //List<Integer> temp = new ArrayList<>();
    void dfs(List<Integer> temp, int []nums, int idx){

        if(idx==nums.length){
            //lists.add(temp);
            //用res.add(new linkedList(path))而不是res.add(path)。因为path指向的对象在不断地增加和删除元素，
            // 最后会变成空。得到的结果会变成[[][][][][]]所以要用new linkedList(path)拷贝一个对象
            lists.add(new ArrayList<>(temp));// 需要新实例？？
            temp.stream().forEach(System.out::print);
            System.out.println();
            return;
        }
        temp.add(nums[idx]);//选择当前
        dfs(temp,nums,idx+1);
        temp.remove((Object)nums[idx]);// 不选择
        dfs(temp,nums,idx+1);

    }
    public List<List<Integer>> subsets(int[] nums) {
        List<Integer> temp = new ArrayList<>();
        dfs(temp,nums,0);
        return lists;
    }

    // 迭代办法 ，2^n次选择


    // 自己思考的思路
    // 0-n(2^len)-1,转化为二进制，为0则不选择，为1则进行选择 => 二叉树?? 抽象为二进制
    List<List<Integer>> subSet2(int []nums){
        List<List<Integer>> allSet = new ArrayList<>();
        List<Integer> tempSet = new ArrayList<>();

         int len = nums.length;
         for(int i = 0; i< Math.pow(2,len); i++){
             tempSet.clear();//选择之前清理
             int n = i;
              for(int j=0;j<len;j++){
                  // 进行len次选择,考虑使用移位运算
                  if(n%2==1){
                      tempSet.add(nums[len-j-1]);// 进行判断是否选择j
                      n = n>>1;
                  }
              }
              allSet.add(new ArrayList<>(tempSet));
         }
         return allSet;
    }



    public static void main(String[]args){
//        int[] nums = {1,2,3,4};
//        SubSet subSet = new SubSet();
//        subSet.subsets(nums);
//        System.out.println("------");
//        System.out.println(subSet.lists.size()+":"+subSet.lists.get(0).toArray().toString());
//        subSet.lists.stream().forEach(integers -> integers.stream().forEach(System.out::print));
        int n=2>>1;
        System.out.println(n);

    }
}
