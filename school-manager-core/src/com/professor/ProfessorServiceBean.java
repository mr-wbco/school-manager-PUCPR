package com.professor;

import com.objects.DataVO;
import com.objects.Professor;
import com.system.SystemBO;
import com.utils.UTILS;

import java.util.List;
import java.util.Scanner;

public class ProfessorServiceBean implements ProfessorService {

    @Override
    public void insertNewProfessor(DataVO dataVO) {
        UTILS.printHeaderManager();
        System.out.println("INSERINDO NOVO PROFESSOR");

        Professor professor = this.createNewProfessor();

        if (this.professorAlreadyExists(professor, dataVO.getProfessorList())) {
            System.out.println("\nNão foi possível adicionar. Professor já cadastrado no sistema.");
            return;
        }

        dataVO.getProfessorList().add(dataVO.getProfessorList().size(), professor);
        this.saveProfessor(dataVO);

        System.out.printf(("\nO professor %s foi adicionado com sucesso.") + "%n", professor.getName().toUpperCase());
    }

    @Override
    public void viewProfessorList(DataVO dataVO) {
        if (dataVO.getProfessorList() == null || dataVO.getProfessorList().isEmpty()) {
            System.out.println("\nNão há professores cadastrados!\n");
            return;
        }

        for (Professor professor : dataVO.getProfessorList()) {
            System.out.println(" ");
            System.out.println("CÓDIGO: " + professor.getCode());
            System.out.println("NOME: " + professor.getName().toUpperCase());
            System.out.println("IDADE: " + professor.getAge());
            System.out.println("CPF: " + professor.getFederalIdentification());
        }
    }

    @Override
    public void updateProfessor(DataVO dataVO) {
        this.viewProfessorList(dataVO);

        if (dataVO.getProfessorList() == null || dataVO.getProfessorList().isEmpty()) {
            return;
        }

        Professor professorToUpdate = this.findProfessor(dataVO);
        if (professorToUpdate == null) {
            return;
        }

        String studentName = this.generateProfessorName();
        int studentAge = this.generateProfessorAge();
        long studentFederalIdentification = this.generateProfessorFederalIdentification();

        if (!this.professorNameAlreadyExists(studentName, dataVO.getProfessorList()) || !this.professorFederalIdentificationAlreadyExists(studentFederalIdentification, dataVO.getProfessorList())) {
            professorToUpdate.setName(studentName);
            professorToUpdate.setAge(studentAge);
            professorToUpdate.setFederalIdentification(studentFederalIdentification);
        } else {
            System.out.println("\nNão foi possível adicionar. Professor já cadastrado no sistema.");
        }
    }

    @Override
    public void deleteOneProfessor(DataVO dataVO) {
        this.viewProfessorList(dataVO);

        if (dataVO.getProfessorList() == null || dataVO.getProfessorList().isEmpty()) {
            return;
        }

        Professor professorToDelete = this.findProfessor(dataVO);
        if (professorToDelete == null) {
            return;
        }

        dataVO.getProfessorList().remove(professorToDelete);
        this.saveProfessor(dataVO);
        System.out.printf(("Professor %s removido com sucesso.") + "%n", professorToDelete.getName().toUpperCase());
    }

    @Override
    public void clearProfessorList(DataVO dataVO) {
        System.out.println("\nConfirma exclusão de todos os registros? Esta ação não poderá ser desfeita. (1) CONFIRMAR EXCLUSÃO / (2) CANCELAR");

        int choice = UTILS.scannerIntValue();

        if (choice != 1) {
            System.out.println("A exclusão foi cancelada.");
            return;
        }

        dataVO.getProfessorList().clear();
        this.saveProfessor(dataVO);
        System.out.println("Todos os registros foram excluídos com sucesso.");
    }

    private Professor createNewProfessor() {
        int professorCode = this.generateProfessorCode();
        String professorName = this.generateProfessorName();
        int professorAge = this.generateProfessorAge();
        long professorFederalIdentification = this.generateProfessorFederalIdentification();

        return new Professor(professorCode, professorName, professorAge, professorFederalIdentification);
    }

    private int generateProfessorCode() {
        System.out.println("\nDigite o código do professor (somente números):");
        return UTILS.scannerIntValue();
    }

    private String generateProfessorName() {
        System.out.println("\nDigite o nome do professor:");
        return new Scanner(System.in).nextLine();
    }

    private int generateProfessorAge() {
        System.out.println("\nDigite a idade do professor:");
        return UTILS.scannerIntValue();
    }

    private long generateProfessorFederalIdentification() {
        System.out.println("\nDigite o CPF do professor:");
        return UTILS.scannerLongValue();
    }

    public boolean professorAlreadyExists(Professor newProfessor, List<Professor> professorList) {
        for (Professor professorFromList : professorList) {
            if (professorFromList.getCode() == newProfessor.getCode() || professorFromList.getName().equals(newProfessor.getName()) || professorFromList.getFederalIdentification().equals(newProfessor.getFederalIdentification())) {
                return true;
            }
        }

        return false;
    }

    private boolean professorNameAlreadyExists(String newProfessorName, List<Professor> professorList) {
        for (Professor professor : professorList) {
            if (professor.getName().equals(newProfessorName)) {
                return true;
            }
        }

        return false;
    }

    private boolean professorFederalIdentificationAlreadyExists(Long newFederalIdentification, List<Professor> professorList) {
        for (Professor professor : professorList) {
            if (professor.getFederalIdentification().equals(newFederalIdentification)) {
                return true;
            }
        }

        return false;
    }

    private Professor findProfessor(DataVO dataVO) {
        int professorCodeToUpdateOrDelete = this.generateProfessorCode();

        Professor professorToUpdateOrDelete = null;
        for (Professor professorFromList : dataVO.getProfessorList()) {
            if (professorFromList.getCode() == professorCodeToUpdateOrDelete) {
                professorToUpdateOrDelete = professorFromList;
                break;
            }
        }

        if (professorToUpdateOrDelete == null) {
            System.out.printf(("Professor com o código %s não encontrado") + "%n", professorCodeToUpdateOrDelete);
        }

        return professorToUpdateOrDelete;
    }

    private void saveProfessor(DataVO dataVO) {
        SystemBO systemBO = new SystemBO();
        systemBO.writeData(SystemBO.DATA_JSON_FILE_NAME, dataVO);
    }
}
