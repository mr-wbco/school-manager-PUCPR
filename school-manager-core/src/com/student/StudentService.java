package com.student;

import com.enums.MainMenuOptionsEnum;
import com.objects.DataVO;

public interface StudentService {
    void insertNewStudent(MainMenuOptionsEnum mainMenuOptionsEnum, DataVO dataVO);

    void viewStudentList(DataVO dataVO);

    void updateStudent(DataVO dataVO);

    void deleteOneStudent(DataVO dataVO);

    void clearStudentList(DataVO dataVO);
}
