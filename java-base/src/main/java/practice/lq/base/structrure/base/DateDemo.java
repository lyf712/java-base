package practice.lq.base.structrure.base;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @AUTHOR LYF
 * @DATE 2021/4/5
 * @VERSION 1.0
 * @DESC
 * 一、日期的基本使用
 * 1.Date
 * 2.Calender
 * 3.format(SimpleDateFormat、DateFormat、
 *
 *
 */
public class DateDemo {
    void test(){

        // 日期类，获取当前时间
        Date date = new Date();
        System.out.println("date:"+date+";time:"+date.getTime());// Mon Apr 05 18:39:21 CST 2021 ;time:1617619161143
        // getTime()或1970至此 的毫秒数
        System.out.println("Year:"+date.getYear()+";month:"+date.getMonth()+";day"+date.getDay()+";date"+date.getDate());
        // Year:121;month:3;day1;date5  ,date才是日期？day 是代表星期几
        // Year从1900开始算的
        date.setTime(2000);
        System.out.println("2000ms之后："+date); //Thu Jan 01 08:00:02 CST 1970

        // 日期格式化 (按格式进行String和Date的对象相互转换）
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//按此进行格式化 MM 代表月，mm代表分
        // sdf.对象方法format
        sdf.format(date);//返回String
        String str = sdf.format(date);

        System.out.println("格式化后:"+date.toString()+";"+str);

        // String => date
        try {
            Date tempD = sdf.parse(str);
            System.out.println("tempD:"+tempD+";"+sdf.format(tempD));
        }catch (Exception e){
            e.printStackTrace();
        }


        // Calender 更为完善复杂，方便进行日期的增减
        Calendar calendar = Calendar.getInstance();// 获取当前时期
        System.out.println(calendar);
        // 创建指定日期
        calendar.set(2009,6-1,9);
        calendar.add(Calendar.DATE,1);

        System.out.println(calendar.toString()+";"+calendar.getTime());
        // 通过 calender 的 getTime 与Date建立联系，Date又可以进行SimpleDateFormat格式化以及字符串相互转换


    }



    public static void main(String[]args){
        DateDemo dateDemo = new DateDemo();
        dateDemo.test();

    }



}
