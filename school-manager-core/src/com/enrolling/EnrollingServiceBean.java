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
        System.out.println("INSERINDO NOVA MATRÍCULA");

        Enrolling enrolling = this.createNewEnrolling();

        if (this.enrollingAlreadyExists(enrolling, dataVO.getEnrollingList())) {
            System.out.println("\nNão foi possível adicionar. Matrícula já cadastrada no sistema.");
            return;
        }

        dataVO.getEnrollingList().add(dataVO.getEnrollingList().size(), enrolling);
        this.saveEnrolling(dataVO);

        System.out.printf(("\nA matrícula %s foi adicionada com sucesso.") + "%n", enrolling.getEnrollingName().toUpperCase());

    }

    @Override
    public void viewEnrollingList(DataVO dataVO) {
        if (dataVO.getEnrollingList() == null || dataVO.getEnrollingList().isEmpty()) {
            System.out.println("\nNão há matrículas cadastradas!\n");
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
            System.out.println("\nNão foi possível adicionar. Matrícula já cadastrada no sistema.");
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
        System.out.printf(("Matrícula %s removida com sucesso.") + "%n", enrollingToDelete.getEnrollingName().toUpperCase());
    }

    @Override
    public void clearEnrollingList(DataVO dataVO) {
        System.out.println("\nConfirma exclusão de todos os registros? Esta ação não poderá ser desfeita. (1) CONFIRMAR EXCLUSÃO / (2) CANCELAR");

        int choice = UTILS.scannerIntValue();

        if (choice != 1){
            System.out.println("A exclusão foi cancelada.");
            return;
        }

        dataVO.getEnrollingList().clear();
        this.saveEnrolling(dataVO);
        System.out.println("Todos os registros foram excluídos com sucesso.");
    }

    private Enrolling createNewEnrolling() {
        int enrollingCode = this.generateEnrollingCode();
        String enrollingName = this.generateEnrollingName();

        return new Enrolling(enrollingCode, enrollingName);
    }

    private int generateEnrollingCode() {
        System.out.println("\nDigite o código da matrícula (somente números):");
        return UTILS.scannerIntValue();
    }

    private String generateEnrollingName() {
        System.out.println("\nDigite o nome da matrícula:");
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
            System.out.printf(("Matrícula com o código %s não encontrado") + "%n", enrollingCodeToUpdateOrDelete);
        }

        return enrollingToUpdateOrDelete;
    }

    private void saveEnrolling(DataVO dataVO) {
        SystemBO systemBO = new SystemBO();
        systemBO.writeData(SystemBO.DATA_JSON_FILE_NAME, dataVO);
    }
}
