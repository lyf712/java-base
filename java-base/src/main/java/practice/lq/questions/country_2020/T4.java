package practice.lq.questions.country_2020;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @AUTHOR LYF
 * @DATE 2021/5/22
 * @VERSION 1.0
 * @DESC
 * 3616159
 * 本子上升序列
 *
 */
public class T4 {
    private static int[] dp;
    public static void main(String[]args) {
        Scanner sc = new Scanner(System.in);
//        String s = sc.next();
        String s="tocyjkdzcieoiodfpbgcncsrjbhmugdnojjddhllnofawllbhfiadgdcdjstemphmnjihecoapdjjrprrqnhgccevdarufmliqijgihhfgdcmxvicfauachlifhafpdccfseflcdgjncadfclvfmadvrnaaahahndsikzssoywakgnfjjaihtniptwoulxbaeqkqhfwl";

        char[] num = s.toCharArray();
        dp = new int[s.length()];
        Arrays.fill(dp, 1);

        for (int i = 0; i < s.length(); i++) {
            for (int j = 0; j < i; j++) {
                if (num[j] < num[i]) {
                    dp[i] += dp[j];
                }
                if (num[j] == num[i]) {
                    //dp[i] -= dp[j];// 去掉以该字符结尾的重复情况
                    dp[i]=0;
                }
            }
        }
        int res = 0;
        for (int i = 0; i < dp.length; i++) {
            res += dp[i];
        }
        System.out.println(res);
    }
}
