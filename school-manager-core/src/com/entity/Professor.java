package com.entity;

import java.io.Serial;
import java.io.Serializable;

public class Professor extends Person implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    public Professor() {
        super.setCode(0);
        super.setName("");
        super.setAge(0);
        super.setFederalIdentification(0L);
    }

    public Professor(int professorCode, String professorName, int professorAge, long professorFederalIdentification) {
        super(professorCode, professorName, professorAge, professorFederalIdentification);
    }
}
