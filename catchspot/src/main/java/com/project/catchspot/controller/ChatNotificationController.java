package com.project.catchspot.controller;

import com.project.catchspot.service.ChatNotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
public class ChatNotificationController {

    @Autowired
    private ChatNotificationService chatNotificationService;

    @PostMapping("/notify")
    public void notifyUser(
            @RequestParam String sender,
            @RequestParam String receiver,
            @RequestParam String content) {
        chatNotificationService.notifyUser(sender, receiver, content);
    }
}
