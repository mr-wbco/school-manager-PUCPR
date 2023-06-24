package com.student;

import com.objects.DataVO;
import com.objects.Student;
import com.system.SystemBO;
import com.utils.UTILS;

import java.util.List;
import java.util.Scanner;

public class StudentServiceBean implements StudentService {

    @Override
    public void insertNewStudent(DataVO dataVO) {
        UTILS.printHeaderManager();
        System.out.println("INSERINDO NOVO ESTUDANTE");

        Student student = this.createNewStudent();

        if (this.studentAlreadyExists(student, dataVO.getStudentList())) {
            System.out.println("\nNão foi possível adicionar. Estudante já cadastrado no sistema.");
            return;
        }

        dataVO.getStudentList().add(dataVO.getStudentList().size(), student);
        this.saveStudent(dataVO);

        System.out.printf(("\nO estudante %s foi adicionado com sucesso.") + "%n", student.getName().toUpperCase());
    }

    @Override
    public void viewStudentList(DataVO dataVO) {
        if (dataVO.getStudentList() == null || dataVO.getStudentList().isEmpty()) {
            System.out.println("\nNão há estudantes cadastrados!\n");
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
            System.out.println("\nNão foi possível adicionar. Estudante já cadastrado no sistema.");
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
        System.out.printf(("Estudante %s removido com sucesso.") + "%n", studentToDelete.getName().toUpperCase());
    }

    @Override
    public void clearStudentList(DataVO dataVO) {
        System.out.println("\nConfirma exclusão de todos os registros? Esta ação não poderá ser desfeita. (1) CONFIRMAR EXCLUSÃO / (2) CANCELAR");

        int choice = UTILS.scannerIntValue();

        if (choice != 1){
            System.out.println("A exclusão foi cancelada.");
            return;
        }

        dataVO.getStudentList().clear();
        this.saveStudent(dataVO);
        System.out.println("Todos os registros foram excluídos com sucesso.");
    }

    private Student createNewStudent() {
        int studentCode = this.generateStudentCode();
        String studentName = this.generateStudentName();
        int studentAge = this.generateStudentAge();
        long studentFederalIdentification = this.generateStudentFederalIdentification();

        return new Student(studentCode, studentName, studentAge, studentFederalIdentification);
    }

    private int generateStudentCode() {
        System.out.println("\nDigite o código do estudante (somente números):");
        return UTILS.scannerIntValue();
    }

    private String generateStudentName() {
        System.out.println("\nDigite o nome do estudante:");
        return new Scanner(System.in).nextLine();
    }

    private int generateStudentAge() {
        System.out.println("\nDigite a idade do estudante:");
        return UTILS.scannerIntValue();
    }

    private long generateStudentFederalIdentification() {
        System.out.println("\nDigite o CPF do estudante:");
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
            System.out.printf(("Estudante com o código %s não encontrado") + "%n", studentCodeToUpdateOrDelete);
        }

        return studentToUpdateOrDelete;
    }

    private void saveStudent(DataVO dataVO) {
        SystemBO systemBO = new SystemBO();
        systemBO.writeData(SystemBO.DATA_JSON_FILE_NAME, dataVO);
    }
}
