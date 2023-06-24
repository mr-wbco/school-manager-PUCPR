package com.professor;

import com.objects.DataVO;
import com.entity.Professor;
import com.system.SystemBO;
import com.utils.UTILS;

import java.util.List;
import java.util.Scanner;

public class ProfessorServiceBean implements ProfessorService {

    @Override
    public void insertNewProfessor(DataVO dataVO) {
        UTILS.printHeaderManager();
        UTILS.printConsoleMessage(UTILS.INSERT_NEW_INFORMATION_MESSAGE);

        Professor professor = this.createNewProfessor();

        if (this.professorAlreadyExists(professor, dataVO.getProfessorList())) {
            UTILS.printConsoleMessage(UTILS.ALREADY_EXISTS_ERROR_MESSAGE);
            return;
        }

        dataVO.getProfessorList().add(dataVO.getProfessorList().size(), professor);
        this.saveProfessor(dataVO);

        UTILS.printConsoleMessage(UTILS.INSERT_NEW_SUCCESS_MESSAGE);
    }

    @Override
    public void viewProfessorList(DataVO dataVO) {
        if (dataVO.getProfessorList() == null || dataVO.getProfessorList().isEmpty()) {
            UTILS.printConsoleMessage(UTILS.EMPTY_LIST_ERROR_MESSAGE);
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

        if (this.professorNameAlreadyExists(studentName, dataVO.getProfessorList()) || this.professorFederalIdentificationAlreadyExists(studentFederalIdentification, dataVO.getProfessorList())) {
            UTILS.printConsoleMessage(UTILS.ALREADY_EXISTS_ERROR_MESSAGE);
            return;
        }

        professorToUpdate.setName(studentName);
        professorToUpdate.setAge(studentAge);
        professorToUpdate.setFederalIdentification(studentFederalIdentification);
        this.saveProfessor(dataVO);
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
        UTILS.printConsoleMessage(UTILS.DELETE_RECORD_SUCCESS_MESSAGE);
    }

    @Override
    public void clearProfessorList(DataVO dataVO) {
        UTILS.printConsoleMessage(UTILS.DELETE_ALL_RECORDS_QUESTION_MESSAGE);

        int choice = UTILS.scannerIntValue();

        if (choice != 1) {
            UTILS.printConsoleMessage(UTILS.DELETE_ALL_RECORDS_CANCEL_MESSAGE);
            return;
        }

        dataVO.getProfessorList().clear();
        this.saveProfessor(dataVO);
        UTILS.printConsoleMessage(UTILS.DELETE_ALL_RECORDS_SUCCESS_MESSAGE);
    }

    private Professor createNewProfessor() {
        int professorCode = this.generateProfessorCode();
        String professorName = this.generateProfessorName();
        int professorAge = this.generateProfessorAge();
        long professorFederalIdentification = this.generateProfessorFederalIdentification();

        return new Professor(professorCode, professorName, professorAge, professorFederalIdentification);
    }

    private int generateProfessorCode() {
        UTILS.printConsoleMessage(UTILS.CODE);
        return UTILS.scannerIntValue();
    }

    private String generateProfessorName() {
        UTILS.printConsoleMessage(UTILS.NAME);
        return new Scanner(System.in).nextLine();
    }

    private int generateProfessorAge() {
        UTILS.printConsoleMessage(UTILS.AGE);
        return UTILS.scannerIntValue();
    }

    private long generateProfessorFederalIdentification() {
        UTILS.printConsoleMessage(UTILS.FEDERAL_IDENTIFICATION);
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
            UTILS.printConsoleMessage(UTILS.CODE_NOT_FOUND_ERROR_MESSAGE);
        }

        return professorToUpdateOrDelete;
    }

    private void saveProfessor(DataVO dataVO) {
        SystemBO systemBO = new SystemBO();
        systemBO.writeData(SystemBO.DATA_JSON_FILE_NAME, dataVO);
    }
}
