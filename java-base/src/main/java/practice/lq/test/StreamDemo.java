package practice.lq.test;

import domain.Person;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * @AUTHOR LYF
 * @DATE 2021/4/1
 * @VERSION 1.0
 * @DESC
 */
public class StreamDemo {

    //测试数据
    static private List<Integer> intData = Arrays.asList(1,23,4,21,32);

    static private List<Person> personList = new ArrayList<>();

    static {
        for(int i =0;i<10;i++)
        {
            Random random = new Random();
            int ranAge = random.nextInt(10000);
            String ranName = "person"+random.nextInt(1000);
            Person person=new Person();
            person.setAge(ranAge);
            person.setName(ranName);
            personList.add(person);
        }

        // 流  filter (过滤） ->  sorted(排序) -> map （映射） -> reduce(约束） -> collect(收集 ，形成集合、或者map）
        // 统计 ,count ,InSummaryStatic(sum,average,max,min) , forEach()

//        personList.stream().forEach(System.out::println); //action??
//        personList.stream().filter(t->t.getAge()<20).count();
//        personList.stream().sorted(Comparator.comparing(Person::toString));

        // 测试排序
        List<Person> collectList = personList.stream().sorted(Comparator.naturalOrder()).collect(Collectors.toList());

        System.out.println("未收集:");
        // 未收集时的（原数据）
        personList.stream().forEach(System.out::println); //
        personList.stream().forEach(person -> {
            System.out.println(person.getName());});

        System.out.println("收集:");
        collectList.stream().forEach(System.out::println);


        // 测试查找 findAny()返回任意一个
        System.out.println(personList.stream().findAny());

        Person person = new Person();
        personList.stream().anyMatch(Predicate.isEqual(person));


    }
    static void practice(){




    }

    public static void main(String[]args){}


}
