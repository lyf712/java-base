package practice.nju.wangdao.sort;

import java.util.Arrays;
import java.util.Random;

/**
 * @AUTHOR LYF
 * @DATE 2021/5/5
 * @VERSION 1.0
 * @DESC
 */
public class RandomData {
    static public int[] data = new int[100];
    static {
        for(int i =0;i<100;i++)
        {
            data[i] = new Random().nextInt(1000);
            System.out.print(data[i]+" ");
        }
        System.out.println();

    }
}
