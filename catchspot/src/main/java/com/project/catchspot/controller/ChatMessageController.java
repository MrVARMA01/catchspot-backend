package com.project.catchspot.controller;
import com.project.catchspot.entity.ChatMessage;
import com.project.catchspot.service.ChatMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin
@RestController
public class ChatMessageController {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;
    @Autowired
    private ChatMessageService chatMessageService;

    // Handle WebSocket messages
    @MessageMapping("/chat/send")
    public void sendMessage(@Payload ChatMessage chatMessage) {
        try {
            ChatMessage savedMessage = chatMessageService.sendMessage(chatMessage);
            messagingTemplate.convertAndSendToUser(
                    chatMessage.getReceiver(),
                    "/queue/messages",
                    savedMessage
            );
        } catch (Exception e) {
            // Log the error
            System.err.println("Error while saving message: " + e.getMessage());
        }
    }


    @GetMapping("/chat/{chatId}")
    public ResponseEntity<?>  getMessages(@PathVariable String chatId) {
        return new ResponseEntity<>( chatMessageService.getChatMessages(chatId), HttpStatus.OK);
    }
}
