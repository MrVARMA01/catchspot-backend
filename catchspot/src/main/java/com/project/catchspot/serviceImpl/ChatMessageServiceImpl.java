package com.project.catchspot.serviceImpl;

import com.project.catchspot.entity.ChatMessage;
import com.project.catchspot.entity.ChatRoom;
import com.project.catchspot.repository.ChatMessageRepository;
import com.project.catchspot.repository.ChatRoomRepository;
import com.project.catchspot.service.ChatMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ChatMessageServiceImpl implements ChatMessageService {

    @Autowired
    private ChatMessageRepository chatMessageRepository;

    @Autowired
    private ChatRoomRepository chatRoomRepository;

    @Override
    public ChatMessage sendMessage(ChatMessage chatMessage) {
        ChatRoom chatRoom = chatRoomRepository.findByChatId(chatMessage.getChatId());
//        System.out.println(chatRoom);
        if (chatRoom == null) {
            throw new RuntimeException("Chat room not found");
        }
        else {
            chatMessage.setTimestamp(LocalDateTime.now());
            chatMessage.setChatRoom(chatRoom.getId());
            chatMessageRepository.save(chatMessage);
            return chatMessage;
        }

    }

    @Override
    public List<ChatMessage> getChatMessages(String chatId) {
        return chatMessageRepository.findByChatId(chatId);
    }
}
