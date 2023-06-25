package com.entity;

import java.io.Serial;
import java.io.Serializable;

public class Enrolling implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    public final static String ENROLLING_CODE = "enrollingCode";
    public final static String STUDENT_CODE = "studentCode";
    public final static String STUDENT_NAME = "studentName";
    public final static String CLASSROOM_CODE = "classroomCode";
    public final static String CLASSROOM_NAME = "classroomName";

    private int enrollingCode;
    private int studentCode;
    private String studentName;
    private int classroomCode;
    private String classroomName;

    public Enrolling() {
    }

    public Enrolling(int enrollingCode, int studentCode, String studentName, int classroomCode, String classroomName) {
        this.enrollingCode = enrollingCode;
        this.studentCode = studentCode;
        this.studentName = studentName;
        this.classroomCode = classroomCode;
        this.classroomName = classroomName;
    }

    public int getEnrollingCode() {
        return enrollingCode;
    }

    public void setEnrollingCode(int enrollingCode) {
        this.enrollingCode = enrollingCode;
    }

    public int getStudentCode() {
        return studentCode;
    }

    public void setStudentCode(int studentCode) {
        this.studentCode = studentCode;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public int getClassroomCode() {
        return classroomCode;
    }

    public void setClassroomCode(int classroomCode) {
        this.classroomCode = classroomCode;
    }

    public String getClassroomName() {
        return classroomName;
    }

    public void setClassroomName(String classroomName) {
        this.classroomName = classroomName;
    }
}
