package com.professor;

import com.objects.DataVO;

public interface ProfessorService {
    void insertNewProfessor(DataVO dataVO);

    void viewProfessorList(DataVO dataVO);

    void updateProfessor(DataVO dataVO);

    void deleteOneProfessor(DataVO dataVO);

    void clearProfessorList(DataVO dataVO);
}
