package learn.base.core.chapter8;

/**
 * @AUTHOR LYF
 * @DATE 2021/4/30
 * @VERSION 1.0
 * @DESC
 */
public class Person<T1,T2> {
    T1 name;
    T2 age;



    public static void main(String[]args){
        Person<String,Integer> person = new Person<>("Person1",12);
        Person<String,Integer> person2 = new Person<>("Person1",12);
//        (arg1,arg2)->{
//            System.out.println(arg1+";"+arg2);
//        }
        System.out.println(person.getAge());


    }
    public Person(T1 name, T2 age) {
        this.name = name;
        this.age = age;
    }

    public T1 getName() {
        return name;
    }

    public void setName(T1 name) {
        this.name = name;
    }

    public T2 getAge() {
        return age;
    }

    public void setAge(T2 age) {
        this.age = age;
    }
}
