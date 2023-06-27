package com.student;

import com.entity.Student;
import com.enums.MainMenuOptionsEnum;
import com.objects.DataVO;
import com.system.SystemBO;
import com.utils.UTILS;

import java.util.List;

public class StudentServiceBean implements StudentService {

    @Override
    public void insertNewStudent(MainMenuOptionsEnum mainMenuOptionsEnum, DataVO dataVO) {
        Student student = this.createNewStudent();

        if (this.studentAlreadyExists(student, dataVO.getStudentList())) {
            UTILS.showMessageDialog(UTILS.ALREADY_EXISTS_ERROR_MESSAGE);
            return;
        }

        dataVO.getStudentList().add(dataVO.getStudentList().size(), student);
        this.saveStudent(dataVO);

        UTILS.showMessageDialog(UTILS.INSERT_NEW_SUCCESS_MESSAGE);
    }

    @Override
    public void viewStudentList(DataVO dataVO) {
        if (dataVO.getStudentList() == null || dataVO.getStudentList().isEmpty()) {
            UTILS.showMessageDialog(UTILS.EMPTY_LIST_ERROR_MESSAGE);
            return;
        }

        String string = "";

        for (Student student : dataVO.getStudentList()) {
            string += String.format("%nCÓDIGO: %s%nNOME: %s%nIDADE: %s%nCPF: %s%n", student.getCode(), student.getName().toUpperCase(), student.getAge(),student. getFederalIdentification());

            if (student.getEnrolling() != null) {
                string += String.format("MATRÍCULA: %s%n", student.getEnrolling().getEnrollingCode());
            }

            if (student.getClassroom() != null) {
                string += String.format("TURMA: %s%n", student.getClassroom().getClassroomCode());
            }
        }

        UTILS.showMessageDialogWithoutTranslate(string);
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
            UTILS.showMessageDialog(UTILS.ALREADY_EXISTS_ERROR_MESSAGE);
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
        UTILS.showMessageDialog(UTILS.DELETE_RECORD_SUCCESS_MESSAGE);
    }

    @Override
    public void clearStudentList(DataVO dataVO) {
        int choice = UTILS.showInputIntegerDialog(UTILS.DELETE_ALL_RECORDS_QUESTION_MESSAGE);

        if (choice != 1) {
            UTILS.showMessageDialog(UTILS.DELETE_ALL_RECORDS_CANCEL_MESSAGE);
            return;
        }

        dataVO.getStudentList().clear();
        this.saveStudent(dataVO);
        UTILS.showMessageDialog(UTILS.DELETE_ALL_RECORDS_SUCCESS_MESSAGE);
    }

    private Student createNewStudent() {
        int studentCode = this.generateStudentCode();
        String studentName = this.generateStudentName();
        int studentAge = this.generateStudentAge();
        long studentFederalIdentification = this.generateStudentFederalIdentification();

        return new Student(studentCode, studentName, studentAge, studentFederalIdentification);
    }

    private int generateStudentCode() {
        return UTILS.showInputIntegerDialog(UTILS.CODE);
    }

    private String generateStudentName() {
        return UTILS.showInputStringDialog(UTILS.NAME);
    }

    private int generateStudentAge() {
        return UTILS.showInputIntegerDialog(UTILS.AGE);
    }

    private long generateStudentFederalIdentification() {
        return UTILS.showInputIntegerDialog(UTILS.FEDERAL_IDENTIFICATION);
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

    public Student findStudent(DataVO dataVO) {
        int studentCodeToUpdateOrDelete = this.generateStudentCode();

        Student studentToUpdateOrDelete = null;
        for (Student studentFromList : dataVO.getStudentList()) {
            if (studentFromList.getCode() == studentCodeToUpdateOrDelete) {
                studentToUpdateOrDelete = studentFromList;
                break;
            }
        }

        if (studentToUpdateOrDelete == null) {
            UTILS.showMessageDialog(UTILS.CODE_NOT_FOUND_ERROR_MESSAGE);
        }

        return studentToUpdateOrDelete;
    }

    private void saveStudent(DataVO dataVO) {
        SystemBO systemBO = new SystemBO();
        systemBO.writeData(SystemBO.DATA_JSON_FILE_NAME, dataVO);
    }
}
