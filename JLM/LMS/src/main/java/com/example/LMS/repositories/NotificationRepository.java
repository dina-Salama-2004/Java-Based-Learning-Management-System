package com.example.LMS.repositories;
import com.example.LMS.models.NotificationModel;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface NotificationRepository  extends JpaRepository<NotificationModel, Integer> {
    // Custom query to find unread notifications for a user
    List<NotificationModel> findByUserIDAndIsReadFalse(Integer userID);

    // Fetch all notifications for a user
    List<NotificationModel> findByUserID(Integer userID);
}
