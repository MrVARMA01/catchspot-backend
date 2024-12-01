package com.project.catchspot.service;
import com.project.catchspot.entity.ChatMessage;
import java.util.List;

public interface ChatMessageService {
    ChatMessage sendMessage(ChatMessage chatMessage);
    List<ChatMessage>  getChatMessages(String chatId);
}
