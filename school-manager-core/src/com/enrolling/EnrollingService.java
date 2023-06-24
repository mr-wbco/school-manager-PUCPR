package com.enrolling;

import com.objects.DataVO;

public interface EnrollingService {
    void insertNewEnrolling(DataVO dataVO);

    void viewEnrollingList(DataVO dataVO);

    void updateEnrolling(DataVO dataVO);

    void deleteOneEnrolling(DataVO dataVO);

    void clearEnrollingList(DataVO dataVO);
}
