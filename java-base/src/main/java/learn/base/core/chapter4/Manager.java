package learn.base.core.chapter4;

import java.util.Objects;

/**
 * @AUTHOR LYF
 * @DATE 2021/4/29
 * @VERSION 1.0
 * @DESC
 */
public class Manager extends Employee{
    private int bonus;

    public Manager(String name, int age, int salary) {
        super(name, age, salary);
    }

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        Manager manager = (Manager) o;
//        return bonus == manager.bonus;
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(bonus);
//    }

    //重写salary
    @Override
    public int getSalary() {
        //super.age
        return super.getSalary()+bonus;
    }

    @Override
    public void setSalary(int salary) {
        super.setSalary(salary+bonus);
    }

    public int getBonus() {
        return bonus;
    }

    public void setBonus(int bonus) {
        this.bonus = bonus;
    }
}
