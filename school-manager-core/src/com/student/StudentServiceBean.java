package com.student;

import com.objects.DataVO;
import com.objects.Student;
import com.utils.UTILS;

import java.util.List;
import java.util.Scanner;

public class StudentServiceBean implements StudentService {

    @Override
    public void insertNewStudent(DataVO dataVO) {
        UTILS.printHeaderManager();
        System.out.println("INSERINDO NOVO ESTUDANTE");

        int studentCode = this.generateStudentCode();
        String studentName = this.generateStudentName();
        int studentAge = this.generateStudentAge();
        long studentFederalIdentification = this.generateStudentFederalIdentification();

        if (!this.studentAlreadyExists(studentCode, studentFederalIdentification, studentName, dataVO.getStudentList())) {
            Student student = new Student(studentCode, studentName, studentAge, studentFederalIdentification);
            dataVO.getStudentList().add(dataVO.getStudentList().size(), student);

            this.saveStudent(dataVO);

            System.out.printf(("\nO estudante %s foi adicionado com sucesso.") + "%n", student.getName().toUpperCase());
        } else {
            System.out.println("\nNão foi possível adicionar. Estudante já cadastrado no sistema.");
        }
    }

    @Override
    public void viewStudentList(DataVO dataVO) {
        if (dataVO.getStudentList() != null && !dataVO.getStudentList().isEmpty()) {
            for (Student student : dataVO.getStudentList()) {
                System.out.println(" ");
                System.out.println("CÓDIGO: " + student.getCode());
                System.out.println("NOME: " + student.getName().toUpperCase());
                System.out.println("IDADE: " + student.getAge());
                System.out.println("CPF: " + student.getFederalIdentification());
            }
        } else {
            System.out.println("\nNão há estudantes cadastrados!\n");
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

        if (!this.studentNameAlreadyExists(studentName, dataVO.getStudentList()) || !this.studentFederalIdentificationAlreadyExists(studentFederalIdentification, dataVO.getStudentList())) {
            studentToUpdate.setName(studentName);
            studentToUpdate.setAge(studentAge);
            studentToUpdate.setFederalIdentification(studentFederalIdentification);
        } else {
            System.out.println("\nNão foi possível adicionar. Estudante já cadastrado no sistema.");
        }
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
        System.out.printf(("Estudante %s removido com sucesso.") + "%n", studentToDelete.getName().toUpperCase());
    }

    @Override
    public void clearStudentList(DataVO dataVO) {
        System.out.println("\nConfirma exclusão de todos os registros? Esta ação não poderá ser desfeita. (1) CONFIRMAR EXCLUSÃO / (2) CANCELAR");

        int choice = UTILS.scannerIntValue();

        if(choice == 1) {
            dataVO.getStudentList().clear();
            System.out.println("Todos os registros foram excluídos com sucesso.");
        } else {
            System.out.println("A exclusão foi cancelada.");
        }
    }

    private void saveStudent(DataVO dataVO) {
        StudentBO.writeStudent("c:\\temp\\arqObjs.txt", dataVO.getStudentList().get(0));
        StudentBO.readStudent("c:\\temp\\arqObjs.txt");
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

    public boolean studentAlreadyExists(int newStudentCode, Long newFederalIdentification, String newStudentName, List<Student> studentList) {
        for (Student student : studentList) {
            if (student.getCode() == newStudentCode || student.getFederalIdentification().equals(newFederalIdentification) || student.getName().equals(newStudentName)) {
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
        int studentCode = this.generateStudentCode();

        Student studentToUpdate = null;
        for (Student student : dataVO.getStudentList()) {
            if (student.getCode() == studentCode) {
                studentToUpdate = student;
                break;
            }
        }

        if (studentToUpdate == null) {
            System.out.printf(("Estudante com o código %s não encontrado") + "%n", studentCode);
        }

        return studentToUpdate;
    }
}
