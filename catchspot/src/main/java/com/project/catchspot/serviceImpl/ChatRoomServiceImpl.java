package com.project.catchspot.serviceImpl;

import com.project.catchspot.entity.ChatRoom;
import com.project.catchspot.repository.ChatRoomRepository;
import com.project.catchspot.service.ChatRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChatRoomServiceImpl implements ChatRoomService {

    @Autowired
    private ChatRoomRepository chatRoomRepository;

    @Override
    public String getChatRoomId(long user1, long user2) {
        ChatRoom room1 = chatRoomRepository.findByUser1AndUser2(user1, user2);
        ChatRoom room2 = chatRoomRepository.findByUser1AndUser2(user2, user1);

        if (room1 == null){
            if (room2 == null){
                return createChatRoom(user1, user2);
            }
            else {
                return room2.getChatId();
            }
        }else {
            return room1.getChatId();
        }
    }

    @Override
    public String createChatRoom(long user1, long user2) {
        String chatId1 = String.format("%s_%s", user1, user2);
        ChatRoom user1User2Room = new ChatRoom();
        user1User2Room.setChatId(chatId1);
        user1User2Room.setUser1(user1);
        user1User2Room.setUser2(user2);
        chatRoomRepository.save(user1User2Room);
        return chatId1;
    }
}
