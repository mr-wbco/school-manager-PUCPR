package com.objects;

import java.io.Serial;
import java.io.Serializable;

public class Student extends Person implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    public Student(int studentCode, String studentName, int studentAge, long studentFederalIdentification) {
        super(studentCode, studentName, studentAge, studentFederalIdentification);
    }
}
