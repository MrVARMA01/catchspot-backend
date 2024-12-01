package com.project.catchspot.service;

public interface ChatRoomService {

    String getChatRoomId(long user1, long user2);
    String createChatRoom(long user1, long user2);

}
