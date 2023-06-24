package com.subject;

import com.entity.Subject;
import com.objects.DataVO;
import com.system.SystemBO;
import com.utils.UTILS;

import java.util.List;
import java.util.Scanner;

public class SubjectServiceBean implements SubjectService {

    @Override
    public void insertNewSubject(DataVO dataVO) {
        UTILS.printHeaderManager();
        System.out.println("INSERINDO NOVA DISCIPLINA");

        Subject subject = this.createNewSubject();

        if (this.subjectAlreadyExists(subject, dataVO.getSubjectList())) {
            System.out.println("\nNão foi possível adicionar. Disciplina já cadastrada no sistema.");
            return;
        }

        dataVO.getSubjectList().add(dataVO.getSubjectList().size(), subject);
        this.saveSubject(dataVO);

        System.out.printf(("\nA disciplina %s foi adicionada com sucesso.") + "%n", subject.getSubjectName().toUpperCase());
    }

    @Override
    public void viewSubjectList(DataVO dataVO) {
        if (dataVO.getSubjectList() == null || dataVO.getSubjectList().isEmpty()) {
            System.out.println("\nNão há disciplinas cadastradas!\n");
            return;
        }

        for (Subject subject : dataVO.getSubjectList()) {
            System.out.println(" ");
            System.out.println("CÓDIGO: " + subject.getSubjectCode());
            System.out.println("NOME: " + subject.getSubjectName().toUpperCase());
        }
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
            System.out.println("\nNão foi possível adicionar. Disciplina já cadastrada no sistema.");
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
        System.out.printf(("Disciplina %s removida com sucesso.") + "%n", subjectToDelete.getSubjectName().toUpperCase());
    }

    @Override
    public void clearSubjectList(DataVO dataVO) {
        System.out.println("\nConfirma exclusão de todos os registros? Esta ação não poderá ser desfeita. (1) CONFIRMAR EXCLUSÃO / (2) CANCELAR");

        int choice = UTILS.scannerIntValue();

        if (choice != 1){
            System.out.println("A exclusão foi cancelada.");
            return;
        }

        dataVO.getSubjectList().clear();
        this.saveSubject(dataVO);
        System.out.println("Todos os registros foram excluídos com sucesso.");
    }

    private Subject createNewSubject() {
        int subjectCode = this.generateSubjectCode();
        String subjectName = this.generateSubjectName();

        return new Subject(subjectCode, subjectName);
    }

    private int generateSubjectCode() {
        System.out.println("\nDigite o código da disciplina (somente números):");
        return UTILS.scannerIntValue();
    }

    private String generateSubjectName() {
        System.out.println("\nDigite o nome da disciplina:");
        return new Scanner(System.in).nextLine();
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

    private Subject findSubject(DataVO dataVO) {
        int subjectCodeToUpdateOrDelete = this.generateSubjectCode();

        Subject subjectToUpdateOrDelete = null;
        for (Subject subjectFromList : dataVO.getSubjectList()) {
            if (subjectFromList.getSubjectCode() == subjectCodeToUpdateOrDelete) {
                subjectToUpdateOrDelete = subjectFromList;
                break;
            }
        }

        if (subjectToUpdateOrDelete == null) {
            System.out.printf(("Disciplina com o código %s não encontrado") + "%n", subjectCodeToUpdateOrDelete);
        }

        return subjectToUpdateOrDelete;
    }

    private void saveSubject(DataVO dataVO) {
        SystemBO systemBO = new SystemBO();
        systemBO.writeData(SystemBO.DATA_JSON_FILE_NAME, dataVO);
    }
}
