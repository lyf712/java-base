package learn.base.core.chapter6;

import learn.base.core.chapter5.People;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

/**
 * @AUTHOR LYF
 * @DATE 2021/4/29
 * @VERSION 1.0
 * @DESC
 */
public class LambdaTest {
    public static void main(String[]args)
    {
        People[] peoples = new People[10];
        for(int i =0;i<10;i++)
        {
            Random random = new Random();
            peoples[i]= new People();
            peoples[i].setAge(random.nextInt(100));
            peoples[i].setName("person"+i);
            peoples[i].setSalary(random.nextInt(30000));
        }
        Arrays.sort(peoples, Comparator.comparing(People::getAge).thenComparing(People::getSalary));
        for(int i =0;i<10;i++)
        {
            System.out.println(peoples[i]);
        }
    }
}
