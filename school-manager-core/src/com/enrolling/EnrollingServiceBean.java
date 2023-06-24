package com.enrolling;

import com.entity.Enrolling;
import com.objects.DataVO;
import com.system.SystemBO;
import com.utils.UTILS;

import java.util.List;
import java.util.Scanner;

public class EnrollingServiceBean implements EnrollingService {

    @Override
    public void insertNewEnrolling(DataVO dataVO) {
        UTILS.printHeaderManager();
        UTILS.printConsoleMessage(UTILS.INSERT_NEW_INFORMATION_MESSAGE);

        Enrolling enrolling = this.createNewEnrolling();

        if (this.enrollingAlreadyExists(enrolling, dataVO.getEnrollingList())) {
            UTILS.printConsoleMessage(UTILS.ALREADY_EXISTS_ERROR_MESSAGE);
            return;
        }

        dataVO.getEnrollingList().add(dataVO.getEnrollingList().size(), enrolling);
        this.saveEnrolling(dataVO);

        UTILS.printConsoleMessage(UTILS.INSERT_NEW_SUCCESS_MESSAGE);

    }

    @Override
    public void viewEnrollingList(DataVO dataVO) {
        if (dataVO.getEnrollingList() == null || dataVO.getEnrollingList().isEmpty()) {
            UTILS.printConsoleMessage(UTILS.EMPTY_LIST_ERROR_MESSAGE);
            return;
        }

        for (Enrolling enrolling : dataVO.getEnrollingList()) {
            System.out.println(" ");
            System.out.println("CÓDIGO: " + enrolling.getEnrollingCode());
            System.out.println("NOME: " + enrolling.getEnrollingName().toUpperCase());
        }
    }

    @Override
    public void updateEnrolling(DataVO dataVO) {
        this.viewEnrollingList(dataVO);

        if (dataVO.getEnrollingList() == null || dataVO.getEnrollingList().isEmpty()) {
            return;
        }

        Enrolling enrollingToUpdate = this.findEnrolling(dataVO);
        if (enrollingToUpdate == null) {
            return;
        }

        String enrollingName = this.generateEnrollingName();

        if (this.enrollingNameAlreadyExists(enrollingName, dataVO.getEnrollingList())) {
            UTILS.printConsoleMessage(UTILS.ALREADY_EXISTS_ERROR_MESSAGE);
            return;
        }

        enrollingToUpdate.setEnrollingName(enrollingName);
        this.saveEnrolling(dataVO);
    }

    @Override
    public void deleteOneEnrolling(DataVO dataVO) {
        this.viewEnrollingList(dataVO);

        if (dataVO.getEnrollingList() == null || dataVO.getEnrollingList().isEmpty()) {
            return;
        }

        Enrolling enrollingToDelete = this.findEnrolling(dataVO);
        if (enrollingToDelete == null) {
            return;
        }

        dataVO.getEnrollingList().remove(enrollingToDelete);
        this.saveEnrolling(dataVO);
        UTILS.printConsoleMessage(UTILS.DELETE_RECORD_SUCCESS_MESSAGE);
    }

    @Override
    public void clearEnrollingList(DataVO dataVO) {
        UTILS.printConsoleMessage(UTILS.DELETE_ALL_RECORDS_QUESTION_MESSAGE);

        int choice = UTILS.scannerIntValue();

        if (choice != 1) {
            UTILS.printConsoleMessage(UTILS.DELETE_ALL_RECORDS_CANCEL_MESSAGE);
            return;
        }

        dataVO.getEnrollingList().clear();
        this.saveEnrolling(dataVO);
        UTILS.printConsoleMessage(UTILS.DELETE_ALL_RECORDS_SUCCESS_MESSAGE);
    }

    private Enrolling createNewEnrolling() {
        int enrollingCode = this.generateEnrollingCode();
        String enrollingName = this.generateEnrollingName();

        return new Enrolling(enrollingCode, enrollingName);
    }

    private int generateEnrollingCode() {
        UTILS.printConsoleMessage(UTILS.CODE);
        return UTILS.scannerIntValue();
    }

    private String generateEnrollingName() {
        UTILS.printConsoleMessage(UTILS.NAME);
        return new Scanner(System.in).nextLine();
    }

    public boolean enrollingAlreadyExists(Enrolling newEnrolling, List<Enrolling> enrollingList) {
        for (Enrolling enrollingFromList : enrollingList) {
            if (enrollingFromList.getEnrollingCode() == newEnrolling.getEnrollingCode() || enrollingFromList.getEnrollingName().equals(newEnrolling.getEnrollingName())) {
                return true;
            }
        }

        return false;
    }

    private boolean enrollingNameAlreadyExists(String newEnrollingName, List<Enrolling> enrollingList) {
        for (Enrolling enrolling : enrollingList) {
            if (enrolling.getEnrollingName().equals(newEnrollingName)) {
                return true;
            }
        }

        return false;
    }

    private Enrolling findEnrolling(DataVO dataVO) {
        int enrollingCodeToUpdateOrDelete = this.generateEnrollingCode();

        Enrolling enrollingToUpdateOrDelete = null;
        for (Enrolling enrollingFromList : dataVO.getEnrollingList()) {
            if (enrollingFromList.getEnrollingCode() == enrollingCodeToUpdateOrDelete) {
                enrollingToUpdateOrDelete = enrollingFromList;
                break;
            }
        }

        if (enrollingToUpdateOrDelete == null) {
            UTILS.printConsoleMessage(UTILS.CODE_NOT_FOUND_ERROR_MESSAGE);
        }

        return enrollingToUpdateOrDelete;
    }

    private void saveEnrolling(DataVO dataVO) {
        SystemBO systemBO = new SystemBO();
        systemBO.writeData(SystemBO.DATA_JSON_FILE_NAME, dataVO);
    }
}
