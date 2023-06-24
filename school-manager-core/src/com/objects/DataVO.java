package com.objects;

import com.entity.*;

import java.util.ArrayList;
import java.util.List;

public class DataVO {

    public final static String STUDENT_LIST = "studentList";
    public final static String PROFESSOR_LIST = "professorList";
    public final static String SUBJECT_LIST = "subjectList";
    public final static String CLASSROOM_LIST = "classroomList";
    public final static String ENROLLING_LIST = "enrollingList";

    private List<Student> studentList = new ArrayList<>();
    private List<Professor> professorList = new ArrayList<>();
    private List<Subject> subjectList = new ArrayList<>();
    private List<Classroom> classroomList = new ArrayList<>();
    private List<Enrolling> enrollingList = new ArrayList<>();

    public List<Student> getStudentList() {
        return studentList;
    }

    public void setStudentList(List<Student> studentList) {
        this.studentList = studentList;
    }

    public List<Professor> getProfessorList() {
        return professorList;
    }

    public void setProfessorList(List<Professor> professorList) {
        this.professorList = professorList;
    }

    public List<Subject> getSubjectList() {
        return subjectList;
    }

    public void setSubjectList(List<Subject> subjectList) {
        this.subjectList = subjectList;
    }

    public List<Classroom> getClassroomList() {
        return classroomList;
    }

    public void setClassroomList(List<Classroom> classroomList) {
        this.classroomList = classroomList;
    }

    public List<Enrolling> getEnrollingList() {
        return enrollingList;
    }

    public void setEnrollingList(List<Enrolling> enrollingList) {
        this.enrollingList = enrollingList;
    }
}
