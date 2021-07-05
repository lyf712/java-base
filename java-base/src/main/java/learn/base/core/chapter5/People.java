package learn.base.core.chapter5;

import java.util.Objects;

/**
 * @AUTHOR LYF
 * @DATE 2021/4/29
 * @VERSION 1.0
 * @DESC
 */
public class People {
    private String name;
    private Integer age;
    private Integer salary;

    public People(String name, Integer age, Integer salary) {
        this.name = name;
        this.age = age;
        this.salary = salary;
    }

    public People() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        People people = (People) o;
//        return Objects.equals(name, people.name) && Objects.equals(age, people.age) && Objects.equals(salary, people.salary);
//    }


    @Override
    public String toString() {
        return "People{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", salary=" + salary +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age, salary);
    }
}
