package practice.lq.test;

import domain.User;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @AUTHOR LYF
 * @DATE 2021/4/2
 * @VERSION 1.0
 * @DESC
 */
public class Test {

    static List<User> list = new ArrayList<>();
    static List<User> list2 = new ArrayList<>();

    static {
        for(int i =0;i<10;i++){
            Random random = new Random();
            User user = new User("name"+random.nextInt(10), random.nextInt(100));
            list.add(user);
            list2.add(user);
        }

        System.out.println("测试数据:");
        list.stream().forEach(System.out::println);

    }
    static public void testSort(){

        long start1 = System.currentTimeMillis();

        List<User> list1 = list.stream().sorted((user1,user2)->{
            return user1.getAge()-user2.getAge();
        }).filter(user -> {return user.getAge()>60;}).collect(Collectors.toList());
        // 筛选年龄大于一百的

        long end1 = System.currentTimeMillis();

        System.out.println("排序筛选时间:"+(end1-start1)+";结果");
        list1.stream().forEach(System.out::println);

        Collections.sort(list2,(user1,user2)->{
            return user1.getAge()-user2.getAge();
        });

        // 在进行遍历时，删除是不可取的会冲突，但可以进行修改  。.ConcurrentModificationException
        // 因此只能考虑再设定一个集合进行存放符合要求的数据
//        list2.forEach(
//                (user)->{if(user.getAge()>60)
//                {
//                    list2.remove(user);
//                } } );

        List<User> tempList = new ArrayList<>();
        list2.forEach((user)->{
            if(user.getAge()>60)
            {
                tempList.add(user);
                System.out.println("test:"+user);
            }
        });

        tempList.stream().forEach(System.out::println);


        System.out.println("排序筛选时间:"+(end1-start1)+";结果");
        list2.stream().forEach(System.out::println);



    }

    static void test2(){

        list.stream().map(user -> {user.setAge(user.getAge()+1);
        return user;
        }
        ).forEach(System.out::println);

        long num = list.stream().count();
        Optional<User> max =list.stream().max((user1, user2)-> {
            return user1.getAge()-user2.getAge();
        });
        System.out.println(num+";"+max);

    }

    public static void main(String[]args){

        Test.test2();
    }



}
