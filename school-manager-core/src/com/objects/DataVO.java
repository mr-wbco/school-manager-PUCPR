package com.objects;

import java.util.ArrayList;
import java.util.List;

public class DataVO {

    private List<Student> studentList = new ArrayList<>();
    private List<Professor> professorList = new ArrayList<>();

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
}
