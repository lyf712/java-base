package practice.lq.base.structrure.base;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Random;

/**
 * @AUTHOR LYF
 * @DATE 2021/4/3
 * @VERSION 1.0
 * @DESC
 */
public class Decimal {

    // BigDecimal
    void test1(){
        // BigDecimal进行设置
        Float f1 = 9.334F,f2 = 9.335F;
        BigDecimal bigDecimal1 = new BigDecimal(f1);
        BigDecimal bigDecimal2 = new BigDecimal(f2);

        float f11 = bigDecimal1.setScale(2,BigDecimal.ROUND_HALF_DOWN).floatValue();// 四舍无入
        float f21 = bigDecimal2.setScale(2,BigDecimal.ROUND_HALF_DOWN).floatValue();// 四舍无入
        System.out.println(f11+";"+f21);

        // ceil往上取，floor直接舍去
        float f12 = bigDecimal1.setScale(2,BigDecimal.ROUND_CEILING).floatValue();//
        float f13 = bigDecimal1.setScale(2,BigDecimal.ROUND_FLOOR).floatValue();//

        System.out.println(f12+";"+f13);

        // 流中的Statics,获取平均值，最大值，计数等

        IntSummaryStatistics iss = new IntSummaryStatistics();
        List<Integer> list  = new ArrayList<>();
        for(int i=0;i<10;i++){
            Random random = new
                    Random();
            list.add(random.nextInt(20));
        }
        iss = list.stream().mapToInt(Integer::intValue).summaryStatistics();// summary...总结为统计

        System.out.println(iss.getAverage()+";"+iss.getSum());

    }

    static void test2(){
        System.out.format("%.2f",1.3213F);// 百分号
        System.out.format("%.2f",1.3253F);// 百分号,四舍五入
    }

    public static void main(String[]args){
        test2();
    }
}
