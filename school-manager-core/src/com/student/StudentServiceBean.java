package com.student;

import com.objects.DataVO;
import com.entity.Student;
import com.system.SystemBO;
import com.utils.UTILS;

import java.util.List;
import java.util.Scanner;

public class StudentServiceBean implements StudentService {

    @Override
    public void insertNewStudent(DataVO dataVO) {
        UTILS.printHeaderManager();
        UTILS.printConsoleMessage(UTILS.INSERT_NEW_INFORMATION_MESSAGE);

        Student student = this.createNewStudent();

        if (this.studentAlreadyExists(student, dataVO.getStudentList())) {
            UTILS.printConsoleMessage(UTILS.ALREADY_EXISTS_ERROR_MESSAGE);
            return;
        }

        dataVO.getStudentList().add(dataVO.getStudentList().size(), student);
        this.saveStudent(dataVO);

        UTILS.printConsoleMessage(UTILS.INSERT_NEW_SUCCESS_MESSAGE);
    }

    @Override
    public void viewStudentList(DataVO dataVO) {
        if (dataVO.getStudentList() == null || dataVO.getStudentList().isEmpty()) {
            UTILS.printConsoleMessage(UTILS.EMPTY_LIST_ERROR_MESSAGE);
            return;
        }

        for (Student student : dataVO.getStudentList()) {
            System.out.println(" ");
            System.out.println("CÓDIGO: " + student.getCode());
            System.out.println("NOME: " + student.getName().toUpperCase());
            System.out.println("IDADE: " + student.getAge());
            System.out.println("CPF: " + student.getFederalIdentification());
        }
    }

    @Override
    public void updateStudent(DataVO dataVO) {
        this.viewStudentList(dataVO);

        if (dataVO.getStudentList() == null || dataVO.getStudentList().isEmpty()) {
            return;
        }

        Student studentToUpdate = this.findStudent(dataVO);
        if (studentToUpdate == null) {
            return;
        }

        String studentName = this.generateStudentName();
        int studentAge = this.generateStudentAge();
        long studentFederalIdentification = this.generateStudentFederalIdentification();

        if (this.studentNameAlreadyExists(studentName, dataVO.getStudentList()) || this.studentFederalIdentificationAlreadyExists(studentFederalIdentification, dataVO.getStudentList())) {
            UTILS.printConsoleMessage(UTILS.ALREADY_EXISTS_ERROR_MESSAGE);
            return;
        }

        studentToUpdate.setName(studentName);
        studentToUpdate.setAge(studentAge);
        studentToUpdate.setFederalIdentification(studentFederalIdentification);
        this.saveStudent(dataVO);
    }

    @Override
    public void deleteOneStudent(DataVO dataVO) {
        this.viewStudentList(dataVO);

        if (dataVO.getStudentList() == null || dataVO.getStudentList().isEmpty()) {
            return;
        }

        Student studentToDelete = this.findStudent(dataVO);
        if (studentToDelete == null) {
            return;
        }

        dataVO.getStudentList().remove(studentToDelete);
        this.saveStudent(dataVO);
        UTILS.printConsoleMessage(UTILS.DELETE_RECORD_SUCCESS_MESSAGE);
    }

    @Override
    public void clearStudentList(DataVO dataVO) {
        UTILS.printConsoleMessage(UTILS.DELETE_ALL_RECORDS_QUESTION_MESSAGE);

        int choice = UTILS.scannerIntValue();

        if (choice != 1) {
            UTILS.printConsoleMessage(UTILS.DELETE_ALL_RECORDS_CANCEL_MESSAGE);
            return;
        }

        dataVO.getStudentList().clear();
        this.saveStudent(dataVO);
        UTILS.printConsoleMessage(UTILS.DELETE_ALL_RECORDS_SUCCESS_MESSAGE);
    }

    private Student createNewStudent() {
        int studentCode = this.generateStudentCode();
        String studentName = this.generateStudentName();
        int studentAge = this.generateStudentAge();
        long studentFederalIdentification = this.generateStudentFederalIdentification();

        return new Student(studentCode, studentName, studentAge, studentFederalIdentification);
    }

    private int generateStudentCode() {
        UTILS.printConsoleMessage(UTILS.CODE);
        return UTILS.scannerIntValue();
    }

    private String generateStudentName() {
        UTILS.printConsoleMessage(UTILS.NAME);
        return new Scanner(System.in).nextLine();
    }

    private int generateStudentAge() {
        UTILS.printConsoleMessage(UTILS.AGE);
        return UTILS.scannerIntValue();
    }

    private long generateStudentFederalIdentification() {
        UTILS.printConsoleMessage(UTILS.FEDERAL_IDENTIFICATION);
        return UTILS.scannerLongValue();
    }

    public boolean studentAlreadyExists(Student newStudent, List<Student> studentList) {
        for (Student studentFromList : studentList) {
            if (studentFromList.getCode() == newStudent.getCode() || studentFromList.getName().equals(newStudent.getName()) || studentFromList.getFederalIdentification().equals(newStudent.getFederalIdentification())) {
                return true;
            }
        }

        return false;
    }

    private boolean studentNameAlreadyExists(String newStudentName, List<Student> studentList) {
        for (Student student : studentList) {
            if (student.getName().equals(newStudentName)) {
                return true;
            }
        }

        return false;
    }

    private boolean studentFederalIdentificationAlreadyExists(Long newFederalIdentification, List<Student> studentList) {
        for (Student student : studentList) {
            if (student.getFederalIdentification().equals(newFederalIdentification)) {
                return true;
            }
        }

        return false;
    }

    private Student findStudent(DataVO dataVO) {
        int studentCodeToUpdateOrDelete = this.generateStudentCode();

        Student studentToUpdateOrDelete = null;
        for (Student studentFromList : dataVO.getStudentList()) {
            if (studentFromList.getCode() == studentCodeToUpdateOrDelete) {
                studentToUpdateOrDelete = studentFromList;
                break;
            }
        }

        if (studentToUpdateOrDelete == null) {
            UTILS.printConsoleMessage(UTILS.CODE_NOT_FOUND_ERROR_MESSAGE);
        }

        return studentToUpdateOrDelete;
    }

    private void saveStudent(DataVO dataVO) {
        SystemBO systemBO = new SystemBO();
        systemBO.writeData(SystemBO.DATA_JSON_FILE_NAME, dataVO);
    }
}
