package practice.lq.questions.country_2020;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * @AUTHOR LYF
 * @DATE 2021/5/23
 * @VERSION 1.0
 * @DESC
 */
public class T6_2 {

    static int N = 1010;
    static int n,m;
    static int f[][] = new int[N][N];
    static int a[] = new int[N];
    static int b[] = new int[N];
    static int num = 0;
    static Map<String, Integer> map = new HashMap<String, Integer>();

    static int get_Arrays(String s,int p[]) {
        int cnt = 0, st = 0;
        for(int i = 1; i < s.length(); i++) {
            if(s.charAt(i) >= 'A' && s.charAt(i) <= 'Z') {
                String key = s.substring(st,i);
                if(map.containsKey(key)) p[++cnt] = map.get(key);
                else {
                    map.put(key, ++num);
                    p[++cnt] = num;
                }
                st = i;
            }
        }
        String key = s.substring(st,s.length());
        if(map.containsKey(key)) p[++cnt] = map.get(key);
        else {
            map.put(key, ++num);
            p[++cnt] = num;
        }
        return cnt;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s1 = br.readLine();
        String s2 = br.readLine();

        n = get_Arrays(s1, a);
        m = get_Arrays(s2, b);

        int res = 0;
        for(int i = 1; i <= n; i++)
            for(int j = 1; j <= m; j++) {
                f[i][j] = Math.max(f[i-1][j], f[i][j-1]);
                if(a[i] == b[j]) {
                    f[i][j] = Math.max(f[i][j], f[i-1][j-1] + 1);
                }
                res = Math.max(res, f[i][j]);
            }
        System.out.println(res);
    }
}
