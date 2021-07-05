package practice.lq.questions.country_2020;

import java.math.BigDecimal;
import java.util.Scanner;

/**
 * @AUTHOR LYF
 * @DATE 2021/5/23
 * @VERSION 1.0
 * @DESC
 * 画廊
 */
public class T7 {
    public static void  main(String[]args){

        int l,r,d,w;
        Scanner scanner = new Scanner(System.in);
        l = scanner.nextInt();
        r = scanner.nextInt();
        d = scanner.nextInt();
        w = scanner.nextInt();

        int[] lArr = new int[l];
        int[] rArr = new int[r];

        for(int i=0;i<l;i++){
            lArr[i] = scanner.nextInt();
        }
        for(int i=0;i<r;i++){
            rArr[i] = scanner.nextInt();
        }

        // 贪心handle
        double val = 0;
        int p1 = 0,p2 = 0 ;
        int curX = w/2,curY = 0;

        while(curX !=w/2 || curY!=d){
            if(p1==l&&p2==r){
                double dis = Math.sqrt(Math.pow(curX-w/2,2)+Math.pow(curY-d,2));
                //System.out.println("last step:"+dis);
                val += dis;
                break;
            }

            // 左右选择
            double lDis = p1==l?Double.MAX_VALUE:Math.sqrt(Math.pow(curX,2)+Math.pow(curY-lArr[p1],2));
            double rDis = p2==r?Double.MAX_VALUE:Math.sqrt(Math.pow(curX-w,2)+Math.pow(curY-rArr[p2],2));

            if(lDis<=rDis){
                //System.out.println("choose left and dis is:"+lDis);
                val+=lDis;
                curX = 0;
                curY = lArr[p1++];
            }else{
                //System.out.println("choose right and dis is:"+rDis);
                val+=rDis;
                curX = w;
                curY = rArr[p2++];
            }
        }
        System.out.println(new BigDecimal(val).setScale(2,BigDecimal.ROUND_HALF_DOWN).floatValue());
        // 精度处理
        //√ 2 + 2 + √ 5 + 2 + 2 + 2 √ 2 + √ 5 ≈ 14.71。
//        double val = 2*Math.sqrt(5)+6+3*Math.sqrt(2);
//        BigDecimal bigDecimal = new BigDecimal(val);
//        System.out.println(bigDecimal.setScale(2,BigDecimal.ROUND_HALF_DOWN).floatValue());

    }

}
