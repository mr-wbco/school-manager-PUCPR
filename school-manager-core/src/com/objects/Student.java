package com.objects;

import java.io.Serial;
import java.io.Serializable;

public class Student extends Person implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    public Student() {

    }

    public Student(int studentCode, String studentName, int studentAge, long studentFederalIdentification) {
        super.setCode(studentCode);
        super.setName(studentName);
        super.setAge(studentAge);
        super.setFederalIdentification(studentFederalIdentification);
    }

    public String toString(Student student) {
        StringBuilder builder = new StringBuilder();
        builder.append(student.getName());
        builder.append("\n");
        builder.append(student.getAge().toString());
        builder.append("\n");
        builder.append(student.getFederalIdentification().toString());
        builder.append("\n");
        return builder.toString();
    }
}
