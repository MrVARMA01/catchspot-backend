package com.project.catchspot.service;

public interface ChatNotificationService {
    void notifyUser(String sender, String receiver, String content);
}
