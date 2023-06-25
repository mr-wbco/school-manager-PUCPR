package com.entity;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Classroom implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    public final static String CLASSROOM_CODE = "classroomCode";
    public final static String CLASSROOM_NAME = "classroomName";
    public final static String SUBJECT = "subject";
    public final static String PROFESSOR = "professor";
    public final static String STUDENT_LIST = "studentList";

    private int classroomCode;
    private String classroomName;
    private Subject subject;
    private Professor professor;
    private List<Student> studentList = new ArrayList<>();

    public Classroom() {
    }

    public Classroom(int classroomCode, String classroomName, Subject subject, Professor professor) {
        this.classroomCode = classroomCode;
        this.classroomName = classroomName;
        this.subject = subject;
        this.professor = professor;
    }

    public Classroom(int classroomCode, String classroomName, Subject subject, Professor professor, List<Student> studentList) {
        this.classroomCode = classroomCode;
        this.classroomName = classroomName;
        this.subject = subject;
        this.professor = professor;
        this.studentList = studentList;
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

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public List<Student> getStudentList() {
        return studentList;
    }

    public void setStudentList(List<Student> studentList) {
        this.studentList = studentList;
    }
}
