package com.system;

import com.classroom.ClassroomServiceBean;
import com.enrolling.EnrollingServiceBean;
import com.enums.ActionsMenuOptionsEnum;
import com.enums.MainMenuOptionsEnum;
import com.objects.DataVO;
import com.professor.ProfessorServiceBean;
import com.student.StudentServiceBean;
import com.subject.SubjectServiceBean;
import com.utils.UTILS;

import java.util.Scanner;

public class SystemServiceBean implements SystemService {

    public StudentServiceBean studentServiceBean = new StudentServiceBean();
    public ProfessorServiceBean professorServiceBean = new ProfessorServiceBean();
    public SubjectServiceBean subjectServiceBean = new SubjectServiceBean();
    public ClassroomServiceBean classroomServiceBean = new ClassroomServiceBean();
    public EnrollingServiceBean enrollingServiceBean = new EnrollingServiceBean();

    @Override
    public MainMenuOptionsEnum showMainMenu() {
        UTILS.printHeaderManager();
        UTILS.printMainMenuOptions();

        while (true) {
            try {
                int option = new Scanner(System.in).nextInt();
                MainMenuOptionsEnum mainMenuOptionsEnum = null;

                switch (option) {
                    case 1 -> mainMenuOptionsEnum = MainMenuOptionsEnum.STUDENT;
                    case 2 -> mainMenuOptionsEnum = MainMenuOptionsEnum.PROFESSOR;
                    case 3 -> mainMenuOptionsEnum = MainMenuOptionsEnum.SUBJECT;
                    case 4 -> mainMenuOptionsEnum = MainMenuOptionsEnum.CLASS;
                    case 5 -> mainMenuOptionsEnum = MainMenuOptionsEnum.ENROLLING;
                    case 0 -> mainMenuOptionsEnum = MainMenuOptionsEnum.EXIT;
                }

                return mainMenuOptionsEnum;

            } catch (Exception e) {
                UTILS.printConsoleMessage(UTILS.INCORRECT_NUMBER_ERROR_MESSAGE);
                UTILS.pressEnterToContinue();
            }
        }
    }

    @Override
    public ActionsMenuOptionsEnum showSecundaryMenu(MainMenuOptionsEnum mainMenuOptionsEnum) {
        UTILS.printHeaderManager();
        UTILS.printSecundaryMenuOptions(mainMenuOptionsEnum.toString());

        while (true) {
            try {
                int option = new Scanner(System.in).nextInt();
                ActionsMenuOptionsEnum actionsMenuOptionsEnum = null;

                switch (option) {
                    case 1 -> actionsMenuOptionsEnum = ActionsMenuOptionsEnum.INSERT;
                    case 2 -> actionsMenuOptionsEnum = ActionsMenuOptionsEnum.VIEW;
                    case 3 -> actionsMenuOptionsEnum = ActionsMenuOptionsEnum.UPDATE;
                    case 4 -> actionsMenuOptionsEnum = ActionsMenuOptionsEnum.DELETE;
                    case 5 -> actionsMenuOptionsEnum = ActionsMenuOptionsEnum.CLEAR_ALL;
                    case 0 -> actionsMenuOptionsEnum = ActionsMenuOptionsEnum.RETURN;
                }

                return actionsMenuOptionsEnum;

            } catch (Exception e) {
                UTILS.printConsoleMessage(UTILS.INCORRECT_NUMBER_ERROR_MESSAGE);
                UTILS.pressEnterToContinue();
            }
        }
    }

    @Override
    public void insertMenu(MainMenuOptionsEnum mainMenuOptionsEnum, DataVO dataVO) {
        UTILS.printHeaderManager();
        UTILS.printCurrentSecundaryMenuPosition(mainMenuOptionsEnum.toString(), UTILS.INSERT_NEW);

        switch (mainMenuOptionsEnum) {
            case STUDENT -> this.studentServiceBean.insertNewStudent(dataVO);
            case PROFESSOR -> this.professorServiceBean.insertNewProfessor(dataVO);
            case SUBJECT -> this.subjectServiceBean.insertNewSubject(dataVO);
            case CLASS -> this.classroomServiceBean.insertNewClassroom(dataVO);
            case ENROLLING -> this.enrollingServiceBean.insertNewEnrolling(dataVO);
            case EXIT -> UTILS.printConsoleMessage(UTILS.BACK_TO_MAIN_MENU_MESSAGE);
        }

        UTILS.pressEnterToContinue();
    }

    @Override
    public void viewMenu(MainMenuOptionsEnum mainMenuOptionsEnum, DataVO dataVO) {
        UTILS.printHeaderManager();
        UTILS.printCurrentSecundaryMenuPosition(mainMenuOptionsEnum.toString(), UTILS.LIST_ALL);

        switch (mainMenuOptionsEnum) {
            case STUDENT -> this.studentServiceBean.viewStudentList(dataVO);
            case PROFESSOR -> this.professorServiceBean.viewProfessorList(dataVO);
            case SUBJECT -> this.subjectServiceBean.viewSubjectList(dataVO);
            case CLASS -> this.classroomServiceBean.viewClassroomList(dataVO);
            case ENROLLING -> this.enrollingServiceBean.viewEnrollingList(dataVO);
            case EXIT -> UTILS.printConsoleMessage(UTILS.BACK_TO_MAIN_MENU_MESSAGE);
        }

        UTILS.pressEnterToContinue();
    }

    @Override
    public void updateMenu(MainMenuOptionsEnum mainMenuOptionsEnum, DataVO dataVO) {
        UTILS.printHeaderManager();
        UTILS.printCurrentSecundaryMenuPosition(mainMenuOptionsEnum.toString(), UTILS.UPDATE_INFORMATION);

        switch (mainMenuOptionsEnum) {
            case STUDENT -> this.studentServiceBean.updateStudent(dataVO);
            case PROFESSOR -> this.professorServiceBean.updateProfessor(dataVO);
            case SUBJECT -> this.subjectServiceBean.updateSubject(dataVO);
            case CLASS -> this.classroomServiceBean.updateClassroom(dataVO);
            case ENROLLING -> this.enrollingServiceBean.updateEnrolling(dataVO);
            case EXIT -> UTILS.printConsoleMessage(UTILS.BACK_TO_MAIN_MENU_MESSAGE);
        }

        UTILS.pressEnterToContinue();
    }

    @Override
    public void deleteMenu(MainMenuOptionsEnum mainMenuOptionsEnum, DataVO dataVO) {
        UTILS.printHeaderManager();
        UTILS.printCurrentSecundaryMenuPosition(mainMenuOptionsEnum.toString(), UTILS.DELETE_RECORD);

        switch (mainMenuOptionsEnum) {
            case STUDENT -> this.studentServiceBean.deleteOneStudent(dataVO);
            case PROFESSOR -> this.professorServiceBean.deleteOneProfessor(dataVO);
            case SUBJECT -> this.subjectServiceBean.deleteOneSubject(dataVO);
            case CLASS -> this.classroomServiceBean.deleteOneClassroom(dataVO);
            case ENROLLING -> this.enrollingServiceBean.deleteOneEnrolling(dataVO);
            case EXIT -> UTILS.printConsoleMessage(UTILS.BACK_TO_MAIN_MENU_MESSAGE);
        }

        UTILS.pressEnterToContinue();
    }

    @Override
    public void clearListMenu(MainMenuOptionsEnum mainMenuOptionsEnum, DataVO dataVO) {
        UTILS.printHeaderManager();
        UTILS.printCurrentSecundaryMenuPosition(mainMenuOptionsEnum.toString(), UTILS.DELETE_ALL_RECORDS);

        switch (mainMenuOptionsEnum) {
            case STUDENT -> this.studentServiceBean.clearStudentList(dataVO);
            case PROFESSOR -> this.professorServiceBean.clearProfessorList(dataVO);
            case SUBJECT -> this.subjectServiceBean.clearSubjectList(dataVO);
            case CLASS -> this.classroomServiceBean.clearClassroomList(dataVO);
            case ENROLLING -> this.enrollingServiceBean.clearEnrollingList(dataVO);
            case EXIT -> UTILS.printConsoleMessage(UTILS.BACK_TO_MAIN_MENU_MESSAGE);
        }

        UTILS.pressEnterToContinue();
    }
}
