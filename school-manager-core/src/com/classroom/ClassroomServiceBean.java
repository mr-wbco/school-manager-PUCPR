package com.classroom;

import com.entity.Classroom;
import com.objects.DataVO;
import com.system.SystemBO;
import com.utils.UTILS;

import java.util.List;
import java.util.Scanner;

public class ClassroomServiceBean implements ClassroomService {

    @Override
    public void insertNewClassroom(DataVO dataVO) {
        UTILS.printHeaderManager();
        System.out.println("INSERINDO NOVA TURMA");

        Classroom classroom = this.createNewClassroom();

        if (this.classroomAlreadyExists(classroom, dataVO.getClassroomList())) {
            System.out.println("\nNão foi possível adicionar. Turma já cadastrada no sistema.");
            return;
        }

        dataVO.getClassroomList().add(dataVO.getClassroomList().size(), classroom);
        this.saveClassroom(dataVO);

        System.out.printf(("\nA turma %s foi adicionada com sucesso.") + "%n", classroom.getClassroomName().toUpperCase());
    }

    @Override
    public void viewClassroomList(DataVO dataVO) {
        if (dataVO.getClassroomList() == null || dataVO.getClassroomList().isEmpty()) {
            System.out.println("\nNão há turmas cadastradas!\n");
            return;
        }

        for (Classroom classroom : dataVO.getClassroomList()) {
            System.out.println(" ");
            System.out.println("CÓDIGO: " + classroom.getClassroomCode());
            System.out.println("NOME: " + classroom.getClassroomName().toUpperCase());
        }
    }

    @Override
    public void updateClassroom(DataVO dataVO) {
        this.viewClassroomList(dataVO);

        if (dataVO.getClassroomList() == null || dataVO.getClassroomList().isEmpty()) {
            return;
        }

        Classroom classroomToUpdate = this.findClassroom(dataVO);
        if (classroomToUpdate == null) {
            return;
        }

        String classroomName = this.generateClassroomName();

        if (this.classroomNameAlreadyExists(classroomName, dataVO.getClassroomList())) {
            System.out.println("\nNão foi possível adicionar. Turma já cadastrada no sistema.");
            return;
        }

        classroomToUpdate.setClassroomName(classroomName);
        this.saveClassroom(dataVO);
    }

    @Override
    public void deleteOneClassroom(DataVO dataVO) {
        this.viewClassroomList(dataVO);

        if (dataVO.getClassroomList() == null || dataVO.getClassroomList().isEmpty()) {
            return;
        }

        Classroom classroomToDelete = this.findClassroom(dataVO);
        if (classroomToDelete == null) {
            return;
        }

        dataVO.getClassroomList().remove(classroomToDelete);
        this.saveClassroom(dataVO);
        System.out.printf(("Turma %s removida com sucesso.") + "%n", classroomToDelete.getClassroomName().toUpperCase());
    }

    @Override
    public void clearClassroomList(DataVO dataVO) {
        System.out.println("\nConfirma exclusão de todos os registros? Esta ação não poderá ser desfeita. (1) CONFIRMAR EXCLUSÃO / (2) CANCELAR");

        int choice = UTILS.scannerIntValue();

        if (choice != 1){
            System.out.println("A exclusão foi cancelada.");
            return;
        }

        dataVO.getClassroomList().clear();
        this.saveClassroom(dataVO);
        System.out.println("Todos os registros foram excluídos com sucesso.");
    }

    private Classroom createNewClassroom() {
        int classroomCode = this.generateClassroomCode();
        String classroomName = this.generateClassroomName();

        return new Classroom(classroomCode, classroomName);
    }

    private int generateClassroomCode() {
        System.out.println("\nDigite o código da turma (somente números):");
        return UTILS.scannerIntValue();
    }

    private String generateClassroomName() {
        System.out.println("\nDigite o nome da turma:");
        return new Scanner(System.in).nextLine();
    }

    public boolean classroomAlreadyExists(Classroom newClassroom, List<Classroom> classroomList) {
        for (Classroom classroomFromList : classroomList) {
            if (classroomFromList.getClassroomCode() == newClassroom.getClassroomCode() || classroomFromList.getClassroomName().equals(newClassroom.getClassroomName())) {
                return true;
            }
        }

        return false;
    }

    private boolean classroomNameAlreadyExists(String newClassroomName, List<Classroom> classroomList) {
        for (Classroom classroom : classroomList) {
            if (classroom.getClassroomName().equals(newClassroomName)) {
                return true;
            }
        }

        return false;
    }

    private Classroom findClassroom(DataVO dataVO) {
        int classroomCodeToUpdateOrDelete = this.generateClassroomCode();

        Classroom classroomToUpdateOrDelete = null;
        for (Classroom classroomFromList : dataVO.getClassroomList()) {
            if (classroomFromList.getClassroomCode() == classroomCodeToUpdateOrDelete) {
                classroomToUpdateOrDelete = classroomFromList;
                break;
            }
        }

        if (classroomToUpdateOrDelete == null) {
            System.out.printf(("Turma com o código %s não encontrado") + "%n", classroomCodeToUpdateOrDelete);
        }

        return classroomToUpdateOrDelete;
    }

    private void saveClassroom(DataVO dataVO) {
        SystemBO systemBO = new SystemBO();
        systemBO.writeData(SystemBO.DATA_JSON_FILE_NAME, dataVO);
    }
}
