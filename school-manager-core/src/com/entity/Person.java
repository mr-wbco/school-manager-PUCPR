package com.entity;

import java.io.Serial;
import java.io.Serializable;

public class Person implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    public final static String CODE = "code";
    public final static String NAME = "name";
    public final static String AGE = "age";
    public final static String FEDERAL_IDENTIFICATION = "federalIdentification";

    private int code;
    private String name;
    private Integer age;
    private Long federalIdentification;

    public Person() {
    }

    public Person(int code, String name, Integer age, Long federalIdentification) {
        this.code = code;
        this.name = name;
        this.age = age;
        this.federalIdentification = federalIdentification;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getFederalIdentification() {
        return federalIdentification;
    }

    public void setFederalIdentification(Long federalIdentification) {
        this.federalIdentification = federalIdentification;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
