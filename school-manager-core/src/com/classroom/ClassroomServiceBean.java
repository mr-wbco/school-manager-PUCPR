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
        UTILS.printConsoleMessage(UTILS.INSERT_NEW_INFORMATION_MESSAGE);

        Classroom classroom = this.createNewClassroom();

        if (this.classroomAlreadyExists(classroom, dataVO.getClassroomList())) {
            UTILS.printConsoleMessage(UTILS.ALREADY_EXISTS_ERROR_MESSAGE);
            return;
        }

        dataVO.getClassroomList().add(dataVO.getClassroomList().size(), classroom);
        this.saveClassroom(dataVO);

        UTILS.printConsoleMessage(UTILS.INSERT_NEW_SUCCESS_MESSAGE);
    }

    @Override
    public void viewClassroomList(DataVO dataVO) {
        if (dataVO.getClassroomList() == null || dataVO.getClassroomList().isEmpty()) {
            UTILS.printConsoleMessage(UTILS.EMPTY_LIST_ERROR_MESSAGE);
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
            UTILS.printConsoleMessage(UTILS.ALREADY_EXISTS_ERROR_MESSAGE);
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
        UTILS.printConsoleMessage(UTILS.DELETE_RECORD_SUCCESS_MESSAGE);
    }

    @Override
    public void clearClassroomList(DataVO dataVO) {
        UTILS.printConsoleMessage(UTILS.DELETE_ALL_RECORDS_QUESTION_MESSAGE);

        int choice = UTILS.scannerIntValue();

        if (choice != 1) {
            UTILS.printConsoleMessage(UTILS.DELETE_ALL_RECORDS_CANCEL_MESSAGE);
            return;
        }

        dataVO.getClassroomList().clear();
        this.saveClassroom(dataVO);
        UTILS.printConsoleMessage(UTILS.DELETE_ALL_RECORDS_SUCCESS_MESSAGE);
    }

    private Classroom createNewClassroom() {
        int classroomCode = this.generateClassroomCode();
        String classroomName = this.generateClassroomName();

        return new Classroom(classroomCode, classroomName);
    }

    private int generateClassroomCode() {
        UTILS.printConsoleMessage(UTILS.CODE);
        return UTILS.scannerIntValue();
    }

    private String generateClassroomName() {
        UTILS.printConsoleMessage(UTILS.NAME);
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
            UTILS.printConsoleMessage(UTILS.CODE_NOT_FOUND_ERROR_MESSAGE);
        }

        return classroomToUpdateOrDelete;
    }

    private void saveClassroom(DataVO dataVO) {
        SystemBO systemBO = new SystemBO();
        systemBO.writeData(SystemBO.DATA_JSON_FILE_NAME, dataVO);
    }
}
