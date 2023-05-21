package com.student;

import com.enums.ActionsMenuOptionsEnum;
import com.enums.MainMenuOptionsEnum;
import com.objects.DataVO;
import com.objects.Student;

import java.util.List;

public interface StudentService {
    void insertNewStudent(MainMenuOptionsEnum mainMenuOptionsEnum, ActionsMenuOptionsEnum actionsMenuOptionsEnum, DataVO dataVO);

    void viewStudentList(MainMenuOptionsEnum mainMenuOptionsEnum, ActionsMenuOptionsEnum actionsMenuOptionsEnum, DataVO dataVO);

    boolean studentAlreadyExists(int newStudentCode, Long studentFederalIdentification, String studentName, List<Student> studentList);

    void updateStudent(MainMenuOptionsEnum mainMenuOptionsEnum, ActionsMenuOptionsEnum actionsMenuOptionsEnum, DataVO dataVO);
}
