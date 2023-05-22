package com.objects;

public class Student extends Person {

    public Student(int studentCode, String studentName, int studentAge, long studentFederalIdentification) {
        super.setCode(studentCode);
        super.setName(studentName);
        super.setAge(studentAge);
        super.setFederalIdentification(studentFederalIdentification);
    }
}
