package practice.lq.completation.arr;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @AUTHOR LYF
 * @DATE 2021/6/2
 * @VERSION 1.0
 * @DESC
 */
public class ArrDemo {
    public List<List<Integer>> threeSum(int[] nums) {

        // 三重循环
        List<List<Integer>> res = new ArrayList<>();
        Set<Set<Integer>> set = new HashSet<>();

        for(int i=0;i<=nums.length-3;i++){
            List<Integer> res0 = new ArrayList<>();
            Set<Integer> set0 = new HashSet<>();

            for(int j=i+1;j<=nums.length-2;j++){
                for(int k=j+1;k<=nums.length-1;k++){
                    if(nums[i]+nums[j]+nums[k]==0){
//                        res0.add(i);
//                        res0.add(j);
//                        res0.add(k);
//                        res.add(res0);
                         set0.add(nums[i]);
                         set0.add(nums[j]);
                         set0.add(nums[k]);
                         set.add(new HashSet<>(set0));
                         set0.clear();

                    }
                }
            }
        }
        set.stream().forEach(e->{
            List<Integer> tempList = new ArrayList<>();

            e.stream().forEach(e1->{
                tempList.add(e1);
            });
            res.add(tempList);
        });


        return res;
    }
    public static void main(String[]args){
        int[]nums = {1,2,3,4,4,5};
        new ArrDemo().threeSum(nums);
    }

}
