package org.io_programming_.createjsonobject;

import org.json.JSONArray;
import org.json.JSONObject;

public class StudentToJson {
    public static void main(String[] args) {

        // Create object of Student
        Student student = new Student("Sakshi",21,new String[]{"Physics", "Chemistry"});
        // Create the json object
        JSONObject jsonObject = new JSONObject();

        // Add values to jsonObject
        jsonObject.put("Name",student.getName());
        jsonObject.put("Age",student.getAge());

        // Subjects
        JSONArray subject = new JSONArray();
        subject.put(student.getSubjects());

        jsonObject.put("Subjects",subject);

        // Output
        System.out.println(jsonObject.toString());


    }
}