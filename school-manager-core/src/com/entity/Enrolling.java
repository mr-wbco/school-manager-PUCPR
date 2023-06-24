package com.entity;

import java.io.Serial;
import java.io.Serializable;

public class Enrolling implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    public final static String ENROLLING_CODE = "enrollingCode";
    public final static String ENROLLING_NAME = "enrollingName";

    private int enrollingCode;
    private String enrollingName;

    public Enrolling(int enrollingCode, String enrollingName) {
        this.enrollingCode = enrollingCode;
        this.enrollingName = enrollingName;
    }

    public int getEnrollingCode() {
        return enrollingCode;
    }

    public void setEnrollingCode(int enrollingCode) {
        this.enrollingCode = enrollingCode;
    }

    public String getEnrollingName() {
        return enrollingName;
    }

    public void setEnrollingName(String enrollingName) {
        this.enrollingName = enrollingName;
    }
}
