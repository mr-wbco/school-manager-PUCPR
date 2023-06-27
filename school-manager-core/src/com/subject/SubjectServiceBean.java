package com.subject;

import com.entity.Subject;
import com.objects.DataVO;
import com.system.SystemBO;
import com.utils.UTILS;

import java.util.List;

public class SubjectServiceBean implements SubjectService {

    @Override
    public void insertNewSubject(DataVO dataVO) {
        Subject subject = this.createNewSubject();

        if (this.subjectAlreadyExists(subject, dataVO.getSubjectList())) {
            UTILS.showMessageDialog(UTILS.ALREADY_EXISTS_ERROR_MESSAGE);
            return;
        }

        dataVO.getSubjectList().add(dataVO.getSubjectList().size(), subject);
        this.saveSubject(dataVO);

        UTILS.showMessageDialog(UTILS.INSERT_NEW_SUCCESS_MESSAGE);
    }

    @Override
    public void viewSubjectList(DataVO dataVO) {
        if (dataVO.getSubjectList() == null || dataVO.getSubjectList().isEmpty()) {
            UTILS.showMessageDialog(UTILS.EMPTY_LIST_ERROR_MESSAGE);
            return;
        }

        StringBuilder string = new StringBuilder();

        for (Subject subject : dataVO.getSubjectList()) {
            string.append(String.format("%nCÓDIGO: %s%nNOME: %s%n", subject.getSubjectCode(), subject.getSubjectName().toUpperCase()));
        }

        UTILS.showMessageDialogWithoutTranslate(string.toString());
    }

    @Override
    public void updateSubject(DataVO dataVO) {
        this.viewSubjectList(dataVO);

        if (dataVO.getSubjectList() == null || dataVO.getSubjectList().isEmpty()) {
            return;
        }

        Subject subjectToUpdate = this.findSubject(dataVO);
        if (subjectToUpdate == null) {
            return;
        }

        String subjectName = this.generateSubjectName();

        if (this.subjectNameAlreadyExists(subjectName, dataVO.getSubjectList())) {
            UTILS.showMessageDialog(UTILS.ALREADY_EXISTS_ERROR_MESSAGE);
            return;
        }

        subjectToUpdate.setSubjectName(subjectName);
        this.saveSubject(dataVO);
    }

    @Override
    public void deleteOneSubject(DataVO dataVO) {
        this.viewSubjectList(dataVO);

        if (dataVO.getSubjectList() == null || dataVO.getSubjectList().isEmpty()) {
            return;
        }

        Subject subjectToDelete = this.findSubject(dataVO);
        if (subjectToDelete == null) {
            return;
        }

        dataVO.getSubjectList().remove(subjectToDelete);
        this.saveSubject(dataVO);
        UTILS.showMessageDialog(UTILS.DELETE_RECORD_SUCCESS_MESSAGE);
    }

    @Override
    public void clearSubjectList(DataVO dataVO) {
        int choice = UTILS.showInputIntegerDialog(UTILS.DELETE_ALL_RECORDS_QUESTION_MESSAGE);

        if (choice != 1) {
            UTILS.showMessageDialog(UTILS.DELETE_ALL_RECORDS_CANCEL_MESSAGE);
            return;
        }

        dataVO.getSubjectList().clear();
        this.saveSubject(dataVO);
        UTILS.showMessageDialog(UTILS.DELETE_ALL_RECORDS_SUCCESS_MESSAGE);
    }

    private Subject createNewSubject() {
        int subjectCode = this.generateSubjectCode();
        String subjectName = this.generateSubjectName();
        return new Subject(subjectCode, subjectName);
    }

    private int generateSubjectCode() {
        return UTILS.showInputIntegerDialog(UTILS.CODE);
    }

    private String generateSubjectName() {
        return UTILS.showInputStringDialog(UTILS.NAME);
    }

    public boolean subjectAlreadyExists(Subject newSubject, List<Subject> subjectList) {
        for (Subject subjectFromList : subjectList) {
            if (subjectFromList.getSubjectCode() == newSubject.getSubjectCode() || subjectFromList.getSubjectName().equals(newSubject.getSubjectName())) {
                return true;
            }
        }

        return false;
    }

    private boolean subjectNameAlreadyExists(String newSubjectName, List<Subject> subjectList) {
        for (Subject subject : subjectList) {
            if (subject.getSubjectName().equals(newSubjectName)) {
                return true;
            }
        }

        return false;
    }

    public Subject findSubject(DataVO dataVO) {
        int subjectCodeToUpdateOrDelete = this.generateSubjectCode();

        Subject subjectToUpdateOrDelete = null;
        for (Subject subjectFromList : dataVO.getSubjectList()) {
            if (subjectFromList.getSubjectCode() == subjectCodeToUpdateOrDelete) {
                subjectToUpdateOrDelete = subjectFromList;
                break;
            }
        }

        if (subjectToUpdateOrDelete == null) {
            UTILS.showMessageDialog(UTILS.CODE_NOT_FOUND_ERROR_MESSAGE);
        }

        return subjectToUpdateOrDelete;
    }

    private void saveSubject(DataVO dataVO) {
        SystemBO systemBO = new SystemBO();
        systemBO.writeData(SystemBO.DATA_JSON_FILE_NAME, dataVO);
    }
}
