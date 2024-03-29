package com.entity;

import java.io.Serial;
import java.io.Serializable;

public class Subject implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    public final static String SUBJECT_CODE = "subjectCode";
    public final static String SUBJECT_NAME = "subjectName";

    private int subjectCode;
    private String subjectName;

    public Subject() {
    }

    public Subject(int subjectCode, String subjectName) {
        this.subjectCode = subjectCode;
        this.subjectName = subjectName;
    }

    public int getSubjectCode() {
        return subjectCode;
    }

    public void setSubjectCode(int subjectCode) {
        this.subjectCode = subjectCode;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }
}
