package com.project.catchspot.repository;
import com.project.catchspot.entity.ChatMessage;
import com.project.catchspot.entity.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long> {
    ChatRoom findByChatId(String chatId);
    ChatRoom findByUser1AndUser2(long user1, long user2);
}