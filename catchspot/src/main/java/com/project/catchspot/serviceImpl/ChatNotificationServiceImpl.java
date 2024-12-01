package com.project.catchspot.serviceImpl;

import com.project.catchspot.entity.ChatNotification;
import com.project.catchspot.repository.ChatNotificationRepository;
import com.project.catchspot.service.ChatNotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ChatNotificationServiceImpl implements ChatNotificationService {

    @Autowired
    private ChatNotificationRepository chatNotificationRepository;

    @Override
    public void notifyUser(String sender, String receiver, String content) {
        ChatNotification notification = new ChatNotification();
        notification.setSender(sender);
        notification.setReceiver(receiver);
        notification.setContent(content);
        notification.setTimestamp(LocalDateTime.now());

        chatNotificationRepository.save(notification);
    }
}
