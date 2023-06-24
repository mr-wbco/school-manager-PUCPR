package com.subject;

import com.objects.DataVO;

public interface SubjectService {
    void insertNewSubject(DataVO dataVO);

    void viewSubjectList(DataVO dataVO);

    void updateSubject(DataVO dataVO);

    void deleteOneSubject(DataVO dataVO);

    void clearSubjectList(DataVO dataVO);
}
