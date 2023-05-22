package com.objects;

public class Person {

    private int code;
    private String name;
    private Integer age;
    private Long federalIdentification;

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
