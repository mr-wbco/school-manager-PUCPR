package com.enrolling;

import com.classroom.ClassroomServiceBean;
import com.entity.Classroom;
import com.entity.Enrolling;
import com.entity.Student;
import com.objects.DataVO;
import com.student.StudentServiceBean;
import com.system.SystemBO;
import com.utils.UTILS;

import java.util.List;

public class EnrollingServiceBean implements EnrollingService {

    @Override
    public void insertNewEnrolling(DataVO dataVO) {
        Enrolling enrolling = this.createNewEnrolling(dataVO);
        if (enrolling == null) {
            return;
        }

        if (this.enrollingAlreadyExists(enrolling, dataVO.getEnrollingList())) {
            UTILS.showMessageDialog(UTILS.ALREADY_EXISTS_ERROR_MESSAGE);
            return;
        }

        this.updateStudent(enrolling, dataVO);
        this.updateClassroom(enrolling, dataVO);

        dataVO.getEnrollingList().add(dataVO.getEnrollingList().size(), enrolling);
        this.saveEnrolling(dataVO);

        UTILS.showMessageDialog(UTILS.INSERT_NEW_SUCCESS_MESSAGE);
    }

    @Override
    public void viewEnrollingList(DataVO dataVO) {
        if (dataVO.getEnrollingList() == null || dataVO.getEnrollingList().isEmpty()) {
            UTILS.showMessageDialog(UTILS.EMPTY_LIST_ERROR_MESSAGE);
            return;
        }

        StringBuilder string = new StringBuilder();

        for (Enrolling enrolling : dataVO.getEnrollingList()) {
            string.append(String.format("%nCÓDIGO: %s%nESTUDANTE: %s%n", enrolling.getEnrollingCode(), enrolling.getStudentName().toUpperCase()));
        }

        UTILS.showMessageDialogWithoutTranslate(string.toString());
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
        UTILS.showMessageDialog(UTILS.DELETE_RECORD_SUCCESS_MESSAGE);
    }

    @Override
    public void clearEnrollingList(DataVO dataVO) {
        int choice = UTILS.showInputIntegerDialog(UTILS.DELETE_ALL_RECORDS_QUESTION_MESSAGE);

        if (choice != 1) {
            UTILS.showMessageDialog(UTILS.DELETE_ALL_RECORDS_CANCEL_MESSAGE);
            return;
        }

        dataVO.getEnrollingList().clear();
        this.saveEnrolling(dataVO);
        UTILS.showMessageDialog(UTILS.DELETE_ALL_RECORDS_SUCCESS_MESSAGE);
    }

    private Enrolling createNewEnrolling(DataVO dataVO) {
        if (dataVO.getStudentList() == null || dataVO.getStudentList().isEmpty()) {
            UTILS.showMessageDialog(UTILS.EMPTY_STUDENT_LIST_ERROR_MESSAGE);
            return null;
        }

        if (dataVO.getClassroomList() == null || dataVO.getClassroomList().isEmpty()) {
            UTILS.showMessageDialog(UTILS.EMPTY_CLASSROOM_LIST_ERROR_MESSAGE);
            return null;
        }

        int enrollingCode = this.generateEnrollingCode();
        Student student = this.selectStudent(dataVO);
        Classroom classroom =this.selectClassroom(dataVO);

        if (student == null || classroom == null) {
            return null;
        }

        return new Enrolling(enrollingCode, student.getCode(), student.getName(), classroom.getClassroomCode(), classroom.getClassroomName());
    }

    private int generateEnrollingCode() {
        return UTILS.showInputIntegerDialog(UTILS.CODE);
    }

    private Student selectStudent(DataVO dataVO) {
        StudentServiceBean studentServiceBean = new StudentServiceBean();
        studentServiceBean.viewStudentList(dataVO);
        return studentServiceBean.findStudent(dataVO);
    }

    private Classroom selectClassroom(DataVO dataVO) {
        ClassroomServiceBean classroomServiceBean = new ClassroomServiceBean();
        classroomServiceBean.viewClassroomList(dataVO);

        int classroomCodeToUpdateOrDelete = UTILS.showInputIntegerDialog(UTILS.CODE);

        for (Classroom classroomFromList : dataVO.getClassroomList()) {
            if (classroomFromList.getClassroomCode() == classroomCodeToUpdateOrDelete) {
                return classroomFromList;
            }
        }

        UTILS.showMessageDialog(UTILS.CODE_NOT_FOUND_ERROR_MESSAGE);
        return null;
    }

    public boolean enrollingAlreadyExists(Enrolling newEnrolling, List<Enrolling> enrollingList) {
        for (Enrolling enrollingFromList : enrollingList) {
            if (enrollingFromList.getEnrollingCode() == newEnrolling.getEnrollingCode()) {
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
            UTILS.showMessageDialog(UTILS.CODE_NOT_FOUND_ERROR_MESSAGE);
        }

        return enrollingToUpdateOrDelete;
    }

    private void updateStudent(Enrolling enrolling, DataVO dataVO) {
        for (Student student : dataVO.getStudentList()) {
            if (student.getCode() == enrolling.getStudentCode()) {
                student.setEnrolling(enrolling);
            }
        }
    }

    private void updateClassroom(Enrolling enrolling, DataVO dataVO) {
        Student studentToAdd = new Student();
        for (Student studentFromList : dataVO.getStudentList()) {
            if (studentFromList.getCode() == enrolling.getStudentCode()) {
                studentToAdd = studentFromList;
            }
        }

        for (Classroom classroom : dataVO.getClassroomList()) {
            if (classroom.getClassroomCode() == enrolling.getClassroomCode()) {
                classroom.getStudentList().add(classroom.getStudentList().size(), studentToAdd);
            }
        }
    }

    private void saveEnrolling(DataVO dataVO) {
        SystemBO systemBO = new SystemBO();
        systemBO.writeData(SystemBO.DATA_JSON_FILE_NAME, dataVO);
    }
}
