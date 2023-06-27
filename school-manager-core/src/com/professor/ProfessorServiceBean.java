package com.professor;

import com.entity.Professor;
import com.objects.DataVO;
import com.system.SystemBO;
import com.utils.UTILS;

import java.util.List;

public class ProfessorServiceBean implements ProfessorService {

    @Override
    public void insertNewProfessor(DataVO dataVO) {
        Professor professor = this.createNewProfessor();

        if (this.professorAlreadyExists(professor, dataVO.getProfessorList())) {
            UTILS.showMessageDialog(UTILS.ALREADY_EXISTS_ERROR_MESSAGE);
            return;
        }

        dataVO.getProfessorList().add(dataVO.getProfessorList().size(), professor);
        this.saveProfessor(dataVO);

        UTILS.showMessageDialog(UTILS.INSERT_NEW_SUCCESS_MESSAGE);
    }

    @Override
    public void viewProfessorList(DataVO dataVO) {
        if (dataVO.getProfessorList() == null || dataVO.getProfessorList().isEmpty()) {
            UTILS.showMessageDialog(UTILS.EMPTY_LIST_ERROR_MESSAGE);
            return;
        }

        String string = "";

        for (Professor professor : dataVO.getProfessorList()) {
            string += String.format("%nCÓDIGO: %s%nNOME: %s%nIDADE: %s%nCPF: %s%n", professor.getCode(), professor.getName().toUpperCase(), professor.getAge(),professor. getFederalIdentification());
        }

        UTILS.showMessageDialogWithoutTranslate(string);
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
            UTILS.showMessageDialog(UTILS.ALREADY_EXISTS_ERROR_MESSAGE);
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
        UTILS.showMessageDialog(UTILS.DELETE_RECORD_SUCCESS_MESSAGE);
    }

    @Override
    public void clearProfessorList(DataVO dataVO) {
        int choice = UTILS.showInputIntegerDialog(UTILS.DELETE_ALL_RECORDS_QUESTION_MESSAGE);

        if (choice != 1) {
            UTILS.showMessageDialog(UTILS.DELETE_ALL_RECORDS_CANCEL_MESSAGE);
            return;
        }

        dataVO.getProfessorList().clear();
        this.saveProfessor(dataVO);
        UTILS.showMessageDialog(UTILS.DELETE_ALL_RECORDS_SUCCESS_MESSAGE);
    }

    private Professor createNewProfessor() {
        int professorCode = this.generateProfessorCode();
        String professorName = this.generateProfessorName();
        int professorAge = this.generateProfessorAge();
        long professorFederalIdentification = this.generateProfessorFederalIdentification();

        return new Professor(professorCode, professorName, professorAge, professorFederalIdentification);
    }

    private int generateProfessorCode() {
        return UTILS.showInputIntegerDialog(UTILS.CODE);
    }

    private String generateProfessorName() {
        return UTILS.showInputStringDialog(UTILS.NAME);
    }

    private int generateProfessorAge() {
        return UTILS.showInputIntegerDialog(UTILS.AGE);
    }

    private long generateProfessorFederalIdentification() {
        return UTILS.showInputIntegerDialog(UTILS.FEDERAL_IDENTIFICATION);
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

    public Professor findProfessor(DataVO dataVO) {
        int professorCodeToUpdateOrDelete = this.generateProfessorCode();

        Professor professorToUpdateOrDelete = null;
        for (Professor professorFromList : dataVO.getProfessorList()) {
            if (professorFromList.getCode() == professorCodeToUpdateOrDelete) {
                professorToUpdateOrDelete = professorFromList;
                break;
            }
        }

        if (professorToUpdateOrDelete == null) {
            UTILS.showMessageDialog(UTILS.CODE_NOT_FOUND_ERROR_MESSAGE);
        }

        return professorToUpdateOrDelete;
    }

    private void saveProfessor(DataVO dataVO) {
        SystemBO systemBO = new SystemBO();
        systemBO.writeData(SystemBO.DATA_JSON_FILE_NAME, dataVO);
    }
}
