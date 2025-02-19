package org.io_programming_.createjsonobject;

public class Student {
    private String name;
    private int age;
    private String[] subjects;

    // Constructor
    public Student(String name, int age, String[] subjects) {
        this.name = name;
        this.age = age;
        this.subjects = subjects;
    }

    // getters
    public String getName() {
        return name;
    }
    public int getAge() {
        return age;
    }
    public String[] getSubjects() {
        return subjects;
    }
}
