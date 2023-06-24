package com.classroom;

import com.objects.DataVO;

public interface ClassroomService {
    void insertNewClassroom(DataVO dataVO);

    void viewClassroomList(DataVO dataVO);

    void updateClassroom(DataVO dataVO);

    void deleteOneClassroom(DataVO dataVO);

    void clearClassroomList(DataVO dataVO);
}
