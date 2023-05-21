package com.student;

import com.enums.ActionsMenuOptionsEnum;
import com.enums.MainMenuOptionsEnum;
import com.objects.DataVO;
import com.objects.Student;
import com.utils.UTILS;

import java.util.List;
import java.util.Scanner;

public class StudentServiceBean implements StudentService {

    @Override
    public void insertNewStudent(MainMenuOptionsEnum mainMenuOptionsEnum, ActionsMenuOptionsEnum actionsMenuOptionsEnum, DataVO dataVO) {
        UTILS.printHeaderManager();
        System.out.println("INSERINDO NOVO ESTUDANTE");

        int studentCode = this.generateStudentCode();
        String studentName = this.generateStudentName();
        int studentAge = this.generateStudentAge();
        long studentFederalIdentification = this.generateStudentFederalIdentification();

        if (!this.studentAlreadyExists(studentCode, studentFederalIdentification, studentName, dataVO.getStudentList())) {
            Student student = new Student();
            student.setCode(studentCode);
            student.setName(studentName);
            student.setAge(studentAge);
            student.setFederalIdentification(studentFederalIdentification);

            dataVO.getStudentList().add(dataVO.getStudentList().size(), student);
            System.out.printf(("\nO estudante %s foi adicionado com sucesso.") + "%n", student.getName().toUpperCase());
        } else {
            System.out.println("\nNão foi possível adicionar. Estudante já cadastrado no sistema.");
        }
    }

    private int generateStudentCode() {
        Scanner scanner = new Scanner(System.in);

        int studentCode = 0;
        boolean validCode = false;
        while (!validCode) {
            System.out.println("\nDigite a código do estudante (somente números):");
            try {
                studentCode = scanner.nextInt();
                validCode = true;
            } catch (Exception e) {
                System.out.println("\nPor favor, digite um número válido!");
                studentCode = scanner.nextInt();
            }
        }

        return studentCode;
    }

    private String generateStudentName() {
        System.out.println("\nDigite o nome do estudante:");
        return new Scanner(System.in).nextLine();
    }

    private int generateStudentAge() {
        Scanner scanner = new Scanner(System.in);

        int studentAge = 0;
        boolean validAge = false;
        while (!validAge) {
            System.out.println("\nDigite a idade do estudante:");
            try {
                studentAge = scanner.nextInt();
                validAge = true;
            } catch (Exception e) {
                System.out.println("\nPor favor, digite um número válido!");
                studentAge = scanner.nextInt();
            }
        }

        return studentAge;
    }

    private long generateStudentFederalIdentification() {
        Scanner scanner = new Scanner(System.in);

        long studentFederalIdentification = 0;
        boolean validIdentification = false;
        while (!validIdentification) {
            System.out.println("\nDigite o CPF do estudante:");
            try {
                studentFederalIdentification = scanner.nextLong();
                validIdentification = true;
            } catch (Exception e) {
                System.out.println("\nPor favor, digite um número válido!");
                studentFederalIdentification = scanner.nextInt();
            }
        }

        return studentFederalIdentification;
    }

    @Override
    public boolean studentAlreadyExists(int newStudentCode, Long newFederalIdentification, String newStudentName, List<Student> studentList) {
        for (Student student : studentList) {
            if (student.getCode() == newStudentCode || student.getFederalIdentification().equals(newFederalIdentification) || student.getName().equals(newStudentName)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public void viewStudentList(MainMenuOptionsEnum mainMenuOptionsEnum, ActionsMenuOptionsEnum actionsMenuOptionsEnum, DataVO dataVO) {
        if (dataVO.getStudentList() != null && !dataVO.getStudentList().isEmpty()) {
            for (Student student : dataVO.getStudentList()) {
                System.out.println(" ");
                System.out.println("CÓDIGO: " + student.getCode());
                System.out.println("NOME: " + student.getName().toUpperCase());
                System.out.println("IDADE: " + student.getAge());
                System.out.println("CPF: " + student.getFederalIdentification());
            }
        } else {
            System.out.println(" ");
            System.out.println("Não há estudantes cadastrados!");
            System.out.println(" ");
        }
    }

    @Override
    public void updateStudent(MainMenuOptionsEnum mainMenuOptionsEnum, ActionsMenuOptionsEnum actionsMenuOptionsEnum, DataVO dataVO) {
        this.viewStudentList(mainMenuOptionsEnum, actionsMenuOptionsEnum, dataVO);
    }
}
