package com.objects;

public class Professor extends Person {

    public Professor(int professorCode, String professorName, int professorAge, long professorFederalIdentification) {
        super.setCode(professorCode);
        super.setName(professorName);
        super.setAge(professorAge);
        super.setFederalIdentification(professorFederalIdentification);
    }
}
