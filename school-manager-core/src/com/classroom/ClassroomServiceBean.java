package com.classroom;

import com.entity.Classroom;
import com.entity.Professor;
import com.entity.Student;
import com.entity.Subject;
import com.objects.DataVO;
import com.professor.ProfessorServiceBean;
import com.subject.SubjectServiceBean;
import com.system.SystemBO;
import com.utils.UTILS;

import java.util.List;

public class ClassroomServiceBean implements ClassroomService {

    @Override
    public void insertNewClassroom(DataVO dataVO) {
        Classroom classroom = this.createNewClassroom(dataVO);
        if (classroom == null) {
            return;
        }

        if (this.classroomAlreadyExists(classroom, dataVO.getClassroomList())) {
            UTILS.showMessageDialog(UTILS.ALREADY_EXISTS_ERROR_MESSAGE);
            return;
        }

        dataVO.getClassroomList().add(dataVO.getClassroomList().size(), classroom);
        this.saveClassroom(dataVO);

        UTILS.showMessageDialog(UTILS.INSERT_NEW_SUCCESS_MESSAGE);
    }

    @Override
    public void viewClassroomList(DataVO dataVO) {
        if (dataVO.getClassroomList() == null || dataVO.getClassroomList().isEmpty()) {
            UTILS.showMessageDialog(UTILS.EMPTY_LIST_ERROR_MESSAGE);
            return;
        }

        StringBuilder string = new StringBuilder();

        for (Classroom classroom : dataVO.getClassroomList()) {
            string.append(String.format("%nCÓDIGO DA TURMA: %s%nTURMA: %s%nDISCIPLINA: %s%nPROFESSOR: %s%n", classroom.getClassroomCode(), classroom.getClassroomName().toUpperCase(), classroom.getSubject().getSubjectName().toUpperCase(), classroom.getProfessor().getName().toUpperCase()));

            string.append(String.format("%nESTUDANTES MATRICULADOS:%n"));

            if (classroom.getStudentList() ==  null) {
                UTILS.showMessageDialogWithoutTranslate("NÃO HÁ ESTUDANTES MATRICULADOS NESSA TURMA.");
                continue;
            }

            for (Student student : classroom.getStudentList()) {
                string.append(String.format("-> %s%n", student.getName()));
            }
        }

        UTILS.showMessageDialogWithoutTranslate(string.toString());
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
            UTILS.showMessageDialog(UTILS.ALREADY_EXISTS_ERROR_MESSAGE);
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
        UTILS.showMessageDialog(UTILS.DELETE_RECORD_SUCCESS_MESSAGE);
    }

    @Override
    public void clearClassroomList(DataVO dataVO) {
        int choice = UTILS.showInputIntegerDialog(UTILS.DELETE_ALL_RECORDS_QUESTION_MESSAGE);

        if (choice != 1) {
            UTILS.showMessageDialog(UTILS.DELETE_ALL_RECORDS_CANCEL_MESSAGE);
            return;
        }

        dataVO.getClassroomList().clear();
        this.saveClassroom(dataVO);
        UTILS.showMessageDialog(UTILS.DELETE_ALL_RECORDS_SUCCESS_MESSAGE);
    }

    private Classroom createNewClassroom(DataVO dataVO) {
        if (dataVO.getProfessorList() == null || dataVO.getProfessorList().isEmpty()) {
            UTILS.showMessageDialog(UTILS.EMPTY_PROFESSOR_LIST_ERROR_MESSAGE);
            return null;
        }

        if (dataVO.getSubjectList() == null || dataVO.getSubjectList().isEmpty()) {
            UTILS.showMessageDialog(UTILS.EMPTY_SUBJECT_LIST_ERROR_MESSAGE);
            return null;
        }

        int classroomCode = this.generateClassroomCode();
        String classroomName = this.generateClassroomName();
        Subject subject = this.generateSubject(dataVO);
        Professor professor = this.generateProfessor(dataVO);

        return new Classroom(classroomCode, classroomName, subject, professor);
    }

    private int generateClassroomCode() {
        return UTILS.showInputIntegerDialog(UTILS.CODE);
    }

    private String generateClassroomName() {
        return UTILS.showInputStringDialog(UTILS.NAME);
    }

    private Subject generateSubject(DataVO dataVO) {
        SubjectServiceBean subjectServiceBean = new SubjectServiceBean();
        subjectServiceBean.viewSubjectList(dataVO);
        return subjectServiceBean.findSubject(dataVO);
    }

    private Professor generateProfessor(DataVO dataVO) {
        ProfessorServiceBean professorServiceBean = new ProfessorServiceBean();
        professorServiceBean.viewProfessorList(dataVO);
        return professorServiceBean.findProfessor(dataVO);
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

    public Classroom findClassroom(DataVO dataVO) {
        int classroomCodeToUpdateOrDelete = this.generateClassroomCode();

        Classroom classroomToUpdateOrDelete = null;
        for (Classroom classroomFromList : dataVO.getClassroomList()) {
            if (classroomFromList.getClassroomCode() == classroomCodeToUpdateOrDelete) {
                classroomToUpdateOrDelete = classroomFromList;
                break;
            }
        }

        if (classroomToUpdateOrDelete == null) {
            UTILS.showMessageDialog(UTILS.CODE_NOT_FOUND_ERROR_MESSAGE);
        }

        return classroomToUpdateOrDelete;
    }

    private void saveClassroom(DataVO dataVO) {
        SystemBO systemBO = new SystemBO();
        systemBO.writeData(SystemBO.DATA_JSON_FILE_NAME, dataVO);
    }
}
