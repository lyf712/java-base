package practice.lq.questions.country_2020;

import java.util.Scanner;

/**
 * @AUTHOR LYF
 * @DATE 2021/5/25
 * @VERSION 1.0
 * @DESC
 */
public class T5 {
    static long dfs(boolean arr[][], int idx, int x, int y) {
        if (x < 0 || y < 0 || x >= 4 || y >= 4)
            return 0;
        if (arr[y][x])
            return 0;
        if (idx >= 15)
            return 1;
        long count = 0;
        arr[y][x] = true;
        count += dfs(arr, idx + 1, x + 1, y);
        count += dfs(arr, idx + 1, x, y + 1);
        count += dfs(arr, idx + 1, x - 1, y);
        count += dfs(arr, idx + 1, x, y - 1);
        arr[y][x] = false;
        return count;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // long n = sc.nextLong();
        boolean arr[][] = new boolean[4][4];

        long count = 0;
        for (int i = 0; i < 16; ++i) {
            count += dfs(arr, 0, i % 4, i / 4);
        }
        System.out.println(count);

    }
}
