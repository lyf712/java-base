package learn.base.core.chapter4;

import java.util.Objects;

/**
 * @AUTHOR LYF
 * @DATE 2021/4/29
 * @VERSION 1.0
 * @DESC
 */
public class Main {


    public static void main(String[]args){

        Employee employee = new Employee("I am E",12,200);
        Manager manager = new Manager("M",13,3200);
        System.out.println(manager.getSalary());
        manager.setBonus(100);
        System.out.println(manager.getSalary());
        System.out.println(Objects.hash(manager));

    }
}
