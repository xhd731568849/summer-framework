package com.summer.demos.chapter2;

import java.util.Comparator;
import java.util.TreeSet;

public class Person implements Comparable {
    private int age;
    private double salary;
    private MarrySatisfyDegree degree;


    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public MarrySatisfyDegree getDegree() {
        return degree;
    }

    public void setDegree(MarrySatisfyDegree degree) {
        this.degree = degree;
    }

    @Override
    public String toString() {
        return "Person{" +
                "age=" + age +
                ", salary=" + salary +
                ", degree=" + degree +
                '}';
    }

    @Override
    public int compareTo(Object o) {
        Person p = (Person) o;
        return Comparator.comparing(Person::getAge).
                thenComparingDouble(Person::getSalary)
                .compare(this, p);
    }

    public static void main(String[] args) {
        Person p1 = new Person();
        p1.setAge(1);
        p1.setSalary(1.2);

        Person p2 = new Person();
        p2.setAge(1);
        p2.setSalary(1.1);


        Person p3 = new Person();
        p3.setAge(4);
        p3.setSalary(1.2);


        TreeSet<Person> set = new TreeSet<>();
        set.add(p1);
        set.add(p2);
        set.add(p3);

        System.out.println(set);

    }

}
