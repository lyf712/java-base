package practice.lq.questions.consolidate.simulate;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * @AUTHOR LYF
 * @DATE 2021/4/14
 * @VERSION 1.0
 * @DESC
 */


class TimeStream{
    int t,no;

    public TimeStream() {
    }

    public TimeStream(int t, int no) {
        this.t = t;
        this.no = no;
    }

    public int getT() {
        return t;
    }

    public void setT(int t) {
        this.t = t;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    @Override
    public String toString() {
        return "TimeStream{" +
                "t=" + t +
                ", no=" + no +
                '}';
    }
}




public class StreamPractice {


    static  void test(){

        List<TimeStream> list = new ArrayList<>();

        // 随机生成数据
        for(int i =0;i<10;i++){
            Random random = new Random();
            list.add(new TimeStream(random.nextInt(10), random.nextInt(6) ));
        }

        // 测试数据
        list.stream().forEach(System.out::print);

        // 统计时间为1的数量
        System.out.println("统计数据:"+list.stream().filter(timeStream -> timeStream.t==1).count());

        List<TimeStream> list1= list.stream().collect(Collectors.toList());

        list.stream().mapToInt(TimeStream::getNo).summaryStatistics();

        List<Integer> list2 = new ArrayList<>();


        // 排序
        list.sort((o1,o2)->o1.t-o2.t);

        list.stream().forEach(System.out::print);

    }

    public static void main(String[]args)
    {
        test();
    }

}
