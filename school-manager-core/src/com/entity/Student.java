package com.entity;

import java.io.Serial;
import java.io.Serializable;

public class Student extends Person implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Enrolling enrolling;
    private Classroom classroom;

    public Student() {
        super.setCode(0);
        super.setName("");
        super.setAge(0);
        super.setFederalIdentification(0L);
    }

    public Student(int studentCode, String studentName, int studentAge, long studentFederalIdentification) {
        super(studentCode, studentName, studentAge, studentFederalIdentification);
    }

    public Enrolling getEnrolling() {
        return enrolling;
    }

    public void setEnrolling(Enrolling enrolling) {
        this.enrolling = enrolling;
    }

    public Classroom getClassroom() {
        return classroom;
    }

    public void setClassroom(Classroom classroom) {
        this.classroom = classroom;
    }
}
