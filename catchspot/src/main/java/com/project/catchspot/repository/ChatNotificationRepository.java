package com.project.catchspot.repository;

import com.project.catchspot.entity.ChatNotification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatNotificationRepository extends JpaRepository<ChatNotification,Long> {
}
