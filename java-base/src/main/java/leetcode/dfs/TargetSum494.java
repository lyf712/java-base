package leetcode.dfs;

/**
 * @AUTHOR LYF
 * @DATE 2021/6/7
 * @VERSION 1.0
 * @DESC
 */
public class TargetSum494 {
    int res=0;
    void dfs(int val,int pos,int target,int[] nums){
//        if(val == target){
//            res++;
//            return;
//        }
        if(pos==nums.length||val<target){
            if(val == target)
                res++;
            return;
        }
        dfs(val-2*nums[pos],pos+1,target,nums);
        dfs(val,pos+1,target,nums);
    }


    public int findTargetSumWays(int[] nums, int target) {
        int sum=0;
        for(int i=0;i<nums.length;i++)
            sum+=nums[i];

        dfs(sum,0,target,nums);
        return res;
    }
    public static void main(String[]args){
        int[]nums={1,0};
        new TargetSum494().findTargetSumWays(nums,1);

    }
}
