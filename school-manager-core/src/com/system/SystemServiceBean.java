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

public class SystemServiceBean implements SystemService {

    public StudentServiceBean studentServiceBean = new StudentServiceBean();
    public ProfessorServiceBean professorServiceBean = new ProfessorServiceBean();
    public SubjectServiceBean subjectServiceBean = new SubjectServiceBean();
    public ClassroomServiceBean classroomServiceBean = new ClassroomServiceBean();
    public EnrollingServiceBean enrollingServiceBean = new EnrollingServiceBean();

    @Override
    public MainMenuOptionsEnum showMainMenu() {
        while (true) {
            try {
                int option = UTILS.showInputIntegerDialog(UTILS.headerManagerString(), UTILS.mainMenuOptionsString());
                MainMenuOptionsEnum mainMenuOptionsEnum = null;

                switch (option) {
                    case 1 -> mainMenuOptionsEnum = MainMenuOptionsEnum.STUDENT;
                    case 2 -> mainMenuOptionsEnum = MainMenuOptionsEnum.PROFESSOR;
                    case 3 -> mainMenuOptionsEnum = MainMenuOptionsEnum.SUBJECT;
                    case 4 -> mainMenuOptionsEnum = MainMenuOptionsEnum.CLASS;
                    case 5 -> mainMenuOptionsEnum = MainMenuOptionsEnum.ENROLLING;
                    case 9 -> mainMenuOptionsEnum = MainMenuOptionsEnum.CLEAR_ALL_DATA;
                    case 0 -> mainMenuOptionsEnum = MainMenuOptionsEnum.EXIT;
                }

                return mainMenuOptionsEnum;

            } catch (Exception e) {
                UTILS.showMessageDialog(UTILS.INCORRECT_NUMBER_ERROR_MESSAGE);
            }
        }
    }

    @Override
    public ActionsMenuOptionsEnum showSecundaryMenu(MainMenuOptionsEnum mainMenuOptionsEnum) {
        while (true) {
            try {
                int option = UTILS.showInputIntegerDialog(UTILS.headerManagerString(), UTILS.secundaryMenuOptionsString(mainMenuOptionsEnum.toString()));
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
                UTILS.showMessageDialog(UTILS.INCORRECT_NUMBER_ERROR_MESSAGE);
            }
        }
    }

    @Override
    public void insertMenu(MainMenuOptionsEnum mainMenuOptionsEnum, DataVO dataVO) {
        switch (mainMenuOptionsEnum) {
            case STUDENT -> this.studentServiceBean.insertNewStudent(mainMenuOptionsEnum, dataVO);
            case PROFESSOR -> this.professorServiceBean.insertNewProfessor(dataVO);
            case SUBJECT -> this.subjectServiceBean.insertNewSubject(dataVO);
            case CLASS -> this.classroomServiceBean.insertNewClassroom(dataVO);
            case ENROLLING -> this.enrollingServiceBean.insertNewEnrolling(dataVO);
            case EXIT -> UTILS.showMessageDialog(UTILS.BACK_TO_MAIN_MENU_MESSAGE);
        }
    }

    @Override
    public void viewMenu(MainMenuOptionsEnum mainMenuOptionsEnum, DataVO dataVO) {
        switch (mainMenuOptionsEnum) {
            case STUDENT -> this.studentServiceBean.viewStudentList(dataVO);
            case PROFESSOR -> this.professorServiceBean.viewProfessorList(dataVO);
            case SUBJECT -> this.subjectServiceBean.viewSubjectList(dataVO);
            case CLASS -> this.classroomServiceBean.viewClassroomList(dataVO);
            case ENROLLING -> this.enrollingServiceBean.viewEnrollingList(dataVO);
            case EXIT -> UTILS.showMessageDialog(UTILS.BACK_TO_MAIN_MENU_MESSAGE);
        }
    }

    @Override
    public void updateMenu(MainMenuOptionsEnum mainMenuOptionsEnum, DataVO dataVO) {
        switch (mainMenuOptionsEnum) {
            case STUDENT -> this.studentServiceBean.updateStudent(dataVO);
            case PROFESSOR -> this.professorServiceBean.updateProfessor(dataVO);
            case SUBJECT -> this.subjectServiceBean.updateSubject(dataVO);
            case CLASS -> this.classroomServiceBean.updateClassroom(dataVO);
            case ENROLLING -> this.enrollingServiceBean.updateEnrolling(dataVO);
            case EXIT -> UTILS.showMessageDialog(UTILS.BACK_TO_MAIN_MENU_MESSAGE);
        }
    }

    @Override
    public void deleteMenu(MainMenuOptionsEnum mainMenuOptionsEnum, DataVO dataVO) {
        switch (mainMenuOptionsEnum) {
            case STUDENT -> this.studentServiceBean.deleteOneStudent(dataVO);
            case PROFESSOR -> this.professorServiceBean.deleteOneProfessor(dataVO);
            case SUBJECT -> this.subjectServiceBean.deleteOneSubject(dataVO);
            case CLASS -> this.classroomServiceBean.deleteOneClassroom(dataVO);
            case ENROLLING -> this.enrollingServiceBean.deleteOneEnrolling(dataVO);
            case EXIT -> UTILS.showMessageDialog(UTILS.BACK_TO_MAIN_MENU_MESSAGE);
        }
    }

    @Override
    public void clearListMenu(MainMenuOptionsEnum mainMenuOptionsEnum, DataVO dataVO) {
        switch (mainMenuOptionsEnum) {
            case STUDENT -> this.studentServiceBean.clearStudentList(dataVO);
            case PROFESSOR -> this.professorServiceBean.clearProfessorList(dataVO);
            case SUBJECT -> this.subjectServiceBean.clearSubjectList(dataVO);
            case CLASS -> this.classroomServiceBean.clearClassroomList(dataVO);
            case ENROLLING -> this.enrollingServiceBean.clearEnrollingList(dataVO);
            case EXIT -> UTILS.showMessageDialog(UTILS.BACK_TO_MAIN_MENU_MESSAGE);
        }
    }

    public void clearAllData(DataVO dataVO) {
        int choice = UTILS.showInputIntegerDialog(UTILS.DELETE_ALL_RECORDS_QUESTION_MESSAGE);

        if (choice != 1) {
            UTILS.showMessageDialog(UTILS.DELETE_ALL_RECORDS_CANCEL_MESSAGE);
            return;
        }

        dataVO.getStudentList().clear();
        dataVO.getProfessorList().clear();
        dataVO.getSubjectList().clear();
        dataVO.getClassroomList().clear();
        dataVO.getEnrollingList().clear();
        this.saveData(dataVO);
        UTILS.showMessageDialog(UTILS.DELETE_ALL_RECORDS_SUCCESS_MESSAGE);
    }

    private void saveData(DataVO dataVO) {
        SystemBO systemBO = new SystemBO();
        systemBO.writeData(SystemBO.DATA_JSON_FILE_NAME, dataVO);
    }
}
