package practice.lq.completation.arr;

import java.util.Arrays;

/**
 * @AUTHOR LYF
 * @DATE 2021/6/2
 * @VERSION 1.0
 * @DESC
 */
public class Matrix {
    public static void main(String[]args){
        int [][] matrix = {{1,2,3,4},{1,3,2,1}};
        for(int i=0;i< matrix.length;i++){
            for(int j=0;j<matrix[0].length;j++){
                System.out.print(matrix[i][j]+" ");
            }
            System.out.println();
        }

        System.out.println("============");

        Arrays.fill(matrix[0],0);
       // Arrays.fill();

        for(int i=0;i< matrix.length;i++){
            for(int j=0;j<matrix[0].length;j++){
                System.out.print(matrix[i][j]+" ");
            }
            System.out.println();
        }
    }
}
