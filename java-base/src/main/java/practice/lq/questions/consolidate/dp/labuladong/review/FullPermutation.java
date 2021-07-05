package practice.lq.questions.consolidate.dp.labuladong.review;

/**
 * @AUTHOR LYF
 * @DATE 2021/4/14
 * @VERSION 1.0
 * @DESC
 */
public class FullPermutation {

    //
//    void swap(int a,int b){
//        int temp = a;
//        a = b;
//        b = temp;
//    }
    void swap(int []arr,int a,int b){
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

    //
    void permutation(int []arr,int n){// n start
        if(n==arr.length-1)
        {
            // output
            for(int i =0;i<arr.length;i++)
            {
                System.out.print(arr[i]);
            }
            System.out.println();
            return;
        }else{
            for(int i =n;i<arr.length;i++){
                swap(arr,n,i);
                permutation(arr,n+1);
                swap(arr,n,i);
            }
        }

    }

    public static void main(String[]args){
        FullPermutation fullPermutation = new FullPermutation();
        int[] arr = {1,2,3,4};
        fullPermutation.permutation(arr,0);
    }


}
