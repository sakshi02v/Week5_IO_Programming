package org.io_programming_.advancedproblems.csvintojavaobjects;

public class Student {
    private String id;
    private String name;
    private int age;
    private int marks;

    // Constructor
    public Student (String id, String name, int age, int marks) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.marks = marks;
    }

    // Getters and setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getMarks() {
        return marks;
    }

    public void setMarks(int marks) {
        this.marks = marks;
    }

    // Override toString for printing the object
    @Override
    public String toString() {
        return String.format("ID: %s | Name: %s | Age: %d | Marks: %d", id, name, age, marks);
    }
}
