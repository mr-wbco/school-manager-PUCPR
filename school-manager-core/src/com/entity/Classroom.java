package com.entity;

import java.io.Serial;
import java.io.Serializable;

public class Classroom implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    public final static String CLASSROOM_CODE = "classroomCode";
    public final static String CLASSROOM_NAME = "classroomName";

    private int classroomCode;
    private String classroomName;

    public Classroom(int classroomCode, String classroomName) {
        this.classroomCode = classroomCode;
        this.classroomName = classroomName;
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
